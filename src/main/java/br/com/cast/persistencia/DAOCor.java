package br.com.cast.persistencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Cor;
import br.com.cast.persistencia.util.HibernateUtil;

@Repository
public class DAOCor implements Serializable {

	private static final long serialVersionUID = 5951364767894604346L;

	public List<Cor> buscarCoresBanco(String tipo){
		return HibernateUtil.buscarTodos(tipo);
	}
	
}
