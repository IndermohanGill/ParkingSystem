package application;

import java.sql.Time;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.Object;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Booking {

	private String bookingID;
	private int location;
	private LocalDateTime bookingTime;
	private LocalDateTime expiryTime;
	private String licencePlate;
	private boolean isPaid;
	private boolean granted;
	private boolean paymentStatus;
	private int duration;

	public Booking(String id, int pSpace, int duration, String lPlate) {
		this.bookingID = id;
		this.location = pSpace;
		this.bookingTime = LocalDateTime.MAX;
		this.expiryTime = LocalDateTime.MAX;
		this.licencePlate = lPlate;
		this.setPaid(false);
		this.setGranted(false);
		this.setPaymentStatus(false);
		this.setDuration(duration);
	}

	public Booking(String bId, int pSpace, LocalDateTime bTime, LocalDateTime eTime, String plate, boolean paid,
			boolean granted, boolean paymentStatus, int duration) {
		this.bookingID = bId;
		this.location = pSpace;
		this.bookingTime = bTime;
		this.expiryTime = eTime;
		this.licencePlate = plate;
		this.setPaid(paid);
		this.setGranted(granted);
		this.setPaymentStatus(paymentStatus);
		this.setDuration(duration);
	}

	public void setBookingTime(LocalDateTime e) {
		this.bookingTime = e;
	}

	public void setExpiryTime(LocalDateTime e) {
		this.expiryTime = e;
	}

	public boolean getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(boolean tf) {
		this.paymentStatus = tf;
	}


	public String getBookingID() {
		return this.bookingID;
	}

	public int getLocation() {
		return this.location;
	}

	public LocalDateTime getBookingTime() {
		return this.bookingTime;
	}

	public LocalDateTime getExpiryTime() {
		return this.expiryTime;
	}

	public String getLicencePlate() {
		return this.licencePlate;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isGranted() {
		return granted;
	}

	public void setGranted(boolean granted) {
		this.granted = granted;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

//	public static void checkExpiry() {
//		ArrayList<Booking> expired = new ArrayList<Booking>();
//		String mail = "";
//		try {
//			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv");
//
//			System.out.println(file.getAbsolutePath());
//			FileInputStream ft = new FileInputStream(file);
//
//			DataInputStream in = new DataInputStream(ft);
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String strline;
//			while ((strline = br.readLine()) != null) {
//				String[] line = strline.split(",");
//				if (line[4].equals(CustomerController.cList.get(0).getEmail())) {
//					Booking b1 = new Booking(line[1], Integer.parseInt(line[0]), LocalDateTime.parse(line[2]),
//							LocalDateTime.parse(line[3]), line[5], Boolean.valueOf(line[7]), Boolean.valueOf(line[6]),
//							Boolean.valueOf(line[8]), Integer.parseInt(line[9]));
//					Alert a = new Alert(AlertType.WARNING);
//					a.setContentText("Your booking at location: " + b1.getLocation() + " with Booking ID: " + b1.getBookingID() + " has expired. \n ");
//					CustomerController.cList.get(0).cancelBooking(b1.getBookingID());
//					a.show();
//				}
//			}
//			br.close();
//		} catch (Exception e) {
//			System.err.println("Error: " + e.getMessage() + "HE");
//		}
//
//
//
//	}

}
