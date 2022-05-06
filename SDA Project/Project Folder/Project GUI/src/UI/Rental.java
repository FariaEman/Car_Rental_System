package UI;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Rental {
	private Date startDate;
	private Date endDate;
	private String carReg;
	private String status;
	private String phoneNumber;
	private int fee;
	
	
	
	Rental(){
		
	}
	
	Rental(String carReg, String phoneNumber2, Date startDate, Date endDate) throws ParseException{
		this.carReg=carReg;
		this.phoneNumber = phoneNumber2;
		this.startDate=startDate;
		this.endDate = endDate;
		this.status = "active";
		calculateFee();
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
	public String getCarReg() {
		return carReg;
	}
	public void setCarReg(String carReg) {
		this.carReg = carReg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	
	public void copyInfo(Reservation res) {
		this.carReg=res.getCarReg();
		this.phoneNumber = res.getPhoneNumber();
		this.startDate=res.getStartDate();
		this.endDate = res.getEndDate();
		this.fee = res.getFee();
		this.status = "active";
	}
	
	public void calculateFee() throws ParseException {
		int costPerDay = 1000;
		
		
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String d1 = formatter.format(getStartDate());
		String d2 = formatter.format(getEndDate());
		
		
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(d1);
        Date secondDate = sdf.parse(d2);

        long diff = secondDate.getTime() - firstDate.getTime();

        TimeUnit time = TimeUnit.DAYS; 
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
				
        
		setFee(costPerDay * (int)difference);
	}
	
	public void displayInfo() {
	
		
		String slip = "Reserved Car: "+getCarReg()+"\n"+"Start Date: "+getStartDate()+"\n"+"End Date: "+getEndDate()+"\n"+"Renter Contact: "+getPhoneNumber()+"\n"+"Total fee: "+getFee();
		
		System.out.println(slip);
	}
	
	
	
}
