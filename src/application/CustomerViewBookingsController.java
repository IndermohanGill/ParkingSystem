package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerViewBookingsController implements Initializable {

	public TableView<BookingDataType> tableView = new TableView<BookingDataType>();
	public TableColumn<BookingDataType, String> bID = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> bTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> eTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> slot = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> plate = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> granted = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> paid = new TableColumn<BookingDataType, String>();
	public Button cancel = new Button();
	
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
		
		
		System.out.println(CustomerController.cList.size() + " = size of customer list");
		
		fillTable();
	}

	public void fillTable() {
		System.out.println("COMMENCE");
		
		ArrayList<Booking> list = new ArrayList<Booking>(CustomerController.cList.get(0).viewBookings());

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
	
	
	public void cancel() {
		

		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
}
