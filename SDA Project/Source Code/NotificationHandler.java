package UI;

public class NotificationHandler {
	private String generatedCode;
	private SMSAdapter adapter;
	
	NotificationHandler(){
		
	}
	public String generateCode() {
		
		return "1234";
		
	}
	public void sendConfirmationCode(Reservation res) {
		String randomCode = generateCode();
		
		adapter.sendConfirmationCode(res.getPhoneNumber(), randomCode);
		
		setGeneratedCode(randomCode);
		
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public SMSAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(SMSAdapter adapter) {
		this.adapter = adapter;
	}
}
