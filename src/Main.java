
// imports
// import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//import java.util.Set;

//import org.apache.log4j.*;
import javax.swing.*;


public class Main {
	public static JTextField txtMarke;
	public static JTextField txtModell;
	public static JTextField txtFarbe;
	public static JTextField txtKennzeichen;
	public static JTextField txtMietpreis;
	public static JTextField txtKilometer;
	public static JTextField txtSitzplatz;
	public static Map<String, Fahrzeug> fahrzeuge = new HashMap<String, Fahrzeug>();


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
	public static void writeToFile(Map<String, Fahrzeug> fahrzeuge) throws IOException {
		
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
	public static Map<String, Fahrzeug> readFromFile() throws IOException, ClassNotFoundException {
		// create file and object input stream
		FileInputStream fileIn = new FileInputStream("fahrzeuge.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        // read object "thru" streams from file and cast it to list of vehicles; save to variable fahrzeuge
        @SuppressWarnings("unchecked")
        Map<String, Fahrzeug> fahrzeuge = (Map<String, Fahrzeug>) in.readObject();
        
        // close streams
        in.close();
        fileIn.close();
        
        // return from file restored list of vehicles
        return fahrzeuge;
	}
	
	// rent or return car
	public static void Automieten (String kennzeichen, String nutzerID, Map<String, Fahrzeug> fahrzeuge, int km) {
		// search car in list of vehicles
		for (Fahrzeug fahrzeug : fahrzeuge.values()) {
			// if the number plate matches
			if (fahrzeug.getKennzeichen().equals(kennzeichen)) {
				// add / remove user (if userID == null -> user will be removed from car object)
				fahrzeug.setGemietetVon(nutzerID);
				
				// if user returns the car: write new km to car object
				if (nutzerID == null)  {
					fahrzeug.setKilometerstand(km);
					}
				
				// stop iteration if car was found
				break;
			}
		}
	}
	
	// main / start of program
	public static void main(String[] args) {
				
		/* logger example
		 * Logger logger = Logger.getLogger(Main.class.getName());
		 * logger.error("Error");
		*/
		
		// create necessary variables
		Scanner scanner = new Scanner(System.in);
		String eingabe;
		//List<Fahrzeug> fahrzeuge = new ArrayList<>();
		//Set<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
		
		
		List<Customer> customers = new ArrayList<>();
		String name, surname, nutzerID;
		String marke, modell, farbe, kennzeichen;
		int mietpreis, sitzplatz, kilometerstand;
		
		// restore list of vehicles from file system
		try {
			fahrzeuge = (Map<String, Fahrzeug>) readFromFile();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		
		JFrame meinFrame = new JFrame("Autovermietung");
		meinFrame.setSize(371,318);
		
		JButton btnNewButton = new JButton("Fahrzeug anlegen");
		btnNewButton.setBounds(204, 232, 141, 36);
		
		btnNewButton.addActionListener(new ActionSave());
		
		meinFrame.getContentPane().setLayout(null);
		
		meinFrame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Automarke: ");
		lblNewLabel.setBounds(29, 31, 77, 17);
		meinFrame.getContentPane().add(lblNewLabel);
		
		txtMarke = new JTextField();
		txtMarke.setBounds(114, 29, 86, 20);
		meinFrame.getContentPane().add(txtMarke);
		txtMarke.setColumns(10);
		
		JLabel lblModell = new JLabel("Modell:");
		lblModell.setBounds(29, 59, 77, 17);
		meinFrame.getContentPane().add(lblModell);
		
		txtModell = new JTextField();
		txtModell.setColumns(10);
		txtModell.setBounds(114, 57, 86, 20);
		meinFrame.getContentPane().add(txtModell);
		
		JLabel lblFarbe = new JLabel("Farbe:");
		lblFarbe.setBounds(29, 93, 77, 17);
		meinFrame.getContentPane().add(lblFarbe);
		
		txtFarbe = new JTextField();
		txtFarbe.setColumns(10);
		txtFarbe.setBounds(114, 91, 86, 20);
		meinFrame.getContentPane().add(txtFarbe);
		
		JLabel lblKennzeichen = new JLabel("Kennzeichen:");
		lblKennzeichen.setBounds(29, 121, 77, 17);
		meinFrame.getContentPane().add(lblKennzeichen);
		
		txtKennzeichen = new JTextField();
		txtKennzeichen.setColumns(10);
		txtKennzeichen.setBounds(114, 119, 86, 20);
		meinFrame.getContentPane().add(txtKennzeichen);
		
		JLabel lblFarbe_1_1 = new JLabel("Mietpreis:");
		lblFarbe_1_1.setBounds(29, 150, 77, 17);
		meinFrame.getContentPane().add(lblFarbe_1_1);
		
		JLabel lblFarbe_1_2 = new JLabel("Kilometerstand:");
		lblFarbe_1_2.setBounds(29, 184, 77, 17);
		meinFrame.getContentPane().add(lblFarbe_1_2);
		
		JLabel lblFarbe_1_3 = new JLabel("Sitzplätze:");
		lblFarbe_1_3.setBounds(29, 209, 77, 17);
		meinFrame.getContentPane().add(lblFarbe_1_3);
		
		txtMietpreis = new JTextField();
		txtMietpreis.setColumns(10);
		txtMietpreis.setBounds(114, 148, 86, 20);
		meinFrame.getContentPane().add(txtMietpreis);
		
		txtKilometer = new JTextField();
		txtKilometer.setColumns(10);
		txtKilometer.setBounds(114, 182, 86, 20);
		meinFrame.getContentPane().add(txtKilometer);
		
		txtSitzplatz = new JTextField();
		txtSitzplatz.setColumns(10);
		txtSitzplatz.setBounds(114, 207, 86, 20);
		meinFrame.getContentPane().add(txtSitzplatz);
		
		meinFrame.setVisible(true);
		
		

	
		
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
					//fahrzeuge.add(f);
					fahrzeuge.put(f.getKennzeichen(), f);
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
					customers.add(c);
					
					// print the ID if the customer (let him know his ID)
					System.out.println(c.getID());
				}	
				// catch exception if birthday parsing failed
				catch (java.text.ParseException ex) {
					System.out.println("Ungültiges Datum");
				}

			}
			
			// display list of available vehicles
			else if (eingabe.equals("3")) {
				System.out.println("     verfügbare Fahrzeuge anzeigen");
				System.out.println("    verfügbare Fahrzeuge:");
				// print vehicles from list which is not rented at the moment
				for (Fahrzeug fahrzeug : fahrzeuge.values()) {
					if (fahrzeug.getGemietetVon() == null) {
						System.out.println(fahrzeug.toString());
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
