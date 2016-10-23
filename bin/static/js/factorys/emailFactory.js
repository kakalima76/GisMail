app.factory("emailFactory", [function(){
	var email = {}

	var setEmail = function(obj){
		email["destino"] = obj.origem;
		email["titulo"] = obj.titulo;
	}
	
	var getEmail = function(){
		return email;
	}
	
	return {
		setEmail: setEmail,
		getEmail: getEmail
	}

}])


