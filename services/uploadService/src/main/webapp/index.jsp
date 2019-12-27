<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel='stylesheet' href='<%=org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css")%>'>
<script type='text/javascript' src='<%=org.webjars.AssetLocator.getWebJarPath("jquery.min.js")%>'></script>
<script type='text/javascript' src='<%=org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js")%>'></script>
</head>

<c:set var="context" value="${pageContext.request.contextPath}" />
<body>

	<div class="container">
		<div class="jumbotron">
			<form id="uploadForm" enctype="multipart/form-data" action="${context}/upload" method="post">
				<div class="form-group">
					<label for="exampleInputFile">File input</label> <input type="file"
						id="exampleInputFile" name="content">
					<p class="help-block">Select a file.</p>
				</div>
				<button type="submit" class="btn btn-default">Upload</button>
			</form>
		</div>
	</div>

</body>
</html>
