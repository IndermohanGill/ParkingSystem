package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Customer {
	private String fName;
	private String lName;
	private String user;
	private String pass;
	private String lPlate;
	private ArrayList<Booking> bList;
	

	public Customer(String userName, String password) {
		this.user = userName;
		this.pass = password;
		bList = new ArrayList<Booking>();
	}

	public Customer(Customer c1) {
		// TODO Auto-generated constructor stub
		this.user = c1.getEmail();
		this.pass = c1.getEmail();
		this.bList = new ArrayList<Booking>(c1.viewBookings());
	}

	public boolean checkLogin() {

		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/UserDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[2].equals(this.user) && line[3].equals(this.pass) && line[4].equals("Customer")) {
					return true;
				}

			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return false;

	}

	public String getEmail() {
		return this.user;
	}

	

	public ArrayList<Booking> viewBookings() {
		try {
			File file = new File("/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv");

			System.out.println(file.getAbsolutePath());
			FileInputStream ft = new FileInputStream(file);

			DataInputStream in = new DataInputStream(ft);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;
			while ((strline = br.readLine()) != null) {
				String[] line = strline.split(",");
				if (line[4].equals(this.getEmail())) {
					Booking b1 = new Booking(line[1], Integer.parseInt(line[0]), LocalDateTime.parse(line[2]),
							LocalDateTime.parse(line[3]), line[5], Boolean.valueOf(line[7]),
							Boolean.valueOf(line[6]),Boolean.valueOf(line[8]),Integer.parseInt(line[9]));
					boolean tf = false;
					for (int i = 0; i < this.bList.size(); i++) {
						if (b1.getBookingID().equals(this.bList.get(i).getBookingID())) {
							tf = true;
						}
					}
					if (tf == false) {
						this.bList.add(b1);
					}
				}

			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		

		return this.bList;
	}

	

	public boolean cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		boolean tf = false;
		for (int i = 0; i < bList.size(); i++) {
			if (bList.get(i).getBookingID().equals(bookingID)) {
				bList.remove(i);
				tf = true;
				break;
			}
		}
		if (tf == false) {
			return false;
		}

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
			if (temp.get(i)[1].equals(bookingID)) {
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

		return true;
	}

	public int addBooking(Booking b1) {
		// TODO Auto-generated method stub
		boolean tf = false;
		if (this.bList.size() < 3) {
			bList.add(b1);
			tf = true;
		}

		if (tf == false) {
			return -1;
		}

		try { // writing the bookings to a saved file

			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"/Users/inder/Desktop/EECS3311/3311Project/src/application/BookingDatabase.csv",true));
			StringBuilder sb = new StringBuilder();
			
			sb.append("" + b1.getLocation());
			sb.append(",");
			sb.append(b1.getBookingID() + "");
			sb.append(",");
			sb.append(b1.getBookingTime() + "");
			sb.append(",");
			sb.append(b1.getExpiryTime() + "");
			sb.append(",");
			sb.append(CustomerController.cList.get(0).getEmail() + "");
			sb.append(",");
			sb.append(b1.getLicencePlate() + "");
			sb.append(",");
			sb.append(b1.isGranted() + "");
			sb.append(",");
			sb.append(b1.isPaid() + "");
			sb.append(",");
			sb.append(b1.getPaymentStatus() + "");
			sb.append(",");
			sb.append(b1.getDuration() + "");
			sb.append("\n");

			writer.append(sb.toString());
			System.out.println(sb.toString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return this.bList.size() - 1;
	}

	public String getPassword() {
		return this.pass;
	}
	
	

}
