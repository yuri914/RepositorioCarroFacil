package br.com.cast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_carro")
@SequenceGenerator(sequenceName = "ID", initialValue = 1, name = "id")
public class Carro implements Serializable {

	private static final long serialVersionUID = -1762793501842163973L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Cor.class)
	@JoinColumn(name = "COR")
	private Cor cor;

	@Column(name="VALOR")
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name="ANO")
	private Ano ano;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Modelo.class)
	@JoinColumn(name = "MODELO")
	private Modelo modelo;

	@Column(name = "AR_CONDICIONADO")
	private boolean arCondicionado;

	@Column(name = "DIRECAO_HIDRAULICA")
	private boolean direcaoHidraulica;

	@Column(name = "VT_ELETRICAS")
	private boolean vtEletrico;
	
	@Transient
	private String arFormatado;
	
	@Transient
	private String direcaoFormatada;
	
	@Transient
	private String vtFormatado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public boolean isArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public boolean isDirecaoHidraulica() {
		return direcaoHidraulica;
	}

	public void setDirecaoHidraulica(boolean direcaoHidraulica) {
		this.direcaoHidraulica = direcaoHidraulica;
	}

	public boolean isVtEletrico() {
		return vtEletrico;
	}

	public void setVtEletrico(boolean vtEletrico) {
		this.vtEletrico = vtEletrico;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getArFormatado() {
		String arFormatado = null;
		if(isArCondicionado())
			arFormatado = "Sim";
		else 
			arFormatado = "Não";
		return arFormatado;
	}

	public String getDirecaoFormatada() {
		String direcaoFormatada = null;
		if(isArCondicionado())
			direcaoFormatada = "Sim";
		else 
			direcaoFormatada = "Não";
		return direcaoFormatada;
	}

	public String getVtFormatado() {
		String vtFormatado = null;
		if(isArCondicionado())
			vtFormatado = "Sim";
		else 
			vtFormatado = "Não";
		return vtFormatado;
	}
	
	@Transient
	public String getDescricaoModelo(){
		return getModelo().getDescricao();
	}
	
	@Transient
	public String getDescricaoCor(){
		return getCor().getDescricao();
	}
	
	@Transient
	public String getDescricaoAno(){
		return getAno().getDescricao();
	}
	
}
