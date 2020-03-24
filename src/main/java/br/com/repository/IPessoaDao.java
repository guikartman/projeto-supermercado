package br.com.repository;

import java.util.List;

import br.com.entidades.Pessoa;

public interface IPessoaDao {
	
	public Pessoa verificarCadastro(String login, String senha);
	public List<String> recuperaEmails();
}
