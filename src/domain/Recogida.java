package domain;

public class Recogida {

	String fechaDeRecogida;
	String lugarDeRecogida;
	String tipoDeEnvio;
	
	public Recogida(String fechaDeRecogida, String lugarDeRecogida, String tipoDeEnvio) {
		this.fechaDeRecogida = fechaDeRecogida;
		this.lugarDeRecogida = lugarDeRecogida;
		this.tipoDeEnvio = tipoDeEnvio;
	}

	public Recogida(String tipoDeEnvio) {
		this.tipoDeEnvio = tipoDeEnvio;
	}

	public String getFechaDeRecogida() {
		return fechaDeRecogida;
	}

	public void setFechaDeEnvio(String fechaDeRecogida) {
		this.fechaDeRecogida = fechaDeRecogida;
	}

	public String getLugarDeRecogida() {
		return lugarDeRecogida;
	}

	public void setLugarDeRecogida(String lugarDeRecogida) {
		this.lugarDeRecogida = lugarDeRecogida;
	}

	public String getTipoDeEnvio() {
		return tipoDeEnvio;
	}

	public void setTipoDeEnvio(String tipoDeEnvio) {
		this.tipoDeEnvio = tipoDeEnvio;
	}

	@Override
	public String toString() {
		return "Recogida [fechaDeEnvio=" + fechaDeRecogida + ", lugarDeRecogida=" + lugarDeRecogida + ", tipoDeEnvio="
				+ tipoDeEnvio + "]";
	}
	
}
