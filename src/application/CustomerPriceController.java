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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerPriceController  {

	public Button pay = new Button();
	public Button cancel = new Button();
	public TextField field = new TextField();
	public Button enter =  new Button();
	public static ArrayList<Booking> payment = new ArrayList<Booking>();
	public ListView<String> lView =  new ListView<String>();
	
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
		
	}
	
	public void fillTable() {
	
		String id = field.getText();
		ArrayList<Booking> list = new ArrayList<Booking>(CustomerController.cList.get(0).viewBookings());
		for (int i = 0; i < list.size(); i ++ ) {
			System.out.println("durattion= " + list.get(i).getDuration());
			if (list.get(i).getBookingID().equals(id) && list.get(i).getPaymentStatus() == false) {
				payment.add(list.get(i));
				boolean tf = false;
				String total = "Parking Space payment for Booking: " + list.get(i).getBookingID() + " = " + ((double) list.get(i).getDuration() / 60 * 15); 
				for (int j =0; j < lView.getItems().size(); j++) {
					if (lView.getItems().get(i).equals(total)) {
						tf = true;
						break;
					}
				}
				if (tf == false) {
					lView.getItems().add(total);
				}
			}
			if (list.get(i).getBookingID().equals(id) && list.get(i).getPaymentStatus() == true) {
				Alert b = new Alert(AlertType.WARNING);
				b.setContentText("This booking has already been paid for. Please enter a booking that has not already been paid for. \n"
						+ "Please wait for a System Administrator to confirm the payment to change the status if it has not already been changed.");
				b.showAndWait();
				return;
			}
		}
	}
	
	
	public void pay() {
		
		lView.getItems().clear();
		field.clear();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
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
