package br.ind.guararapes.challenge.controller;

import br.ind.guararapes.challenge.controller.form.OrdemForm;
import br.ind.guararapes.challenge.service.OrdemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordem")
public class OrdemController {

	@Autowired
	private OrdemService ordemService;

	@ApiOperation(value = "Buscar Ordens de Produção cadastradas")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Busca ordens de produção"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@GetMapping
	public ResponseEntity<?> listar() {
		return ordemService.listar();
	}

	@ApiOperation(value = "Cadastrar nova ordem")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cadastra nova ordem de produção"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Materiais não encontrados")
	})
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody @Valid OrdemForm ordemForm, UriComponentsBuilder uriComponentsBuilder) {
		return ordemService.criar(ordemForm.getMaterialForm(), uriComponentsBuilder);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid MaterialForm materialForm) throws Exception {
//		return ordemService.atualizar(id, materialForm.getNome(), materialForm.getMateraisDeProducao());
//	}


//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> remover(@PathVariable Long id) {
//		return ordemService.remover(id);
//	}
}
