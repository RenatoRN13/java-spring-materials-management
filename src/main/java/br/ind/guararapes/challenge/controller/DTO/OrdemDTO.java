package br.ind.guararapes.challenge.controller.DTO;

import br.ind.guararapes.challenge.domain.Ordem;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrdemDTO {
    private Long id;
    private String nomeMaterial;
    private LocalDateTime dataOrdem;

    public OrdemDTO(Ordem ordem) {
        this.id = ordem.getId();
        this.nomeMaterial = ordem.getMaterial().getNome();
        this.dataOrdem = ordem.getDataOrdem();
    }

    public static List<OrdemDTO> converter(List<Ordem> ordens) {
        return ordens.stream().map(OrdemDTO::new).collect(Collectors.toList());
    }
}
