app.controller('chatCtrl', function($scope, chatMessageService) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    
    $scope.messages = [];
    
    refresh = function() {
    	chatMessageService.getMessageList().then(function successCallback(data) {
    		console.log("chatMessageService -> GET -> Done")
    		$scope.messages = data.data;
    	}, function errorCallback(response) {
    		console.log("chatMessageService -> GET -> Error")
    	});
    }  
    
    $scope.sendMessage = function(msg) {
    	console.log(msg);
    	chatMessageService.sendMessage(msg)
    		.then(function() {
    			console.log("chatMessageService -> POST -> Done")
    			refresh();
    			$scope.msg = null;
    		}, function errorCallback() {
    			console.log("chatMessageService -> POST -> Error")
    		})
    }
    
    refresh();
    
});