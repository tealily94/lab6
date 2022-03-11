<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <h1>User Management System</h1>
        <div class="add">
            <h2>Add User</h2>
            <form action="users" method="post">
                <input type="text" id="email" name="email" required size="20" placeholder="E-mail"><br>
                <input type="text" id="fname" name="fname" required size="20" placeholder="First Name"><br>
                <input type="text" id="lname" name="lname" required size="20" placeholder="Last Name"><br>
                <input type="password" id="password" name="password" required size="20" placeholder="Password"><br>
                <select name="roleId">
                    <option value="1">System admin</option>
                    <option value="2">Regular User</option>
                    <option value="3">Company admin</option>                   
                </select><br>
                <input type="submit" id="submit" name="action" required size="20" value="Save">
            </form>
            
        </div>
        <div class="container">
            <div class="col">
            <table class="table">
            <thead>
                <tr>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                    </tr>                        
                </c:forEach>
            </tbody>
        </table>
            </div>
                
        </div>
        
        <div class="edit">
            <h2>Edit/Delete</h2>
            <form action="users" method="post">
                <input type="text" name="email" placeholder="Email" value="${selectedEmail.email}" required><br>
                <input type="text"  name="firstName" placeholder="Firstname"  value="${selectedEmail.firstName}" required><br> 
                <input type="text"  name="lastName" placeholder="lastName"  value="${selectedEmail.lastName}"  required><br> 
                <input type="password" name="password" placeholder="Password" value="${selectedEmail.password}" required><br> 
                <select name="roleId">
                    <option value ="1">system admin</option>
                    <option value ="2">regular user</option>
                    <option value ="3">company admin</option>
                </select><br>            
                    <input type="submit"  value="Edit" name="action">
                    <input type="submit" value="Delete" name="action">
            </form>                            
        </div>     
    </body>
</html>
