package recursividad;

import java.util.ArrayList;
import java.util.List;

import db.BaseDatosConfiguracion;
import domain.Envio;
import domain.Pago;
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
        // Crear datos ficticios de usuarios, envíos, paquetes, etc.
        List<Usuario> usuarios = new ArrayList<>();

        // Crear usuarios ficticios
        Usuario usuario1 = new Usuario("Juan", "Pérez", "123456789", "juan@example.com", "respuesta1", "pregunta1", "password123", new ArrayList<>());
        Usuario usuario2 = new Usuario("Ana", "Gómez", "987654321", "ana@example.com", "respuesta2", "pregunta2", "password456", new ArrayList<>());
        
        // Crear envíos ficticios para usuario1
        Trayecto trayecto1 = new Trayecto("Madrid", "Barcelona");
        Paquete paquete1 = new Paquete("123", "Caja", "5.0", "10", "10", "10", "10", "10");
        Recogida recogida1 = new Recogida("2025-01-20", "Madrid", "Normal");
        Envio envio1 = new Envio(trayecto1, paquete1, recogida1, null);
        usuario1.addEnvio(envio1);

        // Crear envíos ficticios para usuario2
        Trayecto trayecto2 = new Trayecto("Sevilla", "Barcelona");
        Paquete paquete2 = new Paquete("456", "Sobre", "2.0", "5", "5", "5", "5", "5");
        Recogida recogida2 = new Recogida("2025-01-21", "Sevilla", "Urgente");
        Envio envio2 = new Envio(trayecto2, paquete2, recogida2, null);
        usuario2.addEnvio(envio2);

        // Agregar usuarios a la lista
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Llamar al método recursivo para calcular el peso total de los envíos a Barcelona
        List<Double> pesosTotales = calcularPesoPorDestino(usuarios, "Barcelona");

        // Sumar los pesos totales
        double pesoTotal = 0.0;
        for (Double peso : pesosTotales) {
            pesoTotal += peso;
        }

        // Mostrar el resultado
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




