import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frontend {
	public JTextField txtMarke;
	public JTextField txtModell;
	public JTextField txtFarbe;
	public JTextField txtKennzeichen;
	public JTextField txtMietpreis;
	public JTextField txtKilometer;
	public JTextField txtSitzplatz;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void run_frontend(Controller controller) {
	JFrame meinFrame = new JFrame("Autovermietung");
	meinFrame.setSize(371,318);
	
	JButton btnNewButton = new JButton("Fahrzeug anlegen");
	btnNewButton.setBounds(204, 232, 141, 36);
	
	btnNewButton.addActionListener(new ActionSave(controller));
	
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
	} // run frontend
	
	// Action for Save vehicle button
	public class ActionSave implements ActionListener{
		
		private Controller controller;
		public ActionSave(Controller controller) {
			this.controller = controller;
		} // Constructor
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// create vehicle object object
			
			// add f (vehicle) to list of vehicles		
			Vehicle car = new Vehicle(txtMarke.getText(), txtModell.getText(), txtFarbe.getText(), 
					txtKennzeichen.getText(), Integer.valueOf(txtMietpreis.getText()), 
					Integer.valueOf(txtSitzplatz.getText().toString()), Integer.valueOf(
							txtKilometer.getText()));
			
			// add f (vehicle) to list of vehicles
			controller.cars.put(car.getNumberPlate(), car);
			// write whole list to file system
			try {
				controller.writeToFile(controller.cars);
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		} // actionPerformed 
	} // ActionSave
} // Frontend
