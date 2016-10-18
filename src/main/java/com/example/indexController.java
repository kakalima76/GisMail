package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class indexController {

	@RequestMapping("/")
	String index(){
		return "index";
	}
	
	@RestController
	class RestEmail{
		@RequestMapping(method = RequestMethod.POST, value = "/imprime", produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
			
		public ResponseEntity<EmailTrabalho> imprimir(@RequestBody EmailTrabalho emailTrabalho){
			EmailTrabalho pegaEamil = new EmailTrabalho(emailTrabalho);
			return new ResponseEntity<EmailTrabalho>(HttpStatus.OK);
		}
		
	}
	
	
}
