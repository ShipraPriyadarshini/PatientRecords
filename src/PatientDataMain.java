import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PatientDataMain {

	//Main Method where the program runs the project
	public static void main(String[] args) {
		List<Patient> patients = new List<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/shipra/Desktop/patient.txt")); 
			String line = null;
			while((line = bufferedReader.readLine())!=null) {
				Patient patientFromFile = new Patient();
				patientFromFile.setName(line);
				patientFromFile.setPatientId(bufferedReader.readLine());
				patientFromFile.setAddress(bufferedReader.readLine());
				patientFromFile.setHeight(Integer.valueOf(bufferedReader.readLine()));
				patientFromFile.setWeight(Integer.valueOf(bufferedReader.readLine()));
				String d0 = bufferedReader.readLine();
				String d1 = bufferedReader.readLine();
				String d2 = bufferedReader.readLine();
				Date date0 = new SimpleDateFormat("MM/dd/yyyy").parse(d0);
				Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(d1);
				Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(d2);
				patientFromFile.setBirthDate(date0);
				patientFromFile.setInitialVisitDate(date1);
				patientFromFile.setLastVisitDate(date2);
				patients.add(patientFromFile);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		processPatientList(patients);
		printPatientList(patients);
	}

	//Process Patient List for accessing and manipulating the data on that list
	public static void processPatientList(List<Patient> patients) {
		PatientDataManipulator patientProcessor = new PatientDataManipulator();
		System.out.println("Welcome to the Patient Record Viewer");
		System.out.println();
		patientProcessor.displayChoiceList();
		System.out.println("Enter choice: ");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String choice = "y";
			while(choice.equalsIgnoreCase("y")) {
				String index = sc.nextLine();
				switch (index) {
				case "1":
					patientProcessor.displayPatientList(patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "2":
					patientProcessor.addPatientInfo(patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "3":
					System.out.println("Enter patient's id to view: ");
					String id = sc.nextLine();
					patientProcessor.getPatientInfo(id,patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "4":
					System.out.println("Enter patient's id to remove: ");
					String idDel = sc.nextLine();
					patientProcessor.deletePatientRecord(idDel, patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "5":
					patientProcessor.getAverageAge(patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "6":
					patientProcessor.getYoungestPatient(patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "7":
					patientProcessor.getOverduePatientList(patients);
					System.out.println("\nEnter Choice: ");
					break;
				case "8":
					System.out.println("Do you want to exit? (y/n)");
					choice = sc.nextLine();
					if(choice.equalsIgnoreCase("y")){
						System.out.println("Bye!");
						return;
					}else {
						patientProcessor.displayChoiceList();
						System.out.println("Enter Choice: ");
						choice = "y";
					}
					break;
				default:
					System.out.println("You entered the wrong choice. Please try again!");
					break;
				}
			}
			sc.close();
		}
	}

	//Print the patient list on to an output file
	public static void printPatientList(List<Patient> patients) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("/Users/shipra/Desktop/patientOut.txt"));
			for(int i=0;i<patients.size;i++) {
				bufferedWriter.write(patients.get(i).getName()+"\n");
				bufferedWriter.write(patients.get(i).getPatientId()+"\n");
				bufferedWriter.write(patients.get(i).getAddress()+"\n");
				bufferedWriter.write(patients.get(i).getHeight()+"\n");
				bufferedWriter.write(Double.toString(patients.get(i).getWeight())+"\n");
				bufferedWriter.write(new SimpleDateFormat("MM/dd/yyyy").format(patients.get(i).getBirthDate())+"\n");
				bufferedWriter.write(new SimpleDateFormat("MM/dd/yyyy").format(patients.get(i).getInitialVisitDate())+"\n");
				bufferedWriter.write(new SimpleDateFormat("MM/dd/yyyy").format(patients.get(i).getLastVisitDate())+"\n");
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

