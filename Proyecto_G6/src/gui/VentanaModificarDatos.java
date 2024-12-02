package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaModificarDatos extends JFrame{
	
		
private JLabel txtModDatos, txtCorreo, txtTelefono, txtNewCon, txtVerifCon, txtNom,
				txtApel, txtPregSeg, txtRes, aceptarCond;

private JTextField campoCorreo, campoTelefono, campoRes, campoNom, campoApel, 
					campoContrasenia1, campoVenificaCon1, campoPregSeg;

private JPasswordField campoCon, campoVerifCon;

private JButton btnElimCuen, btnModif, btnVolver, btnOjoCon, btnOjoConVen, mostrarContra;

private JPanel pNorte, pSur, pCentro, pVenificaCon, pContrasenia, pNombre,
				pApellido, pRespuesta, pTelefono, pCorreo, 
				pPregSeg, pTYC, pBotones;

private JCheckBox checkTerminos;

    
private boolean esOjoAbierto = false;
private boolean esOjoAbiertoVen = false;

private String contrasenia;
private String contraseniaVen;
private Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 20);



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
	pBotones = new JPanel(); 
    pTYC = new JPanel(); 
	
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
	
	txtModDatos.setFont(fontTextoTitulo);
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
       
   btnVolver.addActionListener(new ActionListener() {
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
		   SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
		   dispose();
	   }
   	});
   
   btnModif.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int result = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres modificar tus datos?", "Modificar Datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(result == JOptionPane.OK_OPTION) {
			SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
			dispose();
		}
	}
   });
   
   btnElimCuen.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar tu cuenta?", "Eliminar Cuenta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
	            dispose();
	        }
	    }
	});

	
	
   btnOjoCon.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!esOjoAbierto) {
				String contrasenia = new String(campoCon.getPassword());
				campoContrasenia1.setText(contrasenia);
				pContrasenia.remove(campoCon);
				pContrasenia.add(campoContrasenia1);
				pContrasenia.revalidate();
				pContrasenia.repaint();
				btnOjoCon.setIcon(ojoCerrado);
				pContrasenia.remove(btnOjoCon);
				pContrasenia.add(btnOjoCon);
			}
			else {
				String contrasenia = new String(campoContrasenia1.getText());
				campoCon.setText(contrasenia);
				pContrasenia.remove(campoContrasenia1);
				pContrasenia.add(campoCon);
				pContrasenia.revalidate();
				pContrasenia.repaint();
				btnOjoCon.setIcon(ojoAbierto);
				pContrasenia.remove(btnOjoCon);
				pContrasenia.add(btnOjoCon);
			}
			esOjoAbierto = !esOjoAbierto;
		}
	});
	
	btnOjoConVen.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!esOjoAbiertoVen) {
				String contrasenia = new String(campoVerifCon.getPassword());
				campoVenificaCon1.setText(contrasenia);
				pVenificaCon.remove(campoVerifCon);
				pVenificaCon.add(campoVenificaCon1);
				pVenificaCon.revalidate();
				pVenificaCon.repaint();
				btnOjoConVen.setIcon(ojoCerrado1);
				pVenificaCon.remove(btnOjoConVen);
				pVenificaCon.add(btnOjoConVen);
			}
			else {
				String contrasenia = new String(campoVenificaCon1.getText());
				campoVerifCon.setText(contrasenia);
				pVenificaCon.remove(campoVenificaCon1);
				pVenificaCon.add(campoVerifCon);
				pVenificaCon.revalidate();
				pVenificaCon.repaint();
				btnOjoConVen.setIcon(ojoAbierto1);
				pVenificaCon.remove(btnOjoConVen);
				pVenificaCon.add(btnOjoConVen);
			}
			esOjoAbierto = !esOjoAbierto;
		}
	});
	

	setTitle("Modificar Datos"); 
	setBounds(300, 200, 600, 500); 
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setVisible(true);
	setResizable(false);


	}
       

}
		
