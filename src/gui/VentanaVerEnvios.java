package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Paquete;
import domain.Usuario;

import javax.swing.AbstractCellEditor;

public class VentanaVerEnvios  extends JFrame {
	
	private static final TableCellRenderer RenderTabla = null;

    
	public VentanaVerEnvios(Usuario u) {
		setTitle("Ver Envios");
		setSize(900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		
		JTable tablaEnvios = new JTable(new EnvioTableModel(u.getListaEnvios())) {
		
			private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 3;
	        }
	     };
	        
	    tablaEnvios.setRowHeight(30);
	    tablaEnvios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

	    
	    tablaEnvios.setDefaultRenderer(Object.class, new RenderTabla(tablaEnvios));
	    
//	    ButtonRenderer buttonRendererEditor = new ButtonRenderer((EnvioTableModel) tablaEnvios.getModel(), tablaEnvios);
//        tablaEnvios.getColumnModel().getColumn(6).setCellRenderer(buttonRendererEditor);
//        tablaEnvios.getColumnModel().getColumn(6).setCellEditor(buttonRendererEditor);	        
	    JPanel tablePanel = new JPanel(new BorderLayout());
	    tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
	        
	    JScrollPane scrollPane = new JScrollPane(tablaEnvios);
			
	    tablePanel.add(scrollPane, BorderLayout.CENTER);
	    	   

	    String[] opcionesFiltro = {"Enviado", "Pendiente", "En tránsito"};
	    JComboBox<String> filtroComboBox = new JComboBox<>(opcionesFiltro);
	    filtroComboBox.setSelectedIndex(0); 
	    filtroComboBox.setPreferredSize(new Dimension(150, 20));
	    filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
	    
	    
        
	    /*filtroComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	String estadoSeleccionado = (String) filtroComboBox.getSelectedItem();
	            DefaultTableModel modeloTabla = (DefaultTableModel) tablaEnvios.getModel();
	            modeloTabla.setRowCount(0); 

	            String usuarioID = getUsuarioID(); 
				cargarEnviosDelUsuario(modeloTabla, con, usuarioID);

	            tablaEnvios.revalidate();
	            tablaEnvios.repaint();
	        }
	    });*/

	    

	    
        
        ImageIcon imageVolverO = new ImageIcon("resources/images/volver.png");
        ImageIcon imageVolverE = new ImageIcon(
        		imageVolverO.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)
        );
        
        JButton btnVolver = new JButton(imageVolverE);
        
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelVolver.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelVolver.add(btnVolver);
        
        btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPantallaPrincipal pantallaPrincipal = new VentanaPantallaPrincipal(u);
				pantallaPrincipal.setVisible(true);
				dispose();
				
			}
		});

       
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelVolver, BorderLayout.WEST);
        
       

		//ImageIcon imgModificar = new ImageIcon(getClass().getResource("/Images/modificar.png"));
        //JButton btnModificar = new JButton("Modificar");
		//ImageIcon imgEliminar = new ImageIcon(getClass().getResource("/Images/eliminar.png"));
       // JButton btnEliminar = new JButton("Eliminar");
        
        ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
		JLabel labelImagenLogo = new JLabel(logo);

		

        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
       // panelDerecha.add(btnModificar);
       // panelDerecha.add(btnEliminar);
        panelDerecha.add(labelImagenLogo);
        panelSuperior.add(panelDerecha, BorderLayout.EAST);
        
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        filtroComboBox.add(new JLabel("Filtrar por estado:"));
        panelCombo.add(filtroComboBox);
        panelSuperior.add(panelCombo);
       
        
        add(panelSuperior, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        
        

		
	}
	
    class RenderTabla implements TableCellRenderer {
        private final JTable table;
        private final ImageIcon iconoLibro;

        public RenderTabla(JTable table) {
            this.table = table;
            ImageIcon originalIcon  = new ImageIcon("resources/images/libro.jpg");
            Image image = originalIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            iconoLibro = new ImageIcon(image);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel(value.toString());;
            table.setBackground(Color.WHITE);

           
            label.setFont(new Font("Arial",  Font.PLAIN, 14));
            
            if (row % 2 == 0) {
				label.setBackground(new Color(250, 249, 249));
			} else {
				label.setBackground(new Color(190, 227, 219));
			}
			
            if(column == 1 || column == 2 || column == 5) {
            	label.setHorizontalAlignment(SwingUtilities.CENTER);
            }
            
            if (isSelected) {
                label.setBackground(Color.LIGHT_GRAY);
            }
            
            
            
            //que la fecha esté en el formato correcto (yyyy-mm-dd).
            if (value instanceof Date) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                value = dateFormat.format((Date) value);
            }
            
				
			
            
            
            if (column == 2) {
                try {
                    double precio = Double.parseDouble(value.toString());
                    if (precio <= 50) {
                        label.setBackground(Color.GREEN);
                    } else if (precio <= 100) {
                        label.setBackground(Color.ORANGE);
                    } else {
                        label.setBackground(Color.RED);
                    }
                } catch (NumberFormatException e) {
                    label.setBackground(Color.WHITE); 
                }
                
            }
            
            if(column == 3 && "Envio de libros".equals(value)) {
            	label.setHorizontalAlignment(SwingConstants.CENTER);
            	label.setText("");
            	label.setIcon(iconoLibro);
            }

            label.setOpaque(true);
            return label;
        }
    }
    
    class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JPanel panel;
        private final JButton btnModificar;
        private final JButton btnEliminar;
        private final DefaultTableModel tablaModel;
        private final JTable tablaEnvios;
        private int row;

        public ButtonRenderer(DefaultTableModel tablaModel, JTable tablaEnvios) {
            this.tablaModel = tablaModel;
            this.tablaEnvios = tablaEnvios;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            
            ImageIcon iconModificar = new ImageIcon("resources/images/modificar.png");
            ImageIcon iconEliminar = new ImageIcon("resources/images/eliminar.png");
            
            iconModificar = new ImageIcon(iconModificar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            iconEliminar = new ImageIcon(iconEliminar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            btnModificar = new JButton(iconModificar);
            btnEliminar = new JButton(iconEliminar);

            btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   /* tablaEnvios.editCellAt(row, 0); // Habilita la edici�n de la fila completa
                    tablaEnvios.setRowSelectionInterval(row, row); // Selecciona la fila actual
                    stopCellEditing();*/
                	tablaEnvios.editCellAt(row, 3);
                    tablaEnvios.editCellAt(row, 0); 
                    tablaEnvios.setRowSelectionInterval(row, row); 
                    stopCellEditing();
                }
            });

            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tablaModel.removeRow(row); 
                    stopCellEditing();
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
    
    //cargar la tabla
    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
    
//    public void cargarEnviosDelUsuario(DefaultTableModel modeloTabla, Connection con, String usuarioId) {
//
//        modeloTabla.setRowCount(0);        
//        
//        String correo = BaseDatosConfiguracion.buscarUsuarioPorCorreo(con, usuarioId);
//        if (correo == null) {
//            System.out.println("No se encontró el correo asociado al usuario.");
//            return;
//        }
//        
//        List<Envio> envios = BaseDatosConfiguracion.obtenerEnviosPorUsuario(con, correo);
//        
//        if (envios.isEmpty()) {
//            System.out.println("No hay envíos para este usuario.");
//            return;
//        }
//        
//        System.out.println("Envios: " + envios);  
//        
//        for (Envio envio : envios) {
//    	    System.out.println("Referencia: " + envio.getPaquete().getnReferencia());
//    	    System.out.println("Fecha de Recogida: " + envio.getRecogida().getFechaDeRecogida());
//    	    System.out.println("Precio: " + envio.getPago().getPrecio());
//    	    System.out.println("Descripción: " + envio.getPago().getDescripcion());
//    	    System.out.println("Estado: " + envio.getEstado());
//    	    System.out.println("Fecha Prevista: " + envio.getRecogida().getFechaDeRecogida());
//
//    	    
//        	// {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista", "Editar"};
//            modeloTabla.addRow(new Object[]{
//                envio.getPaquete().getnReferencia(), 
//                envio.getRecogida().getFechaDeRecogida(),
//                envio.getPago().getPrecio(),
//                envio.getPago().getDescripcion(),
//                envio.getEstado(),
//                envio.getRecogida().getFechaDeRecogida(),
//                "" // Columna para el botón de editar
//            });
//        }
//        
//        
//    }

public class EnvioTableModel extends AbstractTableModel{
	String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista", "Editar"};
	List<Envio> envios;
	
    // Constructor
    public EnvioTableModel(List<Envio> envios) {
        this.envios = envios;
    }

    @Override
    public int getRowCount() {
        return envios.size();  // Devuelve el número de filas (paquetes)
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;  // Devuelve el número de columnas
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];  // Devuelve el nombre de la columna
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Envio envio = envios.get(rowIndex);  // Obtiene el paquete en la fila indicada
        if (columnIndex == 0) {  // Si es la primera columna (Referencia)
            return envio.getPaquete().getnReferencia();  // Devuelve la referencia del paquete
        }
        if (columnIndex == 1) {
			return new Date().toString();
		}
        if (columnIndex == 2) {
			return envio.getPago().getPrecio();
		}
        if (columnIndex == 3) {
			return envio.getPago().getDescripcion();
		}
        if (columnIndex == 4) {
			return envio.getEstado();
		}
        if (columnIndex == 5) {
			return envio.getRecogida().getFechaDeRecogida();
		}
        if (columnIndex == 6) {
			return "botones"; // falta renderer
		}
        
        return null;  // Si llegamos aquí, algo ha ido mal, devuelve null
    }

}
    
}

