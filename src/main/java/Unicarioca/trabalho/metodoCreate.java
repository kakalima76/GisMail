package Unicarioca.trabalho;

import javax.mail.MessagingException; 
import javax.mail.Session; 
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 

import java.util.Properties; 

		public class metodoCreate {
			public static MimeMessage createEmail(String to, String from, String subject, 
		            String bodyText) throws MessagingException { 
		Properties props = new Properties(); 
		Session session = Session.getDefaultInstance(props, null); 
		
		MimeMessage email = new MimeMessage(session); 
		InternetAddress tAddress = new InternetAddress(to); 
		InternetAddress fAddress = new InternetAddress(from); 
		
		email.setFrom(new InternetAddress(from)); 
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to)); 
		email.setSubject(subject); 
		email.setText(bodyText); 
		return email; 
	}
	
}
