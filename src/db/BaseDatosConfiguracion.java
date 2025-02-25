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
import domain.Usuario;
import domain.Trayecto;


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
			//con.createStatement().execute("PRAGMA foreign_keys = ON;");
					
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
	public static void crearTablas(Connection con) throws SQLException {

	    String sql = "CREATE TABLE IF NOT EXISTS trayecto ("
	            + "    nombre_origen VARCHAR(100),"
	            + "    direccion_origen VARCHAR(255),"
	            + "    correo_origen VARCHAR(100),"
	            + "    telefono_origen VARCHAR(20),"
	            + "    nombre_destino VARCHAR(100),"
	            + "    direccion_destino VARCHAR(255),"
	            + "    correo_destino VARCHAR(100),"
	            + "    telefono_destino VARCHAR(20),"
	            + "    PRIMARY KEY (nombre_origen, nombre_destino)"
	            + ");";
	    
	    String sql2 = "CREATE TABLE IF NOT EXISTS paquete ("
	            + "    n_referencia VARCHAR(50) NOT NULL,"
	            + "    embalaje VARCHAR(50),"
	            + "    peso VARCHAR(50),"
	            + "    largo VARCHAR(50),"
	            + "    ancho VARCHAR(50),"
	            + "    alto VARCHAR(50),"
	            + "    valor VARCHAR(50),"
	            + "    fragil VARCHAR(10),"
	            + "    PRIMARY KEY (n_referencia)"
	            + ");";

	    String sql3 = "CREATE TABLE IF NOT EXISTS recogida ("
	            + "    fecha_de_recogida VARCHAR(50),"
	            + "    lugar_de_recogida VARCHAR(255),"
	            + "    tipo_de_envio VARCHAR(50),"
	            + "    PRIMARY KEY (fecha_de_recogida)"
	            + ");";

	    String sql4 = "CREATE TABLE IF NOT EXISTS pago ("
	            + "    dni VARCHAR(20) PRIMARY KEY,"
	            + "    descripcion VARCHAR(255),"
	            + "    numero_tarjeta VARCHAR(16),"
	            + "    fecha_caducidad DATE,"
	            + "    cvv CHAR(3),"
	            + "    remitente_destinatario VARCHAR(255),"
	            + "    factura VARCHAR(255),"
	            + "    precio DECIMAL(10, 2)"
	            + ");";

	    String sql5 = "CREATE TABLE IF NOT EXISTS envio ("
	             + "   trayecto_id VARCHAR(200),"
	             + "   paquete_id VARCHAR(50),"
	             + "   recogida_id VARCHAR(50),"
	             + "   pago_id VARCHAR(20),"
	             + "   usuario_id VARCHAR(100),"
	             + "   PRIMARY KEY (trayecto_id),"
	             + "   FOREIGN KEY (trayecto_id) REFERENCES trayecto(trayecto_id),"
	             + "   FOREIGN KEY (paquete_id) REFERENCES paquete(n_referencia),"
	             + "   FOREIGN KEY (recogida_id) REFERENCES recogida(fecha_de_recogida),"
	             + "   FOREIGN KEY (pago_id) REFERENCES pago(dni),"
	             + "   FOREIGN KEY (usuario_id) REFERENCES usuario(correo)"
	             + ");";



	    String sql6 = "CREATE TABLE IF NOT EXISTS usuario ("
	            + "    nombre VARCHAR(100),"
	            + "    apellido VARCHAR(100),"
	            + "    telefono VARCHAR(20),"
	            + "    correo VARCHAR(100) UNIQUE,"
	            + "    respuesta VARCHAR(255),"
	            + "    pregunta_seg VARCHAR(255),"
	            + "    contrasenia VARCHAR(255),"
	            + "    PRIMARY KEY (correo)"
	            + ");";


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
	    String sql = "INSERT INTO pago (descripcion, numero_Tarjeta, fecha_Caducidad, CVV, remitente_Destinatario, factura, dni, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement st = con.prepareStatement(sql)) {
	        
	        st.setString(1, p.getDescripcion());
	        st.setString(2, p.getNumeroTarjeta()); 
	        st.setString(3, p.getFechaCaducidad());
	        st.setString(4, p.getCVV());
	        st.setString(5, p.getRemitenteDestinatario());
	        st.setString(6, p.getFactura());
	        st.setString(7, p.getDni());
	        st.setString(8, p.getPrecio());

	        
	        st.executeUpdate();
	        logger.info(String.format("Nuevo pago insertado: %s", p.toString()));
	    } catch (SQLException e) {
	        logger.warning(String.format("Error insertando pago %s: %s", p.toString(), e.getMessage()));
	        e.printStackTrace(); 
	    }
	}

	    
	    
	public static Pago buscarPago(Connection con, String dni) {
	    String sql = "SELECT * FROM pago WHERE DNI = ?";
	    Pago p = null;

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, dni); 

	        try (ResultSet rs = ps.executeQuery()) { 
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
	        }
	    } catch (SQLException e) {
	        System.err.println("Error buscando pago: " + e.getMessage());
	        e.printStackTrace(); 
	    }
	    return p;
	}

	    
	    public static Pago obtenerPagoPorId(Connection con, String pagoId) {
	        Pago p = null;
	        String sql = "SELECT * FROM pago WHERE dni = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql); 
	            st.setString(1, pagoId);
	            ResultSet rs = st.executeQuery();
	            
	            if (rs.next()) {
	                 p = new Pago(rs.getString("descripcion"), rs.getString("numero_tarjeta"), rs.getString("fecha_caducidad"),
	                             rs.getString("cvv"), rs.getString("remitente_destinatario"), rs.getString("factura"),
	                             rs.getString("dni"), rs.getString("precio"));
	                 
	                 System.out.println("Pago: " + p);
	            } else {
	                System.out.println("No se encontró un pago con el DNI: " + pagoId);	            
	            }
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo pago: " + e.getMessage());
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
	    
	    public static void insertarTrayecto(Connection con, Trayecto t) {
			String sql = "INSERT INTO trayecto(nombre_origen, direccion_origen, correo_origen, telefono_origen, nombre_destino, direccion_destino, correo_destino, telefono_destino) VALUES (?,?,?,?,?,?,?,?)";
			
			try {
					PreparedStatement st = con.prepareStatement(sql);
		            st.setString(1, t.getNombreOrigen());
				    st.setString(2, t.getDireccionOrigen());
		            st.setString(3, t.getCorreoOrigen());
		            st.setString(4, t.getTelefonoOrigen());
		            st.setString(5, t.getNombreDestino());
		            st.setString(6, t.getDireccionDestino());
		            st.setString(7, t.getCorreoDestino());
		            st.setString(8, t.getTelefonoDestino());
					
					
					st.executeUpdate();
					st.close();
		            logger.info("Trayecto insertado correctamente: " + t);
		            
				} catch (SQLException e) {
					// TODO Auto-generated catch block
		            logger.warning("Error insertando trayecto: " + t + " - " + e.getMessage());

				}
			}
	    
	    
	   
	    public static void borrarTrayecto(Connection con, String nombreOrigen, String nombreDestino) {
	        String sql = String.format("DELETE FROM trayecto WHERE nombre_origen='%s' AND nombre_destino='%s'", nombreOrigen, nombreDestino);
	        try {
	            Statement st = con.createStatement();
	            st.executeUpdate(sql);
	            st.close();
	            
	            logger.info("Trayecto borrado correctamente: " + nombreOrigen + " a " + nombreDestino);                   
	        } catch (SQLException e) {
	            logger.warning("Error borrando el trayecto: " + nombreOrigen + " a " + nombreDestino + " - " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    
	    
	    
	    public static void actualizarTrayecto(Connection con, Trayecto trayecto) {
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
	    
	    public static Trayecto obtenerTrayectoPorId(Connection con, String trayectoId) {
	        Trayecto t = null;
	        String sql = "SELECT * FROM trayecto WHERE nombre_origen || ' - ' || nombre_destino = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql);  
	            st.setString(1, trayectoId);
	            ResultSet rs = st.executeQuery();
	            
	            if (rs.next()) {
	                t = new Trayecto(rs.getString("nombre_origen"), rs.getString("nombre_destino"));
	                
	                System.out.println("Trayecto: " + t);
	            } else {
	                System.out.println("No se encontró un pago con el DNI: " + trayectoId);	            	           
	            }
	            
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo trayecto: " + e.getMessage());
	        }
	        return t;
	    }

	    
//ENVIO
	    
	    public static void insertarEnvio(Connection con, Envio e, Usuario u) {
	        String sql = "INSERT INTO envio VALUES (?, ?, ?, ?, ?)";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, e.getTrayecto().getNombreOrigen() + " - " + e.getTrayecto().getNombreDestino()); 
	            st.setString(2, e.getPaquete().getnReferencia());  // 			MIRAR
	            st.setString(3, e.getRecogida().getLugarDeRecogida());  
	            st.setString(4, e.getPago().getDni()); 
	            st.setString(5, u.getCorreo()); 

	            
	            st.executeUpdate();
	            st.close();
 
	            logger.info("Envio insertado correctamente.");
	        } catch (SQLException ex) {
	        	System.out.println("Error al insertar el envio: " + ex.getMessage());
	        	logger.warning(String.format("Error insertando envio %s", e.toString()));
	            
	        }
	    }
	    
	    public static void borrarEnvio(Connection con, String trayectoId, String paqueteId, String recogidaId, String pagoId) {	        // 
	        String sql = "DELETE FROM envio WHERE trayecto_id = ? AND paquete_id = ? AND recogida_id = ? AND pago_id = ?";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, trayectoId);  
	            st.setString(2, paqueteId);  
	            st.setString(3, recogidaId);  
	            st.setString(4, pagoId);      
	            
	            st.executeUpdate();
	            st.close();

	            logger.info("Envio borrado correctamente.");
	        } catch (SQLException ex) {
	            logger.warning("Error borrando el envio: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	    
	    public static void eliminarEnvio(Connection con, String paqueteId) throws SQLException {
	        String sql = "DELETE FROM envio WHERE paquete_id = ?";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setString(1, paqueteId);
	            stmt.executeUpdate();
	        }
	    }	    	  
	   
		
	    
	    public static List<Envio> obtenerEnviosClientes(Connection con) {
	        String sql = "SELECT * FROM usuario";	       

	        List<Envio> l = new ArrayList<>();
	        try {
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);
	            
	            while (rs.next()) {
	                String nr = rs.getString("n_referencia");
	                String p = rs.getString("precio");
	                String desc = rs.getString("descripcion");
	                @SuppressWarnings("unused")
					String es = rs.getString("estado");
	                String fr = rs.getString("fecha_de_recogida");

	                // Verifica los datos obtenidos
	                System.out.println("n_referencia: " + nr + ", precio: " + p + ", descripcion: " + desc);

	                Paquete paquete = new Paquete(nr);
	                Recogida recogida = new Recogida(fr);
	                Trayecto trayecto = new Trayecto("Origen", "Destino"); // Asegúrate de que estos datos sean correctos
	                Pago pago = new Pago(desc, "Remitente/Destino", "Factura", "Dni", p);

	                Envio envio = new Envio(trayecto, paquete, recogida, pago);
	                l.add(envio);
	            }
	            
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo lista de clientes");
	            e.printStackTrace();  // Imprime la excepción para ayudarte a depurar
	        }
	        return l;
	    }

	    		          
	    
	    public static List<Envio> obtenerEnviosPorUsuario(Connection con, String correo) {
	        String sql = "SELECT * FROM envio WHERE correo = ?";
	        List<Envio> envios = new ArrayList<>();
	        

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setString(1, correo);  
	            ResultSet rs = st.executeQuery();

	            while (rs.next()) {
	            	
	            	
	                String trayectoId = rs.getString("trayecto_id");
	                Trayecto t = obtenerTrayectoPorId(con, trayectoId);  

	                String paqueteId = rs.getString("paquete_id");
	                Paquete p = obtenerPaquetePorId(con, paqueteId);  

	                String recogidaId = rs.getString("recogida_id");
	                Recogida r = obtenerRecogidaPorId(con, recogidaId);  

	                String pagoId = rs.getString("pago_id");
	                Pago pa = obtenerPagoPorId(con, pagoId);  

	                Envio envio = new Envio(t,p,r,pa);
	                envios.add(envio);
	            }

	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            System.err.println("Error obteniendo envíos del usuario: " + e.getMessage());
	        }

	        return envios;
	    }


