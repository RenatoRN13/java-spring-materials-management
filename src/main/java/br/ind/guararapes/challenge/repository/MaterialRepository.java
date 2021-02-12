package br.ind.guararapes.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ind.guararapes.challenge.domain.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
