
// imports
// import java.awt.Color;

//import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;


public class Main {
	
	// show main menu (command line menu)
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
	
	// main / start of program
	public static void main(String[] args) {
		// run user interface
		Controller controller = new Controller();
		Frontend frontend = new Frontend();
		frontend.run_frontend(controller);
		
		
		// create necessary variables for command line menu
		Scanner scanner = new Scanner(System.in);
		String input, name, surname, userID, brand, model, color, numberPlate;
		int price, seating, mileage;
		
		// restore list of vehicles from file system
		try {
			controller.cars = (Map<String, Vehicle>) controller.readFromFile();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		
		// main loop for main menu and user interaction
		while (true) {
			// display main menu
			input = mainMenu(scanner);
			
			// create vehicle
			if (input.equals("1")) {
				// get information for vehicle from user
				System.out.println("    Fahrzeug anlegen");
				System.out.print("    Marke:");
				brand = scanner.nextLine();
				System.out.print("    Modell:");
				model = scanner.nextLine();
				System.out.print("    Farbe:");
				color = scanner.nextLine();
				System.out.print("    Kennzeichen:");
				numberPlate = scanner.nextLine();
				
				// try to get numbers for the next input variables from user
				try { 
					System.out.print("    Mietpreis:");
					price = scanner.nextInt();
					System.out.print("    Sitzplätze:");
					seating = scanner.nextInt();
					System.out.print("    KM-Stand:");
					mileage = scanner.nextInt();
	
					// create vehicle object object
					Vehicle car = new Vehicle(brand, model, color, numberPlate, price, 
							seating, mileage);
					
					// add f (vehicle) to list of vehicles
					//fahrzeuge.add(f);
					controller.cars.put(car.getNumberPlate(), car);
					// write whole list to file system
					try {
						controller.writeToFile(controller.cars);
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
			else if (input.equals("2")) {
				// get name and surname of customer
				System.out.println("    Kunde anlegen");
				System.out.print("    Name:");
				name = scanner.nextLine();
				System.out.print("    Vorname:");
				surname = scanner.nextLine();
				System.out.print("    Geburtstag (DD.MM.YYYY):");
				String input_birthday = scanner.nextLine();
				
				Date birthday = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				try {
					// parse birthday
					birthday = sdf.parse(input_birthday);
					
					//create customer object
					Customer c = new Customer(name, surname, birthday);
					System.out.println(c.getAge());
					// add customer to list of customers
					controller.customers.add(c);
					
					// print the ID if the customer (let him know his ID)
					System.out.println(c.getID());
				}	
				// catch exception if birthday parsing failed
				catch (java.text.ParseException ex) {
					System.out.println("Ungültiges Datum");
				}

			}
			
			// display list of available vehicles
			else if (input.equals("3")) {
				System.out.println("     verfügbare Fahrzeuge anzeigen");
				System.out.println("    verfügbare Fahrzeuge:");
				// print vehicles from list which is not rented at the moment
				for (Vehicle fahrzeug : controller.cars.values()) {
					if (fahrzeug.getRentedBy() == null) {
						System.out.println(fahrzeug.toString());
					}
				}
			}
			
			// rent a car
			else if (input.equals("4")) {
				System.out.println("    Fahrzeug mieten");
				// get number plate from user to identify car
				System.out.print("    Kennzeichen angeben:");
				numberPlate = scanner.nextLine();
				// get user ID from user to identify customer
				System.out.print("    NutzerID angeben:");
				userID = scanner.nextLine();
					
				// rent a car (by number plate) for user (by ID)
				controller.rentACar(numberPlate, userID, controller.cars, 0);
				}
			
			// return a car
			else if (input.equals("5")) {
				System.out.println("    Fahrzeug zurückgeben");
				// get number plate of car to return
				System.out.print("    Kennzeichen des zurück gegebenen Fahrzeugs:");
				numberPlate = scanner.nextLine();
				// get km value of car
				System.out.print("    Kilometerstand angeben");
				mileage = scanner.nextInt();
			
				// return car by using userID == null (removes customer from car)
				controller.rentACar(numberPlate, null, controller.cars, mileage);		
			}
		
			// close program by breaking while loop
			else if (input.equals("6")) {
				break;	
			}
		
		} // while
		
		// close input scanner and finish program 
		System.out.println("beendet");
		scanner.close();
	} // main method
} // main class
