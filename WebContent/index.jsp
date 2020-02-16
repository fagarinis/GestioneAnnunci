<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Gestione Annunci</title>


<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>

<body>

  

  <!-- Page Content -->
  <div class="container">
  
   <%@ include file="header.jsp" %>
  

    <div class="row">

      <div class="col-lg-2">
        <h1 class="my-4">Gestione Annunci</h1>
        <div class="list-group">
        <!-- dentro un for con var categoriaItem -->
        <p> Seleziona Categorie: </p>
        <c:forEach var="categoriaItem" items="${categorieAttr}">
        <div>
        <input type="checkbox" id="categoriaInputId" name="categoriaInput" value ="${categoriaItem.id}"> ${categoriaItem.descrizione} 
        </div>
        </c:forEach>
        	
        <!--  
          <a href="#" class="list-group-item active">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
          -->
        </div>
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9" >
		
        <div class="card mt-4">
        
         <form method ="post" action="ExecuteSearchAnnuncioServlet" class="form-inline" >
         
        	<input name ="nomeAnnuncioInput" type="text" class="form-control" size ="89">
        	
        	<button class="btn btn-success"><b > Cerca Annuncio</b></button>
        	
     	 </form>
     	 
      </div>
      <div class="card mt-4">
        
        
        
          <img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt="">
          <div class="card-body">
            <h3 class="card-title">Product Name</h3>
            <h4>&euro;24.99</h4>
            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente dicta fugit fugiat hic aliquam itaque facere, soluta. Totam id dolores, sint aperiam sequi pariatur praesentium animi perspiciatis molestias iure, ducimus!</p>
            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
            4.0 stars
          </div>
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">
            Product Reviews
          </div>
          <div class="card-body">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <a href="#" class="btn btn-success">Lascia una Recensione</a>
          </div>
        </div>
        <!-- /.card -->
        <p style="text-align:center;">
        <!-- Accedi all'Area Riservata -->
        <a href="${pageContext.request.contextPath}/areariservata/home.jsp" class="btn btn-info">Area Riservata</a>
        </p>
		
      </div>
      <!-- /.col-lg-9 -->

    </div>

  </div>
  <!-- /.container -->

<%@ include file="footer.jsp" %>




</body>

</html>