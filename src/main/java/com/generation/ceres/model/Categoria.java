package com.generation.ceres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="tb_categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="O campo Nome não pode estar em branco")
	private String nome;
	@NotBlank(message="O campo Produtor não pode estar em branco")
	private String produtor;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProdutor() {
		return produtor;
	}
	public void setProdutor(String produtor) {
		this.produtor = produtor;
	}
	
	
}

