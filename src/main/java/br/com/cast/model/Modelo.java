package br.com.cast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_modelo")
public class Modelo implements Serializable {

	private static final long serialVersionUID = 8245768659513608502L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Marca.class)
	@JoinColumn(name="MARCA")
	private Marca marca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
