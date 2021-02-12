package br.ind.guararapes.challenge.controller.DTO;

import br.ind.guararapes.challenge.domain.Material;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MaterialDTO {
    private Long id;
    private String nome;
    private List<MaterialDTO> materiaisDeProducaoDTO;
    private int quantidadeEmEstoque;

    public MaterialDTO(Material material) {
        this.id = material.getId();
        this.nome = material.getNome();
        this.materiaisDeProducaoDTO = !ehProdutoFinal(material) ? material.getMateriaisDeProducao().stream().map(MaterialDTO::new).collect(Collectors.toList()) : null;
        this.quantidadeEmEstoque = material.getEstoque() != null? material.getEstoque().getQuantidade() : 0;
    }

    public static List<MaterialDTO> converter(List<Material> materiais) {
        return materiais.stream().map(MaterialDTO::new).collect(Collectors.toList());
    }

    public boolean ehProdutoFinal (Material material) {
        return (material.getMateriaisDeProducao() == null || material.getMateriaisDeProducao().isEmpty());
    }
}
