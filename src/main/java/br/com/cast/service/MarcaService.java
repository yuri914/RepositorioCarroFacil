package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Marca;
import br.com.cast.persistencia.DAOMarca;

@Service("marcaService")
public class MarcaService implements Serializable {

	private static final long serialVersionUID = 3207776747138680425L;
	
	@Autowired
	private DAOMarca daoMarca;
	
	public List<Marca> buscarMarcas(){
		return daoMarca.buscarListaMarcasBanco("Marca");
	}
}
