package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.eventos.entidades.Categoria;
import br.edu.unifio.eventos.entidades.Evento;
import br.edu.unifio.eventos.entidades.Local;
import br.edu.unifio.eventos.entidades.Palestrante;

@SpringBootTest
public class EventoRepositorioTests {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private LocalRepositorio localRepositorio;

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    private Evento criarEvento(String nome) {

        var categoria = new Categoria();
        categoria.setNome("Categoria Evento");
        categoria.setDescricao("Categoria de teste");
        categoria = categoriaRepositorio.save(categoria);

        var local = new Local();
        local.setNome("Local Evento");
        local.setEndereco("Rua Evento, 100");
        local.setCapacidade(300);
        local = localRepositorio.save(local);

        var palestrante = new Palestrante();
        palestrante.setNome("Palestrante Evento");
        palestrante.setMiniBio("Bio teste");
        palestrante.setEmail("evento@email.com");
        palestrante = palestranteRepositorio.save(palestrante);

        var evento = new Evento();
        evento.setNome(nome);
        evento.setDescricao("Evento de teste");
        evento.setDataInicio(LocalDateTime.of(2026, 10, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 10, 10, 22, 0));
        evento.setCapacidade(200);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        return eventoRepositorio.save(evento);
    }

    @Test
    public void deveSalvarUmEventoNovo() {

        var eventoSalvo = criarEvento("Evento Teste");

        assertNotNull(eventoSalvo.getId());
    }

    @Test
    public void deveBuscarUmEventoPorId() {

        var eventoSalvo = criarEvento("Evento Teste 2");

        var eventoEncontrado = eventoRepositorio.findById(eventoSalvo.getId());

        assertTrue(eventoEncontrado.isPresent());
        assertEquals("Evento Teste 2", eventoEncontrado.get().getNome());
    }

    @Test
    public void deveListarTodosOsEventos() {

        var eventoSalvo = criarEvento("Evento Teste 3");

        var eventos = eventoRepositorio.findAll();

        assertTrue(
            eventos.stream()
                .anyMatch(e -> e.getId().equals(eventoSalvo.getId()))
        );
    }

    @Test
    public void deveAlterarUmEvento() {

        var eventoSalvo = criarEvento("Evento Antigo");

        eventoSalvo.setNome("Evento Alterado");
        eventoSalvo.setDescricao("Descrição alterada");
        eventoSalvo.setCapacidade(500);
        eventoSalvo.setStatus("FINALIZADO");

        eventoRepositorio.save(eventoSalvo);

        var eventoEncontrado = eventoRepositorio
                .findById(eventoSalvo.getId())
                .orElseThrow();

        assertEquals("Evento Alterado", eventoEncontrado.getNome());
        assertEquals("Descrição alterada", eventoEncontrado.getDescricao());
        assertEquals(500, eventoEncontrado.getCapacidade());
        assertEquals("FINALIZADO", eventoEncontrado.getStatus());
    }

    @Test
    public void deveExcluirUmEvento() {

        var eventoSalvo = criarEvento("Evento para excluir");

        eventoRepositorio.deleteById(eventoSalvo.getId());

        var eventoExiste = eventoRepositorio.existsById(eventoSalvo.getId());

        assertFalse(eventoExiste);
    }
}