package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingUtilities;

import db.BaseDatosConfiguracion;
import domain.Paquete;
import gui.VentanaHacerEnvio;
import gui.VentanaInicioSesion;


public class Main {
	
	public static void main(String[] args) throws SQLException {
		Connection con =BaseDatosConfiguracion.initBD("resources/db/Paqueteria.db");
		try {
			BaseDatosConfiguracion.crearTablas(con);
			//BaseDatosConfiguracion.insertarRegistroDePrueba(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//PRUEBAS 
		
		
		BaseDatosConfiguracion.closeBD(con);
		
		
		VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
		
		
	}
} 