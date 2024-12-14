package domain;

public class Trayecto {
	
	String nombreOrigen;
	String direccionOrigen;
	String correoOrigen;
	String telefonoOrigen;
	String nombreDestino;
	String direccionDestino;
	String correoDestino;
	String telefonoDestino;
	
	public Trayecto(String nombreOrigen, String direccionOrigen, String correoOrigen, String telefonoOrigen,
			String nombreDestino, String direccionDestino, String correoDestino, String telefonoDestino) {
		this.nombreOrigen = nombreOrigen;
		this.direccionOrigen = direccionOrigen;
		this.correoOrigen = correoOrigen;
		this.telefonoOrigen = telefonoOrigen;
		this.nombreDestino = nombreDestino;
		this.direccionDestino = direccionDestino;
		this.correoDestino = correoDestino;
		this.telefonoDestino = telefonoDestino;
	}
	
	public Trayecto(String direccionOrigen, String direccionDestino) {
		this.direccionOrigen = direccionOrigen;
		this.direccionDestino = direccionDestino;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}
	
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	
	public String getDireccionOrigen() {
		return direccionOrigen;
	}
	
	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	
	public String getCorreoOrigen() {
		return correoOrigen;
	}
	
	public void setCorreoOrigen(String correoOrigen) {
		this.correoOrigen = correoOrigen;
	}
	
	public String getTelefonoOrigen() {
		return telefonoOrigen;
	}
	
	public void setTelefonoOrigen(String telefonoOrigen) {
		this.telefonoOrigen = telefonoOrigen;
	}
	
	public String getNombreDestino() {
		return nombreDestino;
	}
	
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	
	public String getDireccionDestino() {
		return direccionDestino;
	}
	
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}
	
	public String getCorreoDestino() {
		return correoDestino;
	}
	
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	
	public String getTelefonoDestino() {
		return telefonoDestino;
	}
	
	public void setTelefonoDestino(String telefonoDestino) {
		this.telefonoDestino = telefonoDestino;
	}
	
	public String getTrayectoId() {
        return nombreOrigen + " - " + nombreDestino;  
    }
	
	@Override
	public String toString() {
		return "Trayecto [nombreOrigen=" + nombreOrigen + ", direccionOrigen=" + direccionOrigen + ", correoOrigen="
				+ correoOrigen + ", telefonoOrigen=" + telefonoOrigen + ", nombreDestino=" + nombreDestino
				+ ", direccionDestino=" + direccionDestino + ", correoDestino=" + correoDestino + ", telefonoDestino="
				+ telefonoDestino + "]";
	}
	
}
