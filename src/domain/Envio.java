package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Envio {
	
	Trayecto trayecto;
	Paquete paquete;
	Recogida recogida;
	Pago pago;
	
	public Envio(Trayecto trayecto, Paquete paquete, Recogida recogida, Pago pago) {
		super();
		this.trayecto = trayecto;
		this.paquete = paquete;
		this.recogida = recogida;
		this.pago = pago;
	}

 

	public Envio(String dO, String dD, String nRefe, String fRec, String dni, String precio) {
		dO = trayecto.getDireccionOrigen();
		dD = trayecto.getDireccionDestino();
		nRefe = paquete.getnReferencia();
		fRec = recogida.getFechaDeRecogida();
		dni = pago.getDni();
		precio = pago.getPrecio();
	}

	 public String getEstado() {
		LocalDate fechaRecogida = LocalDate.parse(recogida.getFechaDeRecogida());
	    LocalDate fechaActual = LocalDate.now();

	    long diasTranscurridos = ChronoUnit.DAYS.between(fechaActual,fechaRecogida);

	    if (diasTranscurridos >= 10) {
	        return "Pendiente";
	    } else if (diasTranscurridos >= 6 && diasTranscurridos <= 9) {
	        return "En tránsito";
	    } else if (diasTranscurridos >= 0 && diasTranscurridos <= 5) {
	        return "Enviado";
	    } else {
	        return "Fecha inválida"; 
	    }
    }

	public Trayecto getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(Trayecto trayecto) {
		this.trayecto = trayecto;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public Recogida getRecogida() {
		return recogida;
	}

	public void setRecogida(Recogida recogida) {
		this.recogida = recogida;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	@Override
	public String toString() {
		return "Envio [trayecto=" + trayecto + ", paquete=" + paquete + ", recogida=" + recogida + ", pago=" + pago
				+ "]";
	}
	

}
