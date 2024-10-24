package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class VentanaHacerEnvio extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabEnvios;
	
	private JButton btnVolver, btnAnterior, btnSiguiente, btnFinalizar;
	
	private JLabel txtCrearEnvio;
	
	private JPanel  pNorte, pNorte2, pNorte3,
					pCentro, pSur, pBtnAnterior, pBtnSiguiente, pBtnFinalizar;
	
	//DONDE
	
	private JPanel pDonde;
	
	//QUE
	
	private JPanel pQue, pAltLarAnc, pPeso, pEmbalaje, pValor, pNQue, pCQue;	
	
	private JLabel txtEmbalado, txtDescripcion, txtLargo, txtAncho, txtAlto, txtPeso, txtValor, txtInfo;

	private JTextField campoDescripcion, campoLargo, campoAncho, campoAlto, campoPeso, campoValor;

	private JCheckBox checkFragil;
	
	private JComboBox<String> comboEmbalaje;
	
	//COMO 
	
	private JPanel pComo;
	
	
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
		
		

		pQue = new JPanel();

		
		
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
	txtLargo = new JLabel("Largo: ");
	txtAncho = new JLabel("Alto: ");
	txtAlto = new JLabel("Ancho: ");
	txtPeso = new JLabel("Peso: ");
	txtValor = new JLabel("Valor del paquete: ");
	txtInfo = new JLabel("El nº de referencia se asigna automáticamente.");
	
	campoLargo = new JTextField(10);
	campoAncho = new JTextField(10);
	campoAlto = new JTextField(10);
	campoValor = new JTextField(10);
	campoPeso = new JTextField(10);
	
	
	
	checkFragil = new JCheckBox("¿Frágil?");
	
	comboEmbalaje = new JComboBox<String>();
	comboEmbalaje.addItem("Necesita embalaje");
	comboEmbalaje.addItem("No necesita embalaje");
	
	pEmbalaje = new JPanel(new GridLayout(1,2));
	pAltLarAnc = new JPanel();
	pPeso = new JPanel();
	pValor = new JPanel();
	pNQue = new JPanel();
	pCQue = new JPanel();
	
	pEmbalaje.add(txtEmbalado);
	pEmbalaje.add(comboEmbalaje);
	
	pAltLarAnc.add(txtLargo);
	pAltLarAnc.add(campoLargo);
	pAltLarAnc.add(txtAlto);
	pAltLarAnc.add(campoAlto);
	pAltLarAnc.add(txtAncho);
	pAltLarAnc.add(campoAncho);
	
	pValor.add(txtValor);
	pValor.add(campoValor);
	
	pPeso.add(txtPeso);
	pPeso.add(campoPeso);
	
	
	pNQue.add(pEmbalaje);
	pNQue.add(pPeso);
	pCQue.add(pValor);
	pCQue.add(checkFragil);
	pQue.add(pNQue);
	pQue.add(pAltLarAnc);
	pQue.add(pCQue);
	pQue.add(txtInfo);
	
	add(pQue);
	
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
