<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Minty Theme -->
<link href="${css}/bootstrap-minty.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/sitem.css" rel="stylesheet">


<title>Online Shopping - ${title}</title>

</head>

<body>

	<div class="wrapper">

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>


		<div class="content">

			<div class="container">

				<div class="row">

					<div class="col-xs-12">


						<div class="jumbotron">

							<h1>${errorTitle}</h1>
							<hr/>

							<blockquote style="word-wrap: break-word">

								${errorDescription}
								
							</blockquote>

						</div>
						
					</div>

				</div>

			</div>

		</div>

		<%@include file="./shared/footer.jsp"%>

	</div>

 <!-- Bootstrap core JavaScript -->
  <script src="${js}/jquery.js"></script>
  <script src="${js}/bootstrap.bundle.min.js"></script>
</body>

</html>