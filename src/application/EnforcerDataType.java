package application;

public class EnforcerDataType {

	private final String f;
	private final String l;
	private final String email;

	
	
	public EnforcerDataType(String fname, String lname, String email) {
		this.f = fname;
		this.l = lname;
		this.email= email;
		
	}


	public String getF() {
		return f;
	}


	public String getL() {
		return l;
	}


	public String getEmail() {
		return email;
	}


	
}
