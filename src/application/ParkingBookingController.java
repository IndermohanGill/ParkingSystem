package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ParkingBookingController {

	public Button enter = new Button();
	public TextField textBox = new TextField();
	public TextField plate = new TextField();
	public TextField duration = new TextField();
	public Button cancel = new Button();

	@FXML
	public void parkingSpace(ActionEvent event) {
		
		try {
			int num = Integer.parseInt(textBox.getText());
			int time = Integer.parseInt(duration.getText());
			System.out.println("space: " + num + " duration: " + time);
			if (num < 1 || num > 10000) {
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText("Please enter a number between 1 and 10000");
				a.show();
			}
			ArrayList<Booking> list = new ArrayList<Booking>(CustomerController.cList.get(0).viewBookings());
			if (list.size() == 3) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("You have exceeded the maximum booking requirement you cannot book anymore");
				a.showAndWait();
				Stage stage = (Stage) enter.getScene().getWindow();
				stage.close();
				return;
			}
			Booking b1 = new Booking(RandomString.getAlphaNumericString(8), num, time, plate.getText());
			CustomerController.cList.get(0).addBooking(b1);

			System.out.println("a new booking has been added, size of bookingList: "
					+ CustomerController.cList.get(0).viewBookings().size());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("Please enter a number only");
			a.show();
		}

		Stage stage = (Stage) enter.getScene().getWindow();
		stage.close();

	}
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
		
	}
	
	
}
