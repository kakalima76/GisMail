app.service("emailService", ["$http", function($http){
	
	var listar = function(){
		return  $http.get("http://localhost:8080/emails");
	}
	
	
	
	return {
		listar: listar
	}
}])
