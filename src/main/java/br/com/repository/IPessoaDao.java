package br.com.repository;

import br.com.entidades.Pessoa;

public interface IPessoaDao {
	
	public Pessoa verificarCadastro(String login, String senha);
}
