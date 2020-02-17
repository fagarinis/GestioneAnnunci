<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio Annuncio</title>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Dettaglio Annuncio</h3>
		</div>

		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Testo Annuncio</dt>
				<dd class="col-sm-9">${annuncioAttr.testoAnnuncio}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Inserimento</dt>
				<dd class="col-sm-9">${annuncioAttr.dataInserimento }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Prezzo</dt>
				<dd class="col-sm-9">${annuncioAttr.prezzo } &euro;</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Stato</dt>
				<dd class="col-sm-9">${annuncioAttr.isAperto()? 'Aperto' : 'Chiuso' }</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Categorie</dt>
				<dd class="col-sm-9"><c:forEach var="categoriaItem" items="${annuncioAttr.categorie}">
	    			${categoriaItem.descrizione} 
	    		</c:forEach></dd>

				



			</dl>




			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna Indietro</a>
			
		</div>
		
	</div>
	<%@ include file="../../footer.jsp"%>

</body>
</html>