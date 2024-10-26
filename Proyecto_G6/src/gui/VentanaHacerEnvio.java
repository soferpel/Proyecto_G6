package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;


public class VentanaHacerEnvio extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabEnvios;
	private JButton btnVolver, btnAnterior, btnSiguiente;
	private JLabel txtCrearEnvio;
	private JPanel  pNorte, pNorte2, pNorte3,
					pSur, pBtnAnterior, pBtnSiguiente;
	private Font fontGrande = new Font("Arial", Font.PLAIN, 15);
	
	
	//DONDE
	
	private JPanel pDonde;
	
	
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
	
	private JPanel pRev;
	
	
	
	public VentanaHacerEnvio() {
		
		tabEnvios = new JTabbedPane();
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
		
		txtCrearEnvio = new JLabel("CREAR ENVÍO:");
		
		btnVolver = new JButton("<-");
		
		pQue = new JPanel(new GridLayout(4, 1));
		pComo = new JPanel();
		pPago = new JPanel();
		
		pNorte = new JPanel(new GridLayout(1,2));
		pNorte2 = new JPanel();
		pNorte3 = new JPanel();

		pSur = new JPanel(new GridLayout(1,2));
		pBtnAnterior = new JPanel();
		pBtnSiguiente = new JPanel();
		
		
	//TAB DONDE
	
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
	pEmbalaje.setBackground(Color.LIGHT_GRAY);
	pValor.setBackground(Color.RED);
	pNQue.setBackground(Color.CYAN);
	pCQue.setBackground(Color.MAGENTA);
	*/
	
	pQue.setBorder(new EmptyBorder(10, 50, 10, 50));
	
	pCQue.setBorder(new EmptyBorder(10,0,0,50));
	pNQue.setBorder(new EmptyBorder(10,0,0,0));
	
	pAlto.setBorder(new EmptyBorder(0,30,0,0));
	pAncho.setBorder(new EmptyBorder(0,0,0,40));
	pLargo.setBorder(new EmptyBorder(0,20,0,0));
	pPeso.setBorder(new EmptyBorder(0,0,0,30));
	
	pValor.setBorder(new EmptyBorder(0,0,0,70));
	
	txtInfo.setBorder(new EmptyBorder(0, 210, 0, 0));
	
	

	
	
	//TAB COMO
	
	txtRecog = new JLabel("Recogida:");
	txtFEnvio = new JLabel("Fecha de recogida:");
	txtPrecioEnvio = new JLabel();

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
	
    txtRecog.setFont(fontGrande);

    
    
    
    
    
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
	
	
	
	
	

	
	//TAB REVISION
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	tabEnvios.addTab("DONDE", pDonde);
	tabEnvios.addTab("QUE", pQue);
	tabEnvios.addTab("COMO", pComo);
	tabEnvios.addTab("PAGO", pPago);
	tabEnvios.addTab("REVISIÓN", pRev);
	tabEnvios.setEnabled(true);
	
	
	pNorte2.add(btnVolver);
	pNorte2.add(txtCrearEnvio);
	pNorte.add(pNorte2);
	pNorte.add(pNorte3);
	pBtnAnterior.add(btnAnterior);
	pBtnSiguiente.add(btnSiguiente);
	pSur.add(pBtnAnterior);
	pSur.add(pBtnSiguiente);
	add(pNorte, BorderLayout.NORTH);
	add(tabEnvios, BorderLayout.CENTER);
	add(pSur, BorderLayout.SOUTH);
	
	setTitle("Hacer envío");
	setBounds(300, 200, 800, 400);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
}
