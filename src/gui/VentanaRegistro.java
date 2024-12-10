package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import db.BaseDatosConfiguracion;
import domain.Dominio;
import domain.Envio;
import domain.Usuario;


public class VentanaRegistro extends JFrame{
	
	private JPanel pCentro,pSur,pNorte;
	private JLabel lblNom,lblApe, lblCorreo,lblTel,lblContra,lblRepeContra,lblSegu,lblResp;
	private JTextField txtNom,txtApe,txttel,txtCorreo,txtContra,txtRepeContra,txtResp, txtSegu;
	private JButton btnRegistro,btnMostrarContra,btnMostrarRepeContra,btnFlecha, mostrarContra, mostrarRepeContra;
	private String textoTYC;
	private JTextArea textTYC;
	private JScrollPane scrollTYC;
	
	public VentanaRegistro () {
		setTitle("Formulario de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setBounds(100, 100,  778, 455);
        setVisible(true);
		setResizable(false);

        
       // pNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pNorte = new JPanel(new BorderLayout());
        //pNorte.setBackground(Color.WHITE);
        
        pCentro = new JPanel(new GridLayout(4,4));
       // pCentro.setBackground(Color.WHITE);
        pSur = new JPanel();
        pSur.setLayout(new BoxLayout(pSur, BoxLayout.Y_AXIS));
        //pSur.setBackground(Color.WHITE);
        pCentro.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		
		lblNom = new JLabel("NOMBRE: ");
		lblNom.setBounds(50, 30, 100, 25);
		lblApe = new JLabel("APELLIDO: ");
		lblApe.setBounds(50, 30, 100, 25);
		lblCorreo = new JLabel("CORREO: ");
		lblCorreo.setBounds(50, 30, 100, 25);
		lblTel = new JLabel("TELEFONO: ");
		lblTel.setBounds(50, 30, 100, 25);
		lblContra = new JLabel("CONTRASEÑA: ");
		lblContra.setBounds(50, 30, 100, 25);
		lblRepeContra = new JLabel("REPETIR CONTRASEÑA: ");
		lblRepeContra.setBounds(50, 30, 100, 25);
		lblSegu = new JLabel("P.SEGURIDAD: ");
		lblSegu.setBounds(50, 30, 100, 25);
		lblResp = new JLabel("RESPUESTA: ");
		lblResp.setBounds(50, 30, 100, 25);
		
		txtNom = new JTextField();
		txtNom.setBounds(150, 30, 250, 25);
		txtApe = new JTextField();
		txtApe.setBounds(150, 30, 250, 25);
		txtCorreo = new JTextField();
		txtCorreo.setBounds(150, 30, 250, 25);
		txttel = new JTextField();
		txttel.setBounds(150, 30, 250, 25);
		txtContra = new JPasswordField();
		txtContra.setBounds(150, 30, 250, 25);
		txtRepeContra = new JPasswordField();
		txtRepeContra.setBounds(150, 30, 250, 25);
		txtResp = new JTextField();
		txtResp.setBounds(150, 30, 250, 25);
		txtSegu = new JTextField();
		txtSegu.setBounds(150, 30, 250, 25);
		
		String[] preguntas = {"¿Dia de nacimiento?", "¿Ciudad de nacimiento?", "¿Nombre del primer colegio?"};
        JComboBox<String> comboPreguntas = new JComboBox<>(preguntas);
        txtSegu.add(comboPreguntas);
        String preguntaSeg = (String) comboPreguntas.getSelectedItem();
        comboPreguntas.setBackground(Color.WHITE);
        
     // Flecha
        ImageIcon iconoFlecha = new ImageIcon("resources/images/flecha.jpg");
        Image imgFlecha = iconoFlecha.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btnFlecha = new JButton(new ImageIcon(imgFlecha));
        btnFlecha.setFocusPainted(false);
        btnFlecha.setBorderPainted(false);
        btnFlecha.setContentAreaFilled(false);
        JPanel panelVolver = new JPanel(new GridLayout(1, 1));
        panelVolver.setPreferredSize(new Dimension(50, 50));
        panelVolver.setBorder(new EmptyBorder(0,0,0,0));//10, 10, 10, 10));
        panelVolver.add(btnFlecha);
        pNorte.add(panelVolver, BorderLayout.WEST);
        
        
        // Título
        JLabel lblTitulo = new JLabel("REGISTRO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30)); 
       // lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        pNorte.add(lblTitulo, BorderLayout.CENTER);
        
        
        pCentro.add(lblNom);
        pCentro.add(txtNom);
        pCentro.add(lblApe);
        pCentro.add(txtApe);
        pCentro.add(lblCorreo);
        pCentro.add(txtCorreo);
        pCentro.add(lblTel);
        pCentro.add(txttel);
        //pCentro.add(lblContra);
        //pCentro.add(txtContra);
        //pCentro.add(lblRepeContra);
        //pCentro.add(txtRepeContra);
        pCentro.add(lblSegu);
        pCentro.add(comboPreguntas);
        pCentro.add(lblResp);
        pCentro.add(txtResp);
        
        //USAR SETBOUNDS PARA HACERLO MAS PEQUEÑO
        Dimension dimension = new Dimension(80, 20); 
        lblNom.setPreferredSize(dimension);
        lblApe.setPreferredSize(dimension);
        lblCorreo.setPreferredSize(dimension);
        lblTel.setPreferredSize(dimension);
        lblContra.setPreferredSize(dimension);
        lblRepeContra.setPreferredSize(dimension);
        lblSegu.setPreferredSize(dimension);
        lblResp.setPreferredSize(dimension);
        
        txtNom.setBounds(100, 100, 250, 25);
        txtApe.setPreferredSize(new Dimension(80, 20));
        txtCorreo.setPreferredSize(new Dimension(80, 20));
        txttel.setPreferredSize(new Dimension(80, 20));
        txtContra.setPreferredSize(new Dimension(80, 20));
        txtRepeContra.setPreferredSize(new Dimension(80, 20));
        txtResp.setPreferredSize(new Dimension(80, 20));
        txtSegu.setPreferredSize(new Dimension(80, 20));
		
		
        JCheckBox aceptarTerminos = new JCheckBox("<html><span style='color:blue; text-decoration: underline;'>Acepto Términos y Condiciones de uso</span></html>");
		aceptarTerminos.setHorizontalAlignment(SwingConstants.CENTER);
		aceptarTerminos.setAlignmentX(Component.CENTER_ALIGNMENT);
        pSur.add(aceptarTerminos);
		btnRegistro = new JButton("REGISTRRSE");
		pSur.add(btnRegistro);
		//aceptarTerminos.setBackground(Color.WHITE);
		//pSur.setBackground(Color.WHITE);
		//btnRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //OJOS
        mostrarRepeContra = new JButton();
        mostrarContra = new JButton();
        
        ImageIcon ojoVer = new ImageIcon("resources/images/ojoAbierto.png");
        ImageIcon ojoOcultar = new ImageIcon("resources/images/ojoCerrado.png");

        mostrarContra.setIcon(ojoVer);
        mostrarRepeContra.setIcon(ojoVer);
        
        mostrarContra.setBounds(410, 80, 50, 25);
        mostrarContra.setBounds(410, 80, 50, 25);
        
        pCentro.add(lblContra);
        JPanel panelContra = new JPanel(new BorderLayout());
        panelContra.add(txtContra, BorderLayout.CENTER);
        panelContra.add(mostrarContra, BorderLayout.EAST);
        pCentro.add(panelContra);

        pCentro.add(lblRepeContra);
        JPanel panelRepeContra = new JPanel(new BorderLayout());
        panelRepeContra.add(txtRepeContra, BorderLayout.CENTER);
        panelRepeContra.add(mostrarRepeContra, BorderLayout.EAST);
        pCentro.add(panelRepeContra);

        mostrarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JPasswordField) txtContra).getEchoChar() == '*') {
                    ((JPasswordField) txtContra).setEchoChar((char) 0);
                    mostrarContra.setIcon(ojoOcultar);
                } else {
                    ((JPasswordField) txtContra).setEchoChar('*');
                    mostrarContra.setIcon(ojoVer);
                }
                
            }
        });
        
        mostrarRepeContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JPasswordField) txtRepeContra).getEchoChar() == '*') {
                    ((JPasswordField) txtRepeContra).setEchoChar((char) 0); 
                    mostrarRepeContra.setIcon(ojoOcultar); 
                } else {
                    ((JPasswordField) txtRepeContra).setEchoChar('*');
                    mostrarRepeContra.setIcon(ojoVer); 
                }
            }
        });
		
        setVisible(true);

        //EVENTOS
        
        btnFlecha.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
    			dispose();			

        	}
        });
        
       
        
        btnRegistro.addActionListener(e -> {
        	
        	if (!aceptarTerminos.isSelected()) {
				JOptionPane.showMessageDialog(
						VentanaRegistro.this, "Debes aceptar los Términos y Condiciones para registrarte.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    return;
			}
        	            
			Connection c = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
			
            String nombre = txtNom.getText().trim();
            String apellido = txtApe.getText().trim();
            String telefono = txttel.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contra = txtContra.getText().trim();
            String contra2 = txtRepeContra.getText().trim();
            String resp = txtResp.getText().trim();
           // String segu = txtSegu.getText().trim();
            String segu = (String) comboPreguntas.getSelectedItem();
            
            // Comprobar si hay algún campo vacío
            if (nombre.equals("") || apellido.equals("") || telefono.equals("") || correo.equals("") 
                    || resp.equals("") || contra.equals("") || contra2.equals("") || segu == null) {
                JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

                              
            } else {

                if (contra.equals(contra2)) {

                    if (comprobarEmail()) {

                        if (comprobarTlf()) {

                            Usuario u = BaseDatosConfiguracion.buscarUsuarioPorCorreo(c, correo);

                            if (u == null) {  // Si no existe, agregarlo
                                Usuario nuevoUsuario = new Usuario(nombre, apellido, telefono, correo, resp, segu, contra2, new ArrayList<Envio>());
                                BaseDatosConfiguracion.insertarUsuario(c, nuevoUsuario);
                                JOptionPane.showMessageDialog(null, "Registro realizado con éxito");
                                SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
                                dispose();
                             
                            } else {
                                JOptionPane.showMessageDialog(null, "Ya existe este usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El teléfono debe tener 9 números", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El email introducido no es correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtContra.setText("");
                    txtRepeContra.setText("");
                }
            }

            BaseDatosConfiguracion.closeBD(c);
        });


        
        textoTYC = new String("Aceptación de Términos y Condiciones de uso:\r\n"
    			+ "\r\n"
    			+ "Al utilizar nuestro sistema, el usuario acepta estos términos y condiciones y se compromete a cumplir con ellos. Estos términos pueden ser modificados en cualquier momento, y el usuario se compromete a revisarlos regularmente para estar al tanto de cualquier cambio.\r\n"
    			+ "\r\n"
    			+ "Los usuarios pueden necesitar registrarse para utilizar ciertas funciones del sistema. La información proporcionada durante el registro debe ser precisa y completa.\r\n"
    			+ "Los usuarios son responsables de mantener la confidencialidad de sus credenciales de inicio de sesión y notificar a Hermes de cualquier uso no autorizado de su cuenta.\r\n"
    			+ "\r\n"
    			+ "Los usuarios se comprometen a utilizar el sistema de manera adecuada y legal, sin infringir derechos de terceros.\r\n"
    			+ "No se permite el uso del sistema para actividades ilegales o fraudulentas.\r\n"
    			+ "\r\n"
    			+ "Los usuarios son responsables de la exactitud de la información proporcionada al sistema, incluyendo datos de contacto y direcciones de envío.\r\n"
    			+ "Los usuarios son responsables de asegurarse de que los paquetes y envíos cumplan con las restricciones y regulaciones aplicables.\r\n"
    			+ "\r\n"
    			+ "Los usuarios aceptan pagar las tarifas correspondientes a los servicios utilizados, según las tarifas publicadas por Hermes.\r\n"
    			+ "Los pagos se pueden realizar a través de los métodos de pago aceptados por el sistema.\r\n"
    			+ "\r\n"
    			+ "Hermes se compromete a proteger la privacidad y los datos de los usuarios de acuerdo con las leyes aplicables.\r\n"
    			+ "\r\n"
    			+ "Hermes no se hará responsable de daños indirectos, consecuentes o incidentales.\r\n"
    			+ "La responsabilidad de Hermes se limita a los términos establecidos en acuerdos específicos.\r\n"
    			+ "\r\n"
    			+ "Las políticas de cancelación y devolución se basan en las tarifas y políticas específicas de Hermes.\r\n"
    			+ "Los usuarios deben revisar nuestras políticas de cancelación y devolución antes de utilizar el sistema.\r\n"
    			+ "\r\n"
    			+ "Hermes se reserva el derecho de suspender o cancelar la cuenta de cualquier usuario que incumpla estos términos y condiciones.\r\n"
    			+ "\r\n"
    			+ "Estos términos y condiciones se rigen por las leyes del país (en este caso España) y cualquier disputa se resolverá mediante arbitraje de conformidad con las reglas de Hermes o ante los tribunales competentes en España.\r\n"
    			+ "\r\n"
    			+ "Si tiene alguna pregunta o inquietud acerca de estos términos y condiciones, por favor contáctenos a través de support@hermes.es.\r\n"
    			+ "\r\n"
    			+ "Al utilizar el Sistema de Paquetería de Hermes, usted acepta y comprende estos términos y condiciones. Le recomendamos que imprima o descargue una copia de este documento para su referencia futura.");
    	
        
        aceptarTerminos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				textTYC = new JTextArea(textoTYC);

		        	scrollTYC = new JScrollPane(textTYC);
		        	scrollTYC.setPreferredSize(new Dimension(400, 300));

		        	int option = JOptionPane.showOptionDialog(
		        			null,
		        			scrollTYC,
		        			"Términos y Condiciones",
		        			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Rechazar"}, "Aceptar");

		        	if (option == JOptionPane.OK_OPTION) {
		        		aceptarTerminos.setSelected(true);
		        	} else {
		        		aceptarTerminos.setSelected(false);
		        	}
			}
		});
        
      aceptarTerminos.addActionListener(e -> btnRegistro.setEnabled(aceptarTerminos.isSelected()));
	

      getRootPane().setDefaultButton(btnRegistro);
        
        
	
	}

	//Metodos para comprobar el Registro
	private boolean comprobarTlf() {
		String patron = "[0-9]{9}";
		return  Pattern.matches(patron, txttel.getText());		
	}
	
	private boolean comprobarEmail() {		
		String patron = "[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-z]{2,}$";
	    String correo = txtCorreo.getText();	    
	    if (!Pattern.matches(patron, correo)) {
	        return false; 
	    }	   
	    String dominio = correo.substring(correo.indexOf('@'));	    	  
	    return Dominio.esDominioValido(dominio);
	}
	

}

