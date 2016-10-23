app.controller("enviarController", ["$scope", "emailService", "emailFactory", function($scope, emailService, emailFactory){
	var vm = this;
	vm.mensagem = {};
	vm.mensagem.destino = emailFactory.getEmail().destino;
	
	if(emailFactory.getEmail().titulo === undefined){
		vm.mensagem.titulo = null
	}else{
		vm.mensagem.titulo = "Re-" + emailFactory.getEmail().titulo;
	}
	
	vm.nome = "Nieraldo da Silva Lima";
	
	vm.salvar = function(obj){	
		console.log("teste");
		
		obj["origem"] = "gismailunicarioca@gmail.com";
		var promise = emailService.enviar(obj);
		
		promise.then(function(data){
			console.log(data);
			vm.mensagem = {};
		})	
	}
	
}])