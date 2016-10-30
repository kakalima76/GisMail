app.service("emailService", ["$http", function($http){
	
	
	var listar = function(){
		return  $http.get("http://localhost:8080/emails");
	}
	
	var enviar = function(obj){
		return $http.post("http://localhost:8080/emails", obj);
	}
	
	return {
		listar: listar,
		enviar: enviar
	}
}])

