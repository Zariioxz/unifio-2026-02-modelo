INSERT INTO categoria (nome, descricao) VALUES ('Tecnologia', 'Eventos da área de tecnologia');
INSERT INTO categoria (nome, descricao) VALUES ('Administração', 'Eventos sobre empresas e gestão');
INSERT INTO categoria (nome, descricao) VALUES ('Educação', 'Eventos voltados para ensino');
INSERT INTO categoria (nome, descricao) VALUES ('Saúde', 'Eventos relacionados à saúde');
INSERT INTO categoria (nome, descricao) VALUES ('Comunicação', 'Eventos sobre comunicação e mídia');

INSERT INTO local (nome, endereco, capacidade) VALUES ('Auditório 1', 'Rua Paraná, 150', 200);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Sala 3', 'Rua das Flores, 80', 80);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Centro de Eventos', 'Av. Brasil, 420', 400);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Auditório da Faculdade', 'Rua José Bonifácio, 300', 250);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Sala de Reuniões', 'Av. Central, 90', 60);

INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Marcos Oliveira', 'Trabalha com desenvolvimento de sistemas', 'marcos.oliveira@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Juliana Souza', 'Atua na área de administração', 'juliana.souza@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Ricardo Lima', 'Professor da área de educação', 'ricardo.lima@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Fernanda Alves', 'Profissional da área da saúde', 'fernanda.alves@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Paulo Mendes', 'Trabalha com comunicação e marketing', 'paulo.mendes@email.com');

INSERT INTO participante (nome, email, telefone) VALUES ('Lucas Martins', 'lucas.martins@email.com', '43999881111');
INSERT INTO participante (nome, email, telefone) VALUES ('Ana Paula', 'ana.paula@email.com', '43999772222');
INSERT INTO participante (nome, email, telefone) VALUES ('Gabriel Rocha', 'gabriel.rocha@email.com', '43999663333');
INSERT INTO participante (nome, email, telefone) VALUES ('Camila Ferreira', 'camila.ferreira@email.com', '43999554444');
INSERT INTO participante (nome, email, telefone) VALUES ('Bruno Santos', 'bruno.santos@email.com', '43999445555');

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Tecnologia e Sistemas', 'Palestra sobre tecnologia e sistemas', '2026-10-08 19:00:00', '2026-10-08 21:30:00', 200, 'ATIVO', 1, 1, 1);
INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Gestão de Empresas', 'Evento sobre administração de empresas', '2026-10-15 19:00:00', '2026-10-15 21:00:00', 80, 'ATIVO', 2, 2, 2);
INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Educação Atual', 'Discussão sobre educação e ensino', '2026-10-22 18:30:00', '2026-10-22 21:00:00', 250, 'ATIVO', 3, 4, 3);
INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Saúde e Bem-estar', 'Palestra sobre cuidados com a saúde', '2026-11-05 19:00:00', '2026-11-05 21:30:00', 400, 'ATIVO', 4, 3, 4);
INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES ('Comunicação no Trabalho', 'Evento sobre comunicação profissional', '2026-11-12 19:00:00', '2026-11-12 21:00:00', 60, 'ATIVO', 5, 5, 5);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-10 14:00:00', 'CONFIRMADA', 1, 1);
INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-11 09:30:00', 'CONFIRMADA', 2, 2);
INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-12 16:20:00', 'CONFIRMADA', 3, 3);
INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-13 11:10:00', 'CONFIRMADA', 4, 4);
INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id) VALUES ('2026-09-14 15:40:00', 'CONFIRMADA', 5, 5);