package UI;

public class PersistenceFactory {
	//Attribute
	
	private static PersistenceFactory Instance = null;
	PersistenceHandler service=null;
	//Method
	
	private PersistenceFactory() {
	}
	
	private PersistenceFactory(PersistenceFactory Instance) {
		PersistenceFactory.Instance = Instance;
	}
	
	public static synchronized PersistenceFactory getInstance() {
		if(Instance == null) {
			Instance = new PersistenceFactory();
		}
		return Instance;		
	}
	public PersistenceHandler  createPersistenceService(String servName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(service == null) {
			if(servName.equals("DBOracle")) {
			service = new DBOracle();
			}
		}
		return service;
		
	}
}
