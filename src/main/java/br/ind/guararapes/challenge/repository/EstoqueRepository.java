package br.ind.guararapes.challenge.repository;

import br.ind.guararapes.challenge.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Estoque findByMaterialId(Long id);
}
