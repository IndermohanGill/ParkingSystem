package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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

public class CancelBookingController implements Initializable{

	public TableView<BookingDataType> tableView = new TableView<BookingDataType>();
	public TableColumn<BookingDataType, String> bID = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> bTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> eTime = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> slot = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> plate = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> granted = new TableColumn<BookingDataType, String>();
	public TableColumn<BookingDataType, String> paid = new TableColumn<BookingDataType, String>();
	public TextField booking = new TextField();
	public Button cancel = new Button();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		bID.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("bookingID"));
		bTime.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("bookingTime"));
		eTime.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("expiryTime"));
		slot.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("location"));
		plate.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("plate"));
		granted.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("granted"));
		paid.setCellValueFactory(new PropertyValueFactory<BookingDataType, String>("paid"));
		
		
	}
	
	public void getBooking() {
		String id = booking.getText();
		
		ArrayList<Booking> list = new ArrayList<Booking>(CustomerController.cList.get(0).viewBookings());
		System.out.println(list.size());
		for (int i =0; i< list.size(); i++) {
			if (list.get(i).getBookingID().equals(id)){
				String bID = list.get(0).getBookingID();
				LocalDateTime eTime = list.get(0).getExpiryTime();
				LocalDateTime bTime = list.get(0).getBookingTime();
				String plate = list.get(0).getLicencePlate();
				int location = list.get(0).getLocation();
				boolean paid = list.get(0).isPaid();
				boolean granted = list.get(0).isGranted();
				tableView.getItems().add(new BookingDataType(bID, bTime, eTime, location, plate,granted,paid));
			}
		}
		
	}
	
	public void removeBooking() {
		System.out.println("We reached fam");
		String id = booking.getText();
		System.out.println(id);
		ArrayList<Booking> list = new ArrayList<Booking>(CustomerController.cList.get(0).viewBookings());
		
		for (int i =0; i < list.size(); i++) {
			if (list.get(i).getBookingID().equals(id)) {
				System.out.println("We reached fam");
				CustomerController.cList.get(0).cancelBooking(id);
			}
		}
		booking.clear();
		tableView.getItems().clear();
	}
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}

}
