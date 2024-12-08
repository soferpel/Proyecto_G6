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

import javax.swing.JComboBox;

import domain.Envio;
import domain.Pago;
import domain.Paquete;
import domain.Recogida;
import domain.Usuario;
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
	            + "    trayecto_nombre_origen VARCHAR(100),"
	            + "    trayecto_nombre_destino VARCHAR(100),"
	            + "    paquete_id VARCHAR(50),"
	            + "    recogida_id VARCHAR(50),"
	            + "    pago_id VARCHAR(20),"
	            + "    PRIMARY KEY (trayecto_nombre_origen, trayecto_nombre_destino, paquete_id, recogida_id, pago_id),"
	            + "    FOREIGN KEY (trayecto_nombre_origen, trayecto_nombre_destino) REFERENCES trayecto(nombre_origen, nombre_destino),"
	            + "    FOREIGN KEY (paquete_id) REFERENCES paquete(n_referencia),"
	            + "    FOREIGN KEY (recogida_id) REFERENCES recogida(fecha_de_recogida),"
	            + "    FOREIGN KEY (pago_id) REFERENCES pago(dni)"
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
	    
	    public static void insertarTrayecto(Connection con, trayecto t) {
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
	    
	    public static trayecto obtenerTrayectoPorId(Connection con, String trayectoId) {
	        trayecto t = null;
	        String sql = "SELECT * FROM trayecto WHERE nombre_origen || ' - ' || nombre_destino = ?";
	        try {
	            PreparedStatement st = con.prepareStatement(sql);  
	            st.setString(1, trayectoId);
	            ResultSet rs = st.executeQuery();
	            
	            if (rs.next()) {
	                t = new trayecto(rs.getString("nombre_origen"), rs.getString("nombre_destino"));
	                
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
	    
	    public static void insertarEnvio(Connection con, Envio e) {
	        String sql = "INSERT INTO envio (trayecto_id, paquete_id, recogida_id, pago_id) VALUES (?, ?, ?, ?)";

	        try {
	            PreparedStatement st = con.prepareStatement(sql);
	            
	            st.setString(1, e.getTrayecto().getNombreOrigen() + " - " + e.getTrayecto().getNombreDestino()); 
	            st.setString(2, e.getPaquete().getnReferencia());  // 			MIRAR
	            st.setString(3, e.getRecogida().getLugarDeRecogida());  
	            st.setString(4, e.getPago().getDni()); 

	            
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
	    
	    
	    
	    public static List<Envio> obtenerTodosLosEnvios(Connection con) {
	        String sql = "SELECT * FROM envio";
	        List<Envio> envios = new ArrayList<>();
	        Envio envio = null;
	        
	        try {
	        	PreparedStatement st = con.prepareStatement(sql);
	            ResultSet rs = st.executeQuery();

	            while (rs.next()) {
	                
	                String trayectoId = rs.getString("trayecto_id");
	                trayecto t = obtenerTrayectoPorId(con, trayectoId);  
	                envio.setTrayecto(t);
	                //envio.setTrayecto(new trayecto(rs.getString("trayecto_id")));
	                String paqueteId = rs.getString("paquete_id");
	                Paquete p = obtenerPaquetePorId(con, paqueteId);  
	                envio.setPaquete(p);
	                
	                String recogidaId = rs.getString("recogida_id");
	                Recogida r = obtenerRecogidaPorId(con, recogidaId);  
	                envio.setRecogida(r);
	                
	                String pagoId = rs.getString("pago_id");
	                Pago pa = obtenerPagoPorId(con, pagoId);  
	                envio.setPago(pa);
	                envios.add(envio);
	                
	            }

	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            logger.warning("Error obteniendo envios: " + e.getMessage());
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
	    
	    
	    public static void insertarRecogida(Connection con, Recogida r) {
			String sql = "INSERT INTO recogida(fecha_de_recogida, lugar_de_recogida, tipo_de_envio) VALUES (?,?,?)";
			
			try {
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, r.getFechaDeRecogida());
					st.setString(2, r.getLugarDeRecogida());
					st.setString(3, r.getTipoDeEnvio());
					
					
					st.executeUpdate();
					st.close();
		            logger.info("Recogida insertada correctamente");
		            
				} catch (SQLException e) {
					// TODO Auto-generated catch block
		            logger.warning("Error insertando recogida");

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
		            u = new Usuario(nombre, apellido, telefono, email, respuesta, pregunta_seg, contrasenia);
		        }
		        rs.close();
		        st.close();
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
		            logger.warning("No se encontr� un usuario con el correo especificado.");
		        }
		    } catch (SQLException ex) {
		        logger.warning("Error eliminando el usuario: " + ex.getMessage());
		    }
		}

		

	    
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
	  
}
