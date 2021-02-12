package br.ind.guararapes.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.ind.guararapes.challenge.controller.form.EstoqueForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "estoque")
	private Material material;
	private int quantidade;

	public Estoque() { }

	public Estoque(int quantidade) {
		this.quantidade = quantidade;
	}
}

