<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Users </title>
<style>
	* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	line-height: 1.6;
}

header {
	display: flex;
	padding: 20px;
	background-color: #333;
	height: 4em;
	align-items: center;
	justify-content: space-between;
	color: white;	
}

.table-container {
    display: flex;
    justify-content: space-around;
}

caption {
	font-size: 30px;
	font-weight: bolder;
	background-color: #009879;
	color: black;

}
.content-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 1em;
    width: 10%;
    border-radius: 5px 5px 0 0;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0,0,0,0.15);
    height: fit-content;
}

.content-table thead tr{
    background-color: #009879;
    color: #ffffff;
    text-align: left;
    font-weight: bold;
}

.content-table th,
.content-table td {
    padding: 12px 15px;
}

.content-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.content-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.content-table tbody tr:last-of-type {
    border-bottom: 2px solid #009879;
}

.submit-button {
	padding: 10px 20px;
	background-color: #0dac30;
	border: none;
	color: white;
	border-radius: 4px;
	font-size: 1rem;
	cursor: pointer;
}
.content-table tbody tr:hover{
    font-weight: bold;
    color: #009879;
}



</style>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//HTTP 1.1
	
	response.setHeader("Pragma", "no-cache");//HTTP 1.0
	
	response.setHeader("Expires", "0");//Proxies
	
	String name = (String) session.getAttribute("adminName");

	if(name == null){
		response.sendRedirect("/ContactUsPage/admin/login/adminlogin.html");
	}

%>
	
	 <header>	 
        <h1>Welcome <%= name %> </h1>
        <form action="/ContactUsPage/Logout">
        <button type="submit" class="submit-button">Logout</button>
        </form>
    </header>



	<sql:setDataSource var="db" driver="org.postgresql.Driver" url="jdbc:postgresql://localhost:5432/ContactUs" user="postgres" password="12345"/>
	
	<sql:query var="rsActive" dataSource="${db}"> select * from requests where status = 'active'</sql:query>
	
	<div class="table-container">
	<table class="content-table">
	<caption>Active Users</caption>
    <thead>
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>E-mail</th>
            <th>Message</th>
            <th>Action</th>  
                      
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${rsActive.rows}" var="row">
            <tr>
                <td>${row.id}</td>
                <td>${row.name}</td>
                <td>${row.email}</td>
                <td>${row.message}</td>
                <td>
                    <form action="/ContactUsPage/UpdateUserStatus" method="post">
                        <input type="hidden" name="userId" value="${row.id}">
                        <input type="hidden" name="userStatus" value="${row.status}">                      
                        <button type="submit" class="submit-button">Archive</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
	
<sql:query var="rsArchived" dataSource="${db}"> select * from requests where status = 'archived'</sql:query>
	
	<table class="content-table">
    <caption>Archived Users</caption>
    <thead>
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>E-mail</th>
            <th>Message</th>
            <th>Action</th>            
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${rsArchived.rows}" var="row">
            <tr>
                <td>${row.id}</td>
                <td>${row.name}</td>
                <td>${row.email}</td>
                <td>${row.message}</td>
                <td>
                    <form action="/ContactUsPage/UpdateUserStatus" method="post">
                        <input type="hidden" name="userId" value="${row.id}">
                        <input type="hidden" name="userStatus" value="${row.status}">
                        <button type="submit" class="submit-button">Active</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>




</body>
</html>