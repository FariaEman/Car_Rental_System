package UI;
import java.util.Date;

public class Assignment {
	private String carReg;
	private String driverLic;
	private Date startDate;
	private Date endDate;
	private String status;
	
	public String getCarReg() {
		return carReg;
	}
	public void setCarReg(String carReg) {
		this.carReg = carReg;
	}
	public String getDriverLic() {
		return driverLic;
	}
	public void setDriverLic(String driverLic) {
		this.driverLic = driverLic;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//other functions
	
	public void finalizeAssignment(Date startDate, String license, String reg) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		
		this.setCarReg(reg);
		this.setStartDate(startDate);
		this.setDriverLic(license);
		this.setStatus("active");
		
		dbservice.saveAssignment(this);
		
	}
	
	public void terminateAssignment() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		PersistenceFactory pf = PersistenceFactory.getInstance();
		DBOracle dbservice = (DBOracle) pf.createPersistenceService("DBOracle");
		
		this.setStatus("terminated");
		Date date = new Date();
		this.setEndDate(date);
		
		
		dbservice.saveAssignment(this);
	}
	
	public void displayInfo() {
		System.out.println(getCarReg() + " "+getDriverLic()+ " "+ getStartDate() +" "+ getEndDate()+" "+getStatus());
	}
	
	
	
}
