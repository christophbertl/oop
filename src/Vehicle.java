
// imports
import java.io.Serializable;

// implement Serializable to be compatible to In/ Output Streams in main class
public class Vehicle implements Serializable, Comparable<Vehicle> {

	// implement Serializable
	private static final long serialVersionUID = 1L;
	// create a attributes for vehicle
	private String brand;
	private String model;
	private String color;
	private double price; 
	private int seating;
	private int mileage; 
	private String rentedBy = null;
	private String numberPlate;
	
	// constructor to create a car with all attributes
	public Vehicle (String brand, String model, String color, String numberPlate,
			int price, int seating, int mileage)	{
	// write given values to instance attributes
	this.brand = brand;
	this.model = model;
	this.color = color;
	this.price = price;
	this.seating = seating;
	this.mileage = mileage;
	this.numberPlate = numberPlate;
	}

	// getter and setter
	public String getBrand () {
		return brand;
	}

	public void setBrand (String brand) {
		this.brand = brand;
	}

	public String getModel () {
		return model;
	}

	public void setModel (String model) {
		this.model = model;
	}

	public String getColor () {
		return color;
	}

	public void setColor (String color) {
		this.color = color;
	}

	public double getPrice () {
		return price;
	}

	public void setPrice (double price) {
		this.price = price;
	}

	public int getSitzplatz () {
		return seating;
	}

	public void setSeating (int seating) {
		this.seating = seating;
	}

	public int getMileage () {
		return mileage;
	}

	public void setMileage (int mileage) {
		this.mileage = mileage;
	}

	public void setRentedBy(String customerID) {
		rentedBy = customerID;
	}

	public String getRentedBy() {
		return rentedBy;
	}
	
	public String getNumberPlate () {
		return numberPlate;
	}
	
	// methods
	// display string of all information about this vehicle
	@Override
	public String toString() {
		return brand + " " + model + "; " + color + "; " + seating + 
				"; " + price + "; " + mileage + "; Kennzeichen: " + numberPlate;
	}
	
	@Override
	public boolean equals(Object o) {
		
		// check if object is the same object
		if (this == o) return true;
		
		// check if object has the type "Fahrzeug"
		if (o instanceof Vehicle) {
			
			// cast object to correct type
			Vehicle car = (Vehicle) o;
			// compare by using equals of string class to compare number plates
			return getNumberPlate().equals(car.getNumberPlate());
		}
		else
			// use equals of object for objects of other types
			return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return numberPlate.hashCode();
	}

	@Override
	public int compareTo(Vehicle o) {
		return  (int) (price - o.getPrice());
	}


}
