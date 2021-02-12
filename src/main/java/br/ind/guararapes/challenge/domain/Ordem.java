package br.ind.guararapes.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Ordem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Material material;
	private LocalDateTime dataOrdem = LocalDateTime.now();

	public Ordem() {
	}

	public Ordem(Long idMaterial, String nomeMaterial) {
		Material material = new Material();
		material.setId(idMaterial);
		material.setNome(nomeMaterial);

		this.material = material;
	}
}
