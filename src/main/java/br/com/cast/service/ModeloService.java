package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;
import br.com.cast.persistencia.DAOModelo;

@Service("modeloService")
public class ModeloService implements Serializable {

	private static final long serialVersionUID = -8977435873395635362L;
	
	@Autowired
	private DAOModelo daoModelo;
	
	public List<Modelo> buscarModelos(Marca marca){
		return daoModelo.buscarListaModelosBanco(marca);
	}
}
