package domain;

public class Usuario  {
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	private String respuesta;
	private String PreguntaSeg;
	private String contrasenia;
	
	public Usuario(String nombre, String apellido, String telefono, String correo, String respuesta,
			String preguntaSeg, String contrasenia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.respuesta = respuesta;
		this.PreguntaSeg = preguntaSeg;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getPreguntaSeg() {
		return PreguntaSeg;
	}

	public void setPreguntaSeg(String preguntaSeg) {
		PreguntaSeg = preguntaSeg;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo
				+ ", respuesta=" + respuesta + ", PreguntaSeg=" + PreguntaSeg
				+ ", contrasenia=" + contrasenia + "]";
	}

	
}
