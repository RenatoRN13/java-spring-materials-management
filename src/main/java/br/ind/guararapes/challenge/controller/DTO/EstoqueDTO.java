package br.ind.guararapes.challenge.controller.DTO;

import br.ind.guararapes.challenge.domain.Estoque;
import lombok.Getter;

@Getter
public class EstoqueDTO {
    private Long id;
    private MaterialDTO materialDTO;
    private int quantidade;

    public EstoqueDTO(Estoque estoque) {
        this.id = estoque.getId();
        this.materialDTO = new MaterialDTO(estoque.getMaterial());
        this.quantidade = estoque.getQuantidade();
    }
}
