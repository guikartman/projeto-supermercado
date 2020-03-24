package br.com.meusupermercado;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.mail.internet.AddressException;

import br.com.repository.IPessoaDao;
import br.com.repository.IPessoaDaoImpl;
import br.com.threads.EmailThreadImpl;

@ManagedBean(name = "emailBean")
public class EmailBean {

	private IPessoaDao pessoaDao = new IPessoaDaoImpl();
	private List<String> listaEmails = new ArrayList<String>();
	private EmailThreadImpl threadEmail = new EmailThreadImpl();
	
	
	public EmailThreadImpl getThreadEmail() {
		return threadEmail;
	}

	public void setThreadEmail(EmailThreadImpl threadEmail) {
		this.threadEmail = threadEmail;
	}

	public List<String> getListaEmails() {
		return listaEmails;
	}

	public void setListaEmails(List<String> listaEmails) {
		this.listaEmails = listaEmails;
	}
	
	@PostConstruct
	public void carregarEmail() {
		listaEmails = pessoaDao.recuperaEmails();
	}
	
	public String enviarEmail() throws AddressException {

		if (threadEmail == null) {
			threadEmail = new EmailThreadImpl();
			threadEmail.start();
		}
		
		for (String email : listaEmails) {
			threadEmail.add(email);
		}
		
		threadEmail.start();
		
		return "painel.jsf";
	}
}
