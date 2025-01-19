package recursividad;

import java.util.ArrayList;
import java.util.List;

import domain.Envio;
import domain.Paquete;
import domain.Recogida;
import domain.Trayecto;
import domain.Usuario;

public class CalcularPesoTotalPorDestino {


	   //Calcula el peso total de los paquetes enviados a un destino específico.	   	     
	 private static void calcularPesoPorDestino(List<Double> result, List<Usuario> usuarios, int index, String destino, List<Envio> aux) {
        // Caso base
        if (index >= usuarios.size()) {
            double pesoTotal = 0.0;
            for (Envio envio : aux) {
                pesoTotal += Double.parseDouble(envio.getPaquete().getPeso());
            }
            result.add(pesoTotal);
            return;
        }

        Usuario usuarioActual = usuarios.get(index);
        for (Envio envio : usuarioActual.getListaEnvios()) {
            if (envio.getTrayecto().getDireccionDestino().equals(destino)) {
                aux.add(envio); 
                calcularPesoPorDestino(result, usuarios, index + 1, destino, aux);
                aux.remove(aux.size() - 1);
            }
        }
    }

    public static List<Double> calcularPesoPorDestino(List<Usuario> usuarios, String destino) {
        List<Double> result = new ArrayList<>();
        calcularPesoPorDestino(result, usuarios, 0, destino, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<>();

        // usuario 1
        Usuario usuario1 = new Usuario("Juan", "Pérez", "123456789", "juan@example.com", "respuesta1", "pregunta1", "password123", new ArrayList<>());
        Trayecto trayecto1 = new Trayecto("Madrid", "Barcelona");
        Paquete paquete1 = new Paquete("123", "Caja", "5.0", "10", "10", "10", "10", "10");
        Recogida recogida1 = new Recogida("2025-01-20", "Madrid", "Normal");
        Envio envio1 = new Envio(trayecto1, paquete1, recogida1, null);
        usuario1.addEnvio(envio1);

        // usuario 2
        Usuario usuario2 = new Usuario("Ana", "Gómez", "987654321", "ana@example.com", "respuesta2", "pregunta2", "password456", new ArrayList<>());
        Trayecto trayecto2 = new Trayecto("Sevilla", "Barcelona");
        Paquete paquete2 = new Paquete("456", "Sobre", "2.0", "5", "5", "5", "5", "5");
        Recogida recogida2 = new Recogida("2025-01-21", "Sevilla", "Urgente");
        Envio envio2 = new Envio(trayecto2, paquete2, recogida2, null);
        usuario2.addEnvio(envio2);

        // usuario 3
        Usuario usuario3 = new Usuario("Carlos", "Lopez", "456123789", "carlos@example.com", "respuesta3", "pregunta3", "password789", new ArrayList<>());
        Trayecto trayecto3 = new Trayecto("Madrid", "Valencia");
        Paquete paquete3 = new Paquete("789", "Caja", "4.0", "12", "12", "12", "12", "12");
        Recogida recogida3 = new Recogida("2025-01-22", "Madrid", "Normal");
        Envio envio3 = new Envio(trayecto3, paquete3, recogida3, null);
        usuario3.addEnvio(envio3);

        // usuario 4
        Usuario usuario4 = new Usuario("Laura", "Martínez", "321654987", "laura@example.com", "respuesta4", "pregunta4", "password321", new ArrayList<>());
        Trayecto trayecto4 = new Trayecto("Sevilla", "Barcelona");
        Paquete paquete4 = new Paquete("987", "Sobre", "3.0", "8", "8", "8", "8", "8");
        Recogida recogida4 = new Recogida("2025-01-23", "Sevilla", "Urgente");
        Envio envio4 = new Envio(trayecto4, paquete4, recogida4, null);
        usuario4.addEnvio(envio4);

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);

        List<Double> pesosTotales = calcularPesoPorDestino(usuarios, "Barcelona");

        System.out.println("Usuarios que han enviado paquetes a Barcelona:");
        for (Usuario usuario : usuarios) {
            boolean haEnviadoABarcelona = false;
            for (Envio envio : usuario.getListaEnvios()) {
                if (envio.getTrayecto().getDireccionDestino().equals("Barcelona")) {
                    haEnviadoABarcelona = true;
                    break;
                }
            }

            if (haEnviadoABarcelona) {
                System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
                for (Envio envio : usuario.getListaEnvios()) {
                    if (envio.getTrayecto().getDireccionDestino().equals("Barcelona")) {
                        System.out.println("      Paquete: " + envio.getPaquete().getnReferencia());
                        System.out.println("      Peso: " + envio.getPaquete().getPeso());
                        System.out.println("      Origen: " + envio.getTrayecto().getDireccionOrigen());
                        System.out.println("      Destino: " + envio.getTrayecto().getDireccionDestino());
                    }
                }
            }
        }

        double pesoTotal = 0.0;
        for (Double peso : pesosTotales) {
            pesoTotal += peso;
        }

        System.out.println("Peso total de los envíos a Barcelona: " + pesoTotal);
    }
	    
//    public static void main(String[] args) {
//    	Connection con = BaseDatosConfiguracion.obtenerConexion();  
//	    List<Usuario> usuarios = BaseDatosConfiguracion.obtenerUsuarios(null);
//	
//	    // Llamar al método recursivo para calcular el peso total de los envíos a un destino específico
//	    List<Double> pesosTotales = calcularPesoPorDestino(usuarios, "Barcelona");
//	
//	    // Sumar los pesos totales
//	    double pesoTotal = 0.0;
//	    for (Double peso : pesosTotales) {
//	        pesoTotal += peso;
//	    }
//	    System.out.println("Peso total de los envíos a Barcelona: " + pesoTotal);
//	}
    
}




