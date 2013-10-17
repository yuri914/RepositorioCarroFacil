package br.com.cast.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.model.Ano;
import br.com.cast.persistencia.DaoAno;

@Service
public class AnoService implements Serializable {

	private static final long serialVersionUID = -6962784438422519138L;
	
	@Autowired
	private DaoAno daoAno;

	
	public List<Ano> consultarListaAno(){
		return daoAno.consultarListaAno();
	}
}
