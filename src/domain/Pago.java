package domain;

public class Pago {
	
	String descripcion;
	String numeroTrajeta;
	String fechaCaducidad;
	String CVV;
	String remitenteDestinatario;
	String factura;
	String Dni;
	String precio;
	
	public Pago(String descripcion, String numeroTrajeta, String fechaCaducidad, String CVV,
			String remitenteDestinatario, String factura, String Dni, String precio) {
		super();
		this.descripcion = descripcion;
		this.numeroTrajeta = numeroTrajeta;
		this.fechaCaducidad = fechaCaducidad;
		this.CVV = CVV;
		this.remitenteDestinatario = remitenteDestinatario;
		this.factura = factura;
		this.Dni = Dni;
		this.precio = precio;
	}

	public Pago(String descripcion, String remitenteDestinatario, String factura, String Dni, String precio) {
		super();
		this.descripcion = descripcion;
		this.remitenteDestinatario = remitenteDestinatario;
		this.factura = factura;
		this.Dni = Dni;
		this.precio = precio;
	}

	public Pago() {
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getNumeroTrajeta() {
		return numeroTrajeta;
	}

	public void setNumeroTrajeta(String numeroTrajeta) {
		this.numeroTrajeta = numeroTrajeta;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String CVV) {
		this.CVV = CVV;
	}

	public String getRemitenteDestinatario() {
		return remitenteDestinatario;
	}

	public void setRemitenteDestinatario(String remitenteDestinatario) {
		this.remitenteDestinatario = remitenteDestinatario;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String Dni) {
		this.Dni = Dni;
	}
	
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Pago [descripcion=" + descripcion + ", numeroTrajeta=" + numeroTrajeta + ", fechaCaducidad="
				+ fechaCaducidad + ", CVV=" + CVV + ", remitenteDestinatario=" + remitenteDestinatario + ", Dni=" + Dni
				+ ", precio= "+ precio + "]";
	}
	
}
