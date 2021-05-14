package model;
import java.sql.*;

public class Researchers {
	
			//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
			
				try
				{
						Class.forName("com.mysql.jdbc.Driver");
						//Provide the correct details: DBServer/DBName, username, password
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root", "");
						
						
				}
					
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return con;
			}
			
		
			//-------insert------
			public String insertResearcher(String rName, String rPhone, String rEmail, String rAddress, String projectName, String rCost)
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
					String query = " insert into researcher"
							+ "	(RID,rName,rPhone,rEmail,rAddress,projectName,rCost)"
							+ " values (?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, rName);
					preparedStmt.setString(3, rPhone);
					preparedStmt.setString(4, rEmail);
					preparedStmt.setString(5, rAddress);
					preparedStmt.setString(6, projectName);
					preparedStmt.setDouble(7, Double.parseDouble(rCost));

					// execute the statement
					preparedStmt.execute();
					
					
					String newResearchers = readResearcher();
					output = "{\"status\":\"success\", \"data\": \"" + newResearchers + "\"}";
				}
				catch (Exception e)
				{
					
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the researcher.\"}";
					
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			
			//---------read-------
			public String readResearcher()
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
				 output = "<table border='1'><tr><th>Researcher Name</th>" +
				 			 "<th>Work Phone</th>" +
							 "<th>Email Address</th>" +
							 "<th>Postal Address</th>" +
							 "<th>Project Name</th>" +
							 "<th>Cost</th>" +
							 "<th>Update</th>" +
							 "<th>Remove</th></tr>";
	
				 String query = "select * from researcher";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String RID = Integer.toString(rs.getInt("RID"));
					 String rName = rs.getString("rName");
					 String rPhone = rs.getString("rPhone");
					 String rEmail = rs.getString("rEmail");
					 String rAddress = rs.getString("rAddress");
					 String projectName = rs.getString("projectName");
					 String rCost = Double.toString(rs.getDouble("rCost"));
					
					 // Add into the html table
					 output += "<tr><td><input id='hidRIDUpdate' "
					 		+ "name='hidRIDUpdate'"
					 		+ "type='hidden' value='" + RID + "'>" 
					 		+ rName + "</td>";
					 output += "<td>" + rPhone + "</td>";
					 output += "<td>" + rEmail + "</td>";
					 output += "<td>" + rAddress + "</td>";
					 output += "<td>" + projectName + "</td>";
					 output += "<td>" + rCost + "</td>";
					
					 // buttons
					 output += "<td><input name='btnUpdate' "
					 		+ "type='button' value='Update'"
					 		+ "class='btnUpdate btn btn-secondary'</td>"
					 + "<td><input name='btnRemove' type='button' value='Remove'"
					 + "class='btnRemove btn btn-danger' data-rid='" +RID+ "'> " + "</td></tr>";
				}
					 con.close();
					
					 // Complete the html table
					 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the researcher.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 }


			//------update-----
			public String updateResearcher(String RID, String rName, String rPhone, String rEmail, String rAddress,String projectName,String rCost)
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
					 String query = "UPDATE researcher SET rName=?,rPhone=?,rEmail=?,rAddress=?,projectName=?,rCost=? WHERE RID=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					
					 // binding values
					 preparedStmt.setString(1, rName);
					 preparedStmt.setString(2, rPhone);
					 preparedStmt.setString(3, rEmail);
					 preparedStmt.setString(4, rAddress);
					 preparedStmt.setString(5, projectName);
					 preparedStmt.setDouble(6, Double.parseDouble(rCost));
					 preparedStmt.setInt(7, Integer.parseInt(RID));
					
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newResearchers = readResearcher();
					 output = "{\"status\":\"success\", \"data\": \"" +
							 newResearchers + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while updating the researcher.\"}";
					 System.err.println(e.getMessage());
				 }
				 return output;
			}
			
			//------delete------
			public String deleteResearcher(String RID)
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
					 String query = "delete from researcher where RID=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(RID));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newResearchers = readResearcher();
					 output = "{\"status\":\"success\", \"data\": \"" +
							 newResearchers + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\": \"" +
					             "\"Error while deleting the researcher.\"}";
					 System.err.println(e.getMessage());
				 }
				 return output;
			} 
}
		

