package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import domain.Envio;
import domain.Pago;
import domain.Paquete;
import domain.Recogida;
import domain.trayecto;


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

//TRAYECTO
	    
	    public static void borrarTrayecto(Connection con, String nombreOrigen, String nombreDestino) {
			String sql = "DELETE FROM trayecto WHERE nombre_origen = ? AND nombre_destino = ?";
			try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, nombreOrigen);  // nombre_origen
		        st.setString(2, nombreDestino); 
		        
				st.executeUpdate(sql);
				st.close();
				logger.info("Trayecto borrado correctamente: " + nombreOrigen + " a " + nombreDestino);				   
			} catch (SQLException e) {
				logger.warning("Error borrando el trayecto: " + nombreOrigen + " a " + nombreDestino + " - " + e.getMessage());
		        e.printStackTrace();
			}
			
		}
	    
	    public static void actualizarTrayecto(Connection con, trayecto trayecto) {
	        String sql = "UPDATE trayecto SET direccion_origen = ?, correo_origen = ?, telefono_origen = ?, "
	                   + "direccion_destino = ?, correo_destino = ?, telefono_destino = ? WHERE nombre_origen = ? AND nombre_destino = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setString(1, trayecto.getDireccionOrigen());
	            st.setString(2, trayecto.getCorreoOrigen());
	            st.setString(3, trayecto.getTelefonoOrigen());
	            st.setString(4, trayecto.getDireccionDestino());
	            st.setString(5, trayecto.getCorreoDestino());
	            st.setString(6, trayecto.getTelefonoDestino());
	            st.setString(7, trayecto.getNombreOrigen());
	            st.setString(8, trayecto.getNombreDestino());

	            st.executeUpdate();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error actualizando trayecto: " + e.getMessage());
	        }
	    }

	    
