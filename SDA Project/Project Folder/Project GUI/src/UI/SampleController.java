package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleController {
	//
	CarRentalStore store;
	@FXML 
	private Button EnterIntoSystem;
	
	@FXML 
	private Button Close;
	
	@FXML 
	private Button Admin;
	
	@FXML 
	private Button AddCars;
	
	@FXML 
	private Button AddDrivers;
	
	@FXML 
	private Button ReserveCar;
	
	@FXML 
	private Button RentACar;
	
	@FXML
	private Button ReturnCar;
	
	@FXML
	private Button BackAdminPage;
	
	@FXML
	private Button BackCarPage;
	
	@FXML
	private Button BackDriverPage;
	
	@FXML
	private Button BackReservePage;
	
	@FXML 
	private Button BackRentPage;
	
	@FXML 
	private Button BackReturnCarPage;
	
	@FXML 
	private Button BackPaymentPage;
	
	//Car Info
	@FXML 
	private Button Submit;
	
	@FXML 
	private TextField PurchaseDatetext;
	
	@FXML
	private TextField Registrationtext;
	
	@FXML
	private TextField Maketext;
	
	@FXML
	private TextField Modeltext;
	
	@FXML
	private TextField Yeartext;
	
	@FXML
	private TextField Typetext;
	
	
	//Driver Car
	@FXML 
	private Button Submit1;
	
	@FXML
	private TextField Nametext;
	
	@FXML
	private TextField Licensetext;
	
	@FXML
	private TextField Contacttext;
	
	@FXML
	private TextField Addresstext;
	
	@FXML
	private TextField CNICtext;
	
	@FXML
	private TextField Gendertext;
	
	//RentaCar
	@FXML
	private Button Submit2;
	@FXML
	private TextField Registertext;
	
	@FXML
	private TextField contacttext;
	
	@FXML
	private TextField RserveDate1text;
	
	@FXML
	private TextField RserveDate2text;
	
	//ReserveCar
	@FXML
	private Button Submit3;
	@FXML
	private TextField Registtext;
	
	@FXML
	private TextField Context;
	
	@FXML
	private TextField ReservationDate1text;

	@FXML
	private TextField ReservationDate2text;
	
	//MakePayment
	@FXML
	private Button MakePayment;
	
	@FXML
	private Button PrintRecipt;
	
	@FXML
	private Button ShowAllCars;
	
	
	@FXML
	private TextField display2;
	
	@FXML
	private TextArea display;
	
	
	//UI Connected to Business Logic
		public void PrintCars(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			
			String PurchaseDate = PurchaseDatetext.getText();
			String Registration = Registrationtext.getText();
			String Model = Modeltext.getText();
			String Make = Maketext.getText();
			String Y = Yeartext.getText();
			String Type = Typetext.getText();
			int Year=Integer.parseInt(Y); 
			store.addCar(PurchaseDate, Registration , Model , Make, Year,Type);
		}
		
		public void PrintDrivers(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			String Name = Nametext.getText();
			String License = Licensetext.getText();
			String Con = Contacttext.getText();
			String Address = Addresstext.getText();
			String ID = CNICtext.getText();
			String Gender = Gendertext.getText();
			int CNIC = Integer.parseInt(ID);
			int Contact = Integer.parseInt(Con);
			store.addDriver(Name, License , Contact , Address , CNIC , Gender);
		}
		
		public void PrintRentACar(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			String register = Registertext.getText();
			String contact = contacttext.getText();
			String Date1 = RserveDate1text.getText();
			String Date2 = RserveDate2text.getText();		
		     //store.createNewRental(register , contact , Date1 , Date2);
		}
		
		public void PrintReserveCar(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			String register1 = Registtext.getText();
			String contact1 = Context.getText();
			String date1 = ReservationDate1text.getText();
			String date2 = ReservationDate2text.getText();		
		//	store.createReservation(register1, contact1 , date1 , date2);
		}
	
		
		
		
	public void ButtonOperations(String FxmlFileName , Button but) throws IOException {
		Stage window=new Stage();
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(FxmlFileName));	
		Scene scene = new Scene(root,850,710);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window = (Stage) but.getScene().getWindow(); 
		window.setScene(scene);
		window.show();
	}	
     	public void ShowAllCarsFun() throws IOException {
     		ButtonOperations("showallcars.fxml",ShowAllCars);
     		Car car1 = new Car("11/12/2021","ABUU1234","Corolla","Toyota",2012,"Seden") ;
    		Car car2 = new Car("8/6/2020","AMMI1234","Civic","Honda",2006,"Seden") ;
    		Car car3 = new Car("8/7/2019","AEEA1234","Parado","Toyota",2020,"Seden") ;
    		Car car4 = new Car("1/1/2015","HUBI1234","Maruti","Suzuki",2021,"Seden") ;
    		display2.setText("WHY the fuck not printing meri jaan");
    		System.out.print("Please work");
    		
		}
		
	public void EnterIntoSystemFun() throws IOException {
		ButtonOperations("Menu.fxml" , EnterIntoSystem);
	}

	public void closeFun() throws IOException {
		    Stage window =new Stage();
			window = (Stage) Close.getScene().getWindow(); 
			window.close(); 
		}
	
	
	public void AdminFun() throws IOException {
		ButtonOperations("admin.fxml" , Admin);		
	}
	
	public void AddCarsFun() throws IOException {
		ButtonOperations("addcar.fxml" , AddCars);
	}

	public void AddDriverFun() throws IOException {
		ButtonOperations("adddriver.fxml" , AddDrivers);
	}
	
	public void ReserveCarFun() throws IOException {
		ButtonOperations("reservecar.fxml" , ReserveCar);
	}
	
	public void RentACarFun() throws IOException {
		ButtonOperations("rentacar.fxml" , RentACar);
	}
	
	public void ReturnCarFun() throws IOException {
		ButtonOperations("returncar.fxml" , ReturnCar);
	}
	
	public void MakePaymentFun() throws IOException {
		ButtonOperations("makepayment.fxml" , MakePayment);
	}
	
//Back button functions
	public void BackFun(String FxmlFileName , Button but) throws IOException {
		Stage window=new Stage();
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(FxmlFileName));	
		Scene scene = new Scene(root,880,710);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window = (Stage) but.getScene().getWindow(); 
		window.setScene(scene);
		window.show();
	}
	
	public void BackAdminFun() throws IOException {
		BackFun("Menu.fxml" , BackAdminPage);
	}
	
	public void BackAddCarFun() throws IOException {
		BackFun("Admin.fxml" , BackCarPage);
	}
	
	public void BackDriverFun() throws IOException {
		BackFun("Admin.fxml" , BackDriverPage);
	}
	
	public void BackReserveFun() throws IOException {
		BackFun("Menu.fxml" , BackReservePage);
	}
	
	public void BackRentFun() throws IOException {
		BackFun("Menu.fxml" , BackRentPage);
	}
	
	public void BackReturnCarFun() throws IOException {
		BackFun("Menu.fxml" , BackReturnCarPage);
	}
	
	public void BackPaymentFun() throws IOException {
		BackFun("Rentacar.fxml" , BackPaymentPage);
	}
	
	
}

