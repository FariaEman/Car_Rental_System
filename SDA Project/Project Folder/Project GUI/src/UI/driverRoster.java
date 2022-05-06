package UI;
import java.util.ArrayList;

public class driverRoster {
//attributes
	private String totalDrivers;
	private static driverRoster Instance=null;
//Methods
	
	public static driverRoster getInstance() {
		if(Instance == null) {
			Instance = new driverRoster();
		}
		return Instance;		
	}
	
	
	public void addDriver(String name , String licence , int contact , String address, int CNIC, String gender) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Driver driver = new Driver(name,licence,contact,address,CNIC,gender);
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		dbservice.SaveDriver(driver);
	}
	
	public static ArrayList<Driver> showAllDrivers(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		return dbservice.getAllDrivers(filterOption);
	}
	
	
	public static void updateDriverStatus(String license, String status) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		Driver driver = dbservice.getDriver(license);
		
		driver.setStatus(status);
		
		dbservice.saveDriverChanges(driver);
	}


}
