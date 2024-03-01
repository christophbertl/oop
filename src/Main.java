
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print("Hallo Welt");
	//}

	
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
	
	// Rückgabe Auto 
	
	public static void Automieten (String kennzeichen, String nutzerID, List<Fahrzeug> fahrzeuge) {
	
		
		for (int i=0; i< fahrzeuge.size(); i++) {
			if (fahrzeuge.get(i).getKennzeichen().equals(kennzeichen)) {
				
				fahrzeuge.get(i).setGemietetVon(nutzerID);
				
				break;
				}
			}
	
		
	}
	
	
	public static void main(String[] args) {
		// create necessary variables
		Scanner scanner = new Scanner(System.in);
		String eingabe;
		List<Fahrzeug> fahrzeuge = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		String fahrzeug, customer, name, surname, nutzerID;
		String marke, modell, farbe, kennzeichen;
		int mietpreis, sitzplatz, kilometerstand;

		while (true) {
			// display main menu
			eingabe = mainMenu(scanner);
			
			// create simple contact
			if (eingabe.equals("1")) {
				// get name and surname
				System.out.println("    Fahrzeug anlegen");
				System.out.print("    Marke:");
				marke = scanner.nextLine();
				System.out.print("    Modell:");
				modell = scanner.nextLine();
				System.out.print("    Farbe:");
				farbe = scanner.nextLine();
				System.out.print("    Kennzeichen:");
				kennzeichen = scanner.nextLine();
				
				
				// TODO: Exception!
				System.out.print("    Mietpreis:");
				mietpreis = scanner.nextInt();
				System.out.print("    Sitzplätze:");
				sitzplatz = scanner.nextInt();
				System.out.print("    KM-Stand:");
				kilometerstand = scanner.nextInt();
				
		
				// create object
				Fahrzeug f = new Fahrzeug(marke, modell, farbe, kennzeichen, mietpreis, 
						sitzplatz, kilometerstand);
				
				// add f to list
				fahrzeuge.add(f);
				
			}
			// create business contact
			else if (eingabe.equals("2")) {
				// get name and surname
				System.out.println("    Kunde anlegen");
				System.out.print("    Name:");
				name = scanner.nextLine();
				System.out.print("    Vorname:");
				surname = scanner.nextLine();
	
				
				//create object
				Customer c = new Customer( name, surname);
				
				customers.add(c);
				
				System.out.println(c.getID());
							
				
				
				
				
				// create object
				
				// add mail address if valid to object and add object to list
				//contacts = addMail(contacts, compContact, scanner);
			}
			else if (eingabe.equals("3")) {
				// get name and surname
				System.out.println("     verfügbare Fahrzeuge anzeigen");
				System.out.print("    verfügbare Fahrzeuge:");
				// print contact(s) from list
				for (int i=0; i< fahrzeuge.size(); i++) {
					if (fahrzeuge.get(i).getGemietetVon() == null) {
						System.out.println(fahrzeuge.get(i).getAll());
					}
				}
			}
			
			
			else if (eingabe.equals("4")) {
				// get name and surname
				System.out.println("    Fahrzeug mieten");
				
				System.out.print("    Kennzeichen angeben:");
				kennzeichen = scanner.nextLine();
				
				System.out.print("    NutzerID angeben:");
				nutzerID = scanner.nextLine();
				
			
				
				Automieten(kennzeichen, nutzerID, fahrzeuge);
			
			}
			
			
			
			else if (eingabe.equals("5")) {
				// get name and surname
				System.out.println("    Fahrzeug zurückgeben");
				System.out.print("    Kennzeichen des zurück gegebenen Fahrzeugs:");
				kennzeichen = scanner.nextLine();
			
			
				Automieten(kennzeichen, null, fahrzeuge);
			
			
			}
		
			
			
			
			
			
			
			
			
			else if (eingabe.equals("6")) {
				break;
			}
		
		}
		System.out.println("beendet");
		scanner.close();
	

		
	}
	
} //main
	


	
	
	
	
	
	
	
	
	
	
	

