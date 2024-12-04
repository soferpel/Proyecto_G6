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

		String sql = "CREATE TABLE IF NOT EXISTS trayecto (\r\n"
	            + "    nombre_origen VARCHAR(100),\r\n"
	            + "    direccion_origen VARCHAR(255),\r\n"
	            + "    correo_origen VARCHAR(100),\r\n"
	            + "    telefono_origen VARCHAR(20),\r\n"
	            + "    nombre_destino VARCHAR(100),\r\n"
	            + "    direccion_destino VARCHAR(255),\r\n"
	            + "    correo_destino VARCHAR(100),\r\n"
	            + "    telefono_destino VARCHAR(20),\r\n"
	            + "    PRIMARY KEY (nombre_origen, nombre_destino)\r\n"
	            + ");\r\n";

		String sql2 = "CREATE TABLE IF NOT EXISTS paquete (\r\n"
	            + "    n_referencia VARCHAR(50) NOT NULL,\r\n"
	            + "    embalaje VARCHAR(50),\r\n"
	            + "    peso VARCHAR(50),\r\n"
	            + "    largo VARCHAR(50),\r\n"
	            + "    ancho VARCHAR(50),\r\n"
	            + "    alto VARCHAR(50),\r\n"
	            + "    valor VARCHAR(50),\r\n"
	            + "    fragil VARCHAR(10),\r\n"
	            + "    PRIMARY KEY (n_referencia)\r\n"
	            + ");\r\n";
		String sql3 = "CREATE TABLE IF NOT EXISTS recogida (\r\n"
	            + "    fecha_de_recogida VARCHAR(50),\r\n"
	            + "    lugar_de_recogida VARCHAR(255),\r\n"
	            + "    tipo_de_envio VARCHAR(50),\r\n"
	            + "    PRIMARY KEY (fecha_de_recogida)\r\n"
	            + ");\r\n";
		String sql4 = "CREATE TABLE IF NOT EXISTS pago (\r\n"
				+ "    dni VARCHAR(20) PRIMARY KEY,\r\n"
				+ "    descripcion VARCHAR(255),\r\n"
				+ "    numero_tarjeta VARCHAR(16),\r\n"
				+ "    fecha_caducidad DATE,\r\n"
				+ "    cvv CHAR(3),\r\n"
				+ "    remitente_destinatario VARCHAR(255),\r\n"
				+ "    factura VARCHAR(255),\r\n"
				+ "    precio DECIMAL(10, 2)\r\n"
				+ ");\r\n"
				+ "";
		String sql5 = "CREATE TABLE IF NOT EXISTS envio (\r\n"
	            + "    trayecto_id VARCHAR(100),\r\n"
	            + "    paquete_id VARCHAR(50),\r\n"
	            + "    recogida_id VARCHAR(50),\r\n"
	            + "    pago_id VARCHAR(20),\r\n"
	            + "    PRIMARY KEY (trayecto_id, paquete_id, recogida_id, pago_id),\r\n"
	            + "    FOREIGN KEY (trayecto_id) REFERENCES trayecto(nombre_origen),\r\n"
	            + "    FOREIGN KEY (paquete_id) REFERENCES paquete(n_referencia),\r\n"
	            + "    FOREIGN KEY (recogida_id) REFERENCES recogida(fecha_de_recogida),\r\n"
	            + "    FOREIGN KEY (pago_id) REFERENCES pago(dni)\r\n"
	            + ");\r\n";
		String sql6 = "CREATE TABLE IF NOT EXISTS usuario (\r\n"
	            + "    nombre VARCHAR(100),\r\n"
	            + "    apellido VARCHAR(100),\r\n"
	            + "    telefono VARCHAR(20),\r\n"
	            + "    correo VARCHAR(100) UNIQUE,\r\n"
	            + "    respuesta VARCHAR(255),\r\n"
	            + "    pregunta_seg VARCHAR(255),\r\n"
	            + "    contrasenia VARCHAR(255),\r\n"
	            + "    PRIMARY KEY (correo)\r\n"
	            + ");\r\n";


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
			String sql = String.format("SELECT * FROM pago WHERE DNI = '%s'", dni);
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
