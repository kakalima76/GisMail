var app = angular.module("app", ["ngRoute"])

app.config(["$routeProvider", function($routeProvider){
	$routeProvider
	.when("/", {
		templateUrl: "./views/home.html",
	})
	.when("/enviar", {
		templateUrl: "./views/enviar.html",
		controller: "enviarController",
		controllerAs: "vm"
	})
	.when("/listar", {
		templateUrl: "./views/listar.html",
		controller: "listarController",
		controllerAs: "vm"
	})
	.when("/responder", {
		templateUrl: "./views/responder.html",
		controller: "responderController",
		controllerAs: "vm"
	});
	
}])