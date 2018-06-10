<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/index.css">
        <title>Login</title>
    </head>
    <body>
        <form class="form-signin" action="Login" method="POST">
            <div class="text-center mb-4">
                <img class="mb-5" src="images/ATlogo.png" alt="" width="268" height="130">
                <h1 class="h3 mb-3 font-weight-normal">Always Together</h1>
            </div>
            <div class="form-label-group">
                <input type="email" name="inputEmail" class="form-control" placeholder="Email" required autofocus>
            </div>
            <div class="form-label-group">
                <input type="password" name="inputPassword" class="form-control" placeholder="Senha" required>
            </div>
            <c:if test="${!empty msg}">
                <div class="alert alert-warning">
                    <strong>Atenção! </strong> ${msg}
                </div>   
            </c:if>  
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2018</p>
        </form>
    </body>
</html>