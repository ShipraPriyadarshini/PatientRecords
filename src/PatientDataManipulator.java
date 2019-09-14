import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * Patient Data Manipulator Class - contains all the method required to manipulate and access the patient record data
 */
public class PatientDataManipulator {

	//Display the Notification List
	public void displayChoiceList() {
		String message = "\nPATIENT RECORD DATA\n" +
				"1. Display list\n" +
				"2. Add a new Patient\n" +
				"3. Show information for a patient\n" +
				"4. Delete a patient\n" +
				"5. Show average patient age\n" +
				"6. Show information for the youngest patient\n" +
				"7. Show overdue patient list\n" +
				"8. Quit\n";
		System.out.println(message);
	}

	//Displaying the names and patient id #’s of all patients
	public void displayPatientList(List<Patient> patients) {
		for(int i=0;i<patients.size();i++) {
			Patient patient = patients.get(i);
			System.out.println(patient.getName() + ", " + patient.getPatientId());
		}
	}

	//Adding the patient information to the list
	public void addPatientInfo(List<Patient> patients) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Lets add patients details!");
		Patient patient = new Patient();
		System.out.println("Enter patient's first name: ");
		try {
			String firstName = sc.nextLine();
			System.out.println("Enter patient's last name: ");
			String lastName = sc.nextLine();
			patient.setName(firstName+" "+lastName);
			System.out.println("Enter patient's id: ");
			String id = sc.nextLine();
			for(int i=0;i<patients.size();i++) {
				if((patients.get(i).getPatientId()).equals(id)) {
					System.out.println("Id already present, please try again!");
					displayChoiceList();
					return;
				}
			}
			patient.setPatientId(id);
			System.out.println("Enter patient's street address: ");
			String streetAddress = sc.nextLine();
			System.out.println("Enter patient's city: ");
			String city = sc.nextLine();
			System.out.println("Enter patient's state: ");
			String state = sc.nextLine();
			System.out.println("Enter patient's zipcode: ");
			String zipcode = sc.nextLine();
			patient.setAddress(streetAddress+" "+city+" "+state+" "+zipcode);
			System.out.println("Enter patient's height (inches): ");
			int height = sc.nextInt();
			patient.setHeight(height);
			System.out.println("Enter patient's weight (lbs): ");
			int weight = sc.nextInt();
			patient.setWeight(weight);
			System.out.println("Enter patient's birth date (MM/DD/YYYY): ");
			String birthDate = sc.next();
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(birthDate);
			patient.setBirthDate(date);
			System.out.println("Enter patient's initial visit (MM/DD/YYYY): ");
			String initialVisitDate = sc.next();
			Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(initialVisitDate);
			patient.setInitialVisitDate(date1);
			System.out.println("Enter patient's last visit date (MM/DD/YYYY): ");
			String lastVisitDate = sc.next();
			Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(lastVisitDate);
			patient.setLastVisitDate(date2);
			patients.add(patient);
			System.out.println("Patient: " +patient.getName()+" added!");
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	//Get the patient information - patient name and patient id
	public void getPatientInfo(String id, List<Patient> patients) {
		for(int i=0;i<patients.size();i++) {
			Patient patient = patients.get(i);
			if(patient.getPatientId().equals(id)) {
				System.out.println("--Patient Record--");
				System.out.println("Patient Name: "+patient.getName());
				System.out.println("Patient Id: "+patient.getPatientId());
				System.out.println("Address: "+patient.getAddress());
				System.out.println("Height: "+patient.getHeightInFeetAndInches());
				System.out.println("Weight: "+patient.getWeight()+" lbs");
				System.out.println("Age: "+patient.getAge());
				System.out.println("No. of years as a patient: "+getPatientTime(patient.getTimeAsPatient()));
				System.out.println("No. of years since last visit: "+getPatientTime(patient.getTimeSinceLastVisit()));
				System.out.println("Visitation Overdue: "+visitOverdue(patient.getTimeSinceLastVisit()));
			}
		}
	}

	//Delete the patient information from the list
	public void deletePatientRecord(String patientId, List<Patient> patients) {
		for(int i=0;i<patients.size();i++) {
			Patient patient = patients.get(i);
			if((patient.getPatientId()).equals(patientId)) {
				patients.remove(patient);
				System.out.println("Patient: " +patient.getName()+" removed!");
			}
		}
	}

	//Get average age of all patients from the list
	public void getAverageAge(List<Patient> patients) {
		int averageAge = 0;
		for(int i=0;i<patients.size();i++) {
			Patient patient = patients.get(i);
			averageAge += patient.getAge();
		}
		System.out.println("The average age of the patient's age is "+averageAge/patients.size());
	}

	//Get the youngest patient from the list
	public void getYoungestPatient(List<Patient> patients) {
		Patient youngest = null;
		for(int i=0;i<patients.size();i++) {
			if(youngest == null || youngest.getAge() > patients.get(i).getAge()) {
				youngest = patients.get(i);
			}
		}
		getPatientInfo(youngest.getPatientId(),patients);
	}

	//Get the list of all overdue patients from the list
	public void getOverduePatientList(List<Patient> patients) {
		for(int i=0;i<patients.size();i++) {
			Patient patient = patients.get(i);
			String overdue = visitOverdue(patient.getTimeSinceLastVisit());
			if(overdue.equals("Yes")) {
				System.out.println(patient.getName() + ", " + patient.getPatientId());
			}
		}
	}

	//private method to get the patient time for getting patient initial visit and last visit time
	private String getPatientTime(int patientTime) {
		if (patientTime!=0) {
			return String.valueOf(patientTime);
		}else {
			return "Less than one year";
		}
	}

	//private method to get the overdue visitation time 
	private String visitOverdue(int patientTime) {
		if(patientTime<=3) {
			return "No";
		}else {
			return "Yes";
		}
	}
}
