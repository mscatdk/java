<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Demo Bank</title>
	
	<link href="${pageContext.request.contextPath}/resources/core/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/core/css/app.css" rel="stylesheet" />
</head>
<body>

	<header class="navbar navbar-inverse navbar-static-top" role="banner">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="${pageContext.request.contextPath}" class="navbar-brand">Demo Bank</a>
			</div>
		</div>
	</header>

	<!-- Begin Body -->
	<div class="container">
		<div class="row">
			<div class="col-md-3" id="leftCol">
			
				<div class="well-inverse">					
					<form class="navbar-form" role="search" action="customerSearch" method="post">
						<div class="input-group">
							<input type="text" id="customerSearch" se-id="customerSearch" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
							<div class="input-group-btn">
								<button class="btn btn-default" id="customerSearchSubmit" type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
				<c:if test="${requestScope['javax.servlet.forward.servlet_path'] != '/'}">
					<ul class="nav nav-pills nav-stacked">						
						<li role="presentation">
							<a href="${pageContext.request.contextPath}/customerOverview?customerNumber=${customerNumber}">Customer Overview</a>
						</li>
						<li role="presentation">
							<a href="${pageContext.request.contextPath}/moneyTransfer?customerNumber=${customerNumber}">Money Transfer</a>
						</li>
					</ul>
				</c:if>

			</div>
			<div class="col-md-9">
				<div id="UIMessages">
					<c:if test="${!empty messages}">
						<c:forEach var="message" items="${messages}">
							<div class="alert <c:out value="${message.messageType}"/> ">
  								<c:out value="${message.message}"/>
							</div>
						</c:forEach>	
					</c:if>
				</div>