//ENVIO
	/*				   trayecto_id VARCHAR(100),\r\n"
	            + "    paquete_id VARCHAR(50),\r\n"
	            + "    recogida_id VARCHAR(50),\r\n"
	            + "    pago_id VARCHAR(20),\r\n"
	            + "    PRIMARY KEY (trayecto_id, paquete_id, recogida_id, pago_id),\r\n"
	            + "    FOREIGN KEY (trayecto_id) REFERENCES trayecto(nombre_origen),\r\n"
	            + "    FOREIGN KEY (paquete_id) REFERENCES paquete(n_referencia),\r\n"
	            + "    FOREIGN KEY (recogida_id) REFERENCES recogida(fecha_de_recogida),\r\n"
	            + "    FOREIGN KEY (pago_id) REFERENCES pago(dni)\r\n"	            
	            */
	    
	    public static void insertarEnvio(Connection con, Envio e) {
	        // SQL para insertar un nuevo registro en la tabla 'envio'
	        String sql = "INSERT INTO envio (trayecto_id, paquete_id, recogida_id, pago_id) VALUES (?, ?, ?, ?)";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, e.getTrayecto().getNombreOrigen() + " - " + e.getTrayecto().getNombreDestino());  // trayecto_id
	            st.setString(2, e.getPaquete().getnReferencia());  // paquete_id			MIRAR
	            st.setString(3, e.getRecogida().getLugarDeRecogida());  // recogida_id
	            st.setString(4, e.getPago().getDni());  // pago_id

	            st.executeUpdate();
	            st.close();
 
	            logger.info("Envio insertado correctamente.");
	        } catch (SQLException ex) {
	        	logger.warning(String.format("Error insertando articulo %s", e.toString()));
	            
	        }
	    }
	    
	    public static void borrarEnvio(Connection con, String trayectoId, String paqueteId, String recogidaId, String pagoId) {
	        // SQL para eliminar un registro de la tabla 'envio' usando las claves foráneas
	        String sql = "DELETE FROM envio WHERE trayecto_id = ? AND paquete_id = ? AND recogida_id = ? AND pago_id = ?";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, trayectoId);  // trayecto_id
	            st.setString(2, paqueteId);    // paquete_id
	            st.setString(3, recogidaId);  // recogida_id
	            st.setString(4, pagoId);      // pago_id
	            
	            st.executeUpdate(sql);
	            st.close();

	            logger.info("Envio borrado correctamente.");
	        } catch (SQLException ex) {
	            // Manejo de errores
	            logger.warning("Error borrando el envio: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	    
	    
	    
	   /* public static List<Envio> obtenerTodosLosEnvios(Connection con) {
	        String sql = "SELECT * FROM envio";
	        List<Envio> envios = new ArrayList<>();
	        try {
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);

	            while (rs.next()) {
	                Envio envio = new Envio();
	                // Asumiendo que puedes obtener los objetos trayecto, paquete, recogida y pago de la base de datos
	                envio.setTrayecto(new trayecto(rs.getString("trayecto_id")));
	                envio.setPaquete(new Paquete(rs.getString("paquete_id")));
	                envio.setRecogida(new Recogida(rs.getString("recogida_id")));
	                envio.setPago(new Pago(rs.getString("pago_id")));

	                envios.add(envio);
	            }

	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo envios: " + e.getMessage());
	        }
	        return envios;
	    }*/


//PAQUETE
	    
	    /*				n_referencia VARCHAR(50) NOT NULL,\r\n"
	            + "    embalaje VARCHAR(50),\r\n"
	            + "    peso VARCHAR(50),\r\n"
	            + "    largo VARCHAR(50),\r\n"
	            + "    ancho VARCHAR(50),\r\n"
	            + "    alto VARCHAR(50),\r\n"
	            + "    valor VARCHAR(50),\r\n"
	            + "    fragil VARCHAR(10),\r\n"
	            + "    PRIMARY KEY (n_referencia)\r\n*/
	    
	    public static List<Paquete> buscarPaquetePorReferencia(Connection con, String nReferencia) {
		    List<Paquete> paquetes = new ArrayList<>();
		    //CAMBIO
		    String sql = "SELECT * FROM articulo WHERE CATEGORIA = ?";
		    
		    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		        pstmt.setString(1, nReferencia);
		        ResultSet rs = pstmt.executeQuery();
		        
		        while (rs.next()) {
		            String n_referencia = rs.getString("n_referencia");
		            String embalaje = rs.getString("embalaje");
		            String peso = rs.getString("peso");
		            String largo = rs.getString("largo");
		            String ancho = rs.getString("ancho");
		            String alto = rs.getString("alto");
		            String valor = rs.getString("valor");
		            String fragil = rs.getString("fragil");
		            
		            paquetes.add(new Paquete(n_referencia, embalaje, peso, largo, ancho, alto, valor, fragil));
		            System.out.println("Paquete encontrado: " + paquetes); 
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println("Número total de paquetes encontrados: " + paquetes.size());
		    return paquetes;
		}
	    
	    
	    public static void insertarRegistroDePrueba(Connection con) {
	        try {
	            // Insertar en la tabla trayecto
	            String sqlTrayecto = "INSERT INTO trayecto (nombre_origen, direccion_origen, correo_origen, telefono_origen, nombre_destino, direccion_destino, correo_destino, telefono_destino) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stTrayecto = con.prepareStatement(sqlTrayecto);
	            stTrayecto.setString(1, "Origen1");
	            stTrayecto.setString(2, "Direccion Origen 1");
	            stTrayecto.setString(3, "correo@origen.com");
	            stTrayecto.setString(4, "123456789");
	            stTrayecto.setString(5, "Destino1");
	            stTrayecto.setString(6, "Direccion Destino 1");
	            stTrayecto.setString(7, "correo@destino.com");
	            stTrayecto.setString(8, "987654321");
	            stTrayecto.executeUpdate();
	            stTrayecto.close();
	            
	            // Insertar en la tabla paquete (si es necesario)
	            String sqlPaquete = "INSERT INTO paquete (n_referencia, embalaje, peso, largo, ancho, alto, valor, fragil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stPaquete = con.prepareStatement(sqlPaquete);
	            stPaquete.setString(1, "REF001");
	            stPaquete.setString(2, "Caja");
	            stPaquete.setString(3, "5kg");
	            stPaquete.setString(4, "50cm");
	            stPaquete.setString(5, "30cm");
	            stPaquete.setString(6, "20cm");
	            stPaquete.setString(7, "100");
	            stPaquete.setString(8, "No");
	            stPaquete.executeUpdate();
	            stPaquete.close();
	            
	            System.out.println("Registros de prueba insertados.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}
