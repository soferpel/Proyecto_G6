package main;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import db.BaseDatosConfiguracion;
import gui.VentanaHacerEnvio;
import gui.VentanaInicioSesion;


public class Main {
	
	public static void main(String[] args) throws SQLException {
		Connection con =BaseDatosConfiguracion.initBD("Paqueteria.db");
		try {
			BaseDatosConfiguracion.crearTablas(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//PRUEBAS 
		String pagoId = "12345678A"; 
		BaseDatosConfiguracion.obtenerPagoPorId(con, pagoId);
		
		String paqueteId = "REF001";
		BaseDatosConfiguracion.obtenerPaquetePorId(con, paqueteId);
		
		String trayectoId = "Origen1 - Destino1";
		BaseDatosConfiguracion.obtenerTrayectoPorId(con, trayectoId);
		
		String recogidaId = "2024-12-04";
		BaseDatosConfiguracion.obtenerRecogidaPorId(con, recogidaId);
		
		
		
		BaseDatosConfiguracion.closeBD(con);
		
		
	
		VentanaInicioSesion ventanaIni = new VentanaInicioSesion();
		
	}
}