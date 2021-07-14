
import application.Customer;
import application.ParkingEnforcer;
import application.SystemAdministrator;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import application.Booking;

public class Test1 {

	
	@Test
	public void Customertest_LOGIN1() {
		Customer c1 = new Customer("user", "pass");
		assertEquals(false, c1.checkLogin());
	}
	
	@Test
	public void Customertest_LOGIN2() {
		Customer c1 = new Customer("inder19@my.yorku.ca", "1234");
		assertEquals(true, c1.checkLogin());
	}
	
	@Test
	public void Admintest_LOGIN1() {
		SystemAdministrator c1 = new SystemAdministrator("user", "pass");
		assertEquals(false, c1.checkLogin());
	}
	
	@Test
	public void Admintest_LOGIN2() {
		SystemAdministrator c1 = new SystemAdministrator("admin1", "admin123");
		assertEquals(true, c1.checkLogin());
	}
	
	@Test
	public void addOfficer() {
		ParkingEnforcer c1 = new ParkingEnforcer("user", "pass");
		SystemAdministrator s1 = new SystemAdministrator("admin1", "admin123");
		s1.addOfficer(c1);
		assertEquals(true,c1.checkLogin());

	}
	@Test
	public void removeOfficer() {
		ParkingEnforcer c1 = new ParkingEnforcer("user", "pass");
		SystemAdministrator s1 = new SystemAdministrator("admin1", "admin123");
		s1.removeOfficer(c1);
		assertEquals(false,c1.checkLogin());

	}
	
	@Test
	public void enforcer_LOGIN1() {
		ParkingEnforcer c1 = new ParkingEnforcer("user", "pass");
		ParkingEnforcer c2 = new ParkingEnforcer(c1.getEmail(),c1.getPass(),"Parking", "Enforcer");
		assertEquals(false, c2.checkLogin());
	}
	
	@Test
	public void enforcer_LOGIN2() {
		ParkingEnforcer c1 = new ParkingEnforcer("enforcer1@yorku.ca", "officer");
		assertEquals(true, c1.checkLogin());
	}
	
	
	@Test
	public void Customertest_Booking1() {
		Booking b1 = new Booking("hello", 30, 20 , "Toronto");
		Customer c1 = new Customer("inder19@my.yorku.ca", "1234");
		
		c1.addBooking(b1);
	
		
 		assertEquals(c1.viewBookings().size(), 1);
			
	}
	
	@Test
	public void Customertest_Booking2() {
		Booking b1 = new Booking("hello", 30, 20 , "Toronto");
		Booking b2 = new Booking("hello", 15, 20 , "Toronto");
		Customer c1 = new Customer("inder19@my.yorku.ca", "1234");
		c1.addBooking(b1);
		c1.addBooking(b2);
		
		assertEquals(c1.viewBookings().size(), 2);	
	}
	
	@Test
	public void Customertest_CanceBooking1() {
		Booking b1 = new Booking("hello", 15, 20 , "Toronto");
		Customer c1 = new Customer("inder19@my.yorku.ca", "1234");
		
		c1.addBooking(b1);
		ArrayList<Booking> list =new ArrayList<Booking>(c1.viewBookings());
		String id = list.get(0).getBookingID();
		
		c1.cancelBooking(id);
		
		assertEquals(c1.viewBookings().size(), 0);
		
	}
	
	@Test
	public void Customertest_CanceBooking2() {
		Booking b1 = new Booking("hello", 30, 20 , "Toronto");
		Booking b2 = new Booking("hello", 15, 20 , "Toronto");
		Customer c1 = new Customer("inder19@my.yorku.ca", "1234");
		c1.addBooking(b1);
		c1.addBooking(b2);
		ArrayList<Booking> list =new ArrayList<Booking>(c1.viewBookings());
		
		for (int i =0 ; i < list.size(); i++) {
			String id = list.get(i).getBookingID();
			
			c1.cancelBooking(id);
			
		}
		
		assertEquals(c1.viewBookings().size(), 0);
		
	}
	
	
}
