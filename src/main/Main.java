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
		/*String pagoId = "12345678A"; 
		BaseDatosConfiguracion.obtenerPagoPorId(con, pagoId);
		
		String paqueteId = "REF001";
		BaseDatosConfiguracion.obtenerPaquetePorId(con, paqueteId);
		
		String trayectoId = "Origen1 - Destino1";
		BaseDatosConfiguracion.obtenerTrayectoPorId(con, trayectoId);
		
		String recogidaId = "2024-12-04";
		BaseDatosConfiguracion.obtenerRecogidaPorId(con, recogidaId);
		
	    String referencia = "REF001"; 
        System.out.println("Buscando paquete con referencia: " + referencia);
        List<Paquete> paquetes = BaseDatosConfiguracion.buscarPaquetePorReferencia(con, referencia);

		*/
		
		BaseDatosConfiguracion.closeBD(con);
		
		
		VentanaInicioSesion ventanaIni = new VentanaInicioSesion();
		
		
	}
} 