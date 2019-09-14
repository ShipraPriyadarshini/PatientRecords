import java.util.Calendar;
import java.util.Date;

public class Patient {

	private String name;
	private String patientId;
	private String address;
	private int height;
	private double weight;
	private Date birthDate;
	private Date initialVisitDate;
	private Date lastVisitDate;

	//Mutator method of each instance variable
	public void setName(String name) {
		this.name = name;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setInitialVisitDate(Date initialVisitDate) {
		this.initialVisitDate = initialVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	//Accessor methods for each instance variable
	public String getName() {
		return name;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getAddress() {
		return address;
	}

	public int getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getInitialVisitDate() {
		return initialVisitDate;
	}

	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	//To get the age from the birthdate entered
	public int getAge() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.birthDate);
		int birthYear = calendar.get(Calendar.YEAR);
		return currentYear-birthYear;
	}

	//To get the time as a patient
	public int getTimeAsPatient() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.initialVisitDate);
		int initialVisitYear= calendar.get(Calendar.YEAR);
		return currentYear-initialVisitYear;
	}

	//To get time since last visit
	public int getTimeSinceLastVisit() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.lastVisitDate);
		int lastVisitYear= calendar.get(Calendar.YEAR);
		return currentYear-lastVisitYear;
	}

	//To convert height from inches to ft/inches
	public String getHeightInFeetAndInches() {
		int feet = this.height/12;
		int inches = this.height%12;
		return (feet+" ft "+inches+" in");
	}

	//ToString method to get the Patient object stored 
	public String toString() {
		return "Patient [name=" + name + ", patientId=" + patientId + ", address=" + address + ", height=" + height
				+ ", weight=" + weight + ", birthDate=" + birthDate + ", initialVisitDate=" + initialVisitDate
				+ ", lastVisitDate=" + lastVisitDate + "]";
	}
}
