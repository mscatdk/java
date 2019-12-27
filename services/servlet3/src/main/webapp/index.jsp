<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel='stylesheet' href='<%=request.getContextPath()%>/webjars/bootstrap/${webjars.bootstap.version}/css/bootstrap.min.css'>
<script type='text/javascript' src='<%=request.getContextPath()%>/webjars/bootstrap/${webjars.bootstap.version}/js/bootstrap.min.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/webjars/jquery/${webjars.jquery.version}/jquery.min.js'></script>
</head>


<body>

<div class="alert alert-success">
  <strong>Success!</strong> Indicates a successful or positive action.
</div>

<div class="container">
  <div class="jumbotron">
    <h1>Bootstrap Tutorial</h1>
    Glyphicons support: <span class="glyphicon glyphicon-ok"></span> 
    <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing
    responsive, mobile-first projects on the web.</p> 
  </div>
  
  <div>
  	<c:out value="${message}" default="!!!! NO DATA !!!" />
  </div>
</div>

</body>
</html>
