package application;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PaymentController {

	public TextField name = new TextField();
	public TextField card = new TextField();
	public TextField cvc = new TextField();
	public TextField month = new TextField();
	public TextField year = new TextField();
	public Button cancel = new Button();
	public Button confirm = new Button();
	
	
	public void cancel() {
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
	public void payment() {
		String na = name.getText();
		String ca = card.getText();
		int cv = Integer.parseInt(cvc.getText());
		int mon = Integer.parseInt(month.getText());
		int yr = Integer.parseInt(year.getText());
		
		if (ca.length() != 16 || cvc.getText().length() > 4 || cvc.getText().length() < 3||  month.getText().length() != 2 || year.getText().length() !=4) {
			Alert a = new Alert(AlertType.WARNING);
			a.setContentText("The information you have entered contains a mistake. Please try again ");
			a.showAndWait();
			
		}
		
		Alert b = new Alert(AlertType.CONFIRMATION);
		b.setContentText("Are you sure you want to pay with this information?");
		b.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.OK);
		b.showAndWait();
		if (b.getResult().equals(ButtonType.OK)) {
			for (int i = 0; i < CustomerPriceController.payment.size();i++) {
				for (int j = 0; j < CustomerController.cList.get(0).viewBookings().size(); j++) {
					if (CustomerController.cList.get(0).viewBookings().get(j).equals(CustomerPriceController.payment.get(i))) {
						Booking b1 = CustomerController.cList.get(0).viewBookings().get(j);
						b1.setPaymentStatus(true);
						CustomerController.cList.get(0).cancelBooking(b1.getBookingID());
						CustomerController.cList.get(0).addBooking(b1);
						CustomerPriceController.payment.remove(i);
					}
				}
			}
		}
		
		Stage stage = (Stage) cancel.getScene().getWindow();
		stage.close();
	}
	
}
