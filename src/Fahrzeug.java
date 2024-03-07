
// imports
import java.io.Serializable;

// implement Serializable to be compatible to In/ Output Streams in main class
public class Fahrzeug implements Serializable {

	// implement Serializable
	private static final long serialVersionUID = 1L;
	// create a attributes for vehicle
	private String marke;
	private String modell;
	private String farbe;
	private double mietpreis; 
	private int sitzplatz;
	private int kilometerstand; 
	private String gemietetVon = null;
	private String kennzeichen;
	
	// constructor to create a car with all attributes
	public Fahrzeug (String marke, String modell, String farbe, String kennzeichen,
			int mietpreis, int sitzplatz, int kilometerstand)	{
	// write given values to instance attributes
	this.marke = marke;
	this.modell = modell;
	this.farbe = farbe;
	this.mietpreis = mietpreis;
	this.sitzplatz = sitzplatz;
	this.kilometerstand = kilometerstand;
	this.kennzeichen = kennzeichen;
	}

	// getter and setter
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

	public String getGemietetVon() {
		return gemietetVon;
	}
	
	public String getKennzeichen () {
		return kennzeichen;
	}
	
	// methods
	// display string of all information about this vehicle
	public String getAll() {
		return marke + " " + modell + "; " + farbe + "; " + sitzplatz + 
				"; " + mietpreis + "; " + kilometerstand;
	}






}
