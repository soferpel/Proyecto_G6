package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaModificarDatos extends JPanel{
	
		
		private JLabel txtModDatos, txtCorreo, txtTelefono, txtNewCon, txtVerifCon, txtNom,
						txtApel, txtPregSeg, txtRes, aceptarCond;
		
		private JTextField campoCorreo, campoTelefono, campoRes, campoNom, campoApel, 
							campoContrasenia1, campoVenificaCon1, campoPregSeg;
		
		private JPasswordField campoCon, campoVerifCon;
		
		private JButton btnElimCuen, btnModif, btnVolver, btnOjoCon, btnOjoConVen;
		
		private JPanel pNorte, pSur, pCentro, pVenificaCon, pContrasenia, pNombre,
						pApellido, pRespuesta, pTelefono, pCorreo, 
						pPregSeg, pTYC, pBotones;
		
		private JCheckBox checkTerminos;

	        
	    private boolean esOjoAbierto = false;
	    private boolean esOjoAbiertoVen = false;
		
		private String contrasenia;
		private String contraseniaVen;
		
		
		
		public VentanaModificarDatos() {

			
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
			pPregSeg = new JPanel();
			pBotones = new JPanel(); // Panel para los botones
	        pTYC = new JPanel(); // Panel para términos y condiciones
			
			txtNom = new JLabel("    Nombre:");
			txtApel = new JLabel("     Apellido:");
			txtModDatos = new JLabel("MODIFICAR DATOS:");
			txtCorreo = new JLabel("      Correo:");
			txtNewCon = new JLabel("Nueva Contraseña:");
			txtPregSeg = new JLabel("Pregunta de seguridad:");
			txtRes = new JLabel("Respuesta:");
			txtTelefono = new JLabel("    Telefono:");
			txtVerifCon = new JLabel("  Repite contraseña:");
			aceptarCond = new JLabel("<html><u>Aceptas terminos y condiciones de uso</u></html>");
			
			checkTerminos = new JCheckBox();
			checkTerminos.setEnabled(true);
			
			ImageIcon ojoAbierto = new ImageIcon(getClass().getResource("/Images/ojoAbierto.png"));
			ImageIcon ojoCerrado = new ImageIcon(getClass().getResource("/Images/ojoCerrado.png"));
			ImageIcon ojoAbierto1 = new ImageIcon(getClass().getResource("/Images/ojoAbierto.png"));
			ImageIcon ojoCerrado1 = new ImageIcon(getClass().getResource("/Images/ojoCerrado.png"));
			
			campoNom = new JTextField(10);
			campoApel = new JTextField(10);
			campoCorreo = new JTextField(10);
			campoTelefono = new JTextField(10);
			campoRes = new JTextField(10);
			campoContrasenia1 = new JTextField(10);
			campoVenificaCon1 = new JTextField(10);
			campoPregSeg = new JTextField(10);
			campoPregSeg.setEditable(false);;
			
			campoCon = new JPasswordField(10);
			campoVerifCon = new JPasswordField(10);
			
			
			btnModif = new JButton("MODIFICAR");
			btnElimCuen = new JButton("ELIMINAR CUENTA");
			btnVolver = new JButton("VOLVER");
			btnOjoCon = new JButton(ojoAbierto);
			btnOjoConVen = new JButton(ojoAbierto1);
			
			pNorte.add(txtModDatos);
			
			
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

			pPregSeg.add(txtPregSeg);		
			pPregSeg.add(campoPregSeg);
			pCentro.add(pPregSeg);

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
		    }
		
		
		public void visualizar() {
		        JFrame frame = new JFrame(); 
		        frame.setTitle("Modificar Datos"); 
		        frame.setBounds(300, 200, 600, 400); 
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setLocationRelativeTo(null);
		        frame.add(this); 
		        frame.setVisible(true);
		    }
		 

		public static void main(String[] args) {
			VentanaModificarDatos ventana = new VentanaModificarDatos();
			ventana.visualizar(); 
			    }
			}


