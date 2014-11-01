<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Dashboard</title>
</head>
<body>
<h1>
	Dashboard 
</h1>

<div> Please select a store from the list to view the inventory </div>

<form name='refreshInventory' action='showInventory' method="post">

<select name="storeList" id='storeList'>
<c:forEach var="storeId" items="${storeList}">
<c:choose>
<c:when test="${storeId == currentStore }">
<option value="${storeId }" selected="selected"> ${storeId } </option>
</c:when>
<c:otherwise>
<option value="${storeId }"> ${storeId } </option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>

<input type='submit' value='Display Inventory' />
</form>


<div id='inventoryTable'>
<table border='1'>
<tr>
<th>SKU ID</th>
<th>Product ID</th>
<th>Product Name</th>
<th>Pack Size</th>
<th>Expiry Date</th>
<th>Discount</th>
<th>Manufacturing Date</th>
<th>MRP</th>
<th>Price</th>
<th>Weight</th>
<th>Note</th>
<th>Vendor ID</th>
<th>Warehouse ID</th>
</tr>
<c:forEach var="skuItem" items="${inventoryList}">
 <tr>
 <td>${skuItem.skuId }</td>
 <td>${skuItem.productId }</td>
 <td>${skuItem.productName }</td>
 <td>${skuItem.packSize }</td>
 <td>${skuItem.expiryDate }</td>
 <td>${skuItem.discount }</td>
 <td>${skuItem.dateOfMf }</td>
 <td>${skuItem.mrp }</td>
 <td>${skuItem.unitPrice }</td>
 <td>${skuItem.weight }</td>
 <td>${skuItem.note }</td>
 <td>${skuItem.vendorId }</td>
 <td>${skuItem.warehouseId }</td>
 
 </tr>
</c:forEach>
</table>
</div>

</body>
</html>
