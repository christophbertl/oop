// import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
// import java.util.Calendar;
// import java.util.GregorianCalendar;

public class Customer {

	// set variables of Customer with default values ---------------------------
	private String userID;
	private String name;
	private String surname;
	private int pwhash;
	private int validatePW;
	private Date birthday;
	
	// constructor -------------------------------------------------------------
	public Customer() {
		validatePW = 0;
		userID = UUID.randomUUID().toString();
	}
	
	public Customer (String name, String surname, Date birthday) {
		this();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		// this.birthday.setTime(birthday);
	}
	
	// methods -----------------------------------------------------------------
	
	// check if given password is correct
	public Boolean checkPW(String password) {
		return pwhash == hashString(password);
	}
	
	// check if given password is correct for 2nd time
	public Boolean validatePW(String password) {
		return validatePW == hashString(password);
	}
	
	// hashes the given string an return numeric hash of it
	public int hashString(String stringToHash) {
		return String.valueOf(stringToHash.hashCode()).hashCode();
	}
	
	// setter ------------------------------------------------------------------
	
	// save password as hash to private instance variable
	public int setPW(String password) {
		if (validatePW == 0) {
			validatePW = hashString(password);
			return 0;
		}
		else {
			if (validatePW(password)) {
				pwhash = hashString(password);
				validatePW = 0;
				return 0;
			}
			else {
				validatePW = 0;
				return -1;
			}	
		}
	}
	
	// set name of the Customer
	public void setName(String name) {
		 this.name = name;
	}
	
	// set surname of the Customer
	public void setsurname(String name) {
		 surname = name;
	}
	
	// getter ------------------------------------------------------------------	
	// calculates and return whole name of the Customer
	public String getFullName() {
		return surname + " " + name;
	}
	
	// returns the UUID of the user
	public String getID() {
		return userID;
	}

	public long getAge() {
		Date now = new Date();
		long diff = now.getTime() - birthday.getTime();
		return TimeUnit.MILLISECONDS.toDays(diff) / 365;

	}

	
	
	
	
	
	
	
	
	
}