//PAQUETE
	    
	    public static void insertarPaquete(Connection con, Paquete p) {
	        String sql = "INSERT INTO paquete (n_referencia, embalaje, peso, largo, ancho, alto, valor, fragil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setString(1, p.getnReferencia());
	            st.setString(2, p.getEmbalaje());
	            st.setString(3, p.getPeso());
	            st.setString(4, p.getLargo());
	            st.setString(5, p.getAncho());
	            st.setString(6, p.getAlto());
	            st.setString(7, p.getValor());
	            st.setString(8, p.getFragil());

	            st.executeUpdate();
	            System.out.println("Paquete insertado correctamente.");
	        } catch (SQLException e) {
	            System.err.println("Error insertando paquete: " + e.getMessage());
	        }
	    }

	    public static Paquete buscarPaquete(Connection con, String nReferencia) {
	        String sql = "SELECT * FROM paquete WHERE n_referencia = ?";
	        Paquete paquete = null;

	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nReferencia);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                String embalaje = rs.getString("embalaje");
	                String peso = rs.getString("peso");
	                String largo = rs.getString("largo");
	                String ancho = rs.getString("ancho");
	                String alto = rs.getString("alto");
	                String valor = rs.getString("valor");
	                String fragil = rs.getString("fragil");

	                paquete = new Paquete(nReferencia, embalaje, peso, largo, ancho, alto, valor, fragil);
	                System.out.println("Paquete encontrado: " + paquete);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error buscando paquete: " + e.getMessage());
	        }
	        return paquete;
	    }

	    public static void borrarPaquete(Connection con, String nReferencia) {
	        String sql = "DELETE FROM paquete WHERE n_referencia = ?";

	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setString(1, nReferencia);
	            st.executeUpdate();
	            System.out.println("Paquete borrado correctamente.");
	        } catch (SQLException e) {
	            System.err.println("Error borrando paquete: " + e.getMessage());
	        }
	    }
	    
	    public static List<Paquete> buscarPaquetePorReferencia(Connection con, String nReferencia) {
		    List<Paquete> paquetes = new ArrayList<>();
		    //CAMBIO
		    String sql = "SELECT * FROM paquete WHERE n_referencia = ?";

		    
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
		            
		            Paquete p = new Paquete(n_referencia, embalaje, peso, largo, ancho, alto, valor, fragil);
		            paquetes.add(p);
		            System.out.println("Paquete encontrado: " + p); 
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println("Número total de paquetes encontrados: " + paquetes.size());
		    return paquetes;
		}
	    
	    public static Paquete obtenerPaquetePorId(Connection con, String paqueteId) {
	        Paquete p = null;
	        String sql = "SELECT * FROM paquete WHERE n_referencia = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql); 
	            st.setString(1, paqueteId);
	            ResultSet rs = st.executeQuery();
	            
	            if (rs.next()) {
	                p = new Paquete(rs.getString("n_referencia"), rs.getString("embalaje"), rs.getString("peso"), 
	                                rs.getString("largo"), rs.getString("ancho"), rs.getString("alto"), 
	                                rs.getString("valor"), rs.getString("fragil"));
	                
	                System.out.println("Paquete: " + p);
	            } else {
	                System.out.println("No se encontró un pago con el DNI: " + paqueteId);	            	           
	            }
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo paquete: " + e.getMessage());
	        }
	        return p;
	    }
	    
	   

