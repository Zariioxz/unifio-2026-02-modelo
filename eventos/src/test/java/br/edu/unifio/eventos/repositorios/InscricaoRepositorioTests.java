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
import br.edu.unifio.eventos.entidades.Inscricao;
import br.edu.unifio.eventos.entidades.Local;
import br.edu.unifio.eventos.entidades.Palestrante;
import br.edu.unifio.eventos.entidades.Participante;

@SpringBootTest
public class InscricaoRepositorioTests {

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private LocalRepositorio localRepositorio;

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    private Evento criarEvento() {

        var categoria = new Categoria();
        categoria.setNome("Categoria Inscrição");
        categoria.setDescricao("Categoria de teste");
        categoria = categoriaRepositorio.save(categoria);

        var local = new Local();
        local.setNome("Local Inscrição");
        local.setEndereco("Rua Teste, 100");
        local.setCapacidade(200);
        local = localRepositorio.save(local);

        var palestrante = new Palestrante();
        palestrante.setNome("Palestrante Inscrição");
        palestrante.setMiniBio("Bio teste");
        palestrante.setEmail("inscricao@email.com");
        palestrante = palestranteRepositorio.save(palestrante);

        var evento = new Evento();
        evento.setNome("Evento Inscrição");
        evento.setDescricao("Evento para teste");
        evento.setDataInicio(LocalDateTime.of(2026, 11, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 11, 10, 22, 0));
        evento.setCapacidade(150);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        return eventoRepositorio.save(evento);
    }

    private Participante criarParticipante() {

        var participante = new Participante();
        participante.setNome("Participante Inscrição");
        participante.setEmail("participante.inscricao@email.com");
        participante.setTelefone("43999999999");

        return participanteRepositorio.save(participante);
    }

    private Inscricao criarInscricao(String status) {

        var inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDateTime.of(2026, 9, 16, 20, 0));
        inscricao.setStatus(status);
        inscricao.setEvento(criarEvento());
        inscricao.setParticipante(criarParticipante());

        return inscricaoRepositorio.save(inscricao);
    }

    @Test
    public void deveSalvarUmaInscricaoNova() {

        var inscricaoSalva = criarInscricao("CONFIRMADA");

        assertNotNull(inscricaoSalva.getId());
    }

    @Test
    public void deveBuscarUmaInscricaoPorId() {

        var inscricaoSalva = criarInscricao("CONFIRMADA");

        var inscricaoEncontrada = inscricaoRepositorio.findById(inscricaoSalva.getId());

        assertTrue(inscricaoEncontrada.isPresent());
        assertEquals("CONFIRMADA", inscricaoEncontrada.get().getStatus());
    }

    @Test
    public void deveListarTodasAsInscricoes() {

        var inscricaoSalva = criarInscricao("PENDENTE");

        var inscricoes = inscricaoRepositorio.findAll();

        assertTrue(
            inscricoes.stream()
                .anyMatch(i -> i.getId().equals(inscricaoSalva.getId()))
        );
    }

    @Test
    public void deveAlterarUmaInscricao() {

        var inscricaoSalva = criarInscricao("PENDENTE");

        inscricaoSalva.setStatus("CONFIRMADA");

        inscricaoRepositorio.save(inscricaoSalva);

        var inscricaoEncontrada = inscricaoRepositorio
                .findById(inscricaoSalva.getId())
                .orElseThrow();

        assertEquals("CONFIRMADA", inscricaoEncontrada.getStatus());
    }

    @Test
    public void deveExcluirUmaInscricao() {

        var inscricaoSalva = criarInscricao("CANCELADA");

        inscricaoRepositorio.deleteById(inscricaoSalva.getId());

        var inscricaoExiste = inscricaoRepositorio.existsById(inscricaoSalva.getId());

        assertFalse(inscricaoExiste);
    }
}