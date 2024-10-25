package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	//DONDE
	
	private JPanel pDonde;
	
	//QUE
	
	private JPanel pQue, pAltLarAncPes, pDescr, pEmbalaje, pValor, pNQue, pCQue, pAlto, pAncho, pPeso, pLargo;	
	
	private JLabel txtEmbalado, txtDescripcion, txtLargo, txtAncho, txtAlto, txtPeso, txtValor, txtInfo;

	private JTextField campoDescripcion, campoLargo, campoAncho, campoAlto, campoPeso, campoValor;

	private JCheckBox checkFragil;
	
	private JComboBox<String> comboEmbalaje;
	
	//COMO 
	
	private JPanel pComo;
	
	private JLabel txtFEnvio, txtRecog, txtCasoRecog, txtEntrega, aceptarCond;
	
	private JTextField campoDescrip, campoTarj, campoFTarj, campoCVV, campoDni;
	
	private JDateChooser dateChooser;
	
	
	//PAGO 
	
	private JPanel pPago;
	
	
	//REVISION
	
	private JPanel pRev;
	
	public VentanaHacerEnvio() {
		
		tabEnvios = new JTabbedPane();
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
		
		txtCrearEnvio = new JLabel("CREAR ENVÍO:");
		
		btnVolver = new JButton("<-");
		
		pQue = new JPanel(new GridLayout(4, 1));
		
		pNorte = new JPanel(new GridLayout(1,2));
		pNorte2 = new JPanel();
		pNorte3 = new JPanel();

		pSur = new JPanel(new GridLayout(1,2));
		pBtnAnterior = new JPanel();
		pBtnSiguiente = new JPanel();
		
		
	//COMO 
	//PAGO 
	//REVISION
	
	
	
	
	
	
	
	
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
	
	
	//TAB PAGO
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
