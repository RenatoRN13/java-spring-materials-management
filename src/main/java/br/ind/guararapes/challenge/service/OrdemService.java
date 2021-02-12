package br.ind.guararapes.challenge.service;

import br.ind.guararapes.challenge.controller.DTO.OrdemDTO;
import br.ind.guararapes.challenge.controller.form.EstoqueForm;
import br.ind.guararapes.challenge.controller.form.MaterialForm;
import br.ind.guararapes.challenge.domain.Material;
import br.ind.guararapes.challenge.domain.Ordem;
import br.ind.guararapes.challenge.repository.OrdemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OrdemService {
    @Autowired
    private OrdemRepository ordemRepository;

    @Autowired
    private MaterialService materialService;

    public OrdemService(OrdemRepository ordemRepository, MaterialService materialService) {
        this.ordemRepository = ordemRepository;
        this.materialService = materialService;
    }

    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(OrdemDTO.converter(ordemRepository.findAll()));
    }

    public ResponseEntity<?> criar(MaterialForm materialForm, UriComponentsBuilder uriComponentsBuilder) {
        if(materialForm.verificarSeEhMateriaPrima()){
            return ResponseEntity.badRequest().body("Não é possível gerar ordem de produção para matéria-prima.");
        }

        if(materialForm.verificarSeHaEstoque()){
            return ResponseEntity.badRequest().body("Não há matéria-prima suficiente em estoque para gerar o produto.");
        }

        Ordem novaOrdem = new Ordem(materialForm.getId(), materialForm.getNome());
        ordemRepository.save(novaOrdem);

        materialForm.getEstoqueForm().setQuantidade(materialForm.getEstoqueForm().getQuantidade() + 1);
        materialForm.getMateriaisDeProducaoForm().forEach(material -> {
            material.getEstoqueForm().setQuantidade(material.getEstoqueForm().getQuantidade() - 1);
            materialService.atualizar(material.getId(), material.getNome(), null, material.getEstoqueForm());
        });

//        for(int i=0; i<materialForm.getMateriaisDeProducaoForm().size(); i++){
//            materialForm.getMateriaisDeProducaoForm().get(i).getEstoqueForm().setQuantidade(materialForm.getMateriaisDeProducaoForm().get(i).getEstoqueForm().getQuantidade() - 1);
//            materialService.atualizar(materialForm.getMateriaisDeProducaoForm().get(0).getId(), materialForm.getMateriaisDeProducaoForm().get(0).getNome(), null, materialForm.getMateriaisDeProducaoForm().get(0).getEstoqueForm());
//        }

        materialService.atualizar(materialForm.getId(), materialForm.getNome(), materialForm.getMateriaisDeProducaoForm(), materialForm.getEstoqueForm());

        URI uri = uriComponentsBuilder.path("/material/{id}").buildAndExpand(novaOrdem.getId()).toUri();
        return ResponseEntity.created(uri).body(novaOrdem);
    }

//    public Optional<Material> findById(Long id) {
//        return ordemRepository.findById(id);
//    }
//
//    public ResponseEntity<?> atualizar(Long id, String nome, List<Material> materaisDeProducao) {
//        try {
//            Material materialRepo = findById(id).orElseThrow();
//
//
//            materialRepo.setNome(nome);
//            materialRepo.setMateraisDeProducao(materaisDeProducao);
//
//            ordemRepository.save(materialRepo);
//
//            return ResponseEntity.ok(new OrdemDTO(materialRepo));
//
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro. Não foi possível atualizar o material.");
//        }
//    }
//
//    public ResponseEntity<?> remover(Long id) {
//        try {
//            ordemRepository.deleteById(id);
//
//            return ResponseEntity.ok().body("Registro deletado com sucesso");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro. Não foi possível excluir o registro");
//        }
//
//    }
}
