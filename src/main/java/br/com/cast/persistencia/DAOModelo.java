package br.com.cast.persistencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;
import br.com.cast.persistencia.util.HibernateUtil;

@Repository
public class DAOModelo implements Serializable {

	private static final long serialVersionUID = -8956409842026222373L;

	public List<Modelo> buscarListaModelosBanco(Marca marca) {
		return HibernateUtil.entityManager.createQuery(
		"from Modelo m where m.marca = " + marca.getId() + " order by descricao asc").getResultList();
	}

	public Modelo buscarModeloBanco(Modelo modelo, Integer id) {
		return (Modelo) HibernateUtil.buscar(modelo, id);
	}

}
