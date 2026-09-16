package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.eventos.entidades.Palestrante;

@SpringBootTest
public class PalestranteRepositorioTests {

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Test
    public void deveSalvarUmPalestranteNovo() {

        var palestrante = new Palestrante();
        palestrante.setNome("João Teste");
        palestrante.setMiniBio("Palestrante de teste");
        palestrante.setEmail("joao.teste@email.com");

        var palestranteSalvo = palestranteRepositorio.save(palestrante);

        assertNotNull(palestranteSalvo.getId());
    }

    @Test
    public void deveBuscarUmPalestrantePorId() {    

    var palestrante = new Palestrante();
    palestrante.setNome("Carlos Teste");
    palestrante.setMiniBio("Bio aleatória");
    palestrante.setEmail("carlos.teste@email.com");

    var palestranteSalvo = palestranteRepositorio.save(palestrante);

    var palestranteEncontrado = palestranteRepositorio.findById(palestranteSalvo.getId());

    assertTrue(palestranteEncontrado.isPresent());
    assertEquals("Carlos Teste", palestranteEncontrado.get().getNome());
    }

    @Test
    public void deveListarTodosOsPalestrantes() {   

    var palestrante = new Palestrante();
    palestrante.setNome("Palestrante Teste 3");
    palestrante.setMiniBio("Bio de teste");
    palestrante.setEmail("teste3@email.com");

    var palestranteSalvo = palestranteRepositorio.save(palestrante);

    var palestrantes = palestranteRepositorio.findAll();

    assertTrue(
        palestrantes.stream()
            .anyMatch(p -> p.getId().equals(palestranteSalvo.getId()))
    );
    }

    @Test
    public void deveAlterarUmPalestrante() {

    var palestrante = new Palestrante();
    palestrante.setNome("Nome Antigo");
    palestrante.setMiniBio("Bio antiga");
    palestrante.setEmail("antigo@email.com");

    var palestranteSalvo = palestranteRepositorio.save(palestrante);

    palestranteSalvo.setNome("Nome Alterado");
    palestranteSalvo.setMiniBio("Bio alterada");
    palestranteSalvo.setEmail("alterado@email.com");

    palestranteRepositorio.save(palestranteSalvo);

    var palestranteEncontrado = palestranteRepositorio
            .findById(palestranteSalvo.getId())
            .orElseThrow();

    assertEquals("Nome Alterado", palestranteEncontrado.getNome());
    assertEquals("Bio alterada", palestranteEncontrado.getMiniBio());
    assertEquals("alterado@email.com", palestranteEncontrado.getEmail());
    }

    @Test
    public void deveExcluirUmPalestrante() {

    var palestrante = new Palestrante();
    palestrante.setNome("Palestrante para excluir");
    palestrante.setMiniBio("Bio para exclusão");
    palestrante.setEmail("excluir@email.com");

    var palestranteSalvo = palestranteRepositorio.save(palestrante);

    palestranteRepositorio.deleteById(palestranteSalvo.getId());

    var palestranteExiste = palestranteRepositorio.existsById(palestranteSalvo.getId());

    assertFalse(palestranteExiste);
    }
}