package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FundingBodiesServlet {
	
	private static Connection connect() {

		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund1", "root", "");

			System.out.println("Connection succsessfull");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	
	public static String readFunds() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed

			output = "<div class=''><table class='table table-hover table-bordered table-striped table-bordered' style='width:100%' style='text-align:center'><thead class='thead-dark'>"
					+ "<th style='padding:10px; text-align:center;'>Fund ID</th>"
					+ "<th style='padding:10px; text-align:center;'>fund Organization Name</th>"
					+ "<th style='padding:10px; text-align:center;'>fund Description</th>"
					+ "<th style='padding:10px; text-align:center;'>fund Country</th>"
					+ "<th style='padding:10px; text-align:center;'>fund Amount</th>"
					+ "<th style='padding:10px; text-align:center;'>Update</th><th>Remove</th></thead>";

			String query = "SELECT * FROM fundingbodies";
//			System.out.println(query);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String fundOrganizationName = rs.getString("fundOrganizationName");
				String fundDescrption = rs.getString("fundDescrption");
				String fundCountry = rs.getString("fundCountry");
				String fundAmount = rs.getString("fundAmount");
				
//				System.out.println(projectID);
//				System.out.println(projectName);

				// Add into the html table
				output += "<tbody><td style='padding:10px; text-align:center;'>" + fundID + "</td>";
				output += "<td style='padding:10px; text-align:center;'>" + fundOrganizationName + "</td>";
				output += "<td style='padding:10px; text-align:center;'>" + fundDescrption + "</td>";
				output += "<td style='padding:10px; text-align:center;'>" + fundCountry + "</td>";
				output += "<td style='padding:10px; text-align:center;'>" + fundAmount + "</td>";

				// buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success'></td>"
						+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ fundID +"' >Remove</button></td></tbody>";
				
			}
			con.close();
			// Complete the html table
			output += "</table></div>";
		} catch (Exception e) {
			output = "Error while reading the project details...!";
			System.out.println(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return output;
	}
	
	// insert
		public String insertItem(String fundOrganizationName, String fundDescrption, String fundCountry,
				String fundAmount) {

			String output = "";

			try {

				Connection con = connect();
				if (con == null) {
					return "Error Inserting";
				}

				String query = "INSERT INTO fundingbodies (fundOrganizationName, fundCountry,fundDescrption, fundAmount) VALUES (?, ?, ?, ?)";

				PreparedStatement ps = con.prepareStatement(query);

//						ps.setInt(1, 0);;
				ps.setString(1, fundOrganizationName);
				ps.setString(2, fundCountry);
				ps.setString(3, fundDescrption);
				ps.setString(4, fundAmount);

				ps.execute();
				con.close();

				String newFund = readFunds();
				output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";

				// output = "Project Details have been Inserted Successfully !";

			} catch (Exception e) {
				// output = "Project Details Inserted Failed";

				output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
				System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
			}

			return output;
		}
	
	
		// update
		public String updateItem(String ID, String Name, String fundDescrption, String Country, String Amount)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE fundingbodies SET fundOrganizationName=?,fundDescrption=?,fundCountry=?,fundAmount=? WHERE fundID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, Name);
			 preparedStmt.setString(2, fundDescrption);
			 preparedStmt.setString(3, Country);
			 preparedStmt.setString(4, Amount);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();

				String newFund = readFunds();

				output = "{\"status\":\"success\", \"data\": \"" + newFund +  "\"}";

				 //output = "Project details have been updated successfully...!";

			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\": \"Error while updating the order.\"}";

				// output = "Error while updating project details...!";
				System.err.println(e.getMessage());
				System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
			}

			return output;

		}

		// delete
		public String deleteFund(String fundID) {

			String output = "";

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			try {

				String query = "DELETE FROM fundingbodies WHERE fundID=?";
				PreparedStatement ps = con.prepareStatement(query);

				//System.out.println(query);
				//System.out.println(projectID);

				ps.setInt(1, Integer.parseInt(fundID));

				ps.execute();
				con.close();

				String newFund = readFunds();

				output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";

				// output = "Project has been deleted successfully";

			} catch (SQLException e) {
				
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}";

				//output = "Error while deleting the project from the database.";
				System.err.println(e.getMessage());
				System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();

			}

			return output;

		}
		
}



