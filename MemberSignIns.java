import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class MemberSignIns {

	private static final String FILENAME = "src/sign-ins.txt";
	private HashMap<String, Student> memberSignIns;
	private Scanner keyboard;
	private FileWriter writer;
	private File signInFile;
	
	public MemberSignIns() {
		memberSignIns = new HashMap<String, Student>();
		signInFile = new File(FILENAME);
		
		buildMap();
	}
	
	public boolean existingMember(String mNumber) {
		return memberSignIns.containsKey(mNumber);
	}
	
	public void addNewMember(Student student) {
		addNewMemberToMap(student);
		addNewMemberToFile(student);
	}
	
	public void addNewMemberToMap(Student student) {
		memberSignIns.put(student.getmNum(), student);
	}
	
	public void addNewMemberToFile(Student student) {
		try{
			writer = new FileWriter(signInFile);
			writer.write(student.toString() + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getFirstName(String mNumber) {
		return memberSignIns.get(mNumber).getFirstName();
	}
	
	public void buildMap() {
		try {
			keyboard = new Scanner(signInFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(keyboard.hasNextLine() ) {
			String line = keyboard.nextLine();
			addNewMemberToMap(parseLine(line));
		}
	}
	

	private Student parseLine(String line) {
		String[] parts = line.split("%");
		String mNum = parts[0];
		String first = parts[1];
		String last = parts[2];
		String email = parts[3];
		String major = parts[4];
		String status = parts[5];
		boolean cameraShy = parts[0].equals("True") ? true : false;
		
		return new Student(mNum, first, last, email, major, cameraShy, status);
		
	}
	
}
