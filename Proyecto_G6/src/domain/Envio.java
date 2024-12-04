package domain;


public class Envio {
	
	trayecto trayecto;
	Paquete paquete;
	Recogida recogida;
	Pago pago;
	
	public Envio(trayecto trayecto, Paquete paquete, Recogida recogida, Pago pago) {
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
		fRec = recogida.getFechaDeEnvio();
		dni = pago.getDni();
		precio = pago.getPrecio();
	}


	public trayecto getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(trayecto trayecto) {
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
