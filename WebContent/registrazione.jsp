<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src = "validateUtente.js" ></script>

<meta charset="ISO-8859-1">
<title>Registrati</title>
</head>
<body>
<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Registrati</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty utenteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${utenteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>





      	<form onsubmit ="return validateUtenteForm()" name="utenteForm" class="form-horizontal" action="${pageContext.request.contextPath}/ExecuteRegistrazioneUtenteServlet" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nomeInput" 
					value = "${utenteAttr.nome}">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognomeInput" 
					value = "${utenteAttr.cognome}">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="usernameInputId">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="usernameInputId" name="usernameInput" 
					value = "${utenteAttr.username}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="passwordInputId">Password:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="password" id="passwordInputId" name="passwordInput" 
					value = "${utenteAttr.password}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="confermaPasswordInputId">Conferma Password:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="password" id="confermaPasswordInputId" name="confermaPasswordInput" 
					value = "${utenteAttr.confermaPassword}">
					<font color="red"><p id = "confermaPasswordTextId"></p></font>
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="emailInputId">Email:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="emailInputId" name="emailInput" 
					value = "${utenteAttr.email}">
					<font color="red"><p id = "emailTextId"></p></font>
			 	</div>
  			</div>
  			
  			
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma Registrazione</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->
</body>
</html>