<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Riservata</title>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>
		
		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }
		</div>

		<div class="page-header">
			<h3>Area Riservata: Riepilogo Dati Account</h3>
		</div>

		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Nome</dt>
				<dd class="col-sm-9">${userInfo.nome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Cognome</dt>
				<dd class="col-sm-9">${userInfo.cognome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Username</dt>
				<dd class="col-sm-9">${userInfo.username }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Email</dt>
				<dd class="col-sm-9">${userInfo.email }</dd>
			</dl>
			
			<dl class="row">
				<dt class="col-sm-3 text-right">Credito Residuo</dt>
				<dd class="col-sm-9">${userInfo.creditoResiduo } &euro;</dd>
			</dl>
			
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Registrazione</dt>
				<dd class="col-sm-9">${userInfo.getDataCreazioneSimpleFormat() }</dd>
			</dl>
			
			<a href="${pageContext.request.contextPath}/areariservata/PrepareModificaDaAreaRiservataUtenteServlet" class="btn btn-primary btn-md">Modifica Dati Account</a>
			<a href="${pageContext.request.contextPath}/areariservata/ExecuteSearchAcquistoServlet" class="btn btn-primary btn-md">I Miei Acquisti</a>
			<a href="${pageContext.request.contextPath}/areariservata/imieiannunci/ExecuteSearchIMieiAnnunciServlet" class="btn btn-primary btn-md">I Miei Annunci</a>
			
			
		</div>
		
	</div>
	<%@ include file="../footer.jsp"%>

</body>
</html>