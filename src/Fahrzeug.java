
public class Fahrzeug {

	private String marke;
	private String modell;
	private String farbe;
	private double mietpreis; 
	private int sitzplatz;
	private int kilometerstand; 
	private String gemietetVon;
	
	
	public Fahrzeug (String marke, String modell, String farbe, double mietpreis, int sitzplatz, int kilometerstand)	{
	
	
	this.marke = marke;
	this.modell = modell;
	this.farbe = farbe;
	this.mietpreis = mietpreis;
	this.sitzplatz = sitzplatz;
	this.kilometerstand = kilometerstand;
}


public String getMarke () {
	
	return marke;
}

public void setMarke (String marke) {
	
	this.marke = marke;
}

public String getModell () {
	
	return modell;
}

public void setModell (String modell) {
	
	this.modell = modell;
}

public String getFarbe () {
	
	return farbe;
}

public void setFarbe (String farbe) {
	
	this.farbe = farbe;
}


public double getMietpreis () {
	
	return mietpreis;
}

public void setMietpreis (double mietpreis) {
	
	this.mietpreis = mietpreis;
}




public int getSitzplatz () {
	
	return sitzplatz;
}

public void setSitzplatz (int sitzplatz) {
	
	this.sitzplatz = sitzplatz;
}



public int getKilometerstand () {
	
	return kilometerstand;
}

public void setKilometerstand (int kilometerstand) {
	
	this.kilometerstand = kilometerstand;
}

public void setGemietetVon(String customerID) {
	gemietetVon = customerID;
}

public String getGemietetCon() {
	return gemietetVon;
}




}
