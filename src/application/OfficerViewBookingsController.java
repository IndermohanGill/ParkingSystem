package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OfficerViewBookingsController implements Initializable{

	public TableView<BookingDataType> tableView = new TableView<BookingDataType>();
	public TableColumn<BookingDataType, String> bID = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> bTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> eTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> slot = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> plate = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> granted = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> paid = new TableColumn<BookingDataType, String>();
	public TextField fName = new TextField();
	public TextField lName = new TextField();
	public TextField email = new TextField();
	private ArrayList<Customer> p1 = new ArrayList<Customer>();
	public Button cancel = new Button();
	public Button enter = new Button();
	
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
		
	}
	
	public void getBookings() {
		
		boolean custExist = false;
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[2].equals(email.getText()) && line[0].equals(fName.getText()) && line[1].equals(lName.getText())  && line[4].equals("Customer")) {
					custExist = true;
					Customer c1 = new Customer(email.getText(), line[3]);
					p1.add(c1);
				}

			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		if (custExist == false) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("The information you have entered does not match our records please retry");
			a.showAndWait();
			return;
		}
		
		
		fillTable();
		
	}
	private void fillTable() {
		System.out.println("COMMENCE");
		
		ArrayList<Booking> list = new ArrayList<Booking>(p1.get(0).viewBookings());

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
		
		p1 = new ArrayList<Customer>();
	}
	
public void cancel() {
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
}
