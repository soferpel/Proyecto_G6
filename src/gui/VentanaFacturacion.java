package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Usuario;
import gui.VentanaVerEnvios.EnvioTableModel;


public class VentanaFacturacion extends JFrame {

	private DefaultTableModel model;
	private ArrayList<Object[]> datosOriginales = new ArrayList<>(); //IA
	private JTextField txtReferencia,txtPrecio,txtDescripcion,txtFechaEnvio;
	
	private JRadioButton rbSi; 
	private JRadioButton rbNo; 
	
    public VentanaFacturacion(Usuario u) {

    	setTitle("Facturación");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
  		setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // btnVolver
        ImageIcon imageVolverO = new ImageIcon("resources/images/volver.png");
        ImageIcon imageVolverE = new ImageIcon(imageVolverO.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
        
        
        JButton btnVolver = new JButton(imageVolverE);
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelVolver.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelVolver.add(btnVolver);
        
        panelSuperior.add(panelVolver, BorderLayout.WEST);

        //titulo
        JLabel lblTitulo = new JLabel("FACTURACIÓN", JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        
        //logo
        ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
		JLabel labelImagenLogo = new JLabel(logo);
        panelSuperior.add(labelImagenLogo, BorderLayout.EAST);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JPanel panelIzquierdo = new JPanel(new GridLayout(4, 2, 10, 10));
        
        //referencia
        JLabel lblReferencia = new JLabel("Número de referencia:");
         txtReferencia = new JTextField(15);
        panelIzquierdo.add(lblReferencia);
        panelIzquierdo.add(txtReferencia);
        

	    List<Envio> enviosDelUsuario = new ArrayList<>();
	    try (Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db")) {
	        if (con != null) {
	            System.out.println("Conexión a la base de datos establecida.");
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
	    
        txtReferencia.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarPorReferencia();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarPorReferencia();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarPorReferencia();
				
			}
          
        });
        
        txtReferencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                    buscarDatosReferencia();  
                }
            }
        });


        //precio
        JLabel lblPrecio = new JLabel("Precio:");
         txtPrecio = new JTextField(15);
        panelIzquierdo.add(lblPrecio);
        panelIzquierdo.add(txtPrecio);

        //¿pagado?
        JLabel lblPagado = new JLabel("¿Pagado?");
        JPanel panelPagado = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
         rbSi = new JRadioButton("Sí");
         rbNo = new JRadioButton("No");
        ButtonGroup bgPagado = new ButtonGroup();
        bgPagado.add(rbSi);
        bgPagado.add(rbNo);
        panelPagado.add(rbSi);
        panelPagado.add(rbNo);
        panelIzquierdo.add(lblPagado);
        panelIzquierdo.add(panelPagado);

        panelCentral.add(panelIzquierdo);

        JPanel panelDerecho = new JPanel(new GridLayout(2, 2, 10, 10));

        //descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
         txtDescripcion = new JTextField(15);
        panelDerecho.add(lblDescripcion);
        panelDerecho.add(txtDescripcion);

        //fecha de envío
        JLabel lblFechaEnvio = new JLabel("Fecha Envío:");
         txtFechaEnvio = new JTextField(15);
        panelDerecho.add(lblFechaEnvio);
        panelDerecho.add(txtFechaEnvio);
        
        panelCentral.add(panelDerecho);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        
        txtPrecio.setEditable(false);
        txtDescripcion.setEditable(false);
        txtFechaEnvio.setEditable(false);

        JPanel panelTablaYBoton = new JPanel(new BorderLayout()); 
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));


        
        JTable table = new JTable(new EnvioTableModel(u.getListaEnvios()));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        JComboBox<String> comboBoxEnvio = new JComboBox<>(new String[]{"Estándar", "Premium", "Superior"});
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBoxEnvio));
        table.getColumnModel().getColumn(4).setCellRenderer(new CustomTableCellRenderer());
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650, 120));
        panelTabla.add(scrollPane);
        panelTablaYBoton.add(panelTabla, BorderLayout.CENTER);
        
        //exportar
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnExportarPDF = new JButton("EXPORTAR");
        JProgressBar progressBar = new JProgressBar(0,100);
        progressBar.setPreferredSize(new Dimension(200, 20));
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(75, 175, 80));
        progressBar.setBackground(new Color(230, 230, 230));
        progressBar.setFont(new Font("Tahoma", Font.BOLD, 12));
        progressBar.setForeground(Color.WHITE);
        progressBar.setVisible(false);
	    panelBoton.add(progressBar);
        
        btnExportarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	progressBar.setVisible(true);
            	progressBar.setValue(0);
            	
            	SwingWorker<Void, Integer> hilo = new SwingWorker<>() { //para hacerlo en un segundo plano IA
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50); 
                            publish(i);                         }
                        return null;
                    }

                    @Override
                    protected void process(java.util.List<Integer> chunks) {
                        progressBar.setValue(chunks.get(chunks.size() - 1));
                    }

                    @Override
                    protected void done() {
                        progressBar.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Factura exportada en PDF con éxito");
                    }
                };

                hilo.execute(); 
            }
        });
        
        
        panelBoton.add(btnExportarPDF);
        panelTablaYBoton.add(panelBoton, BorderLayout.SOUTH);
        panelPrincipal.add(panelTablaYBoton, BorderLayout.SOUTH);
        
        btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal(u));
				dispose();
			}
		});
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	
                    txtReferencia.setText(""); 
            /*      txtFechaEnvio.setText("");
                    txtPrecio.setText("");
                    txtDescripcion.setText("");
            */      
                    String referencia = (String) table.getValueAt(selectedRow, 0); 
            /*      String fecha = (String) table.getValueAt(selectedRow, 1); 
                    String precio = (String) table.getValueAt(selectedRow, 2); 
                    String descripcion = (String) table.getValueAt(selectedRow, 3); 
           */  
                    txtReferencia.setText(referencia); 
                    /*txtFechaEnvio.setText(fecha);
                    txtPrecio.setText(precio);
                    txtDescripcion.setText(descripcion);
           */}
            }
        });
        add(panelPrincipal);
        setVisible(true);
        
        
                
    }
    
    private void filtrarPorReferencia() {
        String filtro = txtReferencia.getText().trim();
        
        model.setRowCount(0);
         
        if (filtro.isEmpty()) {
            datosOriginales.forEach(model::addRow);
            return;
        }

        datosOriginales.forEach(fila -> {
            if (fila[0].toString().contains(filtro)) {
                model.addRow(fila);
            }
        });
    }
    

    private void buscarDatosReferencia() {
        String referencia = txtReferencia.getText().trim();
        System.out.println("Referencia buscada: " + referencia);

        if (referencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce una referencia válida.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection c = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");

        try {
            String query = """
                    SELECT pago.precio, pago.descripcion, pago.cvv, envio.recogida_id
                    FROM pago
                    JOIN envio ON envio.pago_id = pago.dni
                    WHERE envio.paquete_id = ?;
                    """;

            try (PreparedStatement stmt = c.prepareStatement(query)) {
                stmt.setString(1, referencia);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String precio = rs.getString("precio");
                    String descripcion = rs.getString("descripcion");
                    String recogidaId = rs.getString("recogida_id");
                    String cvv = rs.getString("cvv");

                    txtPrecio.setText(precio);
                    txtDescripcion.setText(descripcion);
                    txtFechaEnvio.setText(recogidaId); 

                    if (cvv == null) {
                        rbNo.setSelected(true);  
                    } else {
                        rbSi.setSelected(true);  
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la referencia.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar la referencia.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            BaseDatosConfiguracion.closeBD(c);
        }
    }

    //renderer
    private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
            	label.setForeground(new Color(24, 40, 66));
            	label.setBackground(new Color(134, 166, 191));
            } else {
            	
                if (row % 2 == 0) {
                	label.setBackground(new Color(245, 245, 245));
                } else {
                	label.setBackground(Color.WHITE);
                }
                label.setForeground(Color.BLACK);
                
                if ("Estándar".equals(value)) {
                	label.setBackground(new Color(255, 200, 200)); 
                } else if ("Premium".equals(value)) {
                	label.setBackground(new Color(255, 255, 200)); 
                } else if ("Superior".equals(value)) {
                	label.setBackground(new Color(255, 220, 180)); 
                }
            }

            if (column == 0) {
            	label.setForeground(Color.RED);
			}
            
            
            
            label.setHorizontalAlignment(JLabel.CENTER); 
            label.setOpaque(true); 
            
            return label;
        }
    }
    
    //modelo de la tabla
    class EnvioTableModel extends AbstractTableModel {
        String[] nombreColumnas = {"Número de referencia", "Fecha de Recogida", "Precio", "Descripción", "Tipo de envío"};
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
                return envio.getRecogida().getFechaDeRecogida();
            case 2: 
                return envio.getPago().getPrecio();
            case 3: 
                return envio.getPago().getDescripcion();
            case 4: 
                return envio.getRecogida().getTipoDeEnvio();
            default:
                return null;
            }
        }

    }
    


}