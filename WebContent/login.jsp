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
	
	<%-- MESSAGGIO REGISTRAZIONE COMPLETA --%>
	<div class="alert alert-success ${messaggioConfermaRegistrazione!=null?'':'d-none' }" role="alert">
		${messaggioConfermaRegistrazione }
	</div>
	
	<%-- MESSAGGIO ERRORE --%>
	<div class="alert alert-danger ${messaggioErrore!=null?'':'d-none' }" role="alert">
		${messaggioErrore }
	</div>
	
    <form class="form-signin" action="LoginServlet" method="post">
      <h1 class="h1 mb-3 font-weight-normal">Accesso Account</h1>
      <label for="inputUsername" class="sr-only" >Username</label>
      <input  type="text" name="inputUsername" id="inputUsername"  placeholder="Username"  autofocus >
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="inputPassword" id="inputPassword"  placeholder="Password" >
      <button class="btn btn-lg  " type="submit">Accedi</button>
      <a href="${pageContext.request.contextPath}/registrazione.jsp" class="btn btn-lg">Registrati</a>
      
      
    </form>
    
    
    
    </div>
    <%@ include file="footer.jsp" %>
  </body>

</html>