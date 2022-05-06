package UI;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.lang.String;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Date startDate;
	private Date endDate;
	private String carReg;
	private String status;
	private String phoneNumber;
	private int fee;
	private String reservationID;
	
	
	Reservation(String carReg, String phoneNumber2, Date startDate, Date endDate){
		this.carReg=carReg;
		this.phoneNumber = phoneNumber2;
		this.startDate=startDate;
		this.endDate = endDate;
		this.fee = 0;
		this.reservationID = generateID();
		this.status = "unclaimed";
	}
	
	
	public Reservation() {
		// TODO Auto-generated constructor stub
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
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public void setReservationID(String id) {
		this.reservationID = id;
	}
	
	public String getReservationID() {
		return reservationID;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String l) {
		this.phoneNumber = l;
	}
	
	//other functions
	
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
	
	public String generateID() {
		String id = "";
		
		String str1 = getCarReg();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str2 = formatter.format(getStartDate());
		
		id = str1+str2;
		
		return id;
	}
	
	public String generateSlip() {
		String slip = "";
		
		slip = "Reservation ID: "+getReservationID()+"\n"+ "Reserved Car: "+getCarReg()+"\n"+"Start Date: "+getStartDate()+"\n"+"End Date: "+getEndDate()+"\n"+"Renter Contact: "+getPhoneNumber()+"\n"+"Total fee: "+getFee();
		
		return slip;
	}






}
