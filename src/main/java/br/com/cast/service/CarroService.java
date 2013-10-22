package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Carro;
import br.com.cast.model.Marca;
import br.com.cast.persistencia.DAOCarro;
import br.com.cast.persistencia.util.HibernateUtil;

@Service
public class CarroService implements Serializable {

	private static final long serialVersionUID = -1506433505010539837L;
	
	@Autowired
	private DAOCarro daoCarro = new DAOCarro();
	
	public boolean persistirCarroBanco(Carro carro){
		return daoCarro.isPersistirCarro(carro);
	}
	
	public List<Carro> listarCarros(){
		return daoCarro.buscarListaCarrosBanco("Carro");
	}
	
	public Carro buscarCarroTeste(){
		return (Carro) HibernateUtil.buscar(new Carro(), 3);
	}
	
	public List<Carro> buscarCarroFiltro(Carro carro, Marca marca){
		return daoCarro.buscarCarroBancoFiltro(carro, marca);
	}
	
	public void excluirCarro(Carro carro){
		daoCarro.excluirCarro(carro);
	}
}
