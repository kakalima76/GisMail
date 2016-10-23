app.controller("listarController", ["emailService", "emailFactory", "$location", function(emailService, emailFactory, $location){
	var vm = this;
	
	var promise = emailService.listar();
	promise.then(function(data){
		vm.mensagens = data.data;
	})
	
	
	vm.responder = function(obj){
		emailFactory.setEmail(obj);
		$location.path("/enviar");
	}
	
}])