<%@ page import= "com.Product" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/products.js"></script>
</head>
<body>

<div class="container">
<div class="row">
   <div class="col-8">
   
      <h1 class="m-3"> Product  Management </h1>
      
      <form id="formProduct" name="formProduct">
 Product code:
<input id="productCode" name="productCode" type="text"
 class="form-control form-control-sm">
 
<br> Product Name:
<input id="productName" name="productName" type="text"
 class="form-control form-control-sm">
 
<br> Product price:
<input id="productPrice" name="productPrice" type="text"
 class="form-control form-control-sm">
 
<br> Product Inventor:
<input id="productInventor" name="productInventor" type="text"
 class="form-control form-control-sm">
<br>

<br> Product Type:
<input id="productType" name="productType" type="text"
 class="form-control form-control-sm">
<br>

<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
</form>
<br>

<div id="alertSuccess" class = "alert alert-success"></div>
<div id ="alertError" class =" alert alert-danger"></div>

<br>
<div id="divProductGrid"> 
<%
Product productObj = new Product();
out.print(productObj.readproducts());
%>
</div>
</div>
   
   
   </div>


</div>
</div>


</body>
</html>