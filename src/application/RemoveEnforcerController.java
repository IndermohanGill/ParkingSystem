package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RemoveEnforcerController implements Initializable {

	public TextField email = new TextField();
	public TableView<EnforcerDataType> tableView = new TableView<EnforcerDataType>();
	public TableColumn<EnforcerDataType,String> fName = new TableColumn<EnforcerDataType,String>();
	public TableColumn<EnforcerDataType,String> lName = new TableColumn<EnforcerDataType,String>();
	public TableColumn<EnforcerDataType,String> mail = new TableColumn<EnforcerDataType,String>();
	
	public Button enter = new Button();
	public Button cancel = new Button();
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		fName.setCellValueFactory(new PropertyValueFactory<EnforcerDataType, String>("f"));
		lName.setCellValueFactory(new PropertyValueFactory<EnforcerDataType, String>("l"));
		mail.setCellValueFactory(new PropertyValueFactory<EnforcerDataType,String>("email"));


	}
	
	@FXML
	public void cancel() {
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
	public void getOfficer() {
		
		String holder = email.getText();
		String[] line = null;
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				 line = strline.split(",");
				 if (line[2].equals(holder)) {
					 tableView.getItems().add(new EnforcerDataType(line[0],line[1],line[2]));
				 }
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		
		
	}
	
	
	public void removeOfficer() {
		
		String holder = mail.getCellData(0);
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
				
				System.out.println(line.length);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		
		String pass = "";
		for (int i =0; i < temp.size(); i ++) {
			if (temp.get(i)[2].equals(holder)) {
				System.out.println("WE FOUND IT ");
				tableView.getItems().clear();
				email.clear();
				pass = temp.get(i)[3];
				temp.remove(i);
			}
		}
		ParkingEnforcer c1 = new ParkingEnforcer(holder,pass);
		SystemAdministrator a1 = new SystemAdministrator(AdminController.aList.get(0).getEmail(),AdminController.aList.get(0).getPass());
		a1.removeOfficer(c1);
		
		
		
		
	}
	
	
	
	
	
}
