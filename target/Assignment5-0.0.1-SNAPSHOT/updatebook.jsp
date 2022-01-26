<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>   
<html>   
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1">  
<title> Login Page </title>  
<style>   
Body {  
  font-family: Calibri, Helvetica, sans-serif;  
  background-color: pink;  
  position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}  
button {   
       background-color: #4CAF50;   
       width: 100%;  
        color: orange;   
        padding: 15px;   
        margin: 10px 0px;   
        border: none;   
        cursor: pointer;   
         }   

 input[type=text], input[type=password], input[type=number],select {   
        width: 100%;   
        margin: 8px 0;  
        padding: 12px 20px;   
        display: inline-block;   
        border: 2px solid green;   
        box-sizing: border-box;   
    }  
 button:hover {   
        opacity: 0.7;   
    }   
  
        
     
 .container {   
        padding: 25px;   
        background-color: lightblue; 
        width:600px;
        align:center;

    }   
</style> 
 
</head>    
<body>    
    <center> <h1 id="head"> Update Book </h1> </center>   
    <form method="POST" action="/Assignment5/updatebook/" name="updateBookForm">
        <div class="container">   
            <label>Book Code : </label>   
            <input type="number" placeholder="Enter code" name="id" value="${book.id}" readonly>  
            
            <label>Book Name : </label>   
            <input type="text" placeholder="Enter Book Name" name="name" value="${book.name}" required>  
            
            <label>Book Author : </label>   
	       <select name="author">
	       <option value="${book.author}" selected>${book.author}</option>
	        <c:forEach items="${authorsArr}" var="i" >
			    <option value="${i}">${i}</option>
			</c:forEach>
			    
  			</select>
            
            <label>Date : </label>   
            <input id="date" type="text"  name="date" value="${book.date}" readonly>  
            
             
            <button type="submit">Update Book</button>          
        </div>   
    </form>    
</body>     
</html>  

