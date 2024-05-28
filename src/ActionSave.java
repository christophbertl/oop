import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ActionSave implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// create vehicle object object
		
		// add f (vehicle) to list of vehicles
		//fahrzeuge.add(f);
		

		Fahrzeug f = new Fahrzeug(Main.txtMarke.getText(), Main.txtModell.getText(), Main.txtFarbe.getText(), Main.txtKennzeichen.getText(), Integer.valueOf(Main.txtMietpreis.getText()), 
				Integer.valueOf(Main.txtSitzplatz.getText().toString()), Integer.valueOf(Main.txtKilometer.getText()));
		
		// add f (vehicle) to list of vehicles
		//fahrzeuge.add(f);
		
		Main.fahrzeuge.put(f.getKennzeichen(), f);
		// write whole list to file system
		try {
			Main.writeToFile(Main.fahrzeuge);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
    
		
		
		

	}

}


