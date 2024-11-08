package main;

import javax.swing.SwingUtilities;

import gui.VentanaHacerEnvio;
import gui.VentanaInicioSesion;


public class Main {
	
	//main provisional
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaInicioSesion());
		
	}
}