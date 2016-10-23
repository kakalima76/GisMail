app.controller("listarController", ["emailService", function(emailService){
	console.log("listarController");

	var vm = this;
	
	var promise = emailService.listar();
	promise.then(function(data){
		vm.mensagens = data.data;
		console.log(data.data);
	})
	
	
	vm.responder = function(value){
		console.log(value);
	}
	
}])