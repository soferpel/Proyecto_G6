package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Usuario;


public class VentanaPantallaPrincipal extends JFrame{
	
	private JLabel imgVerEnvios, imgFacturacion, imgHacerEnvio;
	private static String correoUsuario; 


	  public VentanaPantallaPrincipal(Usuario u) {
		setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel Norte
        JPanel pNorte = new JPanel(new BorderLayout());
        JLabel lblBienvenida = new JLabel("¡BIENVENID@!", SwingConstants.CENTER);
        Font fondo = new Font("Tahoma", Font.BOLD, 30);
        lblBienvenida.setFont(fondo);
        pNorte.add(lblBienvenida, BorderLayout.CENTER);
        

        JPanel pBotonesNorte = new JPanel();
        JButton btnCerrarSesion = new JButton("CERRAR SESIÓN");
        
        ImageIcon imgModif_usu = new ImageIcon("resources/images/modif_usu.png");
        JButton btnModificarDatos = new JButton(imgModif_usu);
        pBotonesNorte.add(btnModificarDatos);
        pBotonesNorte.add(btnCerrarSesion);
        pNorte.add(pBotonesNorte, BorderLayout.EAST);

        pNorte.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(pNorte, BorderLayout.NORTH);

        // Panel Izquierda
        JPanel pIzq = new JPanel(new GridLayout(3, 1, 20, 20));
        pIzq.setBorder(new EmptyBorder(20, 40, 20, 40));	//top, left, bottom, right		//IAG

        // Panel Ver Envios
        JPanel pVerEnvios = new JPanel(new BorderLayout(10, 10));
        pVerEnvios.setBorder(BorderFactory.createTitledBorder("Ver Envios"));							//IAG
        ImageIcon iconVerEnvios = new ImageIcon("resources/images/ver_envios.png");
        Image scaledVerEnvios = iconVerEnvios.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imgVerEnvios = new JLabel(new ImageIcon(scaledVerEnvios));
        JButton btnVerEnvios = new JButton("VER ENVÍOS");
        btnVerEnvios.setPreferredSize(new Dimension(150, 25));
        pVerEnvios.add(imgVerEnvios, BorderLayout.CENTER);
        pVerEnvios.add(btnVerEnvios, BorderLayout.SOUTH);
        pIzq.add(pVerEnvios);

        // Panel FacturaciÃ³n
        JPanel pFacturacion = new JPanel(new BorderLayout(10, 10));
        pFacturacion.setBorder(BorderFactory.createTitledBorder("FacturaciÃ³n"));
        ImageIcon iconFacturacion = new ImageIcon("resources/images/facturacion.png");
        Image scaledFacturacion = iconFacturacion.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imgFacturacion = new JLabel(new ImageIcon(scaledFacturacion));
        JButton btnFacturacion = new JButton("FACTURACIÓN");
        btnFacturacion.setPreferredSize(new Dimension(150, 25));
        pFacturacion.add(imgFacturacion, BorderLayout.CENTER);
        pFacturacion.add(btnFacturacion, BorderLayout.SOUTH);
        pIzq.add(pFacturacion);

        // Panel Hacer Envio
        JPanel pHacerEnvio = new JPanel(new BorderLayout(10, 10));	//IAG
        pHacerEnvio.setBorder(BorderFactory.createTitledBorder("Hacer Envio"));
        ImageIcon iconHacerEnvio = new ImageIcon("resources/images/hacer_envio.jpg");
        Image scaledHacerEnvio = iconHacerEnvio.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imgHacerEnvio = new JLabel(new ImageIcon(scaledHacerEnvio));
        JButton btnHacerEnvio = new JButton("HACER ENVÍO");
        btnHacerEnvio.setPreferredSize(new Dimension(150, 25));
        pHacerEnvio.add(imgHacerEnvio, BorderLayout.CENTER);
        pHacerEnvio.add(btnHacerEnvio, BorderLayout.SOUTH);
        pIzq.add(pHacerEnvio);

        add(pIzq, BorderLayout.WEST);

        // Panel Derecha
        JPanel pDerecha = new JPanel(new BorderLayout(10, 10));
        pDerecha.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel Titulo
        JPanel pTitulo = new JPanel();
        JLabel lblCrearPresupuesto = new JLabel("CREAR PRESUPUESTO:");
        lblCrearPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 16));
        pTitulo.add(lblCrearPresupuesto);
        pTitulo.setBorder(new EmptyBorder(0,70,0,0));
        pDerecha.add(pTitulo, BorderLayout.NORTH);

        // Panel de Medidas
        JPanel pMedidas = new JPanel(new BorderLayout(10, 10));
        pMedidas.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Sub-panel "DESDE" y "HASTA"
        JPanel pDesdeHasta = new JPanel(new GridLayout());
        pDesdeHasta.setBorder(new EmptyBorder(10, 10, 10, 10));

        pDesdeHasta.add(new JLabel("DESDE:", SwingConstants.RIGHT));
        JTextField txtDesde = new JTextField(20);
        txtDesde.setPreferredSize(new Dimension(500, 25));
        pDesdeHasta.add(txtDesde);

        pDesdeHasta.add(new JLabel("HASTA:", SwingConstants.RIGHT));
        JTextField txtHasta = new JTextField(20);
        txtHasta.setPreferredSize(new Dimension(500, 25));
        pDesdeHasta.add(txtHasta);

        pMedidas.add(pDesdeHasta, BorderLayout.NORTH);

        // Sub-panel  "ALTO", "ANCHO", "LARGO" y "PESO"
        JPanel pDimensiones = new JPanel(new GridLayout(2, 2, 10, 10));
        pDimensiones.setBorder(new EmptyBorder(10, 10, 10, 10));

        pDimensiones.add(new JLabel("ALTO:", SwingConstants.RIGHT));
        JTextField txtAlto = new JTextField();
        pDimensiones.add(txtAlto);

        pDimensiones.add(new JLabel("ANCHO:", SwingConstants.RIGHT));
        JTextField txtAncho = new JTextField();
        pDimensiones.add(txtAncho);

        pDimensiones.add(new JLabel("LARGO:", SwingConstants.RIGHT));
        JTextField txtLargo = new JTextField();
        pDimensiones.add(txtLargo);

        pDimensiones.add(new JLabel("PESO:", SwingConstants.RIGHT));
        JTextField txtPeso = new JTextField();
        pDimensiones.add(txtPeso);

        pMedidas.add(pDimensiones, BorderLayout.CENTER);

        pDerecha.add(pMedidas, BorderLayout.CENTER);
        
        //Logo
        ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
		JLabel labelImagenLogo = new JLabel(logo);
		pNorte.add(labelImagenLogo, BorderLayout.WEST);
        

        // BotÃ³n Crear
        JButton btnCrear = new JButton("CREAR");
        JPanel pBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pBoton.add(btnCrear);
        pBoton.setBorder(new EmptyBorder(0,100,0,0));
        pDerecha.add(pBoton, BorderLayout.SOUTH);

        add(pDerecha, BorderLayout.CENTER);

        //EVENTOS
        btnCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaHacerEnvio(u));
				dispose();
			}
		});
        
        btnVerEnvios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaVerEnvios(u));
				dispose();
				
			}
		});

        btnFacturacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> new VentanaFacturacion(u));
				dispose();
				
			}
		});
        
        btnHacerEnvio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> new VentanaHacerEnvio(u));
				dispose();
				
			}
		});
        
        btnModificarDatos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaModificarDatos(u));
				dispose();
			}
		});
        
        btnCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
				int result = JOptionPane.showConfirmDialog(null, "Â¿Seguro que quieres cerrar sesión?", "Cerrar sesión", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					String correo = VentanaPantallaPrincipal.getCorreoUsuario(); 
					BaseDatosConfiguracion.deleteUsuario(con, correo);
			        VentanaPantallaPrincipal.this.dispose();
				}
				//BORRAR LOS DATOS
			}
		});
        
        getRootPane().setDefaultButton(btnCrear);
        
        setVisible(true);
		setResizable(false);

	  }
	 
	 public static void setCorreoUsuario(String correo) {
	        correoUsuario = correo; 
	 }

     public static String getCorreoUsuario() {
        return correoUsuario; 
     }
	
}
