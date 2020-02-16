<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Annuncio</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Modifica Annuncio</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty annuncioErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${annuncioErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		
      	<form class="form-horizontal" action="${pageContext.request.contextPath}/areariservata/imieiannunci/ExecuteModificaAnnuncioServlet" method="post">
      		
      		<input class="form-control" type="hidden" id="idInputId" name="idInput" value = "${annuncioAttr.id}">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="testoAnnuncioInputId">Testo Annuncio:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="testoAnnuncioInputId" name="testoAnnuncioInput" 
					value = "${annuncioAttr.testoAnnuncio}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoInputId">Prezzo:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="prezzoInputId" name="prezzoInput" 
					value = "${annuncioAttr.prezzo}">
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="categorieInputId">Categoria:</label>
	    		<div class="col-sm-4">
	    		
	    			<c:forEach var = "categoriaItem" items ="${categorieListAttr}">
	    				<c:forEach var ="annuncioCategoriaItem" items ="${annuncioAttr.categorie}">
	    					<c:if test="${categoriaItem.id == annuncioCategoriaItem.id}">
	    						<c:set var = "check" value ="yes"/>
	    					</c:if>
	    				</c:forEach>
	    			
						<input <c:if test="${check =='yes'}">checked="checked"</c:if> type="checkbox" id="categoriaInputId" name="categoriaInput" value ="${categoriaItem.id}"> ${categoriaItem.descrizione} 
						<br>
						<c:set var = "check" value ="no"/>
					</c:forEach>
			 	</div>
  			</div>

		<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma Modifica Annuncio</button>
		      </div>
		    </div>
		</form>
    </div><!-- /.container -->
    
    <%@ include file="../../footer.jsp"%>



</body>
</html>