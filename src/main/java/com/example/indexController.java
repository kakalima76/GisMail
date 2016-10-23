package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Mensagem;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import Unicarioca.trabalho.GmailQuickstart;
import Unicarioca.trabalho.metodoGet;
import Unicarioca.trabalho.metodoList;
import ch.qos.logback.core.net.SyslogOutputStream;

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
					
		//End points
		
		@RequestMapping(method = RequestMethod.POST, value="/emails", consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<EmailTrabalho> cadastrarEmail(@RequestBody EmailTrabalho emailTrabalho){
			
			return new ResponseEntity<EmailTrabalho>(HttpStatus.OK);
		}
		
		@RequestMapping(method = RequestMethod.GET, value="/emails", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Mensagem>> retornaMensagens() throws IOException, JSONException, MessagingException{
			
			Gmail service = GmailQuickstart.getGmailService();
	        String userId = "gismailunicarioca@gmail.com";
	        List<String> labelIds = new ArrayList<String>();
	        labelIds.add("INBOX");
	        Collection<Message> msg = metodoList.listMessagesWithLabels(service, userId, labelIds);
			      
	        List<Mensagem> mensagens = new ArrayList<Mensagem>(); 
	        
	        //criar um objeto java Json
	        JSONArray objArray = new JSONArray();
	        
	        //utilizei uma expressão lambida
	        msg.forEach((x) -> 
	        {
	        	objArray.put(x);
	        	Mensagem mensagem  = new Mensagem("","");
	        	mensagens.add(mensagem);
	        	
	        });
	        
	        JSONArray arrayMessagesJson = new JSONArray();
	        
	        for(int i = 0; i < objArray.length(); i++){
	        	JSONObject  obj = objArray.getJSONObject(i);
	        	Message message = metodoGet.getMessage(service, userId, obj.getString("id"));
	        	arrayMessagesJson.put(message);
	        	
	        }
	        
	        JSONArray arrayHeadersJson = new JSONArray();
	        JSONArray arraySnippet = new JSONArray();
	        
	        
	        for (int j = 0; j < arrayMessagesJson.length(); j++){
	        	      	      	
	        	
	        	JSONObject obj = arrayMessagesJson.getJSONObject(j);
	        	arraySnippet.put(obj.get("snippet"));
	        	mensagens.get(j).setMensagem(arraySnippet.get(j).toString());    	
	        	arrayHeadersJson.put(obj.get("payload"));
	        }
	       
	        
	        for(int h = 0; h < arrayHeadersJson.length(); h++){
	        	JSONObject obj = arrayHeadersJson.getJSONObject(h);
	        	JSONArray objHeader = obj.getJSONArray("headers");
	        	JSONObject from = objHeader.getJSONObject(3);
	        	mensagens.get(h).setOrigem(from.get("value").toString().replace("<", "").replace(">", ""));
	        }       
			
			return new ResponseEntity<>(mensagens, HttpStatus.OK);
		}
		
		
			
		
	}
	
	
}
