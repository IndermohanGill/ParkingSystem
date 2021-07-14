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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterController {

	public Button register = new Button();
	public TextField fname = new TextField();
	public TextField lname = new TextField();
	public TextField email = new TextField();
	public PasswordField p1 = new PasswordField();
	public PasswordField p2 = new PasswordField();
	public Button cancel = new Button();

	@FXML
	public void register() throws IOException {
		String fName = fname.getText();
		String lName = lname.getText();
		String pw1 = p1.getText();
		String pw2 = p2.getText();
		String mail = email.getText();
		
		if (fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty() || p1.getText().isEmpty() || p2.getText().isEmpty()) {
			return;
		}
		
		if (!pw1.equals(pw2)) {
			Alert b = new Alert(AlertType.WARNING);
			b.setContentText("The entered passwords don't match. Please re-enter your password");
			b.showAndWait();
			return;
		} 
		else {
			Boolean tf = true;
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
				br.close();
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
				Stage stage = (Stage) register.getScene().getWindow();
				stage.close();
			}
		}
	
		

		
		try { //writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv", true));
			StringBuilder sb = new StringBuilder();
			sb.append(fName); //fName
			sb.append(",");
			sb.append(lName); // lName
			sb.append(",");
			sb.append(mail); // mail
			sb.append(",");
			sb.append(pw1); // pw1
			sb.append(",");
			sb.append("Customer"); // "Customer"
			sb.append("\n");
			
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		

		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void cancel() {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
}
