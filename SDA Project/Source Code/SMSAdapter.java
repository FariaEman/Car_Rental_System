package UI;

public class SMSAdapter {
	private SMSService service;
	
	public void sendConfirmationCode(String renterContact, String randomCode){
		service.sendSMS(renterContact,randomCode);
	}
}
