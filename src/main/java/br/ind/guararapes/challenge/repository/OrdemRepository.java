package br.ind.guararapes.challenge.repository;

import br.ind.guararapes.challenge.domain.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemRepository extends JpaRepository<Ordem, Long> {
}
