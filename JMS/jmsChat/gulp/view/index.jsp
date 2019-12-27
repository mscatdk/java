<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<meta charset="UTF-8">
<script type="text/javascript">
var CONTEXT_PATH = "${pageContext.request.contextPath}";
</script>
<!-- bower:css -->
<!-- bower installed css files will go here... -->
<!-- endinject -->
</head>
<body>
	<div >

	</div>
	<div data-ng-app="JmsChatApp" data-ng-controller="chatCtrl">
		<div data-ng-repeat="message in messages">
			<h1>{{message}}</h1>
		</div>
		<form novalidate class="simple-form">
			<input type="text" data-ng-model="msg.text"/>
			<button data-ng-click="sendMessage(msg)">Send Message</button>
		</form>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/angular.min.js"></script>
<!-- bower:js -->
<!-- bower installed scripts will go here... -->
<!-- endinject -->
<!-- inject:js -->
<!-- endinject -->
</html>
