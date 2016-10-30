package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import Unicarioca.trabalho.metodoCreate;
import Unicarioca.trabalho.metodoGet;
import Unicarioca.trabalho.metodoList;
import Unicarioca.trabalho.metodoSend;
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
		public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) throws MessagingException, IOException{
			
			//esse metodo cria a autorização para acessar o gmail
			Gmail service = GmailQuickstart.getGmailService();
			String to = mensagem.getDestino();
			String from = "gismailunicarioca@gmail.com";
			String subject = mensagem.getOrigem();
			String bodyText = mensagem.getMensagem();
			
			MimeMessage  email = metodoCreate.createEmail(to, from, subject, bodyText);
			metodoSend.sendMessage(service, from, email);
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		
		
		@RequestMapping(method = RequestMethod.GET, value="/emails", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Mensagem>> retornaMensagens() throws IOException, JSONException, MessagingException{
			
			Gmail service = GmailQuickstart.getGmailService();
	        String userId = "gismailunicarioca@gmail.com";
	        List<String> labelIds = new ArrayList<String>();
	        labelIds.add("INBOX");
	        Collection<Message> msg = metodoList.listMessagesWithLabels(service, userId, labelIds);
			//cria a lista de mensagens que será retornada pelo end point      
	        List<Mensagem> mensagens = new ArrayList<Mensagem>(); 
	        
	        //criar um objeto java Json
	        JSONArray objArray = new JSONArray();
	        JSONArray objArrayOrigem = new JSONArray();
	        
	        //utilizei uma expressão lambida
	        msg.forEach((x) -> 
	        {
	        	objArray.put(x);
	        	//instacia um mensagem para cada objeto .json
	        	Mensagem mensagem  = new Mensagem();
	        	//adiciona a nova mensagem a lista de mensagens
	        	mensagens.add(mensagem);	        	
	        });
	       
	        for(int i = 0; i < objArray.length(); i++){
	        	
	        	JSONObject  obj = objArray.getJSONObject(i);
	        	
	        	
	        	Message message = metodoGet.getMessage(service, userId, obj.getString("id"));
	        	//cria um novo jsonArray para poder acessar o método getSttrint()
	        	//do jsonObject, para assim acessar o remetente
	        	objArrayOrigem.put(message.getPayload().getHeaders().get(3));     	
	        	
	        	MimeMessage email = metodoGet.getMimeMessage(service, userId, obj.getString("id"));
	        	        	
	        	mensagens.get(i).setMensagem(message.getSnippet());
	        	mensagens.get(i).setTitulo(email.getSubject());	        	        	
	        }
	        
	        for(int n = 0; n < objArrayOrigem.length(); n++){
	        	JSONObject obj = objArrayOrigem.getJSONObject(n);
	        	//acessa a chave de nome value do jsonObject
	        	mensagens.get(n).setOrigem(obj.getString("value").replace("<", "").replace(">", ""));
	        }	        
			
			return new ResponseEntity<>(mensagens, HttpStatus.OK);
		}		
		
	}
	
}