//RECOGIDA
	    public static Recogida obtenerRecogidaPorId(Connection con, String recogidaId) {
	        Recogida r = null;
	        String sql = "SELECT * FROM recogida WHERE fecha_de_recogida = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql); 
	            st.setString(1, recogidaId);
	            ResultSet rs = st.executeQuery();
	            
	            if (rs.next()) {
	                r = new Recogida(rs.getString("fecha_de_recogida"), rs.getString("lugar_de_recogida"), 
	                		rs.getString("tipo_de_envio"));
	                
	                System.out.println("Recogida: " + r);
	            } else {
	                System.out.println("No se encontró un pago con el DNI: " + recogidaId);	            	           	            
	            }
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo recogida: " + e.getMessage());
	        }
	        return r;
	    }
	    
	    
	    public static void insertarRecogida(Connection con, Recogida r) throws SQLException {
	        String sql = "INSERT INTO recogida (fecha_de_recogida, lugar_de_recogida, tipo_de_envio) VALUES (?, ?, ?)";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            if (r.getFechaDeRecogida() == null || r.getFechaDeRecogida().isEmpty()) {
	                st.setNull(1, java.sql.Types.DATE);
	            } else {
	                st.setString(1, r.getFechaDeRecogida());
	            }

	            if (r.getLugarDeRecogida() == null || r.getLugarDeRecogida().isEmpty()) {
	                st.setNull(2, java.sql.Types.VARCHAR);
	            } else {
	                st.setString(2, r.getLugarDeRecogida());
	            }

	            st.setString(3, r.getTipoDeEnvio());

	            st.executeUpdate();
	            st.close();
	            logger.info("Recogida insertada correctamente.");
	        } catch (SQLException e) {
	            logger.warning("Error insertando recogida: " + e.getMessage());
	            throw e;
	        }
	    }

	    
	    public static Recogida buscarRecogida(Connection con, String fecha_de_recogida) {
			String sql = String.format("SELECT * FROM recogida WHERE fecha_de_recogida = '%s'", fecha_de_recogida);
	        Recogida r = null;
	        try {
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, fecha_de_recogida); 
	            ResultSet rs = ps.executeQuery(); 

	            if (rs.next()) { 
	                String fechaDeRecogida = rs.getString("fecha_de_recogida");
	                String lugarDeRecogida = rs.getString("lugar_de_recogida"); 
	                String tipoDeEnvio = rs.getString("tipo_de_envio");
	                r = new Recogida(fechaDeRecogida, lugarDeRecogida, tipoDeEnvio);
	            }

	            rs.close();
	            ps.close();
	        } catch (SQLException e) {
	            logger.warning("Error buscando recogida: " + e.getMessage());
	        }
	        return r;	       	   	    
	}
	    
	    
	    public static void borrarRecogida(Connection con, String fecha_de_recogida) {
	        String sql = "DELETE FROM recogida WHERE fecha_de_recogida = ?";

	        try {
	        	PreparedStatement st = con.prepareStatement(sql);
		            
		        st.setString(1, fecha_de_recogida);	            
	            
	            st.executeUpdate();
	            st.close();
	            
	            logger.info("Recogida borrada");
	        } catch (SQLException e) {
	            logger.warning("Error borrando la recogida");
	        }
	    }

