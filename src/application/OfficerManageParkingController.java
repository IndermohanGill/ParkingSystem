package application;

import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OfficerManageParkingController implements Initializable{
	
	public Button cancel = new Button();
	public Button add = new Button();
	public Button remove = new Button();
	public Button manage = new Button();
	public TextField txt = new TextField();
	public ListView<String> lView = new ListView<String>();
	
	
	public void manageRequests() {
		int num = Integer.parseInt(txt.getText());
		if (num < 0 || num > 10000) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("This number does not fit in range 0 to 10000. Please re-enter the number");
			a.showAndWait();
			return;
		}
		try {
			OfficerManageRequestController.yee = num;
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("OfficerManageRequest.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txt.clear();
	}
	
	public void addSpace() {
		int num = Integer.parseInt(txt.getText());
		if (num < 0 || num > 10000) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("This number does not fit in range 0 to 10000. Please re-enter the number");
			a.showAndWait();
			return;
		}
		for (int i =0; i < lView.getItems().size(); i++) {
			if (lView.getItems().get(i).equals(Integer.toString(num))){
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("This parking space has already been added. Please enter a new number.");
				a.showAndWait();
				return;
			}
		}
		
		try { //writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/ParkingSpaces.txt", true));
			StringBuilder sb = new StringBuilder();
			sb.append(num + "");
			sb.append("\n");
			
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		lView.getItems().add(Integer.toString(num));
		txt.clear();
	}
	
	public void removeSpace() {
		int num = Integer.parseInt(txt.getText());
		if (num < 0 || num > 10000) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("This number does not fit in range 0 to 10000. Please re-enter the number");
			a.showAndWait();
			return;
		}
		boolean tf = false;
		for (int i =0; i < lView.getItems().size(); i++) {
			if (lView.getItems().get(i).equals(txt.getText())) {
				lView.getItems().remove(i);
				tf = true;
				break;
			}
		}
		if (tf == false) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("This number has not been added. Please re-enter the number");
			a.showAndWait();
			return;
		}
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/ParkingSpaces.txt");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				temp.add(strline);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		for (int i =0; i < temp.size(); i++) {
			if (temp.get(i).equals(Integer.toString(num))) {
				temp.remove(i);
			}
		}
		
		try { //writing the new registration to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/ParkingSpaces.txt"));
			StringBuilder sb = new StringBuilder();
			sb.append(temp.get(0));
			sb.append("\n");
			
			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		}catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		for (int i =1; i< temp.size(); i++) {
			try { //writing the new registration to the file
				BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/inder/Desktop/EECS3311/3311Project/src/application/ParkingSpaces.txt",true));
				StringBuilder sb = new StringBuilder();
				sb.append(temp.get(i));
				sb.append("\n");
				
				writer.append(sb.toString());
				System.out.println(sb.toString());
				writer.flush();
				writer.close();
			}catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
			
		}
		txt.clear();
		
	}
	
	
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/ParkingSpaces.txt");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				temp.add(strline);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		for (int i = 1; i < temp.size(); i++) {
			lView.getItems().add(temp.get(i));
		}
	}
	
	
	
	
	
	
	
}
