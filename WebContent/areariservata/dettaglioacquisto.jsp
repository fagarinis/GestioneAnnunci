<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio Acquisto</title>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Dettaglio Acquisto</h3>
		</div>

		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Descrizione</dt>
				<dd class="col-sm-9">${acquistoAttr.descrizione}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Somma Pagata</dt>
				<dd class="col-sm-9">${acquistoAttr.prezzo }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Anno di Acquisto</dt>
				<dd class="col-sm-9">${acquistoAttr.anno }</dd>
			</dl>


			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna Indietro</a>
			
		</div>
		
	</div>
	<%@ include file="../../footer.jsp"%>

</body>
</html>