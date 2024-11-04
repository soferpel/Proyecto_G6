package main;

import javax.swing.SwingUtilities;

import gui.VentanaHacerEnvio;


public class Main {
	
	//main provisional
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaHacerEnvio());
		
	}
}