package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Usuario;


import javax.swing.AbstractCellEditor;

public class VentanaVerEnvios  extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final TableCellRenderer RenderTabla = null;

    
	public VentanaVerEnvios(Usuario u) {
		setTitle("Ver Envios");
		setSize(900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);


	    List<Envio> enviosDelUsuario = new ArrayList<>();
	    
	    try (Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db")) {
	        if (con != null) {
	            System.out.println("Conexión a la base de datos OK.");
	            enviosDelUsuario = BaseDatosConfiguracion.cargarEnviosPorUsuario(con, u.getCorreo());
	            System.out.println("Número de envíos cargados: " + enviosDelUsuario.size());
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al cargar los envíos del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }

	    if (enviosDelUsuario.isEmpty()) {
	        System.out.println("No se encontraron envíos para el usuario: " + u.getCorreo());
	    }

		
        JTable tablaEnvios = new JTable(new EnvioTableModel(u.getListaEnvios()));
        tablaEnvios.setRowHeight(30);
        tablaEnvios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        ButtonRenderer buttonRendererEditor = new ButtonRenderer((EnvioTableModel) tablaEnvios.getModel(), tablaEnvios, u);
        tablaEnvios.getColumnModel().getColumn(6).setCellRenderer(buttonRendererEditor);
        tablaEnvios.getColumnModel().getColumn(6).setCellEditor(buttonRendererEditor);

	    tablaEnvios.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
	    
	    JScrollPane scrollPane = new JScrollPane(tablaEnvios);
	    JPanel tablePanel = new JPanel(new BorderLayout());
	    tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
	    tablePanel.add(scrollPane, BorderLayout.CENTER);
	    	   
	    ImageIcon imageVolverO = new ImageIcon("resources/images/volver.png");
	    ImageIcon imageVolverE = new ImageIcon(imageVolverO.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
	    JButton btnVolver = new JButton(imageVolverE);
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelVolver.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelVolver.add(btnVolver);

        
        btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal(u));
				dispose();
			}
		});
	    
	    
	    String[] opcionesFiltro = {"Todos", "Enviado", "Pendiente", "En tránsito"}; //FUENTE-EXTERNA
	    JComboBox<String> filtroComboBox = new JComboBox<>(opcionesFiltro);
	    filtroComboBox.setSelectedIndex(0); 
	    filtroComboBox.setPreferredSize(new Dimension(150, 20));
	    filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
	    
        filtroComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estadoSeleccionado = (String) filtroComboBox.getSelectedItem();
                EnvioTableModel modelo = (EnvioTableModel) tablaEnvios.getModel();
                modelo.filtrarPorEstado(estadoSeleccionado);
                tablaEnvios.repaint();
            }
        });
       
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelVolver, BorderLayout.WEST);
        
        
        ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
		JLabel labelImagenLogo = new JLabel(logo);


        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelDerecha.add(labelImagenLogo);
        panelSuperior.add(panelDerecha, BorderLayout.EAST);
        
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        filtroComboBox.add(new JLabel("Filtrar por estado:"));
        panelCombo.add(filtroComboBox);
        panelSuperior.add(panelCombo);
       
        
        add(panelSuperior, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
		
	}
	
	//modelo de la tabla
	//FUENTE-EXTERNA
	class EnvioTableModel extends AbstractTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista", "Editar"};
        List<Envio> envios;
        List<Envio> enviosFiltrados;

        public EnvioTableModel(List<Envio> envios) {
            this.envios = envios;
            this.enviosFiltrados = envios;
        }

        @Override
        public int getRowCount() {
            return enviosFiltrados.size();
        }

        @Override
        public int getColumnCount() {
            return nombreColumnas.length;
        }

        @Override
        public String getColumnName(int column) {
            return nombreColumnas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Envio envio = enviosFiltrados.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return envio.getPaquete().getnReferencia();
                case 1:
                    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                case 2:
                    return envio.getPago().getPrecio();
                case 3:
                    return envio.getPago().getDescripcion();
                case 4:
                    return envio.getEstado();
                case 5:
                    return envio.getRecogida().getFechaDeRecogida();
                case 6:
                    return "Editar";
                default:
                    return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 6;
        }

        public void filtrarPorEstado(String estado) {
            if (estado.equals("Todos")) {
                enviosFiltrados = envios;
            } else {
                enviosFiltrados = envios.stream()
                        .filter(envio -> envio.getEstado().equals(estado))
                        .toList();
            }
          //IAG (ChatGPT)
            fireTableDataChanged();
        }
    }

    // Renderizador personalizado para la tabla
    class CustomTableCellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel(value != null ? value.toString() : "");
            label.setOpaque(true);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            if (row % 2 == 0) {
                label.setBackground(new Color(240, 240, 240));
            } else {
                label.setBackground(Color.WHITE);
            }

            if (isSelected) {
                label.setBackground(new Color(184, 207, 229));
            }

            if (column == 2 && value instanceof Double) {
                double precio = (Double) value;
                if (precio <= 50) {
                    label.setForeground(Color.GREEN);
                } else if (precio <= 100) {
                    label.setForeground(Color.ORANGE);
                } else {
                    label.setForeground(Color.RED);
                }
            } else {
                label.setForeground(Color.BLACK);
            }

            return label;
        }
    }

    // Renderizador y editor para la columna de botones
    @SuppressWarnings("serial")
	class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JPanel panel;
        private final JButton btnModificar;
        private final JButton btnEliminar;
        @SuppressWarnings("unused")
		private final EnvioTableModel tablaModel;
        @SuppressWarnings("unused")
		private final JTable tablaEnvios;
        private final Usuario usuario;
        private int row;

        public ButtonRenderer(EnvioTableModel tablaModel, JTable tablaEnvios, Usuario u) {
            this.tablaModel = tablaModel;
            this.tablaEnvios = tablaEnvios;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

            ImageIcon iconModificar = new ImageIcon("resources/images/modificar.png");
            ImageIcon iconEliminar = new ImageIcon("resources/images/eliminar.png");
            
            iconModificar = new ImageIcon(iconModificar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            iconEliminar = new ImageIcon(iconEliminar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            btnModificar = new JButton(iconModificar);
            btnEliminar = new JButton(iconEliminar);
			this.usuario = u;

            btnModificar.addActionListener(e -> {
                Envio envio = tablaModel.enviosFiltrados.get(row);
                if (envio.getEstado().equals("Pendiente")) {
                    SwingUtilities.invokeLater(() -> new VentanaHacerEnvio(this.usuario));
                    stopCellEditing();
                    dispose();
                } 
            });

            btnEliminar.addActionListener(e -> {
            	//FUENTE EXTERNA
            	if (row >= 0 && row < tablaModel.enviosFiltrados.size()) {
                    Envio envio = tablaModel.enviosFiltrados.get(row);

                    try (Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db")) {
                        if (con != null) {
                            BaseDatosConfiguracion.eliminarEnvio(con, envio.getPaquete().getnReferencia());
                            BaseDatosConfiguracion.borrarPaquete(con, envio.getPaquete().getnReferencia());
                            BaseDatosConfiguracion.borrarRecogida(con, envio.getRecogida().getFechaDeRecogida());
                            BaseDatosConfiguracion.borrarPago(con, envio.getPago().getDni());
                            BaseDatosConfiguracion.borrarTrayecto(con, envio.getTrayecto().getDireccionOrigen(), envio.getTrayecto().getDireccionDestino());
                            

                            tablaModel.envios.remove(envio);
                            tablaModel.enviosFiltrados.remove(envio);
                            tablaEnvios.repaint();
                            stopCellEditing();

                            JOptionPane.showMessageDialog(null, "Envío eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el envío de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El índice no es válido. No se puede eliminar el envío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            panel.add(btnModificar);
            panel.add(btnEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
    
}
