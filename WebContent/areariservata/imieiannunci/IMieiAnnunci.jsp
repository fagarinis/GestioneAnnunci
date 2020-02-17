<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>I Miei Annunci</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>I Miei Annunci</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>
			
		<%-- alert errore --%>
		<div
			class="alert alert-danger ${messaggioErrore!=null?'':'d-none' }"
			role="alert">${messaggioErrore }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Testo Annuncio</th>
					<th>Prezzo</th>
					<th>Stato</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="annuncioItem" items="${listaAnnunciAttr }">
					<tr> 
						<td>${annuncioItem.testoAnnuncio }</td>
						<td>${annuncioItem.prezzo } &euro;</td>
						<td>${annuncioItem.aperto? 'Aperto' : 'Chiuso' }</td>
						
						<td><a
							href="${pageContext.request.contextPath}/areariservata/imieiannunci/ExecuteDettaglioAnnuncioServlet?idAnnuncio=${annuncioItem.id}"
							class="btn btn-info">Visualizza</a> <a
							href="${pageContext.request.contextPath}/areariservata/imieiannunci/PrepareModificaAnnuncioServlet?idAnnuncio=${annuncioItem.id}"
							class="btn btn-info">Modifica</a> 
							<a
							href="${pageContext.request.contextPath}/areariservata/imieiannunci/PrepareEliminaAnnuncioServlet?idAnnuncio=${annuncioItem.id}"
							class="btn btn-info">Elimina</a> 
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
			<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/areariservata/home.jsp" class="btn btn-primary btn-md">Torna all'Area Riservata</a>
			<!-- Inserisci nuovo Annuncio -->
		        <a href="${pageContext.request.contextPath}/areariservata/imieiannunci/PrepareInsertAnnuncioServlet" class="btn btn-primary btn-md">Inserisci Nuovo Annuncio</a>
		      
			</div>
		</div>

	</div>
	<%@ include file="../../footer.jsp"%>
</body>
</html>