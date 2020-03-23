package br.com.meusupermercado;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
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
	public void carregarTodos() {
		listaPessoa = daoGeneric.carregarTodos(Pessoa.class);
	}
	
}
