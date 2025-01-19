package main;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import db.BaseDatosConfiguracion;

import gui.VentanaInicioSesion;


public class Main {
	
	public static void main(String[] args) throws SQLException {

		//IAG ChatGPT
		File f = new File("resources/db");
		if (!f.exists() ) {
			f.mkdir();
		}		
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
		
		
		@SuppressWarnings("unused")
		VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
		
		
	}
} 