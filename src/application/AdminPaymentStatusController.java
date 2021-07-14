package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminPaymentStatusController {

	public Button cancel = new Button();
	public Button change = new Button();
	public TextField fName = new TextField();
	public TextField lName = new TextField();
	public TextField email = new TextField();
	public TextField num = new TextField();
	
	@FXML
	public void cancel() {
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
	public void change() {
		
		String f = fName.getText();
		String l = lName.getText();
		String mail = email.getText();
		String loc = num.getText();
		boolean tf = false;
		boolean st = false;
		Booking b1;
		
		// check if user exists
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[0].equals(f) && line[1].equals(l) && line[4].equals("Customer")) {
					tf = true;
					CustomerController.cList.clear();
					CustomerController.cList.add(new Customer(line[3],line[4]));
				}

			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println("tf = " + tf);
		
		//check if booking exists, if it does add the new booking
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[4].equals(mail) && line[0].equals(loc)) {
					st = true;
					 b1 = new Booking(line[1], Integer.parseInt(line[0]), LocalDateTime.parse(line[2]),
							LocalDateTime.parse(line[3]), line[5], Boolean.valueOf(line[7]),
							Boolean.valueOf(line[6]),Boolean.valueOf(line[8]),Integer.parseInt(line[9]));
					 b1.setPaid(true);
					b1.setBookingTime(LocalDateTime.now());
					b1.setExpiryTime(LocalDateTime.now().plusMinutes(b1.getDuration()));
					CustomerController.cList.get(0).addBooking(b1);
					
				}
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println("st = " + st);
		if (tf == false ||  st == false) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("The information you have entered doesn't match our records. Please check your information and try again");
			a.showAndWait();
			return;
		}
	
		ArrayList<String[]> temp = new ArrayList<String[]>();
		//rechecking the doc after the new booking was added
				try {
					File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv");

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
		
		
		//remove the old booking
		for (int i =0; i < temp.size(); i++) {
			if (temp.get(i)[0].equals(loc) && temp.get(i)[4].equals(mail) && temp.get(i)[7].equals("false")) {
				temp.remove(i);
			}
		}
		
		
		try { // writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv"));
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
			sb.append(",");
			sb.append(temp.get(0)[5]);
			sb.append(",");
			sb.append(temp.get(0)[6]);
			sb.append(",");
			sb.append(temp.get(0)[7]);
			sb.append(",");
			sb.append(temp.get(0)[8]);
			sb.append(",");
			sb.append(temp.get(0)[9]);
			sb.append("\n");

			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		try { // writing the new registration to the file
			for (int i = 1; i < temp.size(); i++) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv", true));
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
				sb.append(",");
				sb.append(temp.get(i)[5]);
				sb.append(",");
				sb.append(temp.get(i)[6]);
				sb.append(",");
				sb.append(temp.get(i)[7]);
				sb.append(",");
				sb.append(temp.get(i)[8]);
				sb.append(",");
				sb.append(temp.get(i)[9]);
				sb.append("\n");

				writer.append(sb.toString());
				System.out.println(sb.toString());
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
}
