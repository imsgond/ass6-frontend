<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td {
  border:1px solid black;
}
.logout{
	margin-left:1200px;
}

</style>
</head>
<body>
<form method="GET" action="logout" >
	             <input class="logout" type="submit" value="Log Out" />
	            </form>
<h1 align="center">Books Listing</h1>
<form method="GET" action="addbook" >
	             <input type="submit" value="Add Book"/>
	            </form>
<table style="width:100%">
  <tr>
            <th>Book Code</th>
            <th>Book Name</th>
            <th>Author</th>
            <th>Date Added</th>
            <th>Actions</th>

  </tr>
  <c:forEach items="${bookArr}" var="i" >
	  <tr align="center">
	            <td>${i.id }</td>
	            <td>${i.name}</td>
	            <td>${i.author}</td>
	            <td>${i.date}</td>
	            <td><form method="GET" action="updatebook/${i.id}" >
	             <input type="submit" value="UPDATE" />
	            </form>
	            <form method="GET" action="deletebook/${i.id}" >
	             <input type="submit" value="DELETE" />
	            </form></td>
	  </tr>
  </c:forEach>
  <tr>
    
  </tr>
</table>

</body>
</html>