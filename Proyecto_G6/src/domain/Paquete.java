package domain;

public class Paquete {
	
	String nReferencia;
	String embalaje;
	String peso;
	String largo;
	String ancho;
	String alto;
	String valor;
	String fragil;
	
	public Paquete(String nReferencia, String embalaje, String peso, String largo, String ancho, String alto, String valor,
			String fragil) {
		this.nReferencia = nReferencia;
		this.embalaje = embalaje;
		this.peso = peso;
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
		this.valor = valor;
		this.fragil = fragil;
	}
	
	public Paquete(String peso, String largo, String ancho, String alto) {
		this.peso = peso;
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
	}

	public String getnReferencia() {
		return nReferencia;
	}

	public void setnReferencia(String nReferencia) {
		this.nReferencia = nReferencia;
	}

	public String getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getLargo() {
		return largo;
	}

	public void setLargo(String largo) {
		this.largo = largo;
	}

	public String getAncho() {
		return ancho;
	}

	public void setAncho(String ancho) {
		this.ancho = ancho;
	}

	public String getAlto() {
		return alto;
	}

	public void setAlto(String alto) {
		this.alto = alto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFragil() {
		return fragil;
	}

	public void setFragil(String fragil) {
		this.fragil = fragil;
	}

	@Override
	public String toString() {
		return "Paquete [nReferencia=" + nReferencia + ", embalaje=" + embalaje + ", peso=" + peso + ", largo=" + largo
				+ ", ancho=" + ancho + ", alto=" + alto + ", valor=" + valor + ", fragil=" + fragil + "]";
	}
	
}

