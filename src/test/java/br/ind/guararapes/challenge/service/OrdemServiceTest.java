package br.ind.guararapes.challenge.service;

import br.ind.guararapes.challenge.controller.form.EstoqueForm;
import br.ind.guararapes.challenge.controller.form.MaterialForm;
import br.ind.guararapes.challenge.domain.Estoque;
import br.ind.guararapes.challenge.domain.Material;
import br.ind.guararapes.challenge.domain.Ordem;
import br.ind.guararapes.challenge.repository.MaterialRepository;
import br.ind.guararapes.challenge.repository.OrdemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdemServiceTest {
    private static String nomeMaterial = "Camisa";
    private static Long idMaterial = 5L;
    private static List<Long> idsMaterial = Arrays.asList(1L, 2L);
    private static EstoqueForm estoqueForm = new EstoqueForm(10);

    @Mock
    private OrdemRepository ordemRepository;
    @Mock
    private MaterialService materialService;

    @Mock
    private MaterialRepository materialRepository;

    private OrdemService ordemService;

    private static MaterialForm materiaPrimaTest1 =  new MaterialForm(1L, "linha", null, new EstoqueForm(5));
    private static MaterialForm materiaPrimaTest2 =  new MaterialForm(2L, "tecido", null, new EstoqueForm(10));
    private static List<MaterialForm> listaMateriasPrima = Arrays.asList(materiaPrimaTest1, materiaPrimaTest2);


    @Before
    public void setUp() {
        ordemService = new OrdemService(ordemRepository, materialService);
    }

    @Test
    public void deve_listar_ordens_producao_com_sucesso(){
        ordemService.listar();

        verify(this.ordemRepository, times(1)).findAll();
    }

    @Test
    public void deve_cadastrar_uma_ordem_producao_com_sucesso(){
        UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance();

        MaterialForm produtoFinal = new MaterialForm();
        produtoFinal.setId(10L);
        produtoFinal.setNome("Camisa");
        produtoFinal.setMateriaisDeProducaoForm(listaMateriasPrima);
        produtoFinal.setEstoqueForm(estoqueForm);

        when(materialRepository.save(any(Material.class))).thenReturn(new Material(materiaPrimaTest1));

        ordemService.criar(produtoFinal, uriComponents);

        verify(this.ordemRepository, times(1)).save(any(Ordem.class));
    }
}
