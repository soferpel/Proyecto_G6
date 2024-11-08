package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class VentanaFacturacion extends JFrame {

	
    public VentanaFacturacion() {


        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // btnVolver
        ImageIcon imageVolverO = new ImageIcon(getClass().getResource("/Images/volver.png"));
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
        ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logoPngNegro.png"));
		JLabel labelImagenLogo = new JLabel(logo);
        panelSuperior.add(labelImagenLogo, BorderLayout.EAST);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
                JPanel panelIzquierdo = new JPanel(new GridLayout(3, 2, 10, 10));
        
        //referencia
        JLabel lblReferencia = new JLabel("Número de referencia:");
        JTextField txtReferencia = new JTextField(15);
        panelIzquierdo.add(lblReferencia);
        panelIzquierdo.add(txtReferencia);

        //precio
        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField(15);
        panelIzquierdo.add(lblPrecio);
        panelIzquierdo.add(txtPrecio);

        //¿pagado?
        JLabel lblPagado = new JLabel("¿Pagado?");
        JPanel panelPagado = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JRadioButton rbSi = new JRadioButton("Sí");
        JRadioButton rbNo = new JRadioButton("No");
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
        JTextField txtDescripcion = new JTextField(15);
        panelDerecho.add(lblDescripcion);
        panelDerecho.add(txtDescripcion);

        //fecha de envío
        JLabel lblFechaEnvio = new JLabel("Fecha Envío:");
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(150, 25));
        panelDerecho.add(lblFechaEnvio);
        panelDerecho.add(dateChooser);

        panelCentral.add(panelDerecho);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        //exportar
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnExportarPDF = new JButton("EXPORTAR");
        btnExportarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Factura exportada en PDF");
            }
        });
        
        
        
        btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
				dispose();
			}
		});
        
        panelBoton.add(btnExportarPDF);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        add(panelPrincipal);
        
        
        
        setTitle("Facturación");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		setResizable(false);

    }

}
