package br.ind.guararapes.challenge.controller.form;

import br.ind.guararapes.challenge.domain.Material;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class MaterialForm {
    private Long id;
    @NotNull @NotEmpty
    private String nome;
    private List<MaterialForm> materiaisDeProducaoForm;
    @NotNull
    private EstoqueForm estoqueForm;

    public MaterialForm() {
    }

    public MaterialForm(Long id, String nome, List<MaterialForm> materiaisDeProducaoForm, EstoqueForm estoqueForm) {
        this.id = id;
        this.nome = nome;
        this.materiaisDeProducaoForm = materiaisDeProducaoForm;
        this.estoqueForm = estoqueForm;
    }

    public MaterialForm(Long id) {
        this.id = id;
    }

    public boolean verificarSeEhMateriaPrima() {
        return this.materiaisDeProducaoForm == null || this.materiaisDeProducaoForm.isEmpty();
    }

    public boolean verificarSeHaEstoque() {
        return this.materiaisDeProducaoForm.stream().anyMatch(material -> material.getEstoqueForm().getQuantidade() < 1);
    }

    public List<Material> converteListaMaterialFormParaMaterial(){
        return this.materiaisDeProducaoForm != null ? this.materiaisDeProducaoForm.stream().map(Material::new).collect(Collectors.toList()) : null;
    }
}
