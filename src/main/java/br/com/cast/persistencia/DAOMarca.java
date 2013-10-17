package br.com.cast.persistencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Marca;
import br.com.cast.persistencia.util.HibernateUtil;

@Repository
public class DAOMarca implements Serializable {

	private static final long serialVersionUID = -1210876849817903903L;

	public List<Marca> buscarListaMarcasBanco(String tipo){
		return HibernateUtil.buscarTodos(tipo);
	}
	
	public Marca buscarCarroBanco(Marca marca ,Integer id){
		return (Marca) HibernateUtil.buscar(marca, id);
	}
	
}
