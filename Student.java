public class Student {

	private String mNum;
	private String firstName;
	private String lastName;
	private String email;
	private String major;
	private String status;
	private boolean cameraShy;
	
	//default constructor
	public Student() {
		this.mNum = "M-null";
		this.firstName = "";
		this.lastName = "";
		
		this.cameraShy = false;
	}
	
	//overloaded constructor
	public Student(String mNum, String first, String last, String email, String major, boolean cameraShy, String status) {
		this.mNum = mNum;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.major = major;
		this.cameraShy = cameraShy;
		this.status = status;
	}

	public String getmNum() {
		return mNum;
	}

	public void setmNum(String mNum) {
		this.mNum = mNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public boolean isCameraShy() {
		return cameraShy;
	}

	public void setCameraShy(boolean cameraShy) {
		this.cameraShy = cameraShy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return mNum + "%" + firstName + "%" + lastName + "%" + email
				+ "%" + major + "%" + status + "%" + cameraShy;
	}
	
	
	
}
