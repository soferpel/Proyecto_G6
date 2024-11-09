package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
			return column == 6;
	        }
	     };
	        
	    int rowHeight = 30;  
	    tablaEnvios.setRowHeight(rowHeight);
	    
	    //tablaEnvios.setDefaultRenderer(Object.class, new RenderTabla(tablaEnvios));
	   // tablaEnvios.getColumnModel().getColumn(2).setCellRenderer(new RenderTabla(tablaEnvios));
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
	    tabla.addRow(new Object[]{"REF-1003", "2024-11-03", "150", "Envio de electrónicos", "En transito", "2024-12-07", ""});
        
        
        ImageIcon imageVolverO = new ImageIcon(getClass().getResource("/Images/volver.png"));
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
        
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logoPngNegro.png"));
		JLabel labelImagenLogo = new JLabel(logo);

		

        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
       // panelDerecha.add(btnModificar);
       // panelDerecha.add(btnEliminar);
        panelDerecha.add(labelImagenLogo);
        panelSuperior.add(panelDerecha, BorderLayout.EAST);
        
        
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

        public RenderTabla(JTable table) {
            this.table = table;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel(value.toString());

            label.setFont(new Font("Arial",  Font.PLAIN, 14));
            
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
            
            ImageIcon iconModificar = new ImageIcon(getClass().getResource("/Images/modificar.png"));
            ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/Images/eliminar.png"));
            
            iconModificar = new ImageIcon(iconModificar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            iconEliminar = new ImageIcon(iconEliminar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            btnModificar = new JButton(iconModificar);
            btnEliminar = new JButton(iconEliminar);

            btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tablaEnvios.editCellAt(row, 0); // Habilita la edici�n de la fila completa
                    tablaEnvios.setRowSelectionInterval(row, row); // Selecciona la fila actual
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
    
   /* class renderTabla extends JLabel implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel result = new JLabel(value.toString());
			//tablaEnvios.setFont(new Font("Arial",  Font.PLAIN, 14)); 
			double precio = Double.parseDouble(value.toString());
			if(precio <= 50) {
				result.setBackground(Color.GREEN);
			}else if(precio <= 100) {
				result.setBackground(Color.ORANGE);
			}else {
				result.setBackground(Color.RED);
			}
			result.setOpaque(true);
			return result;
		}
    	
    }*/

    
    
}

