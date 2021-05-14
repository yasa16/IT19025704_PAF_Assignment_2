package com;
import java.sql.*; 
public class Product {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "nikeshal@123");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertProduct(String code, String name, String price, String inventor, String type)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into products(`productCode`,`productName`,`productPrice`,`productInventor`,`productType`)"
			 + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
				//preparedStmt.setInt(1, 0);
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setDouble(3, Double.parseDouble(price));
				preparedStmt.setString(4, inventor);
				preparedStmt.setString(5, type);
			// execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 String newProduct = readproducts();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newProduct + "\"}" ; 
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error While Inserting the Product.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readproducts()
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
		 output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" + "<th>Product Price</th>"
					+ "<th>Product Inventor</th>"+ "<th>Product Type</th>" + "<th>Update</th><th>Remove</th></tr>";
		 
		 String query = "select * from products";
		
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String productID = Integer.toString(rs.getInt("productID"));
				String productCode = rs.getString("productCode");
				String productName = rs.getString("productName");
				String productPrice = Double.toString(rs.getDouble("productPrice"));
				String productInventor = rs.getString("productInventor");
				String productType = rs.getString("productType");;
		 // Add into the html table;
		 
				output += "<tr><td>" + productCode + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + productPrice + "</td>";
				output += "<td>" + productInventor + "</td>";
				output += "<td>" + productType + "</td>";
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'data-productid='" + productID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-productid='" + productID + "'></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 } 
		
		catch (Exception e)
		 {
		 output = "Error while reading the product.";
		 System.err.println(e.getMessage());
		 }
		return output;
		}

	
	public String updateProduct(String ID, String code, String name, String price, String inventor, String type)

	
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE products SET productCode=?,productName=?,productPrice=?,productInventor=?,productType=?WHERE productID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setDouble(3, Double.parseDouble(price)); 
		 preparedStmt.setString(4, inventor); 
		 preparedStmt.setString(5,type); 
		 preparedStmt.setInt(6, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newProduct = readproducts();
		 output =  "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}" ; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error While Updating the Product.\"}";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 }
		 return output;
		 }
	
		public String deleteproducts(String productID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from products where productID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(productID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newProduct = readproducts();
		 output =  "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}" ; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error While Deleting the Product.\"}";
		 System.err.println(e.getMessage());
		 e.printStackTrace();
		 
		 }
		 return output;
		 }
		} 

