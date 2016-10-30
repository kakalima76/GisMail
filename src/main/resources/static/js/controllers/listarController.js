app.controller("listarController", ["emailService", "emailFactory", "$location", function(emailService, emailFactory, $location){
	var vm = this;
	
	var promise = emailService.listar();
	promise.then(function(resp){
		vm.mensagens = resp.data;
	})
	
	
	vm.responder = function(obj){
		emailFactory.setEmail(obj);
		$location.path("/enviar");
	}
	
}])