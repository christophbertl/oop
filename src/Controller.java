import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
	public Map<String, Vehicle> cars = new HashMap<String, Vehicle>();
	public List<Customer> customers = new ArrayList<>();
	
	// write list of vehicles to file
	public void writeToFile(Map<String, Vehicle> cars) throws IOException {
		// create File and Object output stream
		FileOutputStream fileOut = new FileOutputStream("cars.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
        // write object "thru" streams to file
        out.writeObject(cars);
        
        // close streams
        out.close();
        fileOut.close();
	}
		
	// read vehicle list from file
	public Map<String, Vehicle> readFromFile() throws IOException, ClassNotFoundException {
		// create file and object input stream
		FileInputStream fileIn = new FileInputStream("fahrzeuge.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        // read object "thru" streams from file and cast it to list of vehicles; save to variable fahrzeuge
        @SuppressWarnings("unchecked")
        Map<String, Vehicle> cars = (Map<String, Vehicle>) in.readObject();
        
        // close streams
        in.close();
        fileIn.close();
        
        // return from file restored list of vehicles
        return cars;
	}
		
	// rent or return car
	public void rentACar (String kennzeichen, String nutzerID, Map<String, Vehicle> cars, int km) {
		// search car in list of vehicles
		for (Vehicle car : cars.values()) {
			// if the number plate matches
			if (car.getNumberPlate().equals(kennzeichen)) {
				// add / remove user (if userID == null -> user will be removed from car object)
				car.setRentedBy(nutzerID);
				
				// if user returns the car: write new km to car object
				if (nutzerID == null)  {
					car.setMileage(km);
					}
				
				// stop iteration if car was found
				break;
			}
		}
	}
}
