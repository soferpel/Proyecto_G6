package domain;


public enum Dominio {

    GMAIL("@gmail.com"),
    HOTMAIL("@hotmail.com"),
	OUTLOOK("@outlook.com"),
	AOL("@aol.com"),
	APPLE("@icloud.com"),
	YAHOO("@yahoo.com"),
	PRUEBA("");
	
    private String dominio;

    Dominio(String dominio) {
    	this.dominio = dominio;
    	
    }

	public String getDominio() {
		return dominio;
	}

	
}
