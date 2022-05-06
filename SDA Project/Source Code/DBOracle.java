package UI;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBOracle extends PersistenceHandler{

	public static Connection Connect() {
		String DB_URL = "jdbc:mysql://localhost/sda";
		String USER = "root";
		String PASS = "1234";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return conn;
		/*
		 * 
		 * Statement stmt = conn.createStatement(); String sql =
		 * "CREATE TABLE IF NOT EXISTS test " + "(id INTEGER not NULL AUTO_INCREMENT, "
		 * + " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER, " +
		 * " PRIMARY KEY ( id ))"; stmt.executeUpdate(sql);
		 * 
		 * //insertion
		 * PreparedStatement preparedStatement=conn.
		 * prepareStatement("insert into test (id,first, last,age) values(0,?,?,?)");
		 * preparedStatement.setString(1,"moeeda");
		 * preparedStatement.setString(2,"kashif"); preparedStatement.setNString(3,
		 * "29"); preparedStatement.executeUpdate();
		 * 
		 * 
		 * //extraction PreparedStatement
		 * preparedStatement2=conn.prepareStatement("select * from test"); //Creating
		 * Java ResultSet object ResultSet resultSet=preparedStatement2.executeQuery();
		 * while(resultSet.next()){ String rollNo=resultSet.getString("id"); String
		 * name=resultSet.getString("first"); String dept=resultSet.getString("last");
		 * String age=resultSet.getString("age"); //Printing Results
		 * System.out.println(rollNo+" "+name+" "+dept+" "+age); }
		 * 
		 * 
		 * 
		 * //updation PreparedStatement
		 * preparedStatement3=conn.prepareStatement("update test set age=? where id=?");
		 * preparedStatement3.setString(1,"30"); preparedStatement3.setString(2,"2");
		 * 
		 * preparedStatement3.executeUpdate();
		 */
       
	
	}
	
	@Override
	public void SaveCar(Car car){
		Connection conn = Connect();
		
		  Statement stmt;
		try {
			stmt = conn.createStatement();
			  String sql = "CREATE TABLE IF NOT EXISTS car " + 
						"(registration VARCHAR(20), "+
					   	"model VARCHAR(255), " + 
						"make VARCHAR(255), " + 
						"year INTEGER, " +
						"type VARCHAR(20), " +
						"purchaseDate VARCHAR(50), " +
						"status VARCHAR(20), " +
						"PRIMARY KEY ( registration ))"; 
						
			  stmt.executeUpdate(sql); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		String reg = car.getRegistration();
		String model = car.getModel();
		String make = car.getMake();
		int year = car.getYear();
		String type = car.getType();
		String date = car.getPurchaseDate();
		String status = car.getStatus();
		
		
		
		   PreparedStatement preparedStatement;
		try {
			 preparedStatement = conn.prepareStatement("insert into car values(?,?,?,?,?,?,?)");
			 preparedStatement.setString(1,reg);
			 preparedStatement.setString(2,model);
			 preparedStatement.setString(3,make);
			 preparedStatement.setInt(4,year);
			 preparedStatement.setString(5,type);
			 preparedStatement.setString(6,date);
			 preparedStatement.setNString(7, status);
			 preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Car getCar(String registration) {
		Car car = new Car();
		
		Connection conn = Connect();
		
		  PreparedStatement preparedStatement2;
		  try {
			  preparedStatement2=conn.prepareStatement("select * from car where registration='"+registration+"'");
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  car.setPurchaseDate(resultSet.getString("purchaseDate"));
				  car.setRegistration(resultSet.getString("registration"));
				  car.setModel(resultSet.getString("model"));
				  car.setMake(resultSet.getString("make"));
				  car.setYear(resultSet.getInt("year"));
				  car.setType(resultSet.getString("type"));
				  car.setStatus(resultSet.getString("status"));
				  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return car;	
	}
	
	
	
	public ArrayList<Car> getAllCars(String filterOption){
		ArrayList<Car> cars = new ArrayList<Car>();

		Connection conn = Connect();
		PreparedStatement preparedStatement2=null;
		
			 try {
					if (filterOption.equals("all")) {
						 preparedStatement2=conn.prepareStatement("select * from car");
					}
					else if (filterOption.equals("reserved")) {
						 preparedStatement2=conn.prepareStatement("select * from car where status='reserved'");
					}
					else if (filterOption.equals("available")) {
						 preparedStatement2=conn.prepareStatement("select * from car where status='available'");
					}
					else if (filterOption.equals("unassigned")) {
						 preparedStatement2=conn.prepareStatement("select * from car where status='unassigned'");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  try {
			 
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  Car car = new Car();
				  car.setPurchaseDate(resultSet.getString("purchaseDate"));
				  car.setRegistration(resultSet.getString("registration"));
				  car.setModel(resultSet.getString("model"));
				  car.setMake(resultSet.getString("make"));
				  car.setYear(resultSet.getInt("year"));
				  car.setType(resultSet.getString("type"));
				  car.setStatus(resultSet.getString("status"));
				  cars.add(car);
				  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return cars;
		
	};
	
	
	
	public void saveCarChanges(Car car) {
		Connection conn = Connect();
		
		String reg = car.getRegistration();
		String model = car.getModel();
		String make = car.getMake();
		int year = car.getYear();
		String type = car.getType();
		String date = car.getPurchaseDate();
		String status = car.getStatus();
		
		
		
		PreparedStatement preparedStatement3;
		try {
			preparedStatement3=conn.prepareStatement("UPDATE car SET model=?, make=?, year=?, type=?, purchaseDate=?, status=? where registration = ?");
			preparedStatement3.setString(1,model);
			preparedStatement3.setString(2,make);
			preparedStatement3.setInt(3,year);
			preparedStatement3.setString(4,type);
			preparedStatement3.setString(5,date);
			preparedStatement3.setString(6,status);
			preparedStatement3.setNString(7,reg); 
			preparedStatement3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void SaveDriver(Driver driver) {
		Connection conn = Connect();
		
		  Statement stmt;
		try {
			stmt = conn.createStatement();
			  String sql = "CREATE TABLE IF NOT EXISTS driver" + 
						"(license VARCHAR(50), "+
					   	"name VARCHAR(255), " + 
						"contact VARCHAR(255), " + 
						"address VARCHAR(255), " +
						"CNIC VARCHAR(20), " +
						"gender VARCHAR(50), " +
						"status VARCHAR(50), " +
						"PRIMARY KEY ( license ))"; 

						
			  stmt.executeUpdate(sql); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		String name = driver.getName();
		String license = driver.getLicense();
		int contact = driver.getContact();
		String address = driver.getAddress();
		int CNIC = driver.getCNIC();
		String gender = driver.getGender();
		String status = driver.getStatus();
		
		
		
		   PreparedStatement preparedStatement;
		try {
			 preparedStatement = conn.prepareStatement("insert into driver values(?,?,?,?,?,?,?)");
			 preparedStatement.setString(1,license);
			 preparedStatement.setString(2,name);
			 preparedStatement.setInt(3, contact);
			 preparedStatement.setString(4,address);
			 preparedStatement.setInt(5,CNIC);
			 preparedStatement.setString(6,gender);
			 preparedStatement.setNString(7,status);
			 preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public Driver getDriver(String license) {
		Driver driver= new Driver();
		
		Connection conn = Connect();
		
		  PreparedStatement preparedStatement2;
		  try {
			  preparedStatement2=conn.prepareStatement("select * from driver where license='"+license+"'");
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  driver.setName(resultSet.getString("name"));
				  driver.setLicense(resultSet.getString("license"));
				  driver.setContact(resultSet.getInt("contact"));
				  driver.setAddress(resultSet.getString("address"));
				  driver.setCNIC(resultSet.getInt("CNIC"));
				  driver.setGender(resultSet.getString("gender"));
				  driver.setStatus(resultSet.getString("status"));
				  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return driver;			
		
	}
	
	
	
	public ArrayList<Driver> getAllDrivers(String filterOption){
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		Connection conn = Connect();
		PreparedStatement preparedStatement2=null;
		
			 try {
					if (filterOption.equals("all")) {
						 preparedStatement2=conn.prepareStatement("select * from driver");
					}
					else if (filterOption.equals("assigned")) {
						 preparedStatement2=conn.prepareStatement("select * from driver where status='assigned'");
					}
					else if (filterOption.equals("unassigned")) {
						 preparedStatement2=conn.prepareStatement("select * from driver where status='unassigned'");
					}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  try {
			 
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  Driver driver = new Driver();
				  driver.setName(resultSet.getString("name"));
				  driver.setLicense(resultSet.getString("license"));
				  driver.setContact(resultSet.getInt("contact"));
				  driver.setAddress(resultSet.getString("address"));
				  driver.setCNIC(resultSet.getInt("CNIC"));
				  driver.setGender(resultSet.getString("gender"));
				  driver.setStatus(resultSet.getString("status"));
				  drivers.add(driver);
				  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return drivers;
		
	}
	
	public void saveDriverChanges(Driver driver) {
		Connection conn = Connect();
		
		String name = driver.getName();
		String license = driver.getLicense();
		int contact = driver.getContact();
		String address = driver.getAddress();
		int CNIC = driver.getCNIC();
		String gender = driver.getGender();
		String status = driver.getStatus();
		
		
		PreparedStatement preparedStatement3;
		try {
			preparedStatement3=conn.prepareStatement("UPDATE driver SET name=?, contact=?, address=?, CNIC=?, gender=?, status=? where license = ?");
			preparedStatement3.setString(1,name);
			preparedStatement3.setInt(2,contact);
			preparedStatement3.setString(3,address);
			preparedStatement3.setInt(4,CNIC);
			preparedStatement3.setString(5,gender);
			preparedStatement3.setString(6,status);
			preparedStatement3.setNString(7,license); 
			preparedStatement3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void saveAssignment(Assignment assignment) {
		Connection conn = Connect();
		
		  Statement stmt;
		try {
			stmt = conn.createStatement();
			  String sql = "CREATE TABLE IF NOT EXISTS assignment" + 
						"(id INTEGER NOT NULL AUTO_INCREMENT, "+
					   	"carReg VARCHAR(255), " + 
						"driverLic VARCHAR(255), " + 
						"startDate DATE, " +
						"endDate DATE, " +
						"status VARCHAR(50), " +
						"PRIMARY KEY ( id ))"; 
			  
				
			  stmt.executeUpdate(sql); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		String reg = assignment.getCarReg();
		String license = assignment.getDriverLic();
		Date startDate = (Date) assignment.getStartDate();
		Date endDate = (Date) assignment.getEndDate();
		String status = assignment.getStatus();
		
		
		   PreparedStatement preparedStatement;
		try {
			 preparedStatement = conn.prepareStatement("insert into assignment values(0,?,?,?,?,?)");
			 preparedStatement.setString(1,reg);
			 preparedStatement.setString(2,license);
			 preparedStatement.setObject(3, startDate);
			 preparedStatement.setObject(4,endDate);
			 preparedStatement.setNString(5,status);
			 preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public Assignment getAssignment(String license) {
		Assignment assignment = new Assignment();
		
		
		Connection conn = Connect();
		
		  PreparedStatement preparedStatement2;
		  try {
			  preparedStatement2=conn.prepareStatement("select * from assignment where driverLic='"+license+"' AND status='active'");
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  assignment.setDriverLic(resultSet.getString("driverLic"));
				  assignment.setCarReg(resultSet.getString("carReg"));
				  assignment.setStatus(resultSet.getString("status"));
				  assignment.setStartDate((Date) resultSet.getObject("startDate"));
				  assignment.setEndDate((Date) resultSet.getObject("endDate"));  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return assignment;
	}
	
	
	
	public ArrayList<Assignment> viewAssignmentHistory(String filterOption){
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		Connection conn = Connect();
		PreparedStatement preparedStatement2=null;
		
			 try {
					if (filterOption.equals("all")) {
						 preparedStatement2=conn.prepareStatement("select * from assignment");
					}
					else if (filterOption.equals("active")) {
						 preparedStatement2=conn.prepareStatement("select * from assignment where status='assigned'");
					}
					else if (filterOption.equals("terminated")) {
						 preparedStatement2=conn.prepareStatement("select * from assignment where status='terminated'");
					}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  try {
			 
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  Assignment assignment = new Assignment();
				  assignment.setDriverLic(resultSet.getString("driverLic"));
				  assignment.setCarReg(resultSet.getString("carReg"));
				  assignment.setStatus(resultSet.getString("status"));
				  assignment.setStartDate((Date) resultSet.getObject("startDate"));
				  assignment.setEndDate((Date) resultSet.getObject("endDate"));  
				  assignments.add(assignment);
				  
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return assignments;
	}
	
	
	public void saveAssignmentChanges(Assignment assignment) {
		Connection conn = Connect();
		
		String reg = assignment.getCarReg();
		String license = assignment.getDriverLic();
		Date startDate = (Date) assignment.getStartDate();
		Date endDate = (Date) assignment.getEndDate();
		String status = assignment.getStatus();
		
		
		PreparedStatement preparedStatement3;
		try {
			preparedStatement3=conn.prepareStatement("UPDATE assignment SET carReg=?, status=?, startDate=?, endDate=?, where driverLic = ?");
			preparedStatement3.setString(1,reg);
			preparedStatement3.setString(2,status);
			preparedStatement3.setObject(3,startDate);
			preparedStatement3.setObject(4,endDate);
			preparedStatement3.setString(5,license);
			preparedStatement3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void saveReservation(Reservation res) {
		Connection conn = Connect();
		
		  Statement stmt;
		try {
			stmt = conn.createStatement();
			  String sql = "CREATE TABLE IF NOT EXISTS reservation" + 
						"(reservationID VARCHAR(100)NOT NULL, "+
					   	"startDate DATE, " + 
						"endDate DATE, " + 
						"carReg VARCHAR(50), " +
						"phoneNumber VARCHAR(50), " +
						"fee VARCHAR(10), " +
						"status VARCHAR(50), " +
						"PRIMARY KEY ( reservationID ))"; 
			  
				
			  stmt.executeUpdate(sql); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		String id = res.getReservationID();
		Date sDate = res.getStartDate();
		Date eDate = res.getEndDate();
		String reg = res.getCarReg();
		String num = res.getPhoneNumber();
		int fee = res.getFee();
		String status = res.getStatus();
		
		   PreparedStatement preparedStatement;
		try {
			 preparedStatement = conn.prepareStatement("insert into reservation values(?,?,?,?,?,?,?)");
			 preparedStatement.setString(1,id);
			 preparedStatement.setObject(2,sDate);
			 preparedStatement.setObject(3, eDate);
			 preparedStatement.setString(4,reg);
			 preparedStatement.setString(5,num);
			 preparedStatement.setInt(6,fee);
			 preparedStatement.setNString(7,status);
			 preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	public Reservation getReservation(String id) {
		
		Reservation res = new Reservation();
		
		
		Connection conn = Connect();
		
		  PreparedStatement preparedStatement2;
		  try {
			  preparedStatement2=conn.prepareStatement("select * from reservation where reservationID='"+id+"'");
			  ResultSet resultSet=preparedStatement2.executeQuery();
			  while(resultSet.next()){
				  res.setReservationID(resultSet.getString("reservationID"));
				  res.setCarReg(resultSet.getString("carReg"));
				  res.setStatus(resultSet.getString("status"));
				  res.setStartDate((Date) resultSet.getObject("startDate"));
				  res.setEndDate((Date) resultSet.getObject("endDate"));  
				  res.setFee(resultSet.getInt("fee"));
				  res.setPhoneNumber(resultSet.getString("phoneNumber"));
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return res;
		
	}
	
	public void saveReservationChanges(Reservation res) {
		Connection conn = Connect();
		
		String id = res.getReservationID();
		Date sDate = res.getStartDate();
		Date eDate = res.getEndDate();
		String reg = res.getCarReg();
		String num = res.getPhoneNumber();
		int fee = res.getFee();
		String status = res.getStatus();
		
		
		PreparedStatement preparedStatement3;
		try {
			preparedStatement3=conn.prepareStatement("UPDATE reservation SET carReg=?, status=?, startDate=?, endDate=?, where reservationID = ?");
			preparedStatement3.setString(1,reg);
			preparedStatement3.setString(2,status);
			preparedStatement3.setObject(3,sDate);
			preparedStatement3.setObject(4,eDate);
			preparedStatement3.setString(5,id);
			preparedStatement3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	 public ArrayList<Reservation> getAllReservations(String filterOption) {
		 ArrayList<Reservation> reservations = new ArrayList<Reservation>();
			Connection conn = Connect();
			PreparedStatement preparedStatement2=null;
			
				 try {
						if (filterOption.equals("all")) {
							 preparedStatement2=conn.prepareStatement("select * from reservation");
						}
						else if (filterOption.equals("claimed")) {
							 preparedStatement2=conn.prepareStatement("select * from reservation where status='claimed'");
						}
						else if (filterOption.equals("unclaimed")) {
							 preparedStatement2=conn.prepareStatement("select * from reservation where status='unclaimed'");
						}
						else if (filterOption.equals("cancelled")) {
							 preparedStatement2=conn.prepareStatement("select * from reservation where status='cancelled'");
						}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  try {
				 
				  ResultSet resultSet=preparedStatement2.executeQuery();
				  while(resultSet.next()){
					  Reservation res = new Reservation();
					  res.setReservationID(resultSet.getString("reservationID"));
					  res.setCarReg(resultSet.getString("carReg"));
					  res.setStatus(resultSet.getString("status"));
					  res.setStartDate((Date) resultSet.getObject("startDate"));
					  res.setEndDate((Date) resultSet.getObject("endDate"));  
					  res.setFee(resultSet.getInt("fee"));
					  res.setPhoneNumber(resultSet.getString("phoneNumber"));
					  reservations.add(res);
					  
					 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
			return reservations;
	 }
	
	 
	  public void saveRental(Rental res) {
		  Connection conn = Connect();
			
		  Statement stmt;
		try {
			stmt = conn.createStatement();
			  String sql = "CREATE TABLE IF NOT EXISTS rental" + 
						"(rentalID INTEGER NOT NULL AUTO_INCREMENT, "+
					   	"startDate DATE, " + 
						"endDate DATE, " + 
						"carReg VARCHAR(50), " +
						"phoneNumber VARCHAR(50), " +
						"fee VARCHAR(10), " +
						"status VARCHAR(50), " +
						"PRIMARY KEY ( rentalID ))"; 
			  
				
			  stmt.executeUpdate(sql); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		Date sDate = res.getStartDate();
		Date eDate = res.getEndDate();
		String reg = res.getCarReg();
		String num = res.getPhoneNumber();
		int fee = res.getFee();
		String status = res.getStatus();
		
		   PreparedStatement preparedStatement;
		try {
			 preparedStatement = conn.prepareStatement("insert into rental values(0,?,?,?,?,?,?)");
			 preparedStatement.setObject(1,sDate);
			 preparedStatement.setObject(2, eDate);
			 preparedStatement.setString(3,reg);
			 preparedStatement.setString(4,num);
			 preparedStatement.setInt(5,fee);
			 preparedStatement.setNString(6,status);
			 preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		  
		  
	  }
	  
	  
	  
	  
	  public Rental getRental(String registration) {
		  
			Rental res = new Rental();
			
			
			Connection conn = Connect();
			
			  PreparedStatement preparedStatement2;
			  try {
				  preparedStatement2=conn.prepareStatement("select * from rental where carReg='"+registration+"' and status='active'");
				  ResultSet resultSet=preparedStatement2.executeQuery();
				  while(resultSet.next()){
					  res.setCarReg(resultSet.getString("carReg"));
					  res.setStatus(resultSet.getString("status"));
					  res.setStartDate((Date) resultSet.getObject("startDate"));
					  res.setEndDate((Date) resultSet.getObject("endDate"));  
					  res.setFee(resultSet.getInt("fee"));
					  res.setPhoneNumber(resultSet.getString("phoneNumber"));
					 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			return res; 
		  
		  
	  }
	  
	  
	  public ArrayList<Rental> getAllRentals(String filterOption){
		  ArrayList<Rental> rentals = new ArrayList<Rental>();
			Connection conn = Connect();
			PreparedStatement preparedStatement2=null;
			
				 try {
						if (filterOption.equals("all")) {
							 preparedStatement2=conn.prepareStatement("select * from rental");
						}
						else if (filterOption.equals("active")) {
							 preparedStatement2=conn.prepareStatement("select * from rental where status='active'");
						}
						else if (filterOption.equals("closed")) {
							 preparedStatement2=conn.prepareStatement("select * from rental where status='closed'");
						}
						

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  try {
				 
				  ResultSet resultSet=preparedStatement2.executeQuery();
				  while(resultSet.next()){
					  Rental res = new Rental();
					  res.setCarReg(resultSet.getString("carReg"));
					  res.setStatus(resultSet.getString("status"));
					  res.setStartDate((Date) resultSet.getObject("startDate"));
					  res.setEndDate((Date) resultSet.getObject("endDate"));  
					  res.setFee(resultSet.getInt("fee"));
					  res.setPhoneNumber(resultSet.getString("phoneNumber"));
					  rentals.add(res);
					  
					 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
			return rentals;
	  }
	 
	  
	  public void saveRentalChanges(Rental res) {
		  Connection conn = Connect();
			
			Date sDate = res.getStartDate();
			Date eDate = res.getEndDate();
			String reg = res.getCarReg();
			String num = res.getPhoneNumber();
			int fee = res.getFee();
			String status = res.getStatus();
			
			
			PreparedStatement preparedStatement3;
			try {
				preparedStatement3=conn.prepareStatement("UPDATE rental SET status=? where carReg =? AND status='active'");
				;
				preparedStatement3.setString(1,status);
				preparedStatement3.setNString(2,reg);
				preparedStatement3.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  
	  
	 
	}



			

