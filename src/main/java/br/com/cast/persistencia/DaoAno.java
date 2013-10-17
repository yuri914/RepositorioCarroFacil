package br.com.cast.persistencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Ano;
import br.com.cast.persistencia.util.HibernateUtil;

@Repository
public class DaoAno implements Serializable {

	private static final long serialVersionUID = 6338082826780626004L;

	public List<Ano> consultarListaAno(){
		return HibernateUtil.entityManager.createQuery("from Ano order by descricao desc").getResultList();
	}
	
}
