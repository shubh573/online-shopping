<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content=""> 

  <title>Online Shopping - ${title}</title>
  
  <script>
  	window.menu = '${title}';
  	
  	window.contextRoot = '${contextRoot}';
  </script>

  <!-- Bootstrap core CSS -->
  <link href="${css}/bootstrap.min.css" rel="stylesheet">

  <!-- Bootstrap Minty Theme -->
  <link href="${css}/bootstrap-minty.css" rel="stylesheet">
  
  <!-- Bootstrap DataTables -->
  <link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">
  
  <!-- Bootstrap FontAwesome -->
  <link href="${css}/fontawesome/css/all.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${css}/sitem.css" rel="stylesheet">

</head>

<body>

  <div class="wrapper">

  <!-- Navigation -->
  <%@include file="./shared/navbar.jsp" %>
  
  <!-- Page Content -->
  
  <div class="content">
  <!-- home content -->
  <c:if test="${userClickHome == true }">
  	<%@include file="home.jsp" %>
  </c:if>	
  
  <!-- Load when user click about -->
  <c:if test="${userClickAbout == true }">
  	<%@include file="about.jsp" %>
  </c:if>
  
  <!-- Load when user click contact -->
  <c:if test="${userClickContact == true }">
  	<%@include file="contact.jsp" %>
  </c:if>
  
  <!-- Load when user click Products -->
  <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true }">
  	<%@include file="listProduct.jsp" %>
  </c:if>
  
  <!-- Load when user click show products -->
  <c:if test="${userClickShowProduct == true}">
  	<%@include file="singleProduct.jsp" %>
  </c:if>
  
  </div>
  <!-- Footer -->
  <%@include file="./shared/footer.jsp" %>

  </div>
  <!-- Bootstrap core JavaScript -->
  <script src="${js}/jquery.js"></script>
  <script src="${js}/bootstrap.bundle.min.js"></script>
  
  <!-- DataTable Plugin -->
  <script src="${js}/jquery.dataTables.js"></script>
  
  <!-- DataTable Bootstrap script-->
  <script src="${js}/dataTables.bootstrap4.js"></script>
  
  <!-- fontawesome script-->
  <script src="${css}/fontawesome/js/all.js"></script>
  
  <!-- My JS -->
  <script src="${js}/myapp.js"></script>
</body>

</html>
