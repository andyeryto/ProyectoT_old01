package ec.edu.epn.articulos.recursos;



import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;



public class SendMail {
	private static final String SMTP_HOST_NAME = "correo.epn.edu.ec";
	//private static final String SMTP_HOST_PORT = "465";
	private static final String SMTP_AUTH_USER = "roberto.garcia";
	private static final String SMTP_AUTH_PWD  = "12345678";
	
	private String from;
	private List<String> to;
	private String subject; 
	private String text;
	
	public SendMail(String from, List<String> to, String subject, String text) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	public void send() throws MessagingException{
		Properties props = new Properties();
		boolean debug = true;
		
		//Seteando los parámetros del mail server
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.port", SMTP_HOST_PORT);
		
		//Obteniendo una sesión con el servidor
		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getInstance(props,auth);
		mailSession.setDebug(debug);
		//Iniciando el mensaje
		Message simpleMessage = new MimeMessage(mailSession);
		//Seteando las direccion de origen FROM
		InternetAddress addressFrom = new InternetAddress(from);
		simpleMessage.setFrom(addressFrom);
		//Seteando las direcciones de destino
		InternetAddress[] addressTo = new InternetAddress[to.size()];
	    for (int i = 0; i < to.size(); i++)
	    {
	        addressTo[i] = new InternetAddress(to.get(i));
	    }
	    simpleMessage.setRecipients(Message.RecipientType.TO, addressTo);
		
	    // Seteando el Asunto (Subject) y el tipo de Contenido (Content Type)
	    simpleMessage.setSubject(subject);
	    simpleMessage.setContent(text, "text/plain");
	    
	    BodyPart adjunto = new MimeBodyPart();
	    simpleMessage.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.gif")));
	    simpleMessage.setFileName("futbol.gif");
	    
	    Transport.send(simpleMessage);
	}
	
	/** */
	private class SMTPAuthenticator extends Authenticator
	{
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}
	
		
	
	/** SET AND GETS */
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
