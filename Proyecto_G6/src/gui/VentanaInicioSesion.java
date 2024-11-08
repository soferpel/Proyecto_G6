package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class VentanaInicioSesion extends JFrame{
	
	private JButton bRegistrarse, bIniSesion, mostrarContra;
	private JLabel lblCorreo, lblContra;
	private JTextField txtCorreo, txtContra;

	public VentanaInicioSesion(){
		setResizable(false);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,  778, 350);
		setLayout(new BorderLayout());
		setVisible(true);
		
		JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(100, 50, 600, 300); // Ajustar el tamaño y la posición
        add(panelDatos);
        
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(null);
        add(panelNorte, BorderLayout.NORTH);

        
        JLabel lblTitulo = new JLabel("INICIO SESIÓN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD,30));
        lblTitulo.setBounds(0, 10, 778, 40); // Centrado en la parte superior de la ventana
        add(lblTitulo);
        add(panelNorte);

        
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 30, 100, 25);
        panelDatos.add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 30, 250, 25);
        panelDatos.add(txtCorreo);

        JComboBox<String> dominioEmail = new JComboBox<>(new String[]{"@admin.es", "@gmail.com", "@hotmail.com"});
        dominioEmail.setBounds(410, 30, 120, 25);
        panelDatos.add(dominioEmail);

        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setBounds(50, 80, 100, 25);
        panelDatos.add(lblContra);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(150, 80, 250, 25);
        panelDatos.add(txtContra);

        mostrarContra = new JButton();
        ImageIcon ojoVer = new ImageIcon(getClass().getResource("/Images/ojoVer.jpeg"));
        ImageIcon ojoOcultar = new ImageIcon(getClass().getResource("/Images/ojoOcultar.jpg"));
        Image scaledOjoVer = ojoVer.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        Image scaledOjoOcultar = ojoOcultar.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        mostrarContra.setIcon(new ImageIcon(scaledOjoVer));
        mostrarContra.setBounds(410, 80, 50, 25);
        panelDatos.add(mostrarContra);

        mostrarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtContra.getEchoChar() == '*') {
                    txtContra.setEchoChar((char) 0); 
                    mostrarContra.setIcon(new ImageIcon(scaledOjoOcultar)); 
                } else {
                    txtContra.setEchoChar('*');
                    mostrarContra.setIcon(new ImageIcon(scaledOjoVer)); 
                }
            }
        });

        JLabel olvideContra = new JLabel("<HTML><U>¿Has olvidado tu contraseña?</U></HTML>");
        olvideContra.setForeground(Color.BLUE);
        olvideContra.setBounds(150, 120, 200, 25);
        panelDatos.add(olvideContra);

        bRegistrarse = new JButton("REGISTRARSE");
        bRegistrarse.setBounds(150, 160, 150, 30);
        
        //LOGO
        //logo
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logoPngNegro.png"));
		JLabel labelImagenLogo = new JLabel(logo);
        panelNorte.add(labelImagenLogo, BorderLayout.EAST);
        
        //panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        
        //EVENTOS
        panelDatos.add(bRegistrarse);

        bIniSesion = new JButton("INICIAR SESIÓN");
        bIniSesion.setBounds(320, 160, 150, 30);

        panelDatos.add(bIniSesion);
		

		setVisible(true);
		

		bRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaRegistro());
				dispose();			
			}
		});
		
		bIniSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
				dispose();			
			}
		});
		
	}
	
}

