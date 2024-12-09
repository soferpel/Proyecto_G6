package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Pago;
import domain.Paquete;
import domain.Recogida;
import domain.trayecto;


public class VentanaHacerEnvio extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabEnvios;
	private JButton btnVolver, btnAnterior, btnSiguiente, btnFinalizar;
	private JLabel txtCrearEnvio;
	private JPanel  pNorte, pNorte2, pNorte3,pSur,
					pOeste, pEste, pBtnAnterior, pBtnSiguiente;
	private Font fontTextoTitulo = new Font("Tahoma", Font.BOLD, 20); //IAG (herramienta: ChatGPT)
	private double precioBase, precioFinal;
	private int indiceActual = 0;
	
	private Thread hilo;
	private boolean hiloEjecutando;
	
	//DONDE
	
	private JPanel pDonde;
	
	private JTextField dNombre, dDireccion, dCorreo, dTelefono;
	
	private JTextField hNombre, hDireccion, hCorreo, hTelefono;
	
	
	//QUE
	
	private JPanel pQue, pAltLarAncPes, pDescr, pEmbalaje, pValor, pNQue, pCQue, pAlto, pAncho, pPeso, pLargo;	
	
	private JLabel txtEmbalado, txtDescripcion, txtLargo, txtAncho, txtAlto, txtPeso, txtValor, txtInfo;

	private JTextField campoDescripcion, campoLargo, campoAncho, campoAlto, campoPeso, campoValor, campoPtoRecog;

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
	
	private String textoTYC;
	
	private  JTextArea textTYC;
	
	private JScrollPane scrollTYC;
	
	
	public VentanaHacerEnvio() {
		

		
	ImageIcon logo = new ImageIcon("resources/images/logoPngNegro.png");
	JLabel labelImagenLogo = new JLabel(logo);
	labelImagenLogo.setPreferredSize(new Dimension(350, logo.getIconHeight()));
	
	labelImagenLogo.setBorder(new EmptyBorder(0,250,0,0));
	
	tabEnvios = new JTabbedPane();
	
	
	ImageIcon imgAnterior = new ImageIcon("resources/images/flecha_ant.png");
    btnAnterior = new JButton(imgAnterior);
    ImageIcon imgSiguiente = new ImageIcon("resources/images/flecha_sig.png");
    btnSiguiente = new JButton(imgSiguiente);
	btnFinalizar = new JButton("FINALIZAR");
	btnFinalizar.setEnabled(false);
	
    btnAnterior.setEnabled(false);
    
    
    txtCrearEnvio = new JLabel("CREAR ENVÃ�O:");
	txtCrearEnvio.setFont(fontTextoTitulo);
	
	
	ImageIcon imgVolver = new ImageIcon("resources/images/volver.png");
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
	
    pBtnAnterior.setBorder(new EmptyBorder(120,0,0,0));
    pBtnSiguiente.setBorder(new EmptyBorder(120,0,0,0));
    
    
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
    JLabel lblHastaDireccion = new JLabel("DirecciÃ³n:");
    hDireccion = new JTextField(10);
    JLabel lblHastaCorreo = new JLabel("Correo:");
    hCorreo = new JTextField(10);
    JLabel lblHastaTelefono = new JLabel("TelÃ©fono:");
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
	txtInfo = new JLabel("El nÂº de referencia se asigna automÃ¡ticamente.");
	
	
	campoLargo = new JTextField(10);
	campoAncho = new JTextField(10);
	campoAlto = new JTextField(10);
	campoValor = new JTextField(10);
	campoPeso = new JTextField(10);
	campoDescripcion = new JTextField(10);
	
	checkFragil = new JCheckBox("Â¿FrÃ¡gil?");
	
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
	String[] codigosPostales = {"28001", "28002", "28003", "28004", "28005", "28006", "08001", "08002", "08003", "08004", "09001", "09002"};
 
		for (String codigoPostal : codigosPostales) {
		    comboRecog.addItem(codigoPostal);
		}
	
	tipoEnvioGrupo = new ButtonGroup();
	recogidaGrupo = new ButtonGroup();
	
	pTxtRecog = new JPanel();
	pFRecog = new JPanel();
	pRecYEnt = new JPanel(new GridLayout(1,2, 100, 50));
	pEntrega = new JPanel(new GridLayout(4,1,0,10));
	pRecog2 = new JPanel(new GridLayout(4,1,0,10));
	
	
	dateChooser = new JDateChooser();
	dateChooser.setDateFormatString("dd/MM/yyyy");
	
	//campoPtoRecog = new JTextField(20);  // Campo de texto para la entrada de la dirección del punto de recogida
	//campoPtoRecog.setEnabled(false); 
	
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
	//pRecog2.add(campoPtoRecog);

	
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
    
    txtNTarjeta = new JLabel("NÂº Tarjeta:");
	txtFCad = new JLabel("Fecha Caducidad:");
	txtCVV = new JLabel("CVV:");
	txtDNI = new JLabel("DNI:");
		
	campoNTarjeta = new JTextField(16);
	campoCVV = new JTextField(5);
	campoDNI = new JTextField(5);

	checkFactura = new JCheckBox("Â¿Factura?");
	
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

	 
	campoEnDesde = new JTextField(11);
	campoEnHasta = new JTextField(11);
	campoPago = new JTextField(11);
	campoRevLargo = new JTextField(5);
	campoRevAncho = new JTextField(5);
	campoRevAlto = new JTextField(5);
	campoRevPeso = new JTextField(5);
	campoEnvios = new JTextField(11);
	
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
	pHasta.setBorder(new EmptyBorder(0,0,0,15));
	pTipo.setBorder(new EmptyBorder(0,0,0,0));
	pPagos.setBorder(new EmptyBorder(0,0,0,10));
	
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
	
	
	
	setTitle("Hacer envÃ­o");
	setBounds(300, 200, 900, 425);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setResizable(false);

	 
    textoTYC = new String("AceptaciÃ³n de TÃ©rminos y Condiciones de uso:\r\n"
			+ "\r\n"
			+ "Al utilizar nuestro sistema, el usuario acepta estos tÃ©rminos y condiciones y se compromete a cumplir con ellos. Estos tÃ©rminos pueden ser modificados en cualquier momento, y el usuario se compromete a revisarlos regularmente para estar al tanto de cualquier cambio.\r\n"
			+ "\r\n"
			+ "Los usuarios pueden necesitar registrarse para utilizar ciertas funciones del sistema. La informaciÃ³n proporcionada durante el registro debe ser precisa y completa.\r\n"
			+ "Los usuarios son responsables de mantener la confidencialidad de sus credenciales de inicio de sesiÃ³n y notificar a Hermes de cualquier uso no autorizado de su cuenta.\r\n"
			+ "\r\n"
			+ "Los usuarios se comprometen a utilizar el sistema de manera adecuada y legal, sin infringir derechos de terceros.\r\n"
			+ "No se permite el uso del sistema para actividades ilegales o fraudulentas.\r\n"
			+ "\r\n"
			+ "Los usuarios son responsables de la exactitud de la informaciÃ³n proporcionada al sistema, incluyendo datos de contacto y direcciones de envÃ­o.\r\n"
			+ "Los usuarios son responsables de asegurarse de que los paquetes y envÃ­os cumplan con las restricciones y regulaciones aplicables.\r\n"
			+ "\r\n"
			+ "Los usuarios aceptan pagar las tarifas correspondientes a los servicios utilizados, segÃºn las tarifas publicadas por Hermes.\r\n"
			+ "Los pagos se pueden realizar a travÃ©s de los mÃ©todos de pago aceptados por el sistema.\r\n"
			+ "\r\n"
			+ "Hermes se compromete a proteger la privacidad y los datos de los usuarios de acuerdo con las leyes aplicables.\r\n"
			+ "\r\n"
			+ "Hermes no se harÃ¡ responsable de daÃ±os indirectos, consecuentes o incidentales.\r\n"
			+ "La responsabilidad de Hermes se limita a los tÃ©rminos establecidos en acuerdos especÃ­ficos.\r\n"
			+ "\r\n"
			+ "Las polÃ­ticas de cancelaciÃ³n y devoluciÃ³n se basan en las tarifas y polÃ­ticas especÃ­ficas de Hermes.\r\n"
			+ "Los usuarios deben revisar nuestras polÃ­ticas de cancelaciÃ³n y devoluciÃ³n antes de utilizar el sistema.\r\n"
			+ "\r\n"
			+ "Hermes se reserva el derecho de suspender o cancelar la cuenta de cualquier usuario que incumpla estos tÃ©rminos y condiciones.\r\n"
			+ "\r\n"
			+ "Estos tÃ©rminos y condiciones se rigen por las leyes del paÃ­s (en este caso EspaÃ±a) y cualquier disputa se resolverÃ¡ mediante arbitraje de conformidad con las reglas de Hermes o ante los tribunales competentes en EspaÃ±a.\r\n"
			+ "\r\n"
			+ "Si tiene alguna pregunta o inquietud acerca de estos tÃ©rminos y condiciones, por favor contÃ¡ctenos a travÃ©s de support@hermes.es.\r\n"
			+ "\r\n"
			+ "Al utilizar el Sistema de PaqueterÃ­a de Hermes, usted acepta y comprende estos tÃ©rminos y condiciones. Le recomendamos que imprima o descargue una copia de este documento para su referencia futura.");
	

	
	//EVENTOS
	
    checkTerminos.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				textTYC = new JTextArea(textoTYC);

		        	scrollTYC = new JScrollPane(textTYC);
		        	scrollTYC.setPreferredSize(new Dimension(400, 300));

		        	int option = JOptionPane.showOptionDialog(
		        			null,
		        			scrollTYC,
		        			"TÃ©rminos y Condiciones",
		        			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Aceptar", "Rechazar"}, "Aceptar");

		        	if (option == JOptionPane.OK_OPTION) {
		        		checkTerminos.setSelected(true);
		        	} else {
		        		checkTerminos.setSelected(false);
		        	}
			}
		});
	
    

    //btnFinalizar.addActionListener(e -> {
    btnFinalizar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			guardarDatosPago();
			guardarDatosPaquete();
			guardarDatosTrayecto();
			guardarDatosEnvio();
			try {
				guardarDatosRecogida();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		     
		
		private void guardarDatosRecogida() throws SQLException {
		    java.util.Date fecha = dateChooser.getDate();
		    String fechaDeRecogida = null;
		    String lugarDeRecogida = null; 
		    String tipoDeEnvio = null;

		    if (radDomic.isSelected()) {
		        if (fecha == null) {
		            JOptionPane.showMessageDialog(null, "La fecha de recogida no ha sido seleccionada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        fechaDeRecogida = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
		        lugarDeRecogida = "A domicilio";
			    if (radEstandar.isSelected()) {
			        tipoDeEnvio = "Estandar";
			    } else if (radSuper.isSelected()) {
			        tipoDeEnvio = "Superior";
			    } else if (radPremium.isSelected()) {
			        tipoDeEnvio = "Premium";
			    }
		    } else if (radPtoRecog.isSelected()) {
		        lugarDeRecogida = (String) comboRecog.getSelectedItem();
		        if (lugarDeRecogida == null || lugarDeRecogida.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un punto de recogida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				    if (radEstandar.isSelected()) {
				        tipoDeEnvio = "Estandar";
				    } else if (radSuper.isSelected()) {
				        tipoDeEnvio = "Superior";
				    } else if (radPremium.isSelected()) {
				        tipoDeEnvio = "Premium";
				    }
		    } else {
		        JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción de recogida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    Recogida recogida = new Recogida(fechaDeRecogida, lugarDeRecogida, tipoDeEnvio);

		    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
		    try {
		        BaseDatosConfiguracion.insertarRecogida(con, recogida);
		        JOptionPane.showMessageDialog(null, "Recogida registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    } finally {
		        BaseDatosConfiguracion.closeBD(con);
		    }
		}


		
		private void guardarDatosEnvio() {
		    String trayectoNombreOrigen = dNombre.getText().trim();
		    String trayectoNombreDestino = hNombre.getText().trim();
		    String paqueteId = generarReferenciaAleatoria();
		    String pagoId = campoDNI.getText().trim();
		    String fechaDeRecogida = "";
		    String lugarDeRecogida = "";
		    String tipoDeEnvio = "";

		    if (radDomic.isSelected()) {
		        Date fechaRecogida = dateChooser.getDate();
		        if (fechaRecogida == null) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de recogida.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        fechaDeRecogida = new java.sql.Date(fechaRecogida.getTime()).toString();
		        lugarDeRecogida = "A domicilio";
		        tipoDeEnvio = "Domicilio";
		    } else if (radPtoRecog.isSelected()) {
		        lugarDeRecogida = (String) comboRecog.getSelectedItem();
		        if (lugarDeRecogida == null || lugarDeRecogida.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un punto de recogida.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        tipoDeEnvio = "Punto de recogida";
		    } else {
		        JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción de recogida.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    trayecto trayecto = new trayecto(trayectoNombreOrigen, dDireccion.getText(), dCorreo.getText(), dTelefono.getText(),
		                                     trayectoNombreDestino, hDireccion.getText(), hCorreo.getText(), hTelefono.getText());
		    String trayectoId = trayecto.getTrayectoId();

		    String embalaje = comboEmbalaje.getSelectedItem() != null ? comboEmbalaje.getSelectedItem().toString() : "Sin embalaje";
		    String peso = campoPeso.getText().trim();
		    String largo = campoLargo.getText().trim();
		    String ancho = campoAncho.getText().trim();
		    String alto = campoAlto.getText().trim();
		    String valor = campoValor.getText().trim();
		    String fragil = checkFragil.isSelected() ? "Sí" : "No";

		    Paquete paquete = new Paquete(paqueteId, embalaje, peso, largo, ancho, alto, valor, fragil);

		    String descripcion = campoDescripcion.getText().trim();
		    String numeroTarjeta = radTarjeta.isSelected() ? campoNTarjeta.getText().trim() : "-";
		    String fechaCaducidad = radTarjeta.isSelected() && datechooserTarj.getDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(datechooserTarj.getDate()) : null;
		    String CVV = radTarjeta.isSelected() ? campoCVV.getText().trim() : "-";
		    String remitenteDestinatario = trayectoNombreOrigen + " - " + trayectoNombreDestino;
		    String factura = checkFactura.isSelected() ? "Sí" : "No";
		    String precio = valor;

		    Pago pago = new Pago(descripcion, numeroTarjeta, fechaCaducidad, CVV, remitenteDestinatario, factura, pagoId, precio);

		    Recogida recogida = new Recogida(fechaDeRecogida, lugarDeRecogida, tipoDeEnvio);

		    Envio envio = new Envio(trayecto, paquete, recogida, pago);

		    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
		    try {
		        BaseDatosConfiguracion.insertarEnvio(con, envio);
		        JOptionPane.showMessageDialog(null, "Envío registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    } finally {
		        BaseDatosConfiguracion.closeBD(con);
		    }
		}



		

		private void guardarDatosTrayecto() {
			// TODO Auto-generated method stub
			String nombreOrigen = dNombre.getText().trim();
		    String direccionOrigen = dDireccion.getText().trim();
		    String correoOrigen = dCorreo.getText().trim();
		    String telefonoOrigen = dTelefono.getText().trim();
		    String nombreDestino = hNombre.getText().trim();
		    String direccionDestino = hDireccion.getText().trim();
		    String correoDestino = hCorreo.getText().trim();
		    String telefonoDestino = hTelefono.getText().trim();
		    
		    if (nombreOrigen.isEmpty() || direccionOrigen.isEmpty() || correoOrigen.isEmpty() || telefonoOrigen.isEmpty() ||
		            nombreDestino.isEmpty() || direccionDestino.isEmpty() || correoDestino.isEmpty() || telefonoDestino.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		    
		    trayecto trayecto = new trayecto(nombreOrigen, direccionOrigen, correoOrigen, telefonoOrigen,
                    nombreDestino, direccionDestino, correoDestino, telefonoDestino);
			
		    
		    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
		    try {
		        BaseDatosConfiguracion.insertarTrayecto(con, trayecto);
		    } finally {
		    	
		    	  BaseDatosConfiguracion.closeBD(con);
		    }
		}


		private void guardarDatosPaquete() {
			// TODO Auto-generated method stub
			String embalaje = comboEmbalaje.getSelectedItem().toString();  
		    String peso = campoPeso.getText().trim();
		    String largo = campoLargo.getText().trim();
		    String ancho = campoAncho.getText().trim();
		    String alto = campoAlto.getText().trim();
		    String valor = campoValor.getText().trim();
		    String fragil = checkFragil.isSelected() ? "Sí" : "No"; 
		    
		    
		    if (peso.isEmpty() || largo.isEmpty() || ancho.isEmpty() || alto.isEmpty() || valor.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
			
		    String referencia = generarReferenciaAleatoria();  
		    Paquete paquete = new Paquete(referencia, embalaje, peso, largo, ancho, alto, valor, fragil);
		    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
		    
		    try {
		        BaseDatosConfiguracion.insertarPaquete(con, paquete);
		    } finally {
		        
		        BaseDatosConfiguracion.closeBD(con);
		    }
		}

		public static String generarReferenciaAleatoria() {
		    int numeroAleatorio = (int) (Math.random() * 1000000000);  
		    return "REF-" + String.format("%010d", numeroAleatorio);  
		}

		
		private void guardarDatosPago() {
		    String descripcion = campoDescripcion.getText().trim();
		    String remitenteDestinatario = dNombre.getText().trim() + " - " + hNombre.getText().trim();
		    String factura = checkFactura.isSelected() ? "Sí" : "No";
		    String dni = campoDNI.getText().trim();
		    String precio = campoValor.getText().trim();
		    String numeroTarjeta = null;
		    String fechaCaducidad = null;
		    String CVV = null;

		    
		    if (descripcion.isEmpty() || dni.isEmpty() || precio.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		   
		    if (!precio.matches("\\d+(\\.\\d{1,2})?")) {
		        JOptionPane.showMessageDialog(null, "El precio debe ser un número válido con hasta dos decimales.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    
		    if (radTarjeta.isSelected()) {
		        numeroTarjeta = campoNTarjeta.getText().trim();
		        Date fechaSeleccionada = datechooserTarj.getDate();
		        CVV = campoCVV.getText().trim();

		        if (numeroTarjeta.isEmpty() || fechaSeleccionada == null || CVV.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos de la tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            fechaCaducidad = sdf.format(fechaSeleccionada);
		            LocalDate.parse(fechaCaducidad, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		        } catch (DateTimeParseException ex) {
		            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            if (CVV.length() != 3 || !CVV.matches("\\d+")) {
		                throw new NumberFormatException("El CVV debe ser un número de 3 dígitos.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "El CVV debe ser un número de 3 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		    }

		    
		    Pago pago = new Pago(descripcion, numeroTarjeta != null ? numeroTarjeta : "-", fechaCaducidad, CVV != null ? CVV : "-", remitenteDestinatario, factura, dni, precio);

		    Connection con = BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");

		    try {
				if (radTarjeta.isSelected()) {
					
					pago = new Pago(descripcion, numeroTarjeta, fechaCaducidad, CVV, remitenteDestinatario, factura, dni, Double.toString(precioFinal));
					BaseDatosConfiguracion.insertarPago(con, pago);
				} else {
					pago = new Pago(descripcion, remitenteDestinatario, factura, dni, Double.toString(precioFinal));
					BaseDatosConfiguracion.insertarPago(con, pago);

				}
		    } finally {
		        BaseDatosConfiguracion.closeBD(con);
		    }
		}

	});
    
   
    
    checkTerminos.addActionListener(e -> btnFinalizar.setEnabled(checkTerminos.isSelected()));

    getRootPane().setDefaultButton(btnFinalizar);
    

	//HILOS
	hiloEjecutando = true;
	addWindowListener(new WindowAdapter() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			hilo = new Thread() {
			int reloj = 0;
			public void run() {
				while(hiloEjecutando) {
					reloj++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (reloj == 300) {
						int result = JOptionPane.showConfirmDialog(null, "Han pasado 5 minutos, Â¿desea continuar creando su pedido?", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if (result == JOptionPane.NO_OPTION) {
							SwingUtilities.invokeLater(() -> new VentanaPantallaPrincipal());
							dispose();
							hiloEjecutando = false;
						}
						
					}
				}
			}
			};
			hilo.start();
		}
	});
	
	addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosed(WindowEvent e) {
			hiloEjecutando = false;				
		}

	});
	
	
	//IAG (herramienta: ChatGPT)
	agregarKeyBindingParaEnter(campoDescripcion);
	agregarKeyBindingParaEnter(campoLargo);
	agregarKeyBindingParaEnter(campoAncho);
	agregarKeyBindingParaEnter(campoAlto);
	agregarKeyBindingParaEnter(campoPeso);
	agregarKeyBindingParaEnter(campoValor);
	
	agregarKeyBindingParaEnter(dNombre);
	agregarKeyBindingParaEnter(dDireccion);
	agregarKeyBindingParaEnter(dCorreo);
	agregarKeyBindingParaEnter(dTelefono);
	
	agregarKeyBindingParaEnter(hNombre);
	agregarKeyBindingParaEnter(hDireccion);
	agregarKeyBindingParaEnter(hCorreo);
	agregarKeyBindingParaEnter(hTelefono);
	
	agregarKeyBindingParaEnter(campoNTarjeta);
	agregarKeyBindingParaEnter(campoCVV);
	agregarKeyBindingParaEnter(campoDNI);
	
	agregarKeyBindingParaEnter(campoEnDesde);
	agregarKeyBindingParaEnter(campoEnHasta);
	agregarKeyBindingParaEnter(campoPago);
	agregarKeyBindingParaEnter(campoEnvios);
	agregarKeyBindingParaEnter(campoRevLargo);
	agregarKeyBindingParaEnter(campoRevAncho);
	agregarKeyBindingParaEnter(campoRevAlto);
	agregarKeyBindingParaEnter(campoRevPeso);
	
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

	   tabEnvios.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               if (tabEnvios.getSelectedIndex() == 4) {  
            	   
                   campoEnDesde.setText( dDireccion.getText() );
                   campoEnHasta.setText(hDireccion.getText());
                   campoRevLargo.setText(campoLargo.getText());
                   campoRevAncho.setText(campoAncho.getText());
                   campoRevAlto.setText(campoAlto.getText());
                   campoRevPeso.setText(campoPeso.getText());
               }
                   if (tabEnvios.getSelectedIndex() == 4) {  
                       if (radTarjeta.isSelected()) {
                           campoPago.setText("Con tarjeta");
                       } else if (radContra.isSelected()) {
                           campoPago.setText("Contrarrembolso");
                      
                       }
                       }if (tabEnvios.getSelectedIndex() == 4) {  
                           if (radEstandar.isSelected()) {
                               campoEnvios.setText("Estandar");
                           } else if (radSuper.isSelected()) {
                               campoEnvios.setText("Superior");
                           } else if (radPremium.isSelected()) {
                               campoEnvios.setText("Premium");
                           }
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
				txtPrecioEnvio.setText(precioFinal + "â‚¬");
				
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
				txtPrecioEnvio.setText(precioFinal + "â‚¬");
				
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
				txtPrecioEnvio.setText(precioFinal + "â‚¬");
				
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
	
	
	//IAG (herramienta: ChatGPT)
    private static void agregarKeyBindingParaEnter(JTextField textField) {
        textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "moverFoco");
        textField.getActionMap().put("moverFoco", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                textField.transferFocus(); 
            }
        });
    }
}
