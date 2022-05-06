package UI;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

public class CarRentalStore {
	static Parking parking  = null;
	static driverRoster roster = null;
	static NotificationHandler notifHandler;
	
	CarRentalStore(){
	}
	
	public static void addCar(String PurchaseDate ,String Registration , String Model , String Make , int Year , String Type) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		parking = Parking.getInstance() ;
		parking.addCar(PurchaseDate, Registration ,  Model , Make, Year , Type);
	}
	
	
	public static void addDriver(String name , String licence , int contact , String address, int CNIC, String gender) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		roster = driverRoster.getInstance() ;
		roster.addDriver(name,licence,contact,address,CNIC,gender);
	}
	
	public static ArrayList<Car> showAllCars(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		parking = Parking.getInstance();
		return parking.showAllCars(filterOption);
	}
	
	
	public static ArrayList<Driver> showAllDrivers(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		roster = driverRoster.getInstance();
		return roster.showAllDrivers(filterOption);
	}
	
	
	public static void assignDriver(String license, String registration) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		Assignment assignment = new Assignment();
		Date date = new Date();
		
		assignment.finalizeAssignment(date, license, registration);
		
		
		parking = Parking.getInstance();
		parking.updateCarStatus(registration, "available");
		
		roster = driverRoster.getInstance();
		roster.updateDriverStatus(license, "assigned");
	}
	
	public static ArrayList<Assignment> viewAssignmentHistory(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		return dbservice.viewAssignmentHistory(filterOption);
	}
	
	public static void unassignDriver(String license) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Assignment assignment = dbservice.getAssignment(license);
		assignment.terminateAssignment();
		
		parking = Parking.getInstance();
		parking.updateCarStatus(assignment.getCarReg(), "unassigned");
		
		roster = driverRoster.getInstance();
		roster.updateDriverStatus(license, "unassigned");
			
	}
	
	
	public static String createReservation(String registration, String phoneNumber, Date startDate, Date endDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		String slip = "";
		
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		
		Reservation res = new Reservation(registration, phoneNumber, startDate,endDate);
		
		parking = Parking.getInstance();
		parking.reserveCar(registration);
		
		res.calculateFee();
		dbservice.saveReservation(res);
		
		return res.generateSlip();
	}
	
	public static ArrayList<Reservation> showReservations(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		return dbservice.getAllReservations(filterOption);
	}
	
	
	public static Reservation getReservation(String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		return dbservice.getReservation(id);
		
	}
	
	public static void createNewRental(String registration, String phoneNumber, Date startDate, Date endDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Rental rental = new Rental(registration,phoneNumber,startDate,endDate);
		
		parking = Parking.getInstance();
		parking.updateCarStatus(registration, "rented");
		
		rental.setStatus("active");
		
		dbservice.saveRental(rental);
		
	}
	
	
	public static void createNewRental(Reservation res) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Rental rental = new Rental();
		rental.copyInfo(res);
		
		parking = Parking.getInstance();
		parking.updateCarStatus(res.getCarReg(), "rented");
		
		rental.setStatus("active");
		
		dbservice.saveRental(rental);
	}
	
	
	public static ArrayList<Rental> showRentals(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		return dbservice.getAllRentals(filterOption);
	}
	
	public static void returnCar(String registration) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Rental rental = dbservice.getRental(registration);
		rental.setStatus("closed");
		dbservice.saveRentalChanges(rental);
		
		parking = Parking.getInstance();
		parking.updateCarStatus(registration, "available");
		
	}
	
	
	public static void sendConfirmationCode(String reservationID) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Reservation res = dbservice.getReservation(reservationID);
		NotificationHandler handler = null;
		handler.sendConfirmationCode(res);
	}
	
	

	public NotificationHandler getNotifHandler() {
		return notifHandler;
	}


	public void setNotifHandler(NotificationHandler notifHandler) {
		this.notifHandler = notifHandler;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
	
		
	}



	
		
		
		
	
	
	
}