package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import domain.Pago;


public class BaseDatosConfiguracion {

	protected static Logger logger = Logger.getLogger(BaseDatosConfiguracion.class.getName());

	
	/**
	 * Método que realiza la conexión con la base de datos
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
			con.createStatement().execute("PRAGMA foreign_keys = ON;");
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			logger.warning(String.format("Error conectando con la BBDD: %s", ex.getMessage()));
		}
		
		return con;
	}
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException ex) {
				logger.warning(String.format("Error cerrando conexión con la BBDD: %s", ex.getMessage()));
			}
		}
	}
	
	//CREAR TABLAS
	public static void crearTablas(Connection con) throws SQLException{

		String sql = "CREATE TABLE IF NOT EXISTS trayecto (id INT AUTO_INCREMENT PRIMARY KEY, nombre_origen VARCHAR(100), direccion_origen VARCHAR(255), correo_origen VARCHAR(100), telefono_origen VARCHAR(20), nombre_destino VARCHAR(100), direccion_destino VARCHAR(255), correo_destino VARCHAR(100), telefono_destino VARCHAR(20))";
		String sql2 = "CREATE TABLE IF NOT EXISTS paquete (id INT AUTO_INCREMENT PRIMARY KEY, n_referencia VARCHAR(50) NOT NULL, embalaje VARCHAR(50), peso DECIMAL(10, 2), largo DECIMAL(10, 2), ancho DECIMAL(10, 2), alto DECIMAL(10, 2), valor DECIMAL(10, 2), fragil BOOLEAN)";
		String sql3 = "CREATE TABLE IF NOT EXISTS recogida (id INT AUTO_INCREMENT PRIMARY KEY, fecha_de_recogida DATE, lugar_de_recogida VARCHAR(255), tipo_de_envio VARCHAR(50))";
		String sql4 = "CREATE TABLE IF NOT EXISTS pago (id INT AUTO_INCREMENT PRIMARY KEY, descripcion VARCHAR(255), numero_tarjeta VARCHAR(16), fecha_caducidad DATE, cvv CHAR(3), remitente_destinatario VARCHAR(255), factura VARCHAR(255), dni VARCHAR(20), precio DECIMAL(10, 2))";
		String sql5 = "CREATE TABLE IF NOT EXISTS envio (id INT AUTO_INCREMENT PRIMARY KEY, trayecto_id INT, paquete_id INT, recogida_id INT, pago_id INT, FOREIGN KEY (trayecto_id) REFERENCES trayecto(id), FOREIGN KEY (paquete_id) REFERENCES paquete(id), FOREIGN KEY (recogida_id) REFERENCES recogida(id), FOREIGN KEY (pago_id) REFERENCES pago(id))";
		String sql6 = "CREATE TABLE IF NOT EXISTS usuario (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100), apellido VARCHAR(100), telefono VARCHAR(20), correo VARCHAR(100) UNIQUE, respuesta VARCHAR(255), pregunta_seg VARCHAR(255), contrasenia VARCHAR(255))";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.executeUpdate(sql4);
			st.executeUpdate(sql5);
			st.executeUpdate(sql6);

			st.close();

		} catch (SQLException e) {
			logger.warning("Error creando las tablas");
			e.printStackTrace();
		}
		logger.info("Tablas creadas correctamenrte");

	}
	
	
	//PAGO
	public static void insertarPago(Connection con, Pago p) {
	    String sql = String.format("INSERT INTO pago (descripcion, numeroTarjeta, fechaCaducidad, CVV, remitenteDestinatario, factura, dni, precio) VALUES (?,?,?,?,?,?,?,?)");

	    try {
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, p.getDescripcion());
	        st.setString(2, p.getNumeroTrajeta());
	        st.setString(3, p.getFechaCaducidad());
	        st.setString(4, p.getCVV());
	        st.setString(5, p.getRemitenteDestinatario());
	        st.setString(6, p.getFactura());
	        st.setString(7, p.getDni());
	        st.setString(8, p.getPrecio());
	        
	        st.execute();
	        st.close();
	        logger.info(String.format("Nuevo pago insertado: %s", p.toString()));
	    } catch (SQLException e) {
	        logger.warning(String.format("Error insertando pago %s", p.toString()));
	    }
	}  
	    
	    
	    public static Pago buscarPago(Connection con, String dni) {
			String sql = String.format("SELECT * FROM cliente WHERE DNI = '%s'", dni);
	        Pago p = null;
	        try {
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, dni); 
	            ResultSet rs = ps.executeQuery(); 

	            if (rs.next()) { 
	                String descripcion = rs.getString("DESCRIPCION");
	                String numeroTarjeta = rs.getString("NUMERO_TARJETA"); 
	                String fechaCaducidad = rs.getString("FECHA_CADUCIDAD");
	                String CVV = rs.getString("CVV");
	                String remitenteDestinatario = rs.getString("REMITENTE_DESTINATARIO");
	                String factura = rs.getString("FACTURA");
	                String precio = rs.getString("PRECIO");

	                p = new Pago(descripcion, numeroTarjeta, fechaCaducidad, CVV, remitenteDestinatario, factura, dni, precio);
	            }

	            rs.close();
	            ps.close();
	        } catch (SQLException e) {
	            logger.warning("Error buscando pago: " + e.getMessage());
	        }
	        return p;
	    
	}

	    public static void borrarPago(Connection con, String dni) {
	        String sql = String.format("DELETE FROM pago WHERE dni='%s'", dni);

	        try {
	            Statement st = con.createStatement();
	            
	            st.executeUpdate(sql);
	            
	            st.close();
	            
	            logger.info("Pago borrado correctamente para el DNI: " + dni);
	        } catch (SQLException e) {
	            logger.warning("Error borrando pago para el DNI: " + dni);
	        }
	    }

	
	
	
	

}
