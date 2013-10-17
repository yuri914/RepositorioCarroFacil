package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Cor;
import br.com.cast.persistencia.DAOCor;

@Service
public class CorService implements Serializable {

	private static final long serialVersionUID = 2752214280606761355L;
	
	@Autowired
	private DAOCor daoCor;
	
	public List<Cor> buscarCores(){
		return daoCor.buscarCoresBanco("Cor");
	}
}
