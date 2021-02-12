package br.ind.guararapes.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.ind.guararapes.challenge.controller.DTO.MaterialDTO;
import br.ind.guararapes.challenge.controller.form.EstoqueForm;
import br.ind.guararapes.challenge.controller.form.MaterialForm;
import br.ind.guararapes.challenge.domain.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ind.guararapes.challenge.domain.Material;
import br.ind.guararapes.challenge.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	public MaterialService(MaterialRepository materialRepository) {
		this.materialRepository = materialRepository;
	}

	public ResponseEntity<?> listar(boolean materiaPrima) {
		List<Material> materiais = materialRepository.findAll();

		if(materiaPrima){
			List<Material> produtosFinais = materiais.stream().filter(material -> material.getMateriaisDeProducao().size() == 0).collect(Collectors.toList());
			return ResponseEntity.ok().body(MaterialDTO.converter(produtosFinais));
		}

		List<Material> produtosFinais = materiais.stream().filter(material -> material.getMateriaisDeProducao().size() > 0).collect(Collectors.toList());
		return ResponseEntity.ok().body(MaterialDTO.converter(produtosFinais));
	}

	public MaterialDTO criar(String nomeMaterial, List<MaterialForm> materiaisDeProducaoForm, EstoqueForm estoqueForm) {
		List<Material> materiaisDeProducao = materiaisDeProducaoForm.stream().map(Material::new).collect(Collectors.toList());
		Estoque estoque = new Estoque(estoqueForm.getQuantidade());

		Material novoMaterial = new Material(nomeMaterial, materiaisDeProducao, estoque);

		materialRepository.save(novoMaterial);

		return new MaterialDTO(novoMaterial);
	}

	public Optional<Material> findById(Long id) {
		return materialRepository.findById(id);
	}

	public ResponseEntity<?> atualizar(Long id, String nome, List<MaterialForm> materiaisDeProducaoForm, EstoqueForm estoqueForm) {
		try {
			Material materialRepo = findById(id).orElseThrow();
			List<Material> materiaisDeProducao = materiaisDeProducaoForm != null ? materiaisDeProducaoForm.stream().map(Material::new).collect(Collectors.toList()) : null;

			materialRepo.setNome(nome);
			materialRepo.setMateriaisDeProducao(materiaisDeProducao);
			materialRepo.getEstoque().setQuantidade(estoqueForm.getQuantidade());

			materialRepository.save(materialRepo);

			return ResponseEntity.ok(new MaterialDTO(materialRepo));

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro. Não foi possível atualizar o material.");
		}
	}

	public ResponseEntity<?> remover(Long id) {
		try {
			materialRepository.deleteById(id);

			return ResponseEntity.ok().build()	;
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro. Não foi possível excluir o registro");
		}

	}

	public ResponseEntity<?> buscarPorId(Long id) {
		try {
			Material material = findById(id).orElseThrow();

			return ResponseEntity.ok().body(new MaterialDTO(material));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro. Não foi possível encontrar o registro buscado");
		}
	}
}
