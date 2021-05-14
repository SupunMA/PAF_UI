package model;
import java.sql.*; 
public class Users 
{ 
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/UserData?useTimezone=true&serverTimezone=UTC", "root", ""); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	return con; 
	}
	
	
	public String readUsers() 
	{ 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			} 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th> UserID </th><th> Name </th><th> Email </th><th> Password </th><th> ContactNumber </th><th> role </th><th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from UserDetails"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String UserID = rs.getString("uid");
				String Name = rs.getString("UName");
				String Email = rs.getString("UEmail");
				String Password = rs.getString("UPassword");
				String ContactNumber = rs.getString("UContactNumber");
				String Role = rs.getString("URole");
	
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + UserID+ "'>" + UserID + "</td>"; 
				output += "<td>" + Name + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + ContactNumber + "</td>";
				output += "<td>" + Role + "</td>";
	 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-userid='" + UserID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + UserID + "'></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String RegisterUser(String UName, String UEmail, String UPassword, String UContactNumber, String URole) 
	 { 
		String output = ""; 
		
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for inserting."; 
		 } 
		 
		 // create a prepared statement
		 String query = " insert into UserDetails(`uid`, `uname`, `uemail`, `upassword`, `ucontactnumber`, `urole`) values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
		 // binding values
		 preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, UName);
			preparedStmt.setString(3, UEmail);
			preparedStmt.setString(4, UPassword);
			preparedStmt.setInt(5, Integer.parseInt(UContactNumber));
			preparedStmt.setInt(6, Integer.parseInt(URole)); 
			 
		// execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newUsers = readUsers(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the User Details.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	 return output; 
		 }
	
	
	
	public String updateUser(String uID, String name, String email, String password, String contact, String urole) 
		 { 
		 	String output = ""; 
		 	try
		 { 
		 Connection con = connect(); 
		 	if (con == null) 
		 { 
		 		return "Error while connecting to the database for updating."; 
		 } 
		 	// create a prepared statement
		 	String query = "UPDATE UserDetails SET uname=?,uemail=?,upassword=?,ucontactnumber=?,urole=? WHERE uid=?"; 
		 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 	// binding value
		 	preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, contact);
			preparedStmt.setInt(5, Integer.parseInt(urole));
			preparedStmt.setInt(6, Integer.parseInt(uID));
		 	// execute the statement
		 	preparedStmt.execute(); 
		 	con.close(); 
		 	String newUsers = readUsers(); 
		 	output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 


		public String deleteUser(String uID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from UserDetails where uid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(uID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newUsers = readUsers(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}