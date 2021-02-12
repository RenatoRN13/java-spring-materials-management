package br.ind.guararapes.challenge.controller.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrdemForm {
    @NotNull
    private MaterialForm materialForm;
}
