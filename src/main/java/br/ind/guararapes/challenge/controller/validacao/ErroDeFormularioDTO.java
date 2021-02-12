package br.ind.guararapes.challenge.controller.validacao;

import lombok.Getter;

@Getter
public class ErroDeFormularioDTO {
    private String campo;
    private String mensagemDeErro;

    public ErroDeFormularioDTO(String campo, String mensagemDeErro) {
        this.campo = campo;
        this.mensagemDeErro = mensagemDeErro;
    }
}