//USUARIO	
	    public static List<Usuario> obtenerUsuarios(Connection con) {
	        String sql = "SELECT * FROM usuario";
	        List<Usuario> usuarios = new ArrayList<>();
	        try (PreparedStatement st = con.prepareStatement(sql);
	             ResultSet rs = st.executeQuery()) {

	            while (rs.next()) {
	                String correo = rs.getString("correo");
	                Usuario usuario = buscarUsuarioPorCorreo(con, correo);
	                usuarios.add(usuario);
	            }
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo usuarios: " + e.getMessage());
	        }
	        return usuarios;
	    }

		
		@SuppressWarnings("unused")
		public static Usuario buscarUsuarioPorCorreo(Connection con, String correo) {
		    String sql = "SELECT * FROM usuario WHERE correo = ?";
		    Usuario u = null;
		    try {
		        PreparedStatement st = con.prepareStatement(sql);
		        st.setString(1, correo);
		        ResultSet rs = st.executeQuery();
		        
		        if (rs.next()) {
		            String nombre = rs.getString("nombre");
		            String apellido = rs.getString("apellido");
		            String telefono = rs.getString("telefono");
		            String email = rs.getString("correo");
		            String respuesta = rs.getString("respuesta");		           
		            String pregunta_seg = rs.getString("pregunta_seg");
		            String contrasenia = rs.getString("contrasenia");
		            u = new Usuario(nombre, apellido, telefono, email, respuesta, pregunta_seg, contrasenia, new ArrayList<Envio>());
		        }
		        
			    sql = "SELECT * FROM envio e, paquete p, recogida r, pago pa WHERE usuario_id = ? AND e.paquete_id = p.n_referencia AND e.recogida_id = r.lugar_de_recogida AND e.pago_id = pa.dni";
		        PreparedStatement st2 = con.prepareStatement(sql);
		        st2.setString(1, correo);
		        ResultSet rs2 = st2.executeQuery();
		        
		        while (rs2.next()) {
		        	  // Obtener datos de 'envio'
		            String usuarioId = rs2.getString("usuario_id");
		            String paqueteId = rs2.getString("paquete_id");
		            String recogidaId = rs2.getString("recogida_id");
		            String pagoId = rs2.getString("pago_id");
		            String trayectoId = rs2.getString("trayecto_id");  // "origen - destino"

		            // Obtener datos de 'paquete'
		            String nReferencia = rs2.getString("n_referencia");
//		            String descripcionPaquete = rs2.getString("descripcion_paquete");
		            String embalaje = rs2.getString("embalaje");
		            String peso = rs2.getString("peso");
		            String alto = rs2.getString("alto");
		            String ancho = rs2.getString("ancho");
		            String largo = rs2.getString("largo");
		            Paquete p = new Paquete(nReferencia, embalaje, peso, largo, ancho, alto, ancho, largo);
		            
		            // Obtener datos de 'recogida'
		            String fechaRecogida = rs2.getString("fecha_de_recogida");
		            String lugarRecogida = rs2.getString("lugar_de_recogida");
		            String tipoEnvio = rs2.getString("tipo_de_envio");
		            Recogida r = new Recogida(fechaRecogida, lugarRecogida, tipoEnvio);
		            
		            // Obtener datos de 'pago'
		            String pagoDni = rs2.getString("dni");
		            String descripcion = rs2.getString("descripcion");
		            String numeroTarjeta = rs2.getString("numero_tarjeta");
		            String fechaCaducidad = rs2.getString("fecha_caducidad");
		            String cvv = rs2.getString("cvv");
		            String remitenteDest = rs2.getString("remitente_destinatario");
		            String factura = rs2.getString("factura");
		            String precio = rs2.getString("precio");
		            Pago pa = new Pago(descripcion, numeroTarjeta, fechaCaducidad, cvv, remitenteDest, factura, pagoDni, precio);
		            
		            // Obtener origen y destino del trayecto
		            String[] trayectos = trayectoId.split(" - ");
		            String origen = trayectos[0];
		            String destino = trayectos[1];
		            Trayecto t = new Trayecto(origen, destino);
		            
		        	Envio e = new Envio(t, p, r, pa);
		        	u.addEnvio(e);
		        }
		        rs.close();
		        st.close();
		        rs2.close();
		        st2.close();
		    } catch (SQLException e) {
		        logger.warning("Error buscando usuario: " + e.getMessage());
		    }
		    return u;
		}

	    	
		
		public static void insertarUsuario(Connection con, Usuario u) {
	        String sql = "INSERT INTO usuario (nombre, apellido, telefono, correo, respuesta, pregunta_seg, contrasenia) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, u.getNombre()); 
	            st.setString(2, u.getApellido());
	            st.setString(3, u.getTelefono());  
	            st.setString(4, u.getCorreo());
	            st.setString(5, u.getRespuesta());
	            st.setString(6, u.getPreguntaSeg());
	            st.setString(7, u.getContrasenia());

	            
	            st.executeUpdate();
	            st.close();
 	            
	            logger.info("Usuario insertado correctamente.");
	        } catch (SQLException ex) {
	        	System.out.println("Error al insertar el usuario: " + ex.getMessage());
	        	logger.warning(String.format("Error insertando usuario %s", u.toString()));
	            
	        }
	        
	    }
		
		public static void actualizarUsuario(Connection con, Usuario u) {
		    String sql = "UPDATE usuario SET nombre = ?, apellido = ?, telefono = ?, contrasenia = ? WHERE correo = ?";

		    try (PreparedStatement st = con.prepareStatement(sql)) {
		        st.setString(1, u.getNombre());
		        st.setString(2, u.getApellido());
		        st.setString(3, u.getTelefono());
		        st.setString(4, u.getContrasenia());
		        st.setString(5, u.getCorreo());

		        int rowsUpdated = st.executeUpdate();
		        if (rowsUpdated > 0) {
		            logger.info("Usuario actualizado correctamente.");
		        } else {
		            logger.warning("No se pudo actualizar el usuario. Verifica los datos.");
		        }
		    } catch (SQLException ex) {
		        logger.warning("Error actualizando usuario: " + ex.getMessage());
		    }
		}
		
		
		public static void deleteUsuario(Connection con, String correo) {
		    String sql = "DELETE FROM usuario WHERE correo = ?";
		    try {
		        PreparedStatement st = con.prepareStatement(sql);
		        st.setString(1, correo);
		        int filasAfectadas = st.executeUpdate();
		        st.close();

		        if (filasAfectadas > 0) {
		            logger.info("Usuario eliminado correctamente.");
		        } else {
		            logger.warning("No se encontr  un usuario con el correo especificado.");
		        }
		    } catch (SQLException ex) {
		        logger.warning("Error eliminando el usuario: " + ex.getMessage());
		    }
		}

