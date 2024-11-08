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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class VentanaRegistro extends JFrame{
	
	private JPanel pCentro,pSur,pCentroIzquierda,pCentroDerecha,pNorte;
	private JLabel lblNom,lblApe, lblCorreo,lblTel,lblContra,lblRepeContra,lblSegu,lblResp;
	private JTextField txtNom,txtApe,txttel,txtCorreo,txtContra,txtRepeContra,txtResp, txtSegu;
	private JButton btnRegistro,btnMostrarContra,btnMostrarRepeContra,btnFlecha;
	
	public VentanaRegistro () {
		setTitle("Formulario de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setBounds(100, 100,  778, 455);
        setVisible(true);
		setResizable(false);

        
       // pNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pNorte = new JPanel(new BorderLayout());
        pNorte.setBackground(Color.WHITE);
        
        pCentro = new JPanel(new GridLayout(4,4));
        pCentro.setBackground(Color.WHITE);
        pSur = new JPanel();
        pSur.setLayout(new BoxLayout(pSur, BoxLayout.Y_AXIS));
        pSur.setBackground(Color.WHITE);
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
		txtContra = new JTextField();
		txtContra.setBounds(150, 30, 250, 25);
		txtRepeContra = new JTextField();
		txtRepeContra.setBounds(150, 30, 250, 25);
		txtResp = new JTextField();
		txtResp.setBounds(150, 30, 250, 25);
		txtSegu = new JTextField();
		txtSegu.setBounds(150, 30, 250, 25);
		
		String[] preguntas = {"¿Dia de nacimiento?", "¿Ciudad de nacimiento?", "¿Nombre del primer colegio?"};
        JComboBox<String> comboPreguntas = new JComboBox<>(preguntas);
        txtSegu.add(comboPreguntas);
        comboPreguntas.setBackground(Color.WHITE);
        
     // Flecha
        ImageIcon iconoFlecha = new ImageIcon(getClass().getResource( "/images/flecha.jpg"));
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
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24)); 
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
        pCentro.add(lblContra);
        pCentro.add(txtContra);
        pCentro.add(lblRepeContra);
        pCentro.add(txtRepeContra);
        pCentro.add(lblSegu);
        pCentro.add(comboPreguntas);
        pCentro.add(lblResp);
        pCentro.add(txtResp);
        
        Dimension dimension = new Dimension(80, 20); // Ajustar tamaño deseado
        lblNom.setPreferredSize(dimension);
        lblApe.setPreferredSize(dimension);
        lblCorreo.setPreferredSize(dimension);
        lblTel.setPreferredSize(dimension);
        lblContra.setPreferredSize(dimension);
        lblRepeContra.setPreferredSize(dimension);
        lblSegu.setPreferredSize(dimension);
        lblResp.setPreferredSize(dimension);
        
        txtNom.setPreferredSize(new Dimension(80, 20));
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
		pSur.setBackground(Color.WHITE);
		//btnRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        pSur.setLayout(new BoxLayout(pSur, BoxLayout.Y_AXIS));
		
        setVisible(true);

        //EVENTOS
        
        btnFlecha.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
    			dispose();			

        	}
        });
        
	}
	

}

