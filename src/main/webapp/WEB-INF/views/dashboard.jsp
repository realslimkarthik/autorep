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

<select name="storeList" id='storeList' >
<c:forEach var="storeId" items="${storeList}">
 <option value="${storeId }"> ${storeId } </option>
</c:forEach>
</select>

</body>
</html>
