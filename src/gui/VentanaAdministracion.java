package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import domain.Envio;
import domain.Usuario;

//
public class VentanaAdministracion extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel pNorte, pCentro;
	@SuppressWarnings("unused")
	private JLabel txtMisEnvios, txtEnviosRealizados, txtRelleno;
	private JButton btnVolver;
	private DefaultTableModel modeloTabla;
	private JTable tablaEnvios;
	private JScrollPane Scroll;
	private Font fontTexto = new Font("Tahoma", Font.PLAIN, 13 );
	private Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 20);
		
	   
	@SuppressWarnings({ "serial", "unused" })
	public VentanaAdministracion(List<Usuario> usuarios) {
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
//	    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
//
//	    List<Envio> todosLosEnvios = BaseDatosConfiguracion.obtenerEnviosClientes(con);
//	    BaseDatosConfiguracion.closeBD(con);
//
//	    EnvioTableModel modelo = new EnvioTableModel(todosLosEnvios);
//	    tablaEnvios = new JTable(modelo) {
	    
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
        
		
		 List<Envio> todosLosEnvios1 = usuarios.stream()
	                .flatMap(usuario -> usuario.getListaEnvios().stream())
	                .toList();
	}
	
	@SuppressWarnings("serial")
	class EnvioTableModel extends AbstractTableModel {
        String[] nombreColumnas = {"Nº referencia", "Fecha", "Precio", "Descripción", "Estado", "Fecha prevista"};
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
                default:
                    return null;
            }
        }

        public void filtrarPorEstado(String estado) {
            if (estado.equals("Todos")) {
                enviosFiltrados = envios;
            } else {
                enviosFiltrados = envios.stream()
                        .filter(envio -> envio.getEstado().equals(estado))
                        .toList();
            }
            fireTableDataChanged();
        }
    }


		
   
}

