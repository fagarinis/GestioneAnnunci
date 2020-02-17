<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati Ricerca Annuncio</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina dei Risultati Annunci</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Testo Annuncio</th>
					<th>Prezzo</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="annuncioItem" items="${listaAnnunciAttr }">
					<tr> 
						<td>${annuncioItem.testoAnnuncio }</td>
						<td>${annuncioItem.prezzo }</td>
						
						<td><a
							href="${pageContext.request.contextPath}/ExecuteDettaglioAnnuncioDaRicercaServlet?idAnnuncio=${annuncioItem.id}"
							class="btn btn-info">Visualizza</a> <a
							href="${pageContext.request.contextPath}/areariservata/imieiannunci/PrepareModificaAnnuncioServlet?idAnnuncio=${annuncioItem.id}"
							class="btn btn-info">Compra</a> 
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
			<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/admin/gestioneutenti/PrepareSearchUtenteServlet" class="btn btn-primary btn-md">Nuova Ricerca</a>
		      
			</div>
		</div>

		

	</div>
	<%@ include file="../../footer.jsp"%>
</body>
</html>