package br.edu.unifio.eventos.repositorios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.eventos.entidades.Local;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class LocalRepositorioTests {

    @Autowired
    private LocalRepositorio localRepositorio;

    @Test
    public void deveSalvarUmLocalNovo() {

        var local = new Local();
        local.setNome("Sala Teste");
        local.setEndereco("Rua Exemplo, 123");
        local.setCapacidade(150);

        var localSalvo = localRepositorio.save(local);

        assertNotNull(localSalvo.getId());
    }

    @Test
    public void deveBuscarUmLocalPorId() {

    var local = new Local();
    local.setNome("Sala Teste 2");
    local.setEndereco("Rua Exemplo 2, 456");
    local.setCapacidade(200);

    var localSalvo = localRepositorio.save(local);

    var localEncontrado = localRepositorio.findById(localSalvo.getId());

    assertTrue(localEncontrado.isPresent());
    assertEquals("Sala Teste 2", localEncontrado.get().getNome());
    }

    @Test
    public void deveListarTodosOsLocais() {

    var local = new Local();
    local.setNome("Local Teste 3");
    local.setEndereco("Rua Teste 3, 789");
    local.setCapacidade(300);

    var localSalvo = localRepositorio.save(local);

    var locais = localRepositorio.findAll();

    assertTrue(
        locais.stream()
            .anyMatch(l -> l.getId().equals(localSalvo.getId()))
    );
    }

    @Test
    public void deveAlterarUmLocal() {

    var local = new Local();
    local.setNome("Local Antigo");
    local.setEndereco("Rua Antiga, 100");
    local.setCapacidade(100);

    var localSalvo = localRepositorio.save(local);

    localSalvo.setNome("Local Alterado");
    localSalvo.setEndereco("Rua Nova, 200");
    localSalvo.setCapacidade(250);

    localRepositorio.save(localSalvo);

    var localEncontrado = localRepositorio.findById(localSalvo.getId()).orElseThrow();

    assertEquals("Local Alterado", localEncontrado.getNome());
    assertEquals("Rua Nova, 200", localEncontrado.getEndereco());
    assertEquals(250, localEncontrado.getCapacidade());
    }

    @Test
    public void deveExcluirUmLocal() {

    var local = new Local();
    local.setNome("Local para excluir");
    local.setEndereco("Rua Teste, 999");
    local.setCapacidade(50);

    var localSalvo = localRepositorio.save(local);

    localRepositorio.deleteById(localSalvo.getId());

    var localExiste = localRepositorio.existsById(localSalvo.getId());

    assertFalse(localExiste);
    }
}