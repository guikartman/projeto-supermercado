package br.com.repository;

public interface IPessoaDao {
	
	public boolean verificarCadastro(String login, String senha);
}
