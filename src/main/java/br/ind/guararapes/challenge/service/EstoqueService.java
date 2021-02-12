package br.ind.guararapes.challenge.service;

import br.ind.guararapes.challenge.domain.Estoque;
import br.ind.guararapes.challenge.domain.Material;
import br.ind.guararapes.challenge.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque criar(int quantindade) {
        Estoque novoEstoque = new Estoque(quantindade);
        estoqueRepository.save(novoEstoque);

        return novoEstoque;
    }
}
