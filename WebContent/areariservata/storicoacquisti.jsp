<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Storico Acquisti</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Storico Acquisti</h3>
		</div>


		<table class="table table-striped">
			<thead>
				<tr>
					<th>Descrizione</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="acquistoItem" items="${listaAcquistiAttr }">
					<tr> 
						<td>${acquistoItem.descrizione }</td>
						
						<td><a
							href="${pageContext.request.contextPath}/areariservata/ExecuteDettaglioAcquistoServlet?idAcquisto=${acquistoItem.id}"
							class="btn btn-info">Visualizza</a>
						 
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
			<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/areariservata/home.jsp" class="btn btn-primary btn-md">Torna all'Area Riservata</a>
		      
			</div>
		</div>

	</div>
	<%@ include file="../../footer.jsp"%>
</body>
</html>