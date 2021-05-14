$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
	 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateProductForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT";

$.ajax( 
{ 
url : "ProductAPI", 
type : type, 
data : $("#formProduct").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onItemSaveComplete(response.responseText, status); 
} 
});

});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidProductIDSave").val($(this).data("productid"));
 $("#productCode").val($(this).closest("tr").find('td:eq(0)').text());
 $("#productName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#productPrice").val($(this).closest("tr").find('td:eq(2)').text());
 $("#productInventor").val($(this).closest("tr").find('td:eq(3)').text());
 $("#productType").val($(this).closest("tr").find('td:eq(4)').text());
});
//DELETE====================================================
$(document).on("click", ".btnRemove", function(event)
{ 

 $.ajax( 
 { 
 url : "ProductAPI", 
 type : "DELETE", 
 data : "productID=" + $(this).data("productid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 

 }); 
});
// CLIENT-MODEL================================================================
function validateProductForm()
{
// CODE
if ($("#productCode").val().trim() == "")
 {
 return "Insert Product Code.";
 }
// NAME
if ($("#productName").val().trim() == "")
 {
 return "Insert Product Name.";
 } 
// PRICE-------------------------------
if ($("#productPrice").val().trim() == "")
 {
 return "Insert Product Price.";
 }
// is numerical value
var tmpPrice = $("#productPrice").val().trim();
if (!$.isNumeric(tmpPrice))
 {
 return "Insert a numerical value for Product Price.";
 }
// convert to decimal price
 $("#productPrice").val(parseFloat(tmpPrice).toFixed(2));
// Inventor------------------------
if ($("#productInventor").val().trim() == "")
 {
 return "Insert Product Inventor.";
 }
 if ($("#productType").val().trim() == "")
 {
 return "Insert Product Type...";
 }
return true;
}
function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 

 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved In Product Table."); 
 $("#alertSuccess").show();
 $("#divProductGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error..! While Saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidProductIDSave").val(""); 
 $("#formProduct")[0].reset(); 
}
function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 

 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show();
 $("#divProductGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
} 
}