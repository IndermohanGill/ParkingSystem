package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class LoginController {
	public TextField userName = new TextField();
	public PasswordField password = new PasswordField();
	public Button login = new Button();
	public CheckBox customerCheck = new CheckBox();
	public CheckBox adminCheck = new CheckBox();
	public CheckBox officerCheck = new CheckBox();
	public Button register = new Button();

	@FXML
	public void getLogin(ActionEvent event) {
		String pass = password.getText();
		String user = userName.getText();
		System.out.println(pass);
			CustomerLogin(new Customer(user, pass));
			adminLogin(new SystemAdministrator(user, pass));
			officerLogin(new ParkingEnforcer(user, pass));
		
	}
	
	@FXML 
	public void register() {
		System.out.println("REGISTER");
		Stage stage = (Stage) login.getScene().getWindow();
		stage.close();
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	private void CustomerLogin(Customer c1) {
		boolean tf = c1.checkLogin();
		
		if (tf == true) {
			CustomerController.cList.add(c1);
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("CustomerScreen.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Stage stage = (Stage) register.getScene().getWindow();
			stage.close();
		}

		
		
	}

	private void adminLogin(SystemAdministrator s1) {
		boolean tf = s1.checkLogin();
		if (tf == true ) {
			try {
				AdminController.aList.add(s1);
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage stage = (Stage) register.getScene().getWindow();
			stage.close();
		}
		
		
	}

	private void officerLogin(ParkingEnforcer p1) {
		boolean tf = p1.checkLogin();
		if (tf == true) {
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("OfficerScreen.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage stage = (Stage) register.getScene().getWindow();
			stage.close();
		}
		
		
	}

}
