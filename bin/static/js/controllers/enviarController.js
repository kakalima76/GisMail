app.controller("enviarController", ["emailService", "emailFactory", function(emailService, emailFactory){
	var vm = this;
	vm.mensagem = {};
	vm.mensagem.destino = emailFactory.getEmail().destino;
	
	if(emailFactory.getEmail().titulo === undefined){
		vm.mensagem.titulo = null;
		vm.mensagem.destino = null;
	}else{
		vm.mensagem.titulo = "Re-" + emailFactory.getEmail().titulo;
	}

	
	vm.salvar = function(obj){	

		obj["origem"] = "gismailunicarioca@gmail.com";
		var promise = emailService.enviar(obj);
		
		promise.then(function(data){
			console.log(data);
			vm.mensagem = {};
		})	
	}
	
}])