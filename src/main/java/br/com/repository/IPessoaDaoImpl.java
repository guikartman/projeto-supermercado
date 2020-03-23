package br.com.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

public class IPessoaDaoImpl implements IPessoaDao {

	@Override
	public boolean verificarCadastro(String login, String senha) {
		
		Pessoa pessoa = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where login = '"+login+"' and senha = '"+senha+"'" ).getSingleResult();
		
		transaction.commit();
		entityManager.close();
		
		return pessoa.getLogin().equals(login) && pessoa.getSenha().equals(senha);
		
	}

}
