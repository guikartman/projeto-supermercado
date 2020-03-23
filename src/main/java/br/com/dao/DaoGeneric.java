package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<T> {

	public T salvar(T entidade) {
		T retorno = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		retorno = (T) entityManager.merge(entidade);
		
		transaction.commit();
		entityManager.close();
		
		return retorno;
	}
	
}
