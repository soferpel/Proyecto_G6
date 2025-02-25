package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


import db.BaseDatosConfiguracion;
import domain.Usuario;

import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame{
	
	private JButton bRegistrarse, bIniSesion, mostrarContra;
	@SuppressWarnings("unused")
	private JLabel lblCorreo, lblContra;
	@SuppressWarnings("unused")
	private JTextField txtCorreo, txtContra;

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation", "unused" })
	public VentanaInicioSesion(){
		setResizable(false);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,  778, 350);
		setLayout(new BorderLayout());
		
		JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBounds(100, 50, 550, 300); // Ajustar el tamaño y la posición
        add(panelDatos);
        
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(null);	
        add(panelNorte, BorderLayout.NORTH);
        
        
        ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
		JLabel labelImagenLogo = new JLabel(logo);
		panelNorte.add(labelImagenLogo, BorderLayout.NORTH);
		labelImagenLogo.setBorder(new EmptyBorder(20,680,0,20));
        
        JLabel lblTitulo = new JLabel("INICIO SESIÓN", SwingConstants.CENTER);
        Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 30);
        lblTitulo.setFont(fontTextoTitulo);
        lblTitulo.setBounds(0, 10, 778, 40); //Centrado
        add(lblTitulo);
        add(panelNorte);
      
        
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(60, 30, 100, 25);
        panelDatos.add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(160, 30, 250, 25);
        panelDatos.add(txtCorreo);

        JComboBox<String> dominioEmail = new JComboBox<>(new String[]{"@gmail.com","@admin.es", "@hotmail.com", "@outlook.com", "@aol.com", "@icloud.com", "@yahoo.com"});
        dominioEmail.setBounds(420, 30, 120, 25);
        panelDatos.add(dominioEmail);
        
        //IAG
        ListCellRenderer renderCombo = new DefaultListCellRenderer() {
        	@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        	 if (value != null) {
                 String dominio = value.toString();
                 switch (dominio) {
	                 case "@gmail.com":
	                	 label.setBackground(Color.cyan);
	                	 break;
                     case "@admin.es":
                         label.setBackground(Color.GREEN);
                         break;
                     case "@hotmail.com":
                         label.setBackground(Color.yellow);
                         break;
                     case "@outlook.com":
                         label.setBackground(Color.lightGray);
                         break;
                     case "@aol.com":
                         label.setBackground(Color.magenta);
                         break;
                     case "@icloud.com":
                         label.setBackground(Color.pink);
                         break;
                     default:
                         label.setBackground(Color.white);
                         break;
                 
                 }
        	 }
        	 label.setOpaque(true);
			return label;
        	}
        };
		
		dominioEmail.setRenderer(renderCombo);

        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setBounds(60, 80, 100, 25);
        panelDatos.add(lblContra);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(160, 80, 250, 25);
        panelDatos.add(txtContra);

        
        
        mostrarContra = new JButton();
        ImageIcon ojoVer = new ImageIcon("resources/images/ojoAbierto.png");
        ImageIcon ojoOcultar = new ImageIcon("resources/images/ojoCerrado.png");

        mostrarContra.setIcon(ojoVer);
        mostrarContra.setBounds(420, 80, 50, 25);
        panelDatos.add(mostrarContra);

        //FUENTE EXTERNA
        mostrarContra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtContra.getEchoChar() == '*') {
                    txtContra.setEchoChar((char) 0); 
                    mostrarContra.setIcon(ojoOcultar); 
                } else {
                    txtContra.setEchoChar('*');
                    mostrarContra.setIcon(ojoVer); 
                }
            }
        });

        //IAGS
        JLabel olvideContra = new JLabel("<HTML><U>¿Has olvidado tu contraseña?</U></HTML>");
        olvideContra.setForeground(Color.BLUE);
        olvideContra.setBounds(200, 120, 210, 25);
        panelDatos.add(olvideContra);
        
        olvideContra.addMouseListener(new MouseAdapter() {
           // @Override
//            public void mouseClicked(MouseEvent e) {
//            	
//                new VentanaModificarDatos(u).setVisible(true);
//                dispose();
//            }

            @Override		//MANO
            public void mouseEntered(MouseEvent e) {
                
                olvideContra.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        JPanel pBotones = new JPanel();
        bRegistrarse = new JButton("REGISTRARSE");
        bRegistrarse.setBounds(130, 160, 150, 30);

        bIniSesion = new JButton("INICIAR SESIÓN");
        bIniSesion.setBounds(300, 160, 150, 30);



        panelDatos.add(bRegistrarse);


        panelDatos.add(bIniSesion);

        panelDatos.add(pBotones);
		
        setVisible(true);
		

		//EVENTOS
		bRegistrarse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaRegistro());
				dispose();			
			}
		});	
		
		
		bIniSesion.addActionListener((e)->{
			
			String correo = txtCorreo.getText() + dominioEmail.getSelectedItem().toString();
			correo.toLowerCase();
            String contrasenia = txtContra.getText();
            
            Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
			Usuario u = BaseDatosConfiguracion.buscarUsuarioPorCorreo(con, correo);
			BaseDatosConfiguracion.closeBD(con);
			
			 if (u != null) {
			        if (correo.endsWith("@admin.es")) {
			        	List<Usuario> usuarios = BaseDatosConfiguracion.obtenerUsuarios(con);
			            VentanaAdministracion ventanaAdmin = new VentanaAdministracion(usuarios);
			            ventanaAdmin.setVisible(true);
			            this.dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Bienvenido!", "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
			            SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal(u));
			            dispose();
			        }
			}else {
				JOptionPane.showMessageDialog(null, "Para poder iniciar sesión tienes que estar registrado","ERROR",JOptionPane.ERROR_MESSAGE);
				txtCorreo.setText("");
				txtContra.setText("");
				System.out.println("error");
			}
			
		});
		
	}

	
}
