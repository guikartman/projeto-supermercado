package br.com.meusupermercado;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;
import br.com.repository.IPessoaDao;
import br.com.repository.IPessoaDaoImpl;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	private IPessoaDao pessoaDao = new IPessoaDaoImpl();
	
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String salvar() {
		pessoa = daoGeneric.salvar(pessoa);
		carregarTodos();
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	public String remove() {
		daoGeneric.deletarPorId(pessoa, Pessoa.class);
		pessoa = new Pessoa();
		carregarTodos();
		return "";
	}
	
	@PostConstruct
	private void carregarTodos() {
		listaPessoa = daoGeneric.carregarTodos(Pessoa.class);
	}
	
	public String logar() {
		
		if (pessoaDao.verificarCadastro(pessoa.getLogin(), pessoa.getSenha())) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoa);
			return "painel.jsf";
		}
		return "index.jsf";
	}
	
}
