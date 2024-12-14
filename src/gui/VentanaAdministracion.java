package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

import db.BaseDatosConfiguracion;
import domain.Envio;

//
public class VentanaAdministracion extends JFrame {
	
	private JPanel pNorte, pCentro;
	private JLabel txtMisEnvios, txtEnviosRealizados, txtRelleno;
	private JButton btnVolver;
	private DefaultTableModel modeloTabla;
	private JTable tablaEnvios;
	private JScrollPane Scroll;
	private Font fontTexto = new Font("Tahoma", Font.PLAIN, 13 );
	private Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 20);
		
	   
	public VentanaAdministracion() {
//	
	
		pCentro = new JPanel(new GridLayout(2, 1));
		pNorte = new JPanel();

		pNorte.setBorder(new EmptyBorder(10,0,0,180));
		
		ImageIcon imgVolver = new ImageIcon("resources/images/volver.png");
		btnVolver = new JButton(imgVolver);
		
			
		txtMisEnvios = new JLabel("ADMINISTRACIÓN");
		txtEnviosRealizados = new JLabel("Envios realizados");
		txtRelleno = new JLabel(" ");
			
		txtMisEnvios.setFont(fontTextoTitulo);
		txtEnviosRealizados.setFont(fontTexto);
		
		txtMisEnvios.setBorder(new EmptyBorder(10,125,0,0));
		
		
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

	    pNorte.add(btnVolver);
	    pNorte.add(txtMisEnvios);
			
	    pCentro.add(Scroll);
			
	    pCentro.setBorder(new EmptyBorder(30, 20, 0, 20));
	    
	    add(pNorte, BorderLayout.NORTH);
	    add(pCentro, BorderLayout.CENTER);
			
	  
	    setTitle("Administración");
	    setBounds(300, 200, 600, 400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		setResizable(false);

		btnVolver.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
    			dispose();			

        	}
        });
        
	}
	
	
	private void cargarEnviosEnTabla() {
	    DefaultTableModel model = (DefaultTableModel) tablaEnvios.getModel();
	    model.setRowCount(0); 
	    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");

	    try {
	        List<Envio> envios = BaseDatosConfiguracion.obtenerEnvios(con);
	        for (Envio envio : envios) {
	            model.addRow(new Object[] {
	                envio.getPaquete().getnReferencia(),
	                envio.getPago().getFechaCaducidad(),
	                envio.getPago().getPrecio(),
	                envio.getPago().getDescripcion(),
	                envio.getEstado(),
	                envio.getRecogida().getFechaDeRecogida()
	            });
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al cargar los envíos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        BaseDatosConfiguracion.closeBD(con);
	    }
	}



		
    public static void main(String[] args) {
        VentanaAdministracion ventana = new VentanaAdministracion();
        ventana.setVisible(true); 
    }
}

