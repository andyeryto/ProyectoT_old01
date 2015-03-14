package ec.edu.epn.articulos.mail;

import java.util.List;
import javax.mail.MessagingException;

import ec.edu.epn.articulos.recursos.SendMail;



public class correoVO {
	
	private String id_mail;
	private String sender_user;
	private String sender_passw;
	private String sender_name;
	private String server_name;
	private String subjet;
	private String message;
	
	
	
	public void correoObtener( String from, List<String> to, String subject, String text) {
		
		System.out.println("Entro a enviar el mail");
		
		System.out.println("De" +from );
		System.out.println("Para" +to );
		System.out.println("Asunto" +subject );
		System.out.println("Descripcion" +text );
		
			System.out.println("1");
								
			SendMail sendMail = new SendMail(from,to,subject,text);
			
			System.out.println("try");
			try{
				System.out.println("try*");
				sendMail.send();
			}catch(MessagingException e){
				System.out.println("Error al enviar el mensaje: "+e.getMessage());
			}
		
			System.out.println("ERROR: CONSULTAR CORREO GRADUADOS ALTERNATIVO ");
		
		
		System.out.println(" ***                *** ");
	}
	
	
	
	
	public String getId_mail() {
		return id_mail;
	}
	public void setId_mail(String idMail) {
		id_mail = idMail;
	}
	public String getSender_user() {
		return sender_user;
	}
	public void setSender_user(String senderUser) {
		sender_user = senderUser;
	}
	public String getSender_passw() {
		return sender_passw;
	}
	public void setSender_passw(String senderPassw) {
		sender_passw = senderPassw;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String senderName) {
		sender_name = senderName;
	}
	public String getSubjet() {
		return subjet;
	}
	public void setSubjet(String subjet) {
		this.subjet = subjet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String serverName) {
		server_name = serverName;
	}
	
	
	
	
}
