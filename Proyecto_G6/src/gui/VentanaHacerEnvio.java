package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;


public class VentanaHacerEnvio extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabEnvios;
	private JButton btnVolver, btnAnterior, btnSiguiente, btnFinalizar;
	private JLabel txtCrearEnvio;
	private JPanel  pNorte, pNorte2, pNorte3,pSur,
					pOeste, pEste, pBtnAnterior, pBtnSiguiente;
	private Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 20);
	private double precioBase, precioFinal;
	private int indiceActual = 0;
	
	
	//DONDE
	
	private JPanel pDonde;
	
	private JTextField dNombre, dDireccion, dCorreo, dTelefono;
	
	private JTextField hNombre, hDireccion, hCorreo, hTelefono;
	
	
	//QUE
	
	private JPanel pQue, pAltLarAncPes, pDescr, pEmbalaje, pValor, pNQue, pCQue, pAlto, pAncho, pPeso, pLargo;	
	
	private JLabel txtEmbalado, txtDescripcion, txtLargo, txtAncho, txtAlto, txtPeso, txtValor, txtInfo;

	private JTextField campoDescripcion, campoLargo, campoAncho, campoAlto, campoPeso, campoValor;

	private JCheckBox checkFragil;
	
	private JComboBox<String> comboEmbalaje;
	
	
	//COMO 
	
	private JPanel pComo, pTxtRecog, pFRecog, pEntrega, pRecYEnt, pRecog2;
	
	private JLabel txtFEnvio, txtRecog, txtPrecioEnvio; 
	
	private JDateChooser dateChooser;
	
	private JRadioButton radPtoRecog, radDomic, radEstandar, radSuper, radPremium;
	
	private JComboBox<String> comboRecog;
	
	private ButtonGroup tipoEnvioGrupo, recogidaGrupo;
	
	
	//PAGO 
	
	private JPanel pPago, pNTarjeta, pFCad, pCVV, pDNI, pFactura, pMetodos, pTarjeta;
	
	private JLabel txtNTarjeta, txtFCad, txtCVV, txtDNI;
	
	private JTextField campoNTarjeta, campoCVV, campoDNI;
	
	private JRadioButton radTarjeta, radContra;
	
	private ButtonGroup pagoGrupo;
	
	private JCheckBox checkFactura;
	
	private JDateChooser datechooserTarj;
	
	//REVISION
	
	private JPanel pRev, pRevEnvio, pMedidas, pTYC, pText, pDesde, pHasta, pTipo, pPagos, pRevPeso, pRevAlto, pRevLargo, pRevAncho;

	private JLabel txtEnDesde, txtEnHasta, txtPago, txtEnvios, txtRevLargo, txtRevAncho, txtRevAlto, txtRevPeso, aceptarCond;
	
	private JTextField campoEnDesde, campoEnHasta, campoPago , campoEnvios, campoRevLargo, campoRevAncho, campoRevAlto, campoRevPeso;

	private JCheckBox checkTerminos;
	
	
	public VentanaHacerEnvio() {
		

		
	ImageIcon logo = new ImageIcon(getClass().getResource("/Images/logoPngNegro.png"));
	JLabel labelImagenLogo = new JLabel(logo);
	labelImagenLogo.setPreferredSize(new Dimension(350, logo.getIconHeight()));
	
	labelImagenLogo.setBorder(new EmptyBorder(0,250,0,0));
	
	tabEnvios = new JTabbedPane();
	
	
	ImageIcon imgAnterior = new ImageIcon(getClass().getResource("/Images/flecha_ant.png"));
    btnAnterior = new JButton(imgAnterior);
    ImageIcon imgSiguiente = new ImageIcon(getClass().getResource("/Images/flecha_sig.png"));
    btnSiguiente = new JButton(imgSiguiente);
	btnFinalizar = new JButton("FINALIZAR");
	btnFinalizar.setEnabled(false);
	
    btnAnterior.setEnabled(false);
    
    
    txtCrearEnvio = new JLabel("CREAR ENVÍO:");
	txtCrearEnvio.setFont(fontTextoTitulo);
	
	
	ImageIcon imgVolver = new ImageIcon(getClass().getResource("/Images/volver.png"));
	btnVolver = new JButton(imgVolver);
	
	pQue = new JPanel(new GridLayout(4, 1));
	pComo = new JPanel();
	pPago = new JPanel();
	pRev = new JPanel(); 
	
	pNorte = new JPanel(new GridLayout(1,2));
	pNorte2 = new JPanel();
	pNorte3 = new JPanel();

	pOeste = new JPanel();
	pEste = new JPanel();
	pBtnAnterior = new JPanel();
	pBtnSiguiente = new JPanel();
	pSur = new JPanel();
	
	pNorte2.setBorder(new EmptyBorder(10,0,0,100));
	txtCrearEnvio.setBorder(new EmptyBorder(5,50,0,0));
	
//TAB DONDE
	
	pDonde = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	pDonde.setBorder(new EmptyBorder(20, 40, 20, 40));
	pDesde = new JPanel(new GridLayout(4, 1));
	pHasta = new JPanel(new GridLayout(4, 1));
	pDesde.setBorder(new EmptyBorder(10, 10, 10, 10));
	pHasta.setBorder(new EmptyBorder(10, 10, 10, 10));

	
	JLabel lblDesde = new JLabel("DESDE:");
	JLabel lblDesdeNombre = new JLabel("Nombre:");
    dNombre = new JTextField(10);
    JLabel lblDesdeDireccion = new JLabel("Dirección:");
    dDireccion = new JTextField(10);
    JLabel lblDesdeCorreo = new JLabel("Correo:");
    dCorreo = new JTextField(10);
    JLabel lblDesdeTelefono = new JLabel("Teléfono:");
    dTelefono = new JTextField(10);
    
    pDesde = new JPanel(new GridLayout(5, 2, 10, 10)); 
    pDesde.setBorder(new EmptyBorder(10, 20, 10, 20));
    pDesde.add(lblDesde); 
    pDesde.add(new JLabel());
    pDesde.add(lblDesdeNombre);
    pDesde.add(dNombre);
    pDesde.add(lblDesdeDireccion);
    pDesde.add(dDireccion);
    pDesde.add(lblDesdeCorreo);
    pDesde.add(dCorreo);
    pDesde.add(lblDesdeTelefono);
    pDesde.add(dTelefono);

    JLabel lblHasta = new JLabel("HASTA:");
    JLabel lblHastaNombre = new JLabel("Nombre:");
    hNombre = new JTextField(10);
    JLabel lblHastaDireccion = new JLabel("Dirección:");
    hDireccion = new JTextField(10);
    JLabel lblHastaCorreo = new JLabel("Correo:");
    hCorreo = new JTextField(10);
    JLabel lblHastaTelefono = new JLabel("Teléfono:");
    hTelefono = new JTextField(10);

    pHasta = new JPanel(new GridLayout(5, 2, 10, 10));
    pHasta.setBorder(new EmptyBorder(10, 20, 10, 20));
    pHasta.add(lblHasta); 
    pHasta.add(new JLabel()); 
    pHasta.add(lblHastaNombre);
    pHasta.add(hNombre);
    pHasta.add(lblHastaDireccion);
    pHasta.add(hDireccion);
    pHasta.add(lblHastaCorreo);
    pHasta.add(hCorreo);
    pHasta.add(lblHastaTelefono);
    pHasta.add(hTelefono);

    pDonde.add(pDesde, BorderLayout.WEST);  
    pDonde.add(pHasta, BorderLayout.EAST);  

    tabEnvios.addTab("DONDE", pDonde);
    
	    
	    
//TAB QUE
	
	txtEmbalado = new JLabel("Embalaje: ");
	txtDescripcion = new JLabel("Descripcion: ");
	txtLargo = new JLabel("Largo: ");
	txtAncho = new JLabel("Ancho: ");
	txtAlto = new JLabel("Alto: ");
	txtPeso = new JLabel("Peso: ");
	txtValor = new JLabel("Valor del paquete: ");
	txtInfo = new JLabel("El nº de referencia se asigna automáticamente.");
	
	
	campoLargo = new JTextField(10);
	campoAncho = new JTextField(10);
	campoAlto = new JTextField(10);
	campoValor = new JTextField(10);
	campoPeso = new JTextField(10);
	campoDescripcion = new JTextField(10);
	
	checkFragil = new JCheckBox("¿Frágil?");
	
	comboEmbalaje = new JComboBox<String>();
	comboEmbalaje.addItem("Necesita embalaje");
	comboEmbalaje.addItem("No necesita embalaje");
	
	
	pEmbalaje = new JPanel();
	pAltLarAncPes = new JPanel(new GridLayout(2,2));
	pDescr = new JPanel();
	pValor = new JPanel();
	pAlto = new JPanel();
	pAncho = new JPanel();
	pPeso = new JPanel();
	pLargo = new JPanel();
	pNQue = new JPanel();
	pCQue = new JPanel();
	
	pEmbalaje.add(txtEmbalado);
	pEmbalaje.add(comboEmbalaje);
	
	pAlto.add(txtAlto);
	pAlto.add(campoAlto);
	
	pAncho.add(txtAncho);
	pAncho.add(campoAncho);
	
	pLargo.add(txtLargo);
	pLargo.add(campoLargo);
	
	pPeso.add(txtPeso);
	pPeso.add(campoPeso);
	
	pAltLarAncPes.add(pAlto);
	pAltLarAncPes.add(pAncho);
	pAltLarAncPes.add(pLargo);
	pAltLarAncPes.add(pPeso);

	pValor.add(txtValor);
	pValor.add(campoValor);
	
	pDescr.add(txtDescripcion);
	pDescr.add(campoDescripcion);
	
	
	pNQue.add(pEmbalaje);
	pNQue.add(pDescr);
	pCQue.add(pValor, BorderLayout.WEST);
	pCQue.add(checkFragil, BorderLayout.EAST);
	pQue.add(pNQue);
	pQue.add(pAltLarAncPes);
	pQue.add(pCQue);
	pQue.add(txtInfo);
	
	add(pQue);

/*
	pEmbalaje.setBackground(Color.BLUE);
	pQue.setBackground(Color.YELLOW);
	pAlto.setBackground(Color.PINK);
	pDescr.setBackground(Color.GREEN);
	pValor.setBackground(Color.RED);
	pNQue.setBackground(Color.CYAN);
	pCQue.setBackground(Color.MAGENTA);
*/
	
	pQue.setBorder(new EmptyBorder(10, 50, 10, 50));
	
	pCQue.setBorder(new EmptyBorder(10,0,0,50));
	pNQue.setBorder(new EmptyBorder(10,0,0,0));
	
	pEmbalaje.setBorder(new EmptyBorder(0,0,0,70));
	pDescr.setBorder(new EmptyBorder(0,0,0,30));
	
	pAlto.setBorder(new EmptyBorder(0,30,0,0));
	pAncho.setBorder(new EmptyBorder(0,0,0,40));
	pLargo.setBorder(new EmptyBorder(0,20,0,0));
	pPeso.setBorder(new EmptyBorder(0,0,0,30));
	
	pValor.setBorder(new EmptyBorder(0,0,0,70));
	
	txtInfo.setBorder(new EmptyBorder(0, 210, 0, 0));
	
	

	
	
//TAB COMO
	
	txtRecog = new JLabel("Recogida:");
	txtFEnvio = new JLabel("Fecha de recogida:");
	txtPrecioEnvio = new JLabel("0.0");


	radPtoRecog = new JRadioButton("Punto de recogida");
	radDomic = new JRadioButton("A domicilio");
	
	radEstandar = new JRadioButton("Estandar\n (En 8/12 dias)");
	radSuper = new JRadioButton("Superior\n (En 6/10 dias)");
	radPremium = new JRadioButton("Premium\n (En 2 dias)");
	
	comboRecog = new JComboBox<String>();
	
	tipoEnvioGrupo = new ButtonGroup();
	recogidaGrupo = new ButtonGroup();
	
	pTxtRecog = new JPanel();
	pFRecog = new JPanel();
	pRecYEnt = new JPanel(new GridLayout(1,2, 100, 50));
	pEntrega = new JPanel(new GridLayout(4,1,0,10));
	pRecog2 = new JPanel(new GridLayout(4,1,0,10));
	
	
	dateChooser = new JDateChooser();
	dateChooser.setDateFormatString("dd/MM/yyyy");
	
	recogidaGrupo.add(radPtoRecog);
	recogidaGrupo.add(radDomic);
	
	tipoEnvioGrupo.add(radEstandar);
	tipoEnvioGrupo.add(radPremium);
	tipoEnvioGrupo.add(radSuper);
	
	pTxtRecog.add(txtRecog);
	
	pFRecog.add(txtFEnvio);
	pFRecog.add(dateChooser);
	
	pEntrega.add(radEstandar);
	pEntrega.add(radSuper);
	pEntrega.add(radPremium);
	pEntrega.add(txtPrecioEnvio);
	
	pRecog2.add(radDomic);
	pRecog2.add(pFRecog);
	pRecog2.add(radPtoRecog);
	pRecog2.add(comboRecog);

	
	pRecYEnt.add(pRecog2);
	pRecYEnt.add(pEntrega);
	
	pComo.add(pTxtRecog);
	pComo.add(pRecYEnt);
	
	add(pComo);
	
	
	pFRecog.setBorder(new EmptyBorder(0,40,0,0));
	comboRecog.setBorder(new EmptyBorder(0,40,0,0));
	
	pTxtRecog.setBorder(new EmptyBorder(0,0,150,0));
	pRecYEnt.setBorder(new EmptyBorder(50,0,0,50));
	
	txtPrecioEnvio.setBorder(new EmptyBorder(0,120,0,0));
	
	
    txtRecog.setBorder(new EmptyBorder(10,0,0,0));

    comboRecog.setEnabled(false);
    dateChooser.setEnabled(false);
    
    
    
    
//TAB PAGO
    
    txtNTarjeta = new JLabel("Nº Tarjeta:");
	txtFCad = new JLabel("Fecha Caducidad:");
	txtCVV = new JLabel("CVV:");
	txtDNI = new JLabel("DNI:");
		
	campoNTarjeta = new JTextField(16);
	campoCVV = new JTextField(5);
	campoDNI = new JTextField(5);

	checkFactura = new JCheckBox("¿Factura?");
	
	radTarjeta = new JRadioButton("Tarjeta");
	radContra = new JRadioButton("Contrareembolso");
	
	pagoGrupo = new ButtonGroup();	
	
	pagoGrupo.add(radTarjeta);
	pagoGrupo.add(radContra);
	
	datechooserTarj = new JDateChooser();
	datechooserTarj.setDateFormatString("dd/MM/yyyy");
	
	pNTarjeta = new JPanel();
	pFCad = new JPanel();
	pCVV = new JPanel();
	pDNI = new JPanel();
	pTarjeta = new JPanel();
	pFactura = new JPanel();
	pMetodos = new JPanel(new GridLayout(3,1));

	pNTarjeta.add(txtNTarjeta);
	pNTarjeta.add(campoNTarjeta);
	
	pFCad.add(txtFCad);
	pFCad.add(datechooserTarj);
	
	pCVV.add(txtCVV);
	pCVV.add(campoCVV);
	
	pTarjeta.add(pNTarjeta);
	pTarjeta.add(pFCad);
	pTarjeta.add(pCVV);
	
	pMetodos.add(radTarjeta);
	pMetodos.add(pTarjeta);
	pMetodos.add(radContra);
	
	pDNI.add(txtDNI);
	pDNI.add(campoDNI);
	
	pFactura.add(checkFactura);
	pFactura.add(pDNI);
	
	pPago.add(pMetodos);
	pPago.add(pFactura);
	
	add(pPago);
	
	pMetodos.setBorder(new EmptyBorder(40,0,0,0));
	pTarjeta.setBorder(new EmptyBorder(0,40,0,0));
	pDNI.setBorder(new EmptyBorder(0,30,0,10));
	pFactura.setBorder(new EmptyBorder(10,0,0,0));
	
	campoNTarjeta.setEditable(false);
	campoCVV.setEditable(false);
	campoDNI.setEditable(false);
	dateChooser.setEnabled(false);
	
	
//TAB REVISION
	
	txtEnDesde = new JLabel("Desde:");
	txtEnHasta = new JLabel("Hasta:");
	txtPago = new JLabel("Pago:");
	txtEnvios = new JLabel("Tipo Envio:");
	txtRevLargo = new JLabel("Largo:");
	txtRevAncho = new JLabel("Ancho:");
	txtRevAlto = new JLabel("Alto:");
	txtRevPeso = new JLabel("Peso:");
	aceptarCond = new JLabel("<html><u>Aceptas terminos y condiciones de uso</u></html>");

	 
	campoEnDesde = new JTextField(10);
	campoEnHasta = new JTextField(10);
	campoPago = new JTextField(8);
	campoRevLargo = new JTextField(5);
	campoRevAncho = new JTextField(5);
	campoRevAlto = new JTextField(5);
	campoRevPeso = new JTextField(5);
	campoEnvios = new JTextField(10);
	
	campoEnDesde.setEditable(false);
	campoEnHasta.setEditable(false);
	campoPago.setEditable(false);
	campoRevLargo.setEditable(false);
	campoRevAncho.setEditable(false);
	campoRevAlto.setEditable(false);
	campoRevPeso.setEditable(false);
	campoEnvios.setEditable(false);
	
 
	checkTerminos = new JCheckBox();
	checkTerminos.setEnabled(true);
	
	pRev = new JPanel();
	pRevEnvio = new JPanel(new GridLayout(2,2));
	pDesde = new JPanel();
	pHasta = new JPanel();
	pTipo = new JPanel();
	pPagos = new JPanel();
	pMedidas = new JPanel(new GridLayout(2,2));
	pTYC = new JPanel();
	pRevPeso = new JPanel();
	pRevAlto = new JPanel();
	pRevLargo = new JPanel(); 
	pRevAncho = new JPanel();
	pText = new JPanel();
	
	 
	pDesde.add(txtEnDesde);
	pDesde.add(campoEnDesde);
	pHasta.add(txtEnHasta);
	pHasta.add(campoEnHasta);
	
	pTipo.add(txtEnvios);
	pTipo.add(campoEnvios);
	pPagos.add(txtPago);
	pPagos.add(campoPago);
	
	pRevEnvio.add(pDesde);
	pRevEnvio.add(pHasta);
	pRevEnvio.add(pTipo);
	pRevEnvio.add(pPagos);

	pRevPeso.add(txtRevPeso);
	pRevPeso.add(campoRevPeso);
	pRevAlto.add(txtRevAlto);
	pRevAlto.add(campoRevAlto);
	pRevAncho.add(txtRevAncho);
	pRevAncho.add(campoRevAncho);
	pRevLargo.add(txtRevLargo);
	pRevLargo.add(campoRevLargo);	
	 
	pMedidas.add(pRevPeso);
	pMedidas.add(pRevAlto);
	pMedidas.add(pRevAncho);
	pMedidas.add(pRevLargo);

	
	pText.add(checkTerminos);
	pText.add(aceptarCond);
	
	pTYC.add(pText);
	 
	pRev.add(pRevEnvio);
	pRev.add(pMedidas);
	pRev.add(pTYC);
	 
	add(pRev);
	
	
	
	pRev.setBorder(new EmptyBorder(10, 50, 10, 50));

	pMedidas.setBorder(new EmptyBorder(0,40,0,40));
	
	pRevEnvio.setBorder(new EmptyBorder(10,40,30,40));
	
	pText.setBorder(new EmptyBorder(10,40,40,40));
		
	pDesde.setBorder(new EmptyBorder(0,25,0,0));
	pHasta.setBorder(new EmptyBorder(0,0,0,20));
	pTipo.setBorder(new EmptyBorder(0,0,0,0));
	pPagos.setBorder(new EmptyBorder(0,0,0,30));
	
	pRevPeso.setBorder(new EmptyBorder(0,20,0,0));
	pRevAlto.setBorder(new EmptyBorder(0,0,0,20));
	pRevAncho.setBorder(new EmptyBorder(0,10,0,0));
	pRevLargo.setBorder(new EmptyBorder(0,0,0,30));
	


	
	
	
	
	
	
	tabEnvios.addTab("DONDE", pDonde);
	tabEnvios.addTab("QUE", pQue);
	tabEnvios.addTab("COMO", pComo);
	tabEnvios.addTab("PAGO", pPago);
	tabEnvios.addTab("REVISIÓN", pRev);
	tabEnvios.setEnabled(false);
	
	
	pNorte2.add(btnVolver);
	pNorte2.add(txtCrearEnvio);
	pNorte3.add(labelImagenLogo);
	pNorte.add(pNorte2);
	pNorte.add(pNorte3);
	pBtnAnterior.add(btnAnterior);
	pBtnSiguiente.add(btnSiguiente);
	pSur.add(btnFinalizar);
	pOeste.add(pBtnAnterior);
	pEste.add(pBtnSiguiente);
	add(pNorte, BorderLayout.NORTH);
	add(tabEnvios, BorderLayout.CENTER);
	add(pOeste, BorderLayout.WEST);
	add(pEste, BorderLayout.EAST);
	add(pSur, BorderLayout.SOUTH);
	
	
	
	setTitle("Hacer envío");
	setBounds(300, 200, 900, 425);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
	
	//EVENTOS
	//botones 
	btnVolver.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
			dispose();
		}
	});
	

	btnSiguiente.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int pestañaActual = tabEnvios.getSelectedIndex();
			
			cambiarPestana(1);
            btnAnterior.setEnabled(true);
            
            
            if (pestañaActual == 2) {
            	String altoPrecio1 = campoAlto.getText();
        		String anchoPrecio1 = campoAncho.getText();
        		String largoPrecio1 = campoLargo.getText();
        		
        		if (!altoPrecio1.isEmpty() && !anchoPrecio1.isEmpty() && !largoPrecio1.isEmpty()) {
        			
        		} else {
        			//JOptionPane.showMessageDialog(null, "Debe rellenar los campos ALTO ANCHO Y LARGO", "Error", JOptionPane.ERROR_MESSAGE);
        			//cambiarPestana(-1);
        		}        		
			} else {
			}
            
            if (pestañaActual == 3) {
				btnSiguiente.setEnabled(false);
				btnFinalizar.setEnabled(true);
			} else {
				btnFinalizar.setEnabled(false);
				
			}
            
            

		}
	});


	btnAnterior.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cambiarPestana(-1);
			btnSiguiente.setEnabled(true);
			int pestañaActual = tabEnvios.getSelectedIndex();
			if (pestañaActual ==0) {
				btnAnterior.setEnabled(false);
			} else {
				btnFinalizar.setEnabled(false);
			}
			
		}
	});
	
	btnFinalizar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Pedido Realizado", "Finalizar pedido", JOptionPane.INFORMATION_MESSAGE);
			SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
			dispose();
			}
		});
	
	
	
	
	//botones como
	
	radPtoRecog.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			comboRecog.setEnabled(true);
			dateChooser.setEnabled(false);
		}
	});
	
	radDomic.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			comboRecog.setEnabled(false);
			dateChooser.setEnabled(true);
		}
	});
	
	
	
	
	
	
	radEstandar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				precioFinal = precioBase;
				precioFinal += 2.99;
				txtPrecioEnvio.setText(precioFinal + "€");
				
			} catch (NumberFormatException e2) {
				txtPrecioEnvio.setText("Error");
			}
		}
	});
	
	radSuper.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				precioFinal = precioBase;			    			    				    	
				precioFinal += 3.99;
				txtPrecioEnvio.setText(precioFinal + "€");
				
			} catch (NumberFormatException e2) {
				txtPrecioEnvio.setText("Error");
			}
		}
	});
	
	radPremium.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				precioFinal = precioBase;
				precioFinal += 7.99;
				txtPrecioEnvio.setText(precioFinal + "€");
				
			} catch (NumberFormatException e2) {
				txtPrecioEnvio.setText("Error");}
		}
	});

	
	//botones pago
	
	radTarjeta.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			campoNTarjeta.setEditable(true);
			datechooserTarj.setEnabled(true);
			campoCVV.setEditable(true);
			
		}
	});
	
	radContra.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			campoNTarjeta.setEditable(false);
			datechooserTarj.setEnabled(false);
			campoCVV.setEditable(false);
			
		}
	});
	
	
	
	
	
	
	}
	
	
    private void cambiarPestana(int incremento) {
        int nuevoIndice = indiceActual + incremento;
        if (nuevoIndice >= 0 && nuevoIndice < tabEnvios.getTabCount()) {
            tabEnvios.setSelectedIndex(nuevoIndice);
            indiceActual = nuevoIndice;
        }
        
    }
    
	private static int PrecioBaseAlto(int medida) {
        if (medida <= 15) {
            return 2;
        } else if (medida <= 30) {
            return 4;
        } else {
            return 10;
        }
    }
	
	private static int PrecioBaseAncho(int medida) {
        if (medida <= 15) {
            return 3;
        } else if (medida <= 30) {
            return 5;
        } else {
            return 7;
        }
    }
	
	private static int PrecioBaseLargo(int medida) {
        if (medida <= 15) {
            return 2;
        } else if (medida <= 30) {
            return 3;
        } else {
            return 11;
        }
    }
	
		
}
