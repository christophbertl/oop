
// imports
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	// show main menu
	public static String mainMenu(Scanner scanner) {
		System.out.println("");
		System.out.println("-------------------------");
		System.out.println("Fahrzeug anlegen: 1");
		System.out.println("Kunde anlegen: 2");
		System.out.println("verfügbare Fahrzeuge anzeigen: 3");
		System.out.println("Fahrzeug mieten: 4");
		System.out.println("Fahrzeug zurückgeben: 5");
		System.out.println("Beenden: 6");
		return scanner.nextLine();
	}
	
	// write list of vehicles to file
	public static void writeToFile(List<Fahrzeug> fahrzeuge) throws IOException {
		/*
		// example for simple PrintWriter
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		*/
		
		// create File and Object output stream
		FileOutputStream fileOut = new FileOutputStream("fahrzeuge.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
        // write object "thru" streams to file
        out.writeObject(fahrzeuge);
        
        // close streams
        out.close();
        fileOut.close();
	}
	
	// read vehicle list from file
	public static List<Fahrzeug> readFromFile() throws IOException, ClassNotFoundException {
		// create file and object input stream
		FileInputStream fileIn = new FileInputStream("fahrzeuge.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        // read object "thru" streams from file and cast it to list of vehicles; save to variable fahrzeuge
        @SuppressWarnings("unchecked")
		List<Fahrzeug> fahrzeuge = (List<Fahrzeug>) in.readObject();
        
        // close streams
        in.close();
        fileIn.close();
        
        // return from file restored list of vehicles
        return fahrzeuge;
	}
	
	// rent or return car
	public static void Automieten (String kennzeichen, String nutzerID, List<Fahrzeug> fahrzeuge, int km) {
		// search car in list of vehicles
		for (int i=0; i< fahrzeuge.size(); i++) {
			// if the number plate matches
			if (fahrzeuge.get(i).getKennzeichen().equals(kennzeichen)) {
				// add / remove user (if userID == null -> user will be removed from car object)
				fahrzeuge.get(i).setGemietetVon(nutzerID);
				
				// if user returns the car: write new km to car object
				if (nutzerID == null)  {
					fahrzeuge.get(i).setKilometerstand(km);
					}
				
				// stop iteration if car was found
				break;
			}
		}
	}
		
	
	// main / start of program
	public static void main(String[] args) {
		
		// create necessary variables
		Scanner scanner = new Scanner(System.in);
		String eingabe;
		List<Fahrzeug> fahrzeuge = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		String name, surname, nutzerID;
		String marke, modell, farbe, kennzeichen;
		int mietpreis, sitzplatz, kilometerstand;
		
		// restore list of vehicles from file system
		try {
			fahrzeuge = readFromFile();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		// main loop for main menu and user interaction
		while (true) {
			// display main menu
			eingabe = mainMenu(scanner);
			
			// create vehicle
			if (eingabe.equals("1")) {
				// get information for vehicle from user
				System.out.println("    Fahrzeug anlegen");
				System.out.print("    Marke:");
				marke = scanner.nextLine();
				System.out.print("    Modell:");
				modell = scanner.nextLine();
				System.out.print("    Farbe:");
				farbe = scanner.nextLine();
				System.out.print("    Kennzeichen:");
				kennzeichen = scanner.nextLine();
				
				// try to get numbers for the next input variables from user
				try { 
					System.out.print("    Mietpreis:");
					mietpreis = scanner.nextInt();
					System.out.print("    Sitzplätze:");
					sitzplatz = scanner.nextInt();
					System.out.print("    KM-Stand:");
					kilometerstand = scanner.nextInt();
	
					// create vehicle object object
					Fahrzeug f = new Fahrzeug(marke, modell, farbe, kennzeichen, mietpreis, 
							sitzplatz, kilometerstand);
					
					// add f (vehicle) to list of vehicles
					fahrzeuge.add(f);
					
					// write whole list to file system
					try {
						writeToFile(fahrzeuge);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					
				// catch user type
				} catch (java.util.InputMismatchException ex) { 
					System.out.println("Fehler: muss eine Zahl sein");
				}
			}
			
			// create customer
			else if (eingabe.equals("2")) {
				// get name and surname of customer
				System.out.println("    Kunde anlegen");
				System.out.print("    Name:");
				name = scanner.nextLine();
				System.out.print("    Vorname:");
				surname = scanner.nextLine();
				
				//create customer object
				Customer c = new Customer( name, surname);
				
				// add customer to list of customers
				customers.add(c);
				
				// print the ID if the customer (let him know his ID)
				System.out.println(c.getID());
			}
			
			// display list of available vehicles
			else if (eingabe.equals("3")) {
				System.out.println("     verfügbare Fahrzeuge anzeigen");
				System.out.print("    verfügbare Fahrzeuge:");
				// print vehicles from list which is not rented at the moment
				for (int i=0; i< fahrzeuge.size(); i++) {
					if (fahrzeuge.get(i).getGemietetVon() == null) {
						System.out.println(fahrzeuge.get(i).getAll());
					}
				}
			}
			
			// rent a car
			else if (eingabe.equals("4")) {
				System.out.println("    Fahrzeug mieten");
				// get number plate from user to identify car
				System.out.print("    Kennzeichen angeben:");
				kennzeichen = scanner.nextLine();
				// get user ID from user to identify customer
				System.out.print("    NutzerID angeben:");
				nutzerID = scanner.nextLine();
					
				// rent a car (by number plate) for user (by ID)
				Automieten(kennzeichen, nutzerID, fahrzeuge, 0);
				}
			
			// return a car
			else if (eingabe.equals("5")) {
				System.out.println("    Fahrzeug zurückgeben");
				// get number plate of car to return
				System.out.print("    Kennzeichen des zurück gegebenen Fahrzeugs:");
				kennzeichen = scanner.nextLine();
				// get km value of car
				System.out.print("    Kilometerstand angeben");
				kilometerstand = scanner.nextInt();
			
				// return car by using userID == null (removes customer from car)
				Automieten(kennzeichen, null, fahrzeuge, kilometerstand);		
			}
		
			// close program by breaking while loop
			else if (eingabe.equals("6")) {
				break;	
			}
		
		} // while
		
		// close input scanner and finish program 
		System.out.println("beendet");
		scanner.close();
	} // main method
	
} // main class
