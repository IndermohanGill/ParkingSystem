package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class CustomerController {

	public Button bookSpace = new Button();
	public Button viewBooking = new Button();
	public Button cancelBooking = new Button();
	public static ArrayList<Customer> cList = new ArrayList<Customer>();
	public Button pay = new Button();

	
	
	public void signOut() {
		logout(cList.get(0));
	}
	
	
	public void logout(Customer c1) {
				
		Stage stage = (Stage) bookSpace.getScene().getWindow();
		stage.close();
		
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
	
	public void bookParking() {
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("ParkingBooking.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void viewParking() {

		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CustomerViewBookings.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			System.out.println("Commence");
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void removeBooking() {
	
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CancelBooking.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			System.out.println("Commence");
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void pay() {
	
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CustomerPrice.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			System.out.println("Commence");
			primaryStage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
