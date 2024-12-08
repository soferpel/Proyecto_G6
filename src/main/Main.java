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
		
		/*String origen = "Origen1";
		String destino = "Destino1";
		BaseDatosConfiguracion.borrarTrayecto(con, origen, destino);*/
		
		/*String recogidaId = "2024-12-04";
		BaseDatosConfiguracion.borrarRecogida(con, recogidaId);*/
		
		
		/*String trayectoId = "Origen1 - Destino1"; 
        String paqueteId = "REF12345";
        String recogidaId = "2024-12-04"; 
        String pagoId = "12345678A"; 
        
        // Llamar al método para borrar el envío
        BaseDatosConfiguracion.borrarEnvio(con, trayectoId, paqueteId, recogidaId, pagoId);*/
    
		
		BaseDatosConfiguracion.closeBD(con);
		
		
		VentanaInicioSesion ventanaIni = new VentanaInicioSesion();
		
		
	}
} 