<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accesso Utente</title>
</head>
<body class="text-center">


   <%@ include file="header.jsp" %>
	<div class="container">
	
	
	
	<%-- MESSAGGIO ERRORE --%>
	<div class="alert alert-danger ${messaggioErrore!=null?'':'d-none' }" role="alert">
		${messaggioErrore }
	</div>
	
    <form class="form-signin" action="LoginServlet" method="post">
      <h1 class="h1 mb-3 font-weight-normal">Accedi al Sistema</h1>
      <label for="inputUsername" class="sr-only" >Username</label>
      <input  type="text" name="inputUsername" id="inputUsername" class="form-control" placeholder="Username" required autofocus >
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Accedi</button>
      
      
    </form>
    </div>
    <%@ include file="footer.jsp" %>
  </body>
</html>
</html>