//Todos los envios para el ADMIN
		
		/*public static List<Envio> obtenerEnvios(Connection con) throws SQLException {
		    List<Envio> envios = new ArrayList<>();
		    String query = "SELECT p.n_referencia AS 'Nº referencia', " +
		                   "r.fecha_de_recogida AS 'Fecha', " +
		                   "pa.precio AS 'Precio', " +
		                   "pa.descripcion AS 'Descripción', " +
		                   "e.estado_envio AS 'Estado', " +
		                   "r.fecha_de_recogida AS 'Fecha prevista' " +
		                   "FROM envio en " +
		                   "JOIN paquete p ON en.paquete_id = p.n_referencia " +
		                   "JOIN pago pa ON en.pago_id = pa.dni " +
		                   "JOIN recogida r ON en.recogida_id = r.fecha_de_recogida";

		    try (PreparedStatement pstmt = con.prepareStatement(query);
		         ResultSet rs = pstmt.executeQuery()) {

		        while (rs.next()) {
		            String referencia = rs.getString("Nº referencia");
		            String fecha = rs.getString("Fecha");
		            String precio = rs.getString("Precio");
		            String descripcion = rs.getString("Descripción");
		            String estado = rs.getString("Estado");
		            String fechaPrevista = rs.getString("Fecha prevista");

		            // Crear un objeto Envio (o un objeto que almacene estos datos)
		            Envio envio = new Envio(referencia, fecha, precio, descripcion, estado, fechaPrevista);
		            envios.add(envio);
		        }
		    }
		    return envios;
		}*/


	


		

	    
	   /* public static void insertarRegistroDePrueba(Connection con) {
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
	            
	            
	            
	            String insertRecogida = "INSERT INTO recogida (fecha_de_recogida, lugar_de_recogida, tipo_de_envio) VALUES (?, ?, ?)";
	            PreparedStatement psRecogida = con.prepareStatement(insertRecogida);
	            psRecogida.setString(1, "2024-12-04");
	            psRecogida.setString(2, "Calle Falsa 123");
	            psRecogida.setString(3, "Express");
	            psRecogida.executeUpdate();
	            psRecogida.close();
	            
	            String insertPago = "INSERT INTO pago (dni, descripcion, numero_tarjeta, fecha_caducidad, cvv, remitente_destinatario, factura, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement psPago = con.prepareStatement(insertPago);
	            psPago.setString(1, "12345678A");
	            psPago.setString(2, "Pago por servicio de envío");
	            psPago.setString(3, "4111111111111111");
	            psPago.setDate(4, java.sql.Date.valueOf("2025-05-31"));
	            psPago.setString(5, "123");
	            psPago.setString(6, "Juan Pérez");
	            psPago.setString(7, "Factura001");
	            psPago.setBigDecimal(8, new java.math.BigDecimal("49.99"));
	            psPago.executeUpdate();
	            psPago.close();
	            
	            String insertUsuario = "INSERT INTO usuario (nombre, apellido, telefono, correo, respuesta, pregunta_seg, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement psUsuario = con.prepareStatement(insertUsuario);
	            psUsuario.setString(1, "Juan");
	            psUsuario.setString(2, "Pérez");
	            psUsuario.setString(3, "600123456");
	            psUsuario.setString(4, "juan.perez@example.com");
	            psUsuario.setString(5, "Mi primera mascota");
	            psUsuario.setString(6, "¿Cuál es el nombre de tu mascota?");
	            psUsuario.setString(7, "password123");
	            psUsuario.executeUpdate();
	            psUsuario.close();
	            
	            String insertEnvio = "INSERT INTO envio (trayecto_id, paquete_id, recogida_id, pago_id) VALUES (?, ?, ?, ?)";
	            PreparedStatement psEnvio = con.prepareStatement(insertEnvio);
	            psEnvio.setString(1, "Origen1"  + " - " + "Destino1");
	            psEnvio.setString(2, "REF12345");
	            psEnvio.setString(3, "2024-12-04");
	            psEnvio.setString(4, "12345678A");
	            psEnvio.executeUpdate();
	            psEnvio.close();
	            
	            
	            System.out.println("Registros de prueba insertados.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }*/
	  
		public static List<Envio> cargarEnviosPorUsuario(Connection con, String correo) throws SQLException {
		    String query = "SELECT p.n_referencia AS referencia, pa.precio, pa.descripcion, r.fecha_de_recogida, e.trayecto_id " +
		                   "FROM envio e " +
		                   "JOIN paquete p ON e.paquete_id = p.n_referencia " +
		                   "JOIN pago pa ON e.pago_id = pa.dni " +
		                   "JOIN recogida r ON e.recogida_id = r.fecha_de_recogida " +
		                   "WHERE e.usuario_id = ?";

		    List<Envio> envios = new ArrayList<>();
		    System.out.println("Ejecutando consulta para cargar envíos del usuario: " + correo);

		    try (PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setString(1, correo);

		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                String referencia = rs.getString("referencia");
		                String precio = rs.getString("precio");
		                String descripcion = rs.getString("descripcion");
		                String fechaRecogida = rs.getString("fecha_de_recogida");

		                Paquete paquete = new Paquete(referencia);
		                Pago pago = new Pago(descripcion, "", "", "", "", "", "", precio);
		                Recogida recogida = new Recogida(fechaRecogida);
		                Trayecto trayecto = new Trayecto("Estado", fechaRecogida);

		                Envio envio = new Envio(trayecto, paquete, recogida, pago);
		                envios.add(envio);
		            }
		        }
		    } catch (SQLException e) {
		        System.err.println("Error ejecutando consulta: " + e.getMessage());
		        e.printStackTrace();
		    }
		    System.out.println("Envíos cargados: " + envios.size());
		    return envios;
		}

}
