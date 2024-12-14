package domain;

public class Presupuesto {
	
    private String direccionDesde;
   
    private String direccionHasta;
   
    private String largo;
    private String alto;
    private String ancho;
    private String peso;

    
    public String getDireccionDesde() { return direccionDesde; }
    public void setDireccionDesde(String direccionDesde) { this.direccionDesde = direccionDesde; }

    public String getDireccionHasta() { return direccionHasta; }
    public void setDireccionHasta(String direccionHasta) { this.direccionHasta = direccionHasta; }

    
    public String getLargo() { return largo; }
    public void setLargo(String largo) { this.largo = largo; }

    public String getAlto() { return alto; }
    public void setAlto(String string) { this.alto = string; }

    public String getAncho() { return ancho; }
    public void setAncho(String ancho) { this.ancho = ancho; }

    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }

    public int PrecioBaseAlto() {
        int medida = Integer.parseInt(alto);  
        if (medida <= 15) {
            return 2;
        } else if (medida <= 30) {
            return 4;
        } else {
            return 10;
        }
    }

    public int PrecioBaseAncho() {
        int medida = Integer.parseInt(ancho);  
        if (medida <= 15) {
            return 3;
        } else if (medida <= 30) {
            return 5;
        } else {
            return 7;
        }
    }

    public int PrecioBaseLargo() {
        int medida = Integer.parseInt(largo);  
        if (medida <= 15) {
            return 2;
        } else if (medida <= 30) {
            return 3;
        } else {
            return 11;
        }
    }
    
    public int calcularPrecioTotal() {
        return PrecioBaseAlto() + PrecioBaseAncho() + PrecioBaseLargo();
    }

}
