package UI;
import java.util.ArrayList;

public class Parking {
	//Attributes
		private static Parking Instance=null;
		private String totalCapacity;
		private String remainingCapacity;
		
	//Methods
		public static Parking getInstance() {
			if(Instance == null) {
				Instance = new Parking();
			}
			return Instance;		
		}
		
		public void addCar(String PurchaseDate ,String Registration , String Model , String Make , int Year , String Type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			Car car = new Car(PurchaseDate,Registration,Model,Make,Year,Type);
			PersistenceFactory pf = PersistenceFactory.getInstance();
			DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
			dbservice.SaveCar(car);
		}
		
//		public static Car getCar(String registration) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
//		{
//			PersistenceFactory pf = PersistenceFactory.getInstance();
//			DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
//			return dbservice.getCar(registration);
//		}
		
		public static ArrayList<Car> showAllCars(String filterOption) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
		{
			PersistenceFactory pf = PersistenceFactory.getInstance();
			DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
			return dbservice.getAllCars(filterOption);
		}
		
		public static void updateCarStatus(String registration, String status) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
		{
			PersistenceFactory pf = PersistenceFactory.getInstance();
			DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
			
			Car car = dbservice.getCar(registration);
			
			car.setStatus(status);
			
			dbservice.saveCarChanges(car);
			
		}
		
		public static void reserveCar(String reg) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			PersistenceFactory pf = PersistenceFactory.getInstance();
			DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
			
			Car car = dbservice.getCar(reg);
			
			car.setStatus("reserved");
			dbservice.saveCarChanges(car);
		}



	}

