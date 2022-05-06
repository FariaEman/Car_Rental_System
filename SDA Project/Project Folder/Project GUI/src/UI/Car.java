package UI;

public class Car {
	private String PurchaseDate;
	private String Registration;
	private String Model;
	private String Make;
	private int Year;
	private String Type;
	private String Status;
	
	//Default Constructor
	public Car() {
		PurchaseDate = "";
		Registration = "";
		Model = "";
		Make = "";
		Year = 0;
		Type = "";
		Status = "";
	}
	
	//Argumented Constructor
		public Car(String PurchaseDate ,String Registration , String Model , String Make , int Year , String Type) {
			this.PurchaseDate = PurchaseDate;
			this.Registration = Registration;
			this.Model = Model;
			this.Make = Make;
			this.Year = Year;
			this.Type = Type;
			this.Status = "unassigned"; // can be "all", "unassigned", "reserved" or "available"
		}
	//Setters
	public void setPurchaseDate(String PurchaseDate) {
		this.PurchaseDate = PurchaseDate;
	}
	
	public void setRegistration(String Registration) {
		this.Registration = Registration;
	}
	
	public void setModel(String Model) {
		this.Model = Model;
	}
	
	public void setMake(String Make) {
		this.Make = Make;
	}
	
	public void setYear(int Year) {
		this.Year = Year;
	}
	
	public void setType(String Type) {
		this.Type = Type;
	}
	
	public void setStatus(String Status) {
		this.Status = Status;
	}
	
	//Getters
		public String getPurchaseDate() {
			return PurchaseDate;
		}
		
		public String getRegistration() {
			return Registration;
		}
		
		public String getModel() {
			return Model;
		}
		
		public String getMake() {
			return Make;
		}
		
		public int getYear() {
			return Year;
		}
		
		public String getType() {
			return Type;
		}
		
		public String getStatus() {
			return Status;
		}
		
	//other functions
		
		public void displayInfo() {
			
			System.out.println(getRegistration()+ " " + getMake() + " " + getModel()+ " " + getYear()+ " " + getType()+ " " + getStatus());
		}
};




