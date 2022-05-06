package UI;

public class Driver {
	//attributes 
	private String name;
	private String  license;
	private int contact;
	private String address;
	private int CNIC;
	private String gender;
	private String status;
	
//Default constructor
	public Driver() {
	 name = " ";
	 license = " ";
	 contact = 0;
	 address = " ";
	 CNIC = 0;
	 gender = " ";
	 status = "";
	}
//Argumented constructor
	public Driver(String name , String licence , int contact , String address, int CNIC, String gender) {
		 this.name = name;
		 this.license = licence;
		 this.contact = contact;
		 this.address = address;
		 this.CNIC = CNIC;
		 this.gender = gender;
		 this.status = "unassigned";
		}
	
//setters
	
	public void setStatus(String status) {
		 this.status = status;	
	}
	
	
	public void setName(String name) {
		 this.name = name;	
	}
	
	public void setLicense(String license) {
		 this.license = license;		
	}
	
	public void setContact(int contact) {
		 this.contact = contact;		
	}
	
	public void setAddress(String address) {
		 this.address = address;		
	}
	
	public void setCNIC(int CNIC) {
		 this.CNIC = CNIC;		
	}
	
	public void setGender(String gender) {
		 this.gender = gender;		
	}
	
//getters
	
	public String getStatus() {
		 return status;	
	}
	
	public String getName() {
		 return name;	
	}
	
	public String getLicense() {
		return license;		
	}
	
	public int getContact() {
		 return contact ;		
	}
	
	public String getAddress() {
		return address;		
	}
	
	public int getCNIC() {
		 return CNIC;		
	}
	
	public String getGender() {
		return gender;		
	}
	
	//other functions
	public void displayInfo() {
		
		System.out.println(getName()+ " " + getLicense() + " " + getAddress()+ " " + getCNIC()+ " " + getGender()+ " " + getStatus());
	}
}
