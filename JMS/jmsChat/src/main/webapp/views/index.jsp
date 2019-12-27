<%@ page import="jms.web.common.Config" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<meta charset="UTF-8">
<script type="text/javascript">
var CONTEXT_PATH = "${pageContext.request.contextPath}";
</script>
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
	<div>
		<h1>JMS_FACTORY: ${Config.JMS_FACTORY}</h1>
		<h1>QUEUE: ${Config.QUEUE}</h1>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/angular.min.js"></script>
<!-- inject:js -->
<!-- endinject -->
<script src="${pageContext.request.contextPath}/js/app.min.js"></script>
</html>