package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VentanaVerEnvios  extends JFrame {
	
	public VentanaVerEnvios() {
		setTitle("Ver Envios");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista"};
		DefaultTableModel tabla = new DefaultTableModel(null, nombreColumnas);

		JTable tablaEnvios = new JTable(tabla) {
			
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
	        }
	     };
	        
	    int rowHeight = 30;  
	    tablaEnvios.setRowHeight(rowHeight);
	        
	    JPanel tablePanel = new JPanel(new BorderLayout());
	    tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
	        
	    JScrollPane scrollPane = new JScrollPane(tablaEnvios);
			
	    tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        
        ImageIcon imageVolverO = new ImageIcon(getClass().getResource("/Images/volver.png"));
        ImageIcon imageVolverE = new ImageIcon(
        		imageVolverO.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)
        );
        
        JButton btnVolver = new JButton(imageVolverE);
        
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelVolver.setPreferredSize(new Dimension(40, 40));
        panelVolver.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelVolver.add(btnVolver);
        
        
        btnVolver.setPreferredSize(new Dimension(20, 20));

       
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelVolver, BorderLayout.WEST);

		//ImageIcon imgModificar = new ImageIcon(getClass().getResource("/Images/modificar.png"));
        JButton btnModificar = new JButton("Modificar");
		//ImageIcon imgEliminar = new ImageIcon(getClass().getResource("/Images/eliminar.png"));
        JButton btnEliminar = new JButton("Eliminar");
        
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logoPngNegro.png"));
		JLabel labelImagenLogo = new JLabel(logo);

		

        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelDerecha.add(btnModificar);
        panelDerecha.add(btnEliminar);
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
    
}

