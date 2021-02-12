package br.ind.guararapes.challenge.controller;

import java.net.URI;
import java.util.stream.Collectors;

import br.ind.guararapes.challenge.controller.DTO.MaterialDTO;
import br.ind.guararapes.challenge.controller.form.MaterialForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ind.guararapes.challenge.service.MaterialService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping("/material")
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;

	@ApiOperation(value = "Buscar materiais", notes = "Os materiais podem ser Produtos Finais ou Materias Primas, será buscado de acordo com parâmetro opcional 'materiaPrima'")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Exibe o material buscado"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@GetMapping
	public ResponseEntity<?> listar(@RequestParam(required = false) boolean materiaPrima) {
		return materialService.listar(materiaPrima);
	}

	@ApiOperation(value = "Buscar material específico")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Exibe o material buscado"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		return materialService.buscarPorId(id);
	}

	@ApiOperation(value = "Cadastrar novo material")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cadastra novo material"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody @Valid MaterialForm materialForm, UriComponentsBuilder uriComponentsBuilder) {
		MaterialDTO materialDTO = materialService.criar(materialForm.getNome(),
														materialForm.getMateriaisDeProducaoForm(),
														materialForm.getEstoqueForm());

		URI uri = uriComponentsBuilder.path("/material/{id}").buildAndExpand(materialDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(materialDTO);
	}

	@ApiOperation(value = "Atualizar material específico")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Atualiza o material desejado"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid MaterialForm materialForm) throws Exception {
		return materialService.atualizar(id,
										 materialForm.getNome(),
										 materialForm.getMateriaisDeProducaoForm(),
										 materialForm.getEstoqueForm());
	}

	@ApiOperation(value = "Remover material específico")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Remove o material desejado"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		return materialService.remover(id);
	}
}
