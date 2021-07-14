package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OfficerManageRequestController implements Initializable{

	public Button grant= new Button();
	public Button decline = new Button();
	public Button cancel = new Button();
	public TableView<BookingDataType> tableView = new TableView<BookingDataType>();
	public TableColumn<BookingDataType, String> bID = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> bTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> eTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> slot = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> plate = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> granted = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> paid = new TableColumn<BookingDataType, String>();
	public static int yee = -1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tableView.getItems().clear();
		
		tableView.getSelectionModel().setCellSelectionEnabled(true);
	    tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    bID.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("bookingID"));
		bTime.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("bookingTime"));
		eTime.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("expiryTime"));
		slot.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("location"));
		plate.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("plate"));
		granted.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("granted"));
		paid.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("paid"));
		fillTable();
		
	}

	
	public void fillTable() {
		
		
		ArrayList<Booking> list = new ArrayList<Booking>();
		
		ArrayList<String[]> temp = new ArrayList<String[]>();
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
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i)[0].equals(Integer.toString(yee))) {
				Booking b2 = new Booking(temp.get(i)[1], Integer.parseInt(temp.get(i)[0]), LocalDateTime.parse(temp.get(i)[2]),
						LocalDateTime.parse(temp.get(i)[3]), temp.get(i)[5], Boolean.valueOf(temp.get(i)[7]),
						Boolean.valueOf(temp.get(i)[6]),Boolean.valueOf(temp.get(i)[8]),Integer.parseInt(temp.get(i)[9]));
				list.add(b2);
				System.out.println("WE FOUND IT ");
				
			}
		}

		for (int i = 0; i < list.size(); i++) {

			String bID = list.get(i).getBookingID();
			LocalDateTime eTime = list.get(i).getExpiryTime();
			LocalDateTime bTime = list.get(i).getBookingTime();
			String plate = list.get(i).getLicencePlate();
			int location = list.get(i).getLocation();
			boolean paid = list.get(i).isPaid();
			boolean granted = list.get(i).isGranted();
			tableView.getItems().add(new BookingDataType(bID, bTime, eTime, location, plate,granted,paid));
		}
	}
	
	public void accept() {
		String mail = "";
		ArrayList<String[]> temp = new ArrayList<String[]>();
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
		ArrayList<Booking> list = new ArrayList<Booking>();
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i)[0].equals(Integer.toString(yee))) {
				Booking b2 = new Booking(temp.get(i)[1], Integer.parseInt(temp.get(i)[0]), LocalDateTime.parse(temp.get(i)[2]),
						LocalDateTime.parse(temp.get(i)[3]), temp.get(i)[5], Boolean.valueOf(temp.get(i)[7]),
						Boolean.valueOf(temp.get(i)[6]),Boolean.valueOf(temp.get(i)[8]),Integer.parseInt(temp.get(i)[9]));
				mail = temp.get(i)[4];
				b2.setGranted(true);
				list.add(b2);
				System.out.println("WE FOUND IT ");
				temp.remove(i);
				
			}
		}
		System.out.println(list.get(list.size()-1).getLocation());
		System.out.println("list size:" + list.size());
		System.out.println("temp size:" + temp.size());
		System.out.println(list.get(list.size()-1).isGranted());
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
		
		try { // writing the bookings to a saved file
			System.out.println("Writing the granted booking into hte dattabase");

			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv",true));
			StringBuilder sb = new StringBuilder();
			
			sb.append("" + list.get(0).getLocation());
			sb.append(",");
			sb.append(list.get(0).getBookingID() + "");
			sb.append(",");
			sb.append(list.get(0).getBookingTime() + "");
			sb.append(",");
			sb.append(list.get(0).getExpiryTime() + "");
			sb.append(",");
			sb.append(mail + "");
			sb.append(",");
			sb.append(list.get(0).getLicencePlate() + "");
			sb.append(",");
			sb.append(list.get(0).isGranted() + "");
			sb.append(",");
			sb.append(list.get(0).isPaid() + "");
			sb.append(",");
			sb.append(list.get(0).getPaymentStatus() + "");
			sb.append(",");
			sb.append(list.get(0).getDuration() + "");
			sb.append("\n");

			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		tableView.getItems().remove(0);
		
	}
	
	public void decline() {
		BookingDataType b1 = tableView.getItems().get(0); 
		String bId = b1.getBookingID();
		
		ArrayList<String[]> temp = new ArrayList<String[]>();
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

				System.out.println(line.length);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i)[0].equals(Integer.toString(yee))) {
				
				System.out.println("WE FOUND IT ");
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
		
		
	}
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

	
}
