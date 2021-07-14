package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class AdminController {

	public Button add = new Button();
	public Button remove = new Button();
	public Button payment = new Button();
	public TextField first = new TextField();
	public TextField last = new TextField();
	public TextField email = new TextField();
	public TextField pw1 = new TextField();
	public TextField pw2 = new TextField();
	public static ArrayList<SystemAdministrator> aList = new ArrayList<SystemAdministrator>();
	
	
	public void addEnforcer() {
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("NewEnforcer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void removeEnforcer() {
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveEnforcer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void paymentStatus() {
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AdminPaymentStatus.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
