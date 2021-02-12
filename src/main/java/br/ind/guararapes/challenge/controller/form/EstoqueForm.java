package br.ind.guararapes.challenge.controller.form;

import br.ind.guararapes.challenge.domain.Estoque;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EstoqueForm {
    private Long id;
    @NotNull @NotEmpty
    private int quantidade;

    public EstoqueForm() { }

    public EstoqueForm(int quantidade) {
        this.quantidade = quantidade;
    }

    public Estoque converteParaEstoque(){
        Estoque estoque = new Estoque();
        estoque.setId(this.id);
        estoque.setQuantidade(this.quantidade);

        return estoque;
    }
}


