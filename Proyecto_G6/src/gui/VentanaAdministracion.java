package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
import javax.swing.table.DefaultTableModel;


public class VentanaAdministracion extends JFrame {
	
	private JPanel pNorte, pNorte2, pCentro, pBtnVolver;
	private JLabel txtMisEnvios, txtEnviosRealizados, txtRelleno;
	private JButton btnVolver;
	private DefaultTableModel modeloTabla;
	private JTable tablaEnvios;
	private JScrollPane Scroll;
		
	   
	public VentanaAdministracion() {

		
		pNorte = new JPanel(new GridLayout(1, 5));
		pCentro = new JPanel(new GridLayout(2, 1));
		pBtnVolver = new JPanel();
		pNorte2 = new JPanel();
		pNorte = new JPanel();

		pNorte2.setBorder(new EmptyBorder(0,0,0,400));
			
		txtMisEnvios = new JLabel("Administración");
		txtEnviosRealizados = new JLabel("Envios realizados");
		txtRelleno = new JLabel(" ");
			
		btnVolver = new JButton("<--");
			
			
		/**CREACION JTABLE*/
		String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista"};
		modeloTabla = new DefaultTableModel(null, nombreColumnas);

		tablaEnvios = new JTable(modeloTabla) {
			
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
	        }
	     };
	        
	    int rowHeight = 30;  
	    tablaEnvios.setRowHeight(rowHeight);
	        
	    JPanel tablePanel = new JPanel(new BorderLayout());
	    tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
	        
	    Scroll = new JScrollPane(tablaEnvios);
			
	    tablePanel.add(Scroll, BorderLayout.CENTER);

	    pBtnVolver.add(btnVolver);
	    pNorte.add(pBtnVolver);
	    pNorte.add(txtMisEnvios);
			
	    pCentro.add(Scroll);
			
	    pCentro.setBorder(new EmptyBorder(30, 20, 0, 20));
	    
	    pNorte2.add(btnVolver);
		
	  	pNorte.add(pNorte2);

	    add(pNorte, BorderLayout.NORTH);
	    add(pCentro, BorderLayout.CENTER);
			
	  
	    setTitle("Administración");
	    setBounds(300, 200, 600, 400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
	}

		
    public static void main(String[] args) {
        VentanaAdministracion ventana = new VentanaAdministracion();
        ventana.setVisible(true); 
    }
}

