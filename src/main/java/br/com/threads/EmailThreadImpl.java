package br.com.threads;

import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.meusupermercado.EmailBean;

public class EmailThreadImpl  extends Thread {

	private static ConcurrentLinkedQueue<String> fila = new ConcurrentLinkedQueue<String>();
	private String userName = "trainingjavadev@gmail.com";
	private String password = "@Meuemail123";
	private String assunto;
	private String corpo;
	private Properties props = new Properties();
	private Session sessao;
	private Message mensagem;
	
	public EmailThreadImpl() {
		try {
			
	    	props.put("mail.smtp.auth", "true");/*Autorização*/
			props.put("mail.smtp.starttls", "true");/*Autenticação*/
			props.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail Google*/
			props.put("mail.smtp.port", "465");/*Porta do servidor*/
			props.put("mail.smtp.socketFactory.port", "465");/*Especifica a porta a ser conectada pelo o Sockect*/
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
			
			sessao = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});
			
			mensagem = new MimeMessage(sessao);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void add(String email) {
		fila.add(email);
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			synchronized (fila) {
				
				Iterator iterator = fila.iterator();
				while (iterator.hasNext()) {
					
					String processar = (String) iterator.next();
					
					try {
						mensagem.setFrom(new InternetAddress(userName));
						mensagem.setRecipients(Message.RecipientType.TO, new InternetAddress().parse(processar));
						mensagem.setSubject(assunto);
						mensagem.setText(corpo);
						
						Transport.send(mensagem);
					}catch (Exception e) {
						e.printStackTrace();
					}
					iterator.remove();
				}
				try {
					Thread.sleep(1000); /* Dar um tempo para descarga de memória */
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				Thread.sleep(1000); /* Dar um tempo para descarga de memória */
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

}
