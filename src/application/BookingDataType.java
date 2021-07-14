package application;

import java.time.LocalDateTime;



public class BookingDataType {

	private final String bookingID;
	private final String location;
	private final String bookingTime;
	private final String expiryTime;
	private final String plate;
	private final String paid;
	private final String granted;
	
	public BookingDataType(String bID, LocalDateTime bTime, LocalDateTime eTime, int slot,String lPlate,boolean granted, boolean paid) {
		this.bookingID = bID;
		this.location = Integer.toString(slot);
		this.bookingTime = bTime.toString();
		this.expiryTime = eTime.toString();
		this.plate = lPlate;
		this.paid = Boolean.toString(paid);
		this.granted = Boolean.toString(granted);
	}


	public String getBookingID() {
		return bookingID;
	}


	public String getLocation() {
		return location;
	}


	public String getBookingTime() {
		return bookingTime;
	}


	public String getExpiryTime() {
		return expiryTime;
	}


	public String getPlate() {
		return plate;
	}


	public String getPaid() {
		return paid;
	}


	public String getGranted() {
		return granted;
	}
	
}
