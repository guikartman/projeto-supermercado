package br.com.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

public class IPessoaDaoImpl implements IPessoaDao {

	@Override
	public Pessoa verificarCadastro(String login, String senha) {
		Pessoa pessoa = null;

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		pessoa = (Pessoa) entityManager
				.createQuery("select p from Pessoa p where login = '" + login + "' and senha = '" + senha + "'")
				.getSingleResult();

		transaction.commit();
		entityManager.close();

		return pessoa;
	}

	@Override
	public List<String> recuperaEmails() {
		List<String> listaEmail = null;

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		listaEmail = entityManager.createQuery("select p.email from Pessoa p").getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return listaEmail;
	}

	

}
