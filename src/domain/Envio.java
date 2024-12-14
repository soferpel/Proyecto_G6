package domain;


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
	    // Si el pago está completo, la recogida ha sido realizada y el trayecto está finalizado
        if (pago != null && pago.getDescripcion().equals("Pagado") && 
            recogida != null && !recogida.getFechaDeRecogida().isEmpty() && 
            trayecto != null && trayecto.getNombreDestino() != null) {
            return "Enviado";
        } else if (pago != null && pago.getDescripcion().equals("Pendiente")) {
            return "Pendiente de pago";
        } else if (recogida != null && recogida.getFechaDeRecogida().isEmpty()) {
            return "Pendiente de recogida";
        } else if (trayecto != null && trayecto.getNombreDestino() == null) {
            return "En tránsito";
        } else {
            return "Estado desconocido";
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
