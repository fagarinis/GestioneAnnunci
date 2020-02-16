<!-- Bootstrap -->
<link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%= request.getContextPath() %>/css/shop-item.css" rel="stylesheet">

<!-- JSTL librerie -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>



<!-- Static navbar -->
<!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeActionServlet">Progetto Ebay &#9787;</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      
      <div class="collapse navbar-collapse" id="navbarResponsive">
       
        <ul class="navbar-nav ml-auto">
        
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/HomeActionServlet">&#8459;ome
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <c:if test="${userInfo==null}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">&#x27BE;Accedi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/registrazione.jsp">&#8475;egistrati </a>
          </li>
          </c:if>
          <c:if test="${userInfo!=null}">
           <li class="nav-item">
            <a class ="nav-link"> Utente: ${userInfo.username } (${userInfo.nome } ${userInfo.cognome })</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">&#9735;Logout </a>
          </li>
          </c:if>
          
           <c:if test="${userInfo.isAdmin()}">
          <li class="nav-item">
            <a class="nav-link text-danger" href="${pageContext.request.contextPath}/admin/gestioneutenti/PrepareSearchUtenteServlet">&#9728;Area Admin</a>
          </li>
          </c:if>
          
        </ul>
        
      </div>
      
      
      
    </div>
    
    
    
  </nav>
  
  	  <div class="alert alert-danger ${messaggioUtenteNonAbilitato==null?'d-none':''}" role="alert">
	  ${messaggioUtenteNonAbilitato }
	  </div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%= request.getContextPath() %>/js/jquery-1.10.2.min.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.bundle.js"></script>
<script	src="<%=request.getContextPath()%>/js/jqueryUI/jquery-ui.min.js"></script>

<!-- Bootstrap core JavaScript -->
 <!-- <script src="jquery/jquery.min.js"></script> --> 
  <!--  <script src="js/bootstrap.bundle.min.js"></script> --> 
  
  
  