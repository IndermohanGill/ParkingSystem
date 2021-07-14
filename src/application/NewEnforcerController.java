package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewEnforcerController {

	public TextField first = new TextField();
	public TextField last = new TextField();
	public TextField email = new TextField();
	public PasswordField pw1 = new PasswordField();
	public PasswordField pw2 = new PasswordField();
	public Button add = new Button();
	public Button cancel = new Button();
	

	@FXML
	public void cancel() {
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
	public void create() {
		String f = first.getText();
		String l = last.getText();
		String mail = email.getText();
		String p1 = pw1.getText();
		String p2 = pw2.getText();
		
		
		if (!p1.equals(p2)) {
			Alert b = new Alert(AlertType.WARNING);
			b.setContentText("The entered passwords don't match. Please re-enter your password");
			b.showAndWait();
			return;
		} 
		boolean tf = true;
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");
			

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[2].toLowerCase().equals(mail.toLowerCase())) {
					
					tf = false;
					
				}

			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		if (tf == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("This e-mail is already in use. Please enter a different e-mail");
			alert.showAndWait();
			return;
		}
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText("Are you sure you want to register with this information");
		a.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.OK);
		a.showAndWait();

		if (a.getResult() == (ButtonType.OK)) {
			Stage stage = (Stage) add.getScene().getWindow();
			stage.close();
		}
		
		ParkingEnforcer pe1 = new ParkingEnforcer(mail,p1,f,l);
		AdminController.aList.get(0).addOfficer(pe1);
		
		
	}
	
}
