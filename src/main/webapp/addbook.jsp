<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>   
<html>   
<head>  
<meta name="viewport" content="width=device-width, initial-scale=1">  
<title> Add Book </title>  
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
<body onload="setdate();">    
    <center> <h1 id="head"> Add Book </h1> </center>   
    <form method="POST" action="addbook" name="addBookForm">
        <div class="container">   
            <label>Book Code : </label>   
            <input type="number" placeholder="Enter code" name="id" required>  
            
            <label>Book Name : </label>   
            <input type="text" placeholder="Enter Book Name" name="name" required>  
            
            <label>Book Author : </label>   
	        <select name="author">
	        <c:forEach items="${authorsArr}" var="i" >
			    <option value="${i}">${i}</option>
			</c:forEach>
			    
  			</select>
            
            <label>Date : </label>   
            <input id="date" type="text"  name="date" readonly>  
            
             
            <button type="submit">Add Book</button>          
        </div>   
    </form> 
    
    
    <script>
function setdate(){
	let date = new Date().toUTCString().slice(5, 16);
	document.getElementById("date").value = date;
}

</script>     
</body>     
</html>  

