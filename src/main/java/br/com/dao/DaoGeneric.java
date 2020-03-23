package br.com.dao;

import java.util.List;

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
	
	public void deletarPorId(Object obj,Class<T> classe) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Object id = (Object) JPAUtil.getPrimaryKey(obj);
		
		entityManager.createQuery("delete from " + classe.getName()+" where id = "+id).executeUpdate();
		
		transaction.commit();
		entityManager.close();
	}
	
	public List<T> carregarTodos(Class<T> classe){
		List<T> lista = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		lista = entityManager.createQuery("select p from "+ classe.getName()+ " p").getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return lista;
	}

}
