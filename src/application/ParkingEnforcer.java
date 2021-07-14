package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ParkingEnforcer {

	private String user;
	private String pass;
	private String fName;
	private String lName;
	
	
	public ParkingEnforcer(String user, String pass) {
		this.user = user;
		this.pass = pass;
		
	}
	
	public ParkingEnforcer(String user,String pass, String f, String l){
		this.user = user;
		this.pass = pass;
		this.setFirstName(f);
		this.setLastName(l);
	}
	
	public String getPass() {
		return this.pass;
	}

	public String getEmail() {
		return this.user;
	}

	public boolean checkLogin() {

		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[2].equals(this.user) && line[3].equals(this.pass) && line[4].equals("Enforcer")) {
					return true;
				}
				System.out.println(line.length);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return false;
	}
	
	

	public String getFirstName() {
		return fName;
	}

	public void setFirstName(String fName) {
		this.fName = fName;
	}

	public String getLastName() {
		return lName;
	}

	public void setLastName(String lName) {
		this.lName = lName;
	}
	
	
}
