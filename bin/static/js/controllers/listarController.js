app.controller("listarController", [function(){
	console.log("listarController");
	
	var vm = this;
	
	vm.mensagens =
		[
		 	{"titulo": "Reunião", "msg": "Reunião agendada"},
		 	{"titulo": "Encontro", "msg": "encontro aguardado"}
		 	
		]
	
	vm.responder = function(value){
		console.log(value);
	}
	
}])