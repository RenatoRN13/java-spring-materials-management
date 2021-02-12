package br.ind.guararapes.challenge.service;

import br.ind.guararapes.challenge.controller.form.EstoqueForm;
import br.ind.guararapes.challenge.controller.form.MaterialForm;
import br.ind.guararapes.challenge.domain.Estoque;
import br.ind.guararapes.challenge.domain.Material;
import br.ind.guararapes.challenge.repository.MaterialRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MaterialServiceTest {

    private static MaterialForm materiaPrimaTest1 =  new MaterialForm(1L, "linha", null, new EstoqueForm(5));
    private static MaterialForm materiaPrimaTest2 =  new MaterialForm(2L, "tecido", null, new EstoqueForm(10));
    private static List<MaterialForm> listaMateriasPrima = Arrays.asList(materiaPrimaTest1, materiaPrimaTest2);


    private static String nomeMaterial = "Camisa";
    private static Long idMaterial = 5L;

    private static EstoqueForm estoqueForm = new EstoqueForm(10);

    @Mock
    private MaterialRepository materialRepository;

    private MaterialService materialService;

    @Before
    public void setUp() {
        materialService = new MaterialService(materialRepository);
    }

    @Test
    public void deve_listar_materiais_com_sucesso(){

        materialService.listar(false);

        verify(this.materialRepository, times(1)).findAll();
    }

    @Test
    public void deve_cadastrar_um_material_com_sucesso(){

        materialService.criar(nomeMaterial, listaMateriasPrima, estoqueForm);

        verify(this.materialRepository, times(1)).save(any(Material.class));
    }

    @Test
    public void deve_atualizar_um_material_com_sucesso(){
        Material material = new Material(nomeMaterial, null, new Estoque(0));

        when(materialRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(material));

        materialService.atualizar(idMaterial, nomeMaterial, listaMateriasPrima, estoqueForm);

        verify(this.materialRepository, times(1)).save(any(Material.class));
    }

    @Test
    public void deve_remover_um_material_com_sucesso(){

        materialService.remover(idMaterial);

        verify(this.materialRepository, times(1)).deleteById(idMaterial);
    }

    @Test
    public void deve_buscar_um_unico_material_com_sucesso(){

        materialService.buscarPorId(idMaterial);

        verify(this.materialRepository, times(1)).findById(idMaterial);
    }


}
