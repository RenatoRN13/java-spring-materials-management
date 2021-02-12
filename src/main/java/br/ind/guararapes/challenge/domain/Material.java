package br.ind.guararapes.challenge.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import br.ind.guararapes.challenge.controller.form.MaterialForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany
	@JoinTable(name = "materiais_producao", 
				joinColumns = {@JoinColumn(name = "material_id")},
				inverseJoinColumns = {@JoinColumn(name = "material_producao_id")})
	private List<Material> materiaisDeProducao;
	@OneToOne(cascade = CascadeType.ALL)
	private Estoque estoque;

	public Material(){}

	public Material(String nome, List<Material> materiaisDeProducao, Estoque estoque) {
		this.nome = nome;
		this.materiaisDeProducao = materiaisDeProducao;
		this.estoque = estoque;
	}


	public Material(MaterialForm materialForm) {
		this.id = materialForm.getId();
		this.nome = materialForm.getNome();
		this.materiaisDeProducao = materialForm.converteListaMaterialFormParaMaterial();
		this.estoque = materialForm.getEstoqueForm() != null ? materialForm.getEstoqueForm().converteParaEstoque() : null;
	}
}
