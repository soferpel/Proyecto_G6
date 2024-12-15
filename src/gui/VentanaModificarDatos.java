
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import db.BaseDatosConfiguracion;
import domain.Usuario; 

public class VentanaModificarDatos extends JFrame{
	
		
private JLabel txtModDatos, txtCorreo, txtTelefono, txtNewCon, txtVerifCon, txtNom,
				txtApel, txtRes, aceptarCond;

private JTextField campoCorreo, campoTelefono, campoRes, campoNom, campoApel, 
					campoContrasenia1, campoVenificaCon1;

private String textoTYC;

private JTextArea textTYC;

private JScrollPane scrollTYC;

private JPasswordField campoCon, campoVerifCon;

private JButton btnElimCuen, btnModif, btnVolver, btnOjoCon, btnOjoConVen, mostrarContra;

private JPanel pNorte, pSur, pCentro, pVenificaCon, pContrasenia, pNombre,
				pApellido, pRespuesta, pTelefono, pCorreo, 
			 pTYC, pBotones;

private JCheckBox checkTerminos;

    
private boolean esOjoAbierto = false;
private boolean esOjoAbiertoVen = false;





public VentanaModificarDatos(Usuario u) {

	
	pNorte = new JPanel(new GridLayout(1,2));
	pSur = new JPanel();
	pCentro = new JPanel(new GridLayout(4,2));
	pContrasenia = new JPanel();
	pVenificaCon = new JPanel();
	pNombre = new JPanel();
	pApellido = new JPanel();
	pRespuesta = new JPanel();
	pTelefono = new JPanel();
	pCorreo = new JPanel();
	pBotones = new JPanel(); 
    pTYC = new JPanel(); 
	
	txtNom = new JLabel("    Nombre:");
	txtApel = new JLabel("     Apellido:");
	txtModDatos = new JLabel("MODIFICAR DATOS:");
	txtCorreo = new JLabel("      Correo:");
	txtNewCon = new JLabel("Nueva Contraseña:");
	txtRes = new JLabel("Respuesta de la Pregunta de Seguridad:");
	txtTelefono = new JLabel("    Teléfono:");
	txtVerifCon = new JLabel("  Repite contraseña:");
	aceptarCond = new JLabel("<html><u>Aceptas terminos y condiciones de uso</u></html>");
	
	checkTerminos = new JCheckBox();
	checkTerminos.setEnabled(true);
	
	ImageIcon ojoAbierto = new ImageIcon("resources/images/ojoAbierto.png");
	ImageIcon ojoCerrado = new ImageIcon("resources/images/ojoCerrado.png");
	//ImageIcon ojoAbierto1 = new ImageIcon("resources/images/ojoAbierto.png");
//	ImageIcon ojoCerrado1 = new ImageIcon("resources/images/ojoCerrado.png");
	
	campoNom = new JTextField(10);
	campoApel = new JTextField(10);
	campoCorreo = new JTextField(10);
	campoTelefono = new JTextField(10);
	campoRes = new JTextField(10);
	campoContrasenia1 = new JTextField(10);
	campoVenificaCon1 = new JTextField(10);
	
	campoCon = new JPasswordField(10);
	campoVerifCon = new JPasswordField(10);
	
	
	btnModif = new JButton("MODIFICAR");
	btnElimCuen = new JButton("ELIMINAR CUENTA");
	btnVolver = new JButton("VOLVER");
	btnOjoCon = new JButton(ojoAbierto);
	btnOjoConVen = new JButton(ojoAbierto);
	
	pNorte.add(txtModDatos);
	
	txtModDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
	txtModDatos.setBorder(new EmptyBorder(20,20,20,0));
	
	pNombre.add(txtNom);	
	pNombre.add(campoNom);
	pCentro.add(pNombre);
	
	pApellido.add(txtApel);
	pApellido.add(campoApel);
	pCentro.add(pApellido);
	
	pCorreo.add(txtCorreo);		
	pCorreo.add(campoCorreo);
	pCentro.add(pCorreo);
	
	pTelefono.add(txtTelefono);		
	pTelefono.add(campoTelefono);
	pCentro.add(pTelefono);

	pContrasenia.add(txtNewCon);		
	pContrasenia.add(campoCon);
	pContrasenia.add(btnOjoCon);
	pCentro.add(pContrasenia);
	
	pVenificaCon.add(txtVerifCon);		
	pVenificaCon.add(campoVerifCon);
	pVenificaCon.add(btnOjoConVen);
	pCentro.add(pVenificaCon);



	pRespuesta.add(txtRes);	
	pRespuesta.add(campoRes);
	pCentro.add(pRespuesta);

    pBotones.add(btnElimCuen);
    pBotones.add(btnModif);
    pBotones.add(btnVolver);

    pTYC.add(aceptarCond);
    pTYC.add(checkTerminos);
     
    JPanel pBotonesYTYC = new JPanel(new GridLayout(2, 1));
    pBotonesYTYC.add(pBotones);
    pBotonesYTYC.add(pTYC);

    pSur.add(pBotonesYTYC);

    pTYC.add(aceptarCond);
    pTYC.add(checkTerminos);
	
	 
    setLayout(new BorderLayout());
    add(pNorte, BorderLayout.NORTH);
    add(pCentro, BorderLayout.CENTER);
    add(pSur, BorderLayout.SOUTH);
    
    
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
	

    
    
   btnVolver.addActionListener(new ActionListener() {
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
		   SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal(u));
		   dispose();
	   }
   	});
   
   btnModif.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
	    
	    String nombre = campoNom.getText().trim();
	    String apellido = campoApel.getText().trim();
	    String correo = campoCorreo.getText().trim();
	    String telefono = campoTelefono.getText().trim();
	    String contra = campoContrasenia1.getText().trim();
	    String contra2 = campoVenificaCon1.getText().trim();
	    String respuesta = campoRes.getText().trim();

	    if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || 
	        contra.isEmpty() || contra2.isEmpty() || respuesta.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (!checkTerminos.isSelected()) {
	        JOptionPane.showMessageDialog(null, "Debes aceptar los términos y condiciones.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (!correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
	        JOptionPane.showMessageDialog(null, "Ingresa un correo válido.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (!telefono.matches("\\d+")) {
	        JOptionPane.showMessageDialog(null, "El teléfono debe contener sólo números.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }


	    if (!contra.equals(contra2)) {
	        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    Usuario u = BaseDatosConfiguracion.buscarUsuarioPorCorreo(con, correo);
	    if (u == null) {
	        JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese correo.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (!u.getRespuesta().equalsIgnoreCase(respuesta)) {
	        JOptionPane.showMessageDialog(null, "La respuesta de seguridad no es correcta.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int result = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres modificar tus datos?", "Modificar Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	        u.setNombre(nombre);
	        u.setApellido(apellido);
	        u.setTelefono(telefono);
	        u.setContrasenia(contra);
	        
	        BaseDatosConfiguracion.actualizarUsuario(con, u);

            JOptionPane.showMessageDialog(null, "Datos modificados correctamente.", "éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        BaseDatosConfiguracion.closeBD(con);

	    }
	});

	
   btnElimCuen.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String correo = campoCorreo.getText().trim();
	        if (correo.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Por favor, ingresa tu correo.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        int result = JOptionPane.showConfirmDialog(null, "�Seguro que quieres eliminar tu cuenta?", "Eliminar Cuenta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	            Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
	            try {
	                Usuario u = BaseDatosConfiguracion.buscarUsuarioPorCorreo(con, correo);
	                if (u == null) {
	                    JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese correo.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	               
	                BaseDatosConfiguracion.deleteUsuario(con, correo);
	                JOptionPane.showMessageDialog(null, "Cuenta eliminada con éxito.", "Informacién", JOptionPane.INFORMATION_MESSAGE);
	                dispose(); 
	            } finally {
	                BaseDatosConfiguracion.closeBD(con);
	            }
	        }
	    }
	});


	
	
   btnOjoCon.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
            alternaContrasena(pContrasenia, campoCon, campoContrasenia1, btnOjoCon, esOjoAbierto, ojoCerrado, ojoAbierto);
            esOjoAbierto = !esOjoAbierto;
        }
    });
	
	btnOjoConVen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 alternaContrasena(pVenificaCon, campoVerifCon, campoVenificaCon1, btnOjoConVen, esOjoAbiertoVen, ojoCerrado, ojoAbierto);
             esOjoAbiertoVen = !esOjoAbiertoVen;
         }
     });
	
	checkTerminos.addMouseListener(new MouseAdapter() {
			
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
		        		checkTerminos.setSelected(true);
		        	} else {
		        		checkTerminos.setSelected(false);
		        	}
			}
		});
	
	setTitle("Modificar Datos"); 
	setBounds(300, 200, 600, 500); 
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setVisible(true);
	setResizable(false);


	}

	
	private void alternaContrasena(JPanel panel, JPasswordField campo, JTextField campoTexto, JButton boton, boolean esAbierto, ImageIcon cerrado, ImageIcon abierto) {
	    if (!esAbierto) {
	        campoTexto.setText(new String(campo.getPassword()));
	        panel.remove(campo);
	        panel.add(campoTexto);
	    } else {
	        campo.setText(campoTexto.getText());
	        panel.remove(campoTexto);
	        panel.add(campo);
	    }
	    boton.setIcon(esAbierto ? abierto : cerrado);
	    panel.revalidate();
	    panel.repaint();
	}


	private void cargarDatosUsuario(String correoUsuario) {
        Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
        Usuario usuarioActual = BaseDatosConfiguracion.buscarUsuarioPorCorreo(con, correoUsuario);

        if (usuarioActual != null) {
            campoNom.setText(usuarioActual.getNombre());
            campoApel.setText(usuarioActual.getApellido());
            campoCorreo.setText(usuarioActual.getCorreo());
            campoTelefono.setText(usuarioActual.getTelefono());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario con el correo proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }


	
	}
       


