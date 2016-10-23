app.controller("enviarController", ["$scope", "emailService", function($scope, gmailService){
	console.log("enviarController");
	var vm = this;
	vm.mensagem = {};
	
	vm.nome = "Nieraldo da Silva Lima";
	
	vm.salvar = function(obj){
		console.log(obj);
		$scope.mensagem = {}
	}
	
	
}])