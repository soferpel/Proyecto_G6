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
import java.sql.Date;
import java.text.SimpleDateFormat;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.AbstractCellEditor;

public class VentanaVerEnvios  extends JFrame {
	
	private static final TableCellRenderer RenderTabla = null;

    
	public VentanaVerEnvios() {
		setTitle("Ver Envios");
		setSize(900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		
		String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista", "Editar"};
		DefaultTableModel tabla = new DefaultTableModel(null, nombreColumnas);

		JTable tablaEnvios = new JTable(tabla) {
		
			private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 3;
	        }
	     };
	        
	    tablaEnvios.setRowHeight(30);
	    tablaEnvios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

	    
	    tablaEnvios.setDefaultRenderer(Object.class, new RenderTabla(tablaEnvios));
	    
	    ButtonRenderer buttonRendererEditor = new ButtonRenderer(tabla, tablaEnvios);
        tablaEnvios.getColumnModel().getColumn(6).setCellRenderer(buttonRendererEditor);
        tablaEnvios.getColumnModel().getColumn(6).setCellEditor(buttonRendererEditor);	        
	    JPanel tablePanel = new JPanel(new BorderLayout());
	    tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
	        
	    JScrollPane scrollPane = new JScrollPane(tablaEnvios);
			
	    tablePanel.add(scrollPane, BorderLayout.CENTER);
	    
	    tabla.addRow(new Object[] {"REF-1001", "2024-11-01", "50.00", "Envio de libros", "Pendiente", "2024-12-01", ""});
	    tabla.addRow(new Object[]{"REF-1002", "2024-11-02", "75.00", "Envio de ropa", "En transito", "2024-12-03", ""});
	    tabla.addRow(new Object[]{"REF-1003", "2024-11-03", "150", "Envio de electrónicos", "Enviado", "2024-12-01", ""});
        

	    String[] opcionesFiltro = {"Enviado", "Pendiente", "En tránsito"};
	    JComboBox<String> filtroComboBox = new JComboBox<>(opcionesFiltro);
	    filtroComboBox.setSelectedIndex(0); 
	    filtroComboBox.setPreferredSize(new Dimension(150, 20));
	    filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
	    
        
	    filtroComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String estadoSeleccionado = (String) filtroComboBox.getSelectedItem();
	            DefaultTableModel modeloTabla = (DefaultTableModel) tablaEnvios.getModel();		//!!!!!
	            modeloTabla.setRowCount(0);

	            if ("Enviado".equals(estadoSeleccionado)) {
	                modeloTabla.addRow(new Object[]{"REF-1003", "2024-11-03", "150", "Envio de electrónicos", "Enviado", "2024-12-01", ""});
	            } else if ("Pendiente".equals(estadoSeleccionado)) {
	                modeloTabla.addRow(new Object[]{"REF-1001", "2024-11-01", "50.00", "Envio de libros", "Pendiente", "2024-12-01", ""});
	            } else if ("En tránsito".equals(estadoSeleccionado)) {
	                modeloTabla.addRow(new Object[]{"REF-1002", "2024-11-02", "75.00", "Envio de ropa", "En transito", "2024-12-03", ""});
	            }
	        }
	    });


	    
        
        ImageIcon imageVolverO = new ImageIcon(getClass().getResource("resurces/images/volver.png"));
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
				VentanaPantallaPrincipal pantallaPrincipal = new VentanaPantallaPrincipal();
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
        
        ImageIcon logo = new ImageIcon(getClass().getResource("resources/images/logoPngNegro.png"));
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
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaVerEnvios().setVisible(true);
            }
        });
    }
    
    class RenderTabla implements TableCellRenderer {
        private final JTable table;
        private final ImageIcon iconoLibro;

        public RenderTabla(JTable table) {
            this.table = table;
            ImageIcon originalIcon  = new ImageIcon(getClass().getResource("resources/images/libro.jpg"));
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
            
            ImageIcon iconModificar = new ImageIcon(getClass().getResource("/resourcesimages/modificar.png"));
            ImageIcon iconEliminar = new ImageIcon(getClass().getResource("resources/images/eliminar.png"));
            
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
    
}

