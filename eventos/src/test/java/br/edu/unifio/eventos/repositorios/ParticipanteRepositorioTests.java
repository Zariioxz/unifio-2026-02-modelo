package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.eventos.entidades.Participante;

@SpringBootTest
public class ParticipanteRepositorioTests {

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveSalvarUmParticipanteNovo() {

        var participante = new Participante();
        participante.setNome("Participante Teste");
        participante.setEmail("participante@email.com");
        participante.setTelefone("43999999999");

        var participanteSalvo = participanteRepositorio.save(participante);

        assertNotNull(participanteSalvo.getId());
    }

    @Test
public void deveBuscarUmParticipantePorId() {

    var participante = new Participante();
    participante.setNome("Participante Teste 2");
    participante.setEmail("participante2@email.com");
    participante.setTelefone("43988888888");

    var participanteSalvo = participanteRepositorio.save(participante);

    var participanteEncontrado = participanteRepositorio.findById(participanteSalvo.getId());

    assertTrue(participanteEncontrado.isPresent());
    assertEquals("Participante Teste 2", participanteEncontrado.get().getNome());
    }

    @Test
public void deveListarTodosOsParticipantes() {

    var participante = new Participante();
    participante.setNome("Participante Teste 3");
    participante.setEmail("participante3@email.com");
    participante.setTelefone("43977777777");

    var participanteSalvo = participanteRepositorio.save(participante);

    var participantes = participanteRepositorio.findAll();

    assertTrue(
        participantes.stream()
            .anyMatch(p -> p.getId().equals(participanteSalvo.getId()))
    );
}

@Test
public void deveAlterarUmParticipante() {

    var participante = new Participante();
    participante.setNome("Nome Antigo");
    participante.setEmail("antigo@email.com");
    participante.setTelefone("43966666666");

    var participanteSalvo = participanteRepositorio.save(participante);

    participanteSalvo.setNome("Nome Alterado");
    participanteSalvo.setEmail("alterado@email.com");
    participanteSalvo.setTelefone("43955555555");

    participanteRepositorio.save(participanteSalvo);

    var participanteEncontrado = participanteRepositorio
            .findById(participanteSalvo.getId())
            .orElseThrow();

    assertEquals("Nome Alterado", participanteEncontrado.getNome());
    assertEquals("alterado@email.com", participanteEncontrado.getEmail());
    assertEquals("43955555555", participanteEncontrado.getTelefone());
}

@Test
public void deveExcluirUmParticipante() {

    var participante = new Participante();
    participante.setNome("Participante para excluir");
    participante.setEmail("excluir@email.com");
    participante.setTelefone("43944444444");

    var participanteSalvo = participanteRepositorio.save(participante);

    participanteRepositorio.deleteById(participanteSalvo.getId());

    var participanteExiste = participanteRepositorio.existsById(participanteSalvo.getId());

    assertFalse(participanteExiste);
    }
}