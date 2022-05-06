package UI;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class PersistenceHandler {

	  public abstract void SaveCar(Car car);
	  public abstract void SaveDriver(Driver driver);	
	  public abstract Car getCar(String registration);
	  public abstract ArrayList<Car> getAllCars(String filterOption); 
	  public abstract Driver getDriver(String license);
	  public abstract ArrayList<Driver> getAllDrivers(String filterOption);
	  public abstract void saveCarChanges(Car car);
	  public abstract void saveDriverChanges(Driver driver);
	  public abstract void saveAssignment(Assignment assignment);
	  public abstract Assignment getAssignment(String license);
	  public abstract ArrayList<Assignment> viewAssignmentHistory(String filterOption);
	  public abstract void saveAssignmentChanges(Assignment assignment);
	  public abstract void saveReservation(Reservation res);
	  public abstract Reservation getReservation(String id);
	  public abstract void saveReservationChanges(Reservation res);
	  public abstract ArrayList<Reservation> getAllReservations(String filterOption);
	  public abstract void saveRental(Rental rental);
	  public abstract Rental getRental(String registration);
	  public abstract ArrayList<Rental> getAllRentals(String filterOption);
	  public abstract void saveRentalChanges(Rental rental);
	

	
}
