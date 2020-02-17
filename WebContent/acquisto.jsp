<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conferma Acquisto</title>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>
		
		<%-- MESSAGGIO ERRORE --%>
		<div class="alert alert-danger ${messaggioErrore!=null?'':'d-none' }" role="alert">
			${messaggioErrore }
		</div>

		<div class="page-header">
			<h3>Pagina di Conferma Acquisto</h3>
		</div>

		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Testo Annuncio</dt>
				<dd class="col-sm-9">${annuncioAttr.testoAnnuncio}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Inserimento</dt>
				<dd class="col-sm-9">${annuncioAttr.getDataInserimentoSimpleFormat() }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Prezzo</dt>
				<dd class="col-sm-9">${annuncioAttr.prezzo } &euro;</dd>
			</dl>
			
			<dl class="row">
				<dt class="col-sm-3 text-right">Postato da:</dt>
				<dd class="col-sm-9">${usernameAttr }</dd>
			</dl>
			
			

			<dl class="row">
				<dt class="col-sm-3 text-right">Categorie</dt>
				<dd class="col-sm-9"><c:forEach var="categoriaItem" items="${annuncioAttr.categorie}">
	    			${categoriaItem.descrizione} 
	    		</c:forEach></dd>

			</dl>

			<a href="${pageContext.request.contextPath}/HomeActionServlet" class="btn btn-primary btn-md">Torna alla Home</a>
			<a href="${pageContext.request.contextPath}/ExecuteCompraAnnuncioServlet?idAnnuncio=${annuncioAttr.id}" class="btn btn-primary btn-md">Conferma Acquisto</a>
			
			<dl class="row">
				<dt class="col-sm-3 text-right">Il tuo Credito Residuo: </dt>
				<dd class="col-sm-9">${userInfo.creditoResiduo } &euro;</dd>
			</dl>
			
		</div>
		
	</div>
	<%@ include file="../../footer.jsp"%>

</body>
</html>