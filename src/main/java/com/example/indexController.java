package com.example;

import java.util.HashMap;
import java.util.Map;

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
				
Map<String, EmailTrabalho> emails;

Integer count = 0;
		
		//negocios
		
		private EmailTrabalho cadastrar(EmailTrabalho emailTrabalho){
			
			if(emails==null){
				emails = new HashMap<>();
			}
			
			count++;
			
			emails.put(count.toString(), emailTrabalho);
			
			return emailTrabalho;
		}
		
		
		//End points
		
		@RequestMapping(method = RequestMethod.POST, value="/emails", consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<EmailTrabalho> cadastrarEmail(@RequestBody EmailTrabalho emailTrabalho){
			EmailTrabalho e = cadastrar(emailTrabalho);
			System.out.println(e.getId());
			return new ResponseEntity<EmailTrabalho>(e, HttpStatus.OK);
		}
		
		
			
		
	}
	
	
}
