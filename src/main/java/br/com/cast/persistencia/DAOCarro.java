package br.com.cast.persistencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cast.model.Carro;
import br.com.cast.model.Marca;
import br.com.cast.persistencia.util.HibernateUtil;

@Repository
public class DAOCarro implements Serializable {

	private static final long serialVersionUID = -2744271054684693626L;
	
	public boolean isPersistirCarro(Carro carro){
		return HibernateUtil.salvar(carro);
	}
	
	public List<Carro> buscarListaCarrosBanco(String tipo){
		return HibernateUtil.buscarTodos(tipo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarroBancoFiltro(Carro carro, Marca marca){
		StringBuilder sb = new StringBuilder();
		sb.append("from Carro c where 1=1 ");
		
		if(marca != null){
			sb.append(" and c.modelo.marca.id = ");
			sb.append(marca.getId());
		}
		
		if (carro.getModelo() != null){
			sb.append(" and c.modelo = ");
			sb.append(carro.getModelo().getId());
		}
		if (carro.isArCondicionado() == true)
		sb.append(" and c.arCondicionado = true");
		
		if(carro.isDirecaoHidraulica() == true)
		sb.append(" and c.direcaoHidraulica = true");
		
		if(carro.isVidrosEletricos() == true)
		sb.append(" and c.vidrosEletricos = true");
		
		if(carro.isTravasEletricas() == true)
		sb.append(" and c.travasEletricas = true");
		
		sb.append(" order by modelo asc");
		
		return (List<Carro>) HibernateUtil.entityManager.createQuery(sb.toString()).getResultList();
	}
	
	public void excluirCarro(Carro carro){
		HibernateUtil.excluir(carro);
	}
	
	public Carro buscarCarro(){
		return (Carro) HibernateUtil.entityManager.createQuery("from Carro c where c.id = 3");
	}
	
}
