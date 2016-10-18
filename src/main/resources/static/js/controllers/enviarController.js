app.controller("enviarController", ["$scope", "gmailService", function($scope, gmailService){
	console.log("enviarController");
	var vm = this;
	vm.mensagem = {};
	
	vm.nome = "Nieraldo da Silva Lima";
	
	vm.salvar = function(obj){
		console.log(obj);
		$scope.mensagem = {}
	}
	
	var promise = gmailService.listar();
	promise.then(function(data){
		console.log(data);
	});
	
	
	
}])