package br.com.cast.persistencia.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HibernateUtil {

	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernate_jpa_PU");
	public static EntityManager entityManager = factory.createEntityManager();
	
	public static boolean salvar(Object objeto){
		boolean isSucessoSalvar = false;
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(objeto);
			entityManager.getTransaction().commit();
			isSucessoSalvar = true;
		} catch(Exception e){}
		return isSucessoSalvar;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> buscarTodos(String tipo){
		Query query = entityManager.createQuery("from " + tipo);
		return query.getResultList();
	}
	
	public static Object buscar(Object objeto, Integer id){
		return entityManager.find(objeto.getClass(), id);
	}
	
	public static boolean excluir(Object objeto){
		boolean isSucessoExcluir = false;
		try {
		entityManager.getTransaction().begin();
		entityManager.remove(objeto);
		entityManager.getTransaction().commit();
		isSucessoExcluir = true;
		} catch(Exception e){}
		return isSucessoExcluir;
	}
}
