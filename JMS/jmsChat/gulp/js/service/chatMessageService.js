app.service('chatMessageService', function($http) {
	console.log("Running chatMessageService");
	this.getMessageList = function() {
		return $http({
			method : 'GET',
			url : CONTEXT_PATH + '/messageService'
		});
	}

	this.sendMessage = function(msg) {
		return $http.post(CONTEXT_PATH + '/messageService', msg);
	}
	
});