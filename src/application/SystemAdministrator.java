package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemAdministrator {
	
	private String user;
	private String pass;

	public SystemAdministrator(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public String getEmail() {
		return this.user;
	}
	public String getPass() {
		return this.pass;
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
				if (line[2].equals(this.user) && line[3].equals(this.pass) && line[4].equals("Administrator")) {
					return true;
				}
				System.out.println(line.length);
			}
			br.close(); 
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return false;
	}
	
	public void removeOfficer(ParkingEnforcer p1) {
		
		ArrayList<String[]> temp = new ArrayList<String[]>();
		
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				temp.add(line);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		for (int i =0; i < temp.size(); i++) {
			if (temp.get(i)[2].equals(p1.getEmail())) {
				temp.remove(i);
			}
		}
		
		try { //writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv"));
			StringBuilder sb = new StringBuilder();
			sb.append(temp.get(0)[0]);
			sb.append(",");
			sb.append(temp.get(0)[1]);
			sb.append(",");
			sb.append(temp.get(0)[2]);
			sb.append(",");
			sb.append(temp.get(0)[3]);
			sb.append(",");
			sb.append(temp.get(0)[4]);
			sb.append("\n");
			
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		try { //writing the new registration to the file
			for (int i =1; i < temp.size(); i++) {
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv",true));
			StringBuilder sb = new StringBuilder();
			sb.append(temp.get(i)[0]);
			sb.append(",");
			sb.append(temp.get(i)[1]);
			sb.append(",");
			sb.append(temp.get(i)[2]);
			sb.append(",");
			sb.append(temp.get(i)[3]);
			sb.append(",");
			sb.append(temp.get(i)[4]);
			sb.append("\n");
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
			}
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	

	public void addOfficer(ParkingEnforcer p1) {
		try { //writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv", true));
			StringBuilder sb = new StringBuilder();
			sb.append(p1.getFirstName());
			sb.append(",");
			sb.append(p1.getLastName());
			sb.append(",");
			sb.append(p1.getEmail());
			sb.append(",");
			sb.append(p1.getPass());
			sb.append(",");
			sb.append("Enforcer");
			sb.append("\n");
			
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		
		
		
	}
	
}
