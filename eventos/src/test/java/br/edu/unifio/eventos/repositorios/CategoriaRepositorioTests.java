package br.edu.unifio.eventos.repositorios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.eventos.entidades.Categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveSalvarUmaCategoriaNova() {

        var categoria = new Categoria();
        categoria.setNome("Engenharia de Software");
        categoria.setDescricao("Eventos sobre desenvolvimento de software");

        var categoriaSalva = categoriaRepositorio.save(categoria);

        assertNotNull(categoriaSalva.getId());
    }

    @Test
    public void deveBuscarUmaCategoriaPorId() {

    var categoria = new Categoria();
    categoria.setNome("Banco de Dados");
    categoria.setDescricao("Eventos sobre banco de dados");

    var categoriaSalva = categoriaRepositorio.save(categoria);

    var categoriaEncontrada = categoriaRepositorio.findById(categoriaSalva.getId());

    assertTrue(categoriaEncontrada.isPresent());
    assertEquals("Banco de Dados", categoriaEncontrada.get().getNome());
    }

    @Test
    public void deveListarTodasAsCategorias() {

    var categoria = new Categoria();
    categoria.setNome("Categoria Teste");
    categoria.setDescricao("Descrição qualquer para teste");

    var categoriaSalva = categoriaRepositorio.save(categoria);

    var categorias = categoriaRepositorio.findAll();

    assertTrue(
        categorias.stream()
            .anyMatch(c -> c.getId().equals(categoriaSalva.getId()))
    );
    }

    @Test
    public void deveAlterarUmaCategoria() {

    var categoria = new Categoria();
    categoria.setNome("Categoria Antiga");
    categoria.setDescricao("Descrição antiga");

    var categoriaSalva = categoriaRepositorio.save(categoria);

    categoriaSalva.setNome("Categoria Alterada");
    categoriaSalva.setDescricao("Descrição alterada");

    categoriaRepositorio.save(categoriaSalva);

    var categoriaEncontrada = categoriaRepositorio.findById(categoriaSalva.getId()).orElseThrow();

    assertEquals("Categoria Alterada", categoriaEncontrada.getNome());
    assertEquals("Descrição alterada", categoriaEncontrada.getDescricao());
    }

    @Test
    public void deveExcluirUmaCategoria() {

    var categoria = new Categoria();
    categoria.setNome("Categoria para excluir");
    categoria.setDescricao("Será removida no teste");

    var categoriaSalva = categoriaRepositorio.save(categoria);

    categoriaRepositorio.deleteById(categoriaSalva.getId());

    var categoriaExiste = categoriaRepositorio.existsById(categoriaSalva.getId());

    assertFalse(categoriaExiste);
    }
}