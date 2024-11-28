BEGIN;
-- Inserções para a tabela usuario (não mexi)
INSERT INTO usuario (datahoraatualizacao, datahoracriacao, email, login, senha)
VALUES
    --SENHA: senha1
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', 'lucasdaniel@unitins.br', 'usuario1', 'Csp1B3FE7ExIsmLUCT0FVSjsEviRumTRIAa6xujepRcSSx4iEKX96DaaeJlahnZQJWNSeAcK+wMLHEjK6QEGQw=='),
    --SENHA: senha2
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', 'joaopaulo@gmail.com', 'usuario2', '0hA2bYWKI8y54epeHAaLy6dtROowgFxQWaz1nqQo8/I3RJ/607nbFlzICXKB8LiduecPFH9tcY/GbfewNimxtw==');
    
-- Inserções para a tabela endereco
INSERT INTO endereco (datahoraatualizacao, datahoracriacao, cep, logradouro, numerolote, complemento, localidade, UF)
VALUES
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-906', 'Praça dos Girassóis', 'S/N', 'Esplanada das Secretarias', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-906', 'Praça dos Girassóis', 'S/N', 'Esplanada das Secretarias', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-907', 'Rua 32', '101', 'Apartamento 102', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-908', 'Avenida JK', '200', 'Próximo à Praça', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-909', 'Rua 15', '120', 'Apartamento 203', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-910', 'Rua 45', '150', 'Bloco B', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-911', 'Avenida Tocantins', '300', 'Próximo à praça do relógio', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-912', 'Rua das Palmeiras', '50', 'Casa 10', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-913', 'Rua 40', '102', 'Apto 303', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-914', 'Rua 50', '300', 'Condomínio Solar', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-915', 'Rua 62', '250', 'Loja 5', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-916', 'Avenida Leste', '400', 'Bloco A', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-917', 'Rua das Margaridas', '90', 'Casa 12', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-918', 'Avenida JK', '500', 'Próximo ao mercado central', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-918', 'Avenida JK', '500', 'Próximo ao mercado central', 'Palmas', 'TO');

-- Inserções para a tabela questionariosocial
INSERT INTO questionariosocial (datahoraatualizacao, datahoracriacao, cadastronis, pessoasemcasa, rendafamiliar, condicoesMORADIA) 
VALUES 
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 3, 200.00, 'PESSIMA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 3, 200.00, 'RUIM'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 2, 250.00, 'BOA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 4, 300.00, 'EXCELENTE'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 5, 400.00, 'BOA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 1, 180.00, 'RUIM'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 3, 350.00, 'PESSIMA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 6, 420.00, 'EXCELENTE'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 4, 500.00, 'BOA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 2, 600.00, 'REGULAR'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 5, 550.00, 'PESSIMA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 7, 700.00, 'BOA'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 8, 800.00, 'EXCELENTE'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', false, 8, 800.00, 'EXCELENTE');

-- Inserções para a tabela atleta
INSERT INTO atleta (categoria, datanascimento, dadossociais_id, datahoraatualizacao, datahoracriacao, endereco_id, cpf, nome, sexo, cadastrocompleto, telefone)
	VALUES (0, '1975-05-28', 1, '2024-09-23 14:41:29', '2024-09-23 14:41:29', 1, '58591885287', 'Helena Rodrigues', 'FEMININO', false, '63981590132'),
           (1, '2001-09-27', 2, '2024-09-23 14:41:30', '2024-09-23 14:41:30', 2, '03809323910', 'Marcelo Alves', 'MASCULINO', false, '63992154511'),
           (0, '1999-11-14', 3, '2024-09-23 14:41:31', '2024-09-23 14:41:31', 3, '01457923188', 'Lucas Soares', 'MASCULINO', true, '63992467030'),
           (1, '2003-08-05', 4, '2024-09-23 14:41:32', '2024-09-23 14:41:32', 4, '02159813245', 'Ana Souza', 'FEMININO', true, '63994211788'),
           (1, '2002-12-01', 5, '2024-09-23 14:41:33', '2024-09-23 14:41:33', 5, '03129816412', 'José Oliveira', 'MASCULINO', false, '63991234567'),
           (0, '2005-03-12', 6, '2024-09-23 14:41:34', '2024-09-23 14:41:34', 6, '01549976325', 'Carlos Silva', 'MASCULINO', true, '63998765432'),
           (0, '2000-07-22', 7, '2024-09-23 14:41:35', '2024-09-23 14:41:35', 7, '02950654799', 'Gabriel Almeida', 'MASCULINO', true, '63995123456'),
           (1, '2003-11-11', 8, '2024-09-23 14:41:36', '2024-09-23 14:41:36', 8, '03582649120', 'Roberta Lima', 'FEMININO', true, '63996014851'),
           (1, '1998-06-30', 9, '2024-09-23 14:41:37', '2024-09-23 14:41:37', 9, '01249567348', 'Maria Pereira', 'FEMININO', false, '63994567832'),
           (0, '2006-02-17', 10, '2024-09-23 14:41:38', '2024-09-23 14:41:38', 10, '02467985462', 'Filipe Dias', 'MASCULINO', true, '63984969981');

-- Inserções para a tabela treino
INSERT INTO treino (data, horario, datahoraatualizacao, datahoracriacao, local)
VALUES
    ('2024-11-25', '08:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo A'),
    ('2024-11-26', '09:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo B'),
    ('2024-11-27', '10:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo C'),
    ('2024-11-28', '11:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo D'),
    ('2024-11-29', '12:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo E'),
    ('2024-11-30', '13:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo F'),
    ('2024-12-01', '14:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo G'),
    ('2024-12-02', '15:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo H'),
    ('2024-12-03', '16:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo I'),
    ('2024-12-04', '17:00:00', '2024-09-23 14:41:29', '2024-09-23 14:41:29', 'Campo J');


-- Inserções para a tabela atletas_treino
INSERT INTO atletas_treino (treino_id, atleta_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 6),
    (6, 7),
    (7, 8),
    (8, 9),
    (9, 10),
    (10, 1),
    (1, 3),
    (2, 4),
    (3, 5),
    (4, 6),
    (5, 7),
    (6, 8),
    (7, 9),
    (8, 10),
    (9, 1),
    (10, 2);

INSERT INTO publicacao (usuario_id,conteudo,dataHoraAtualizacao,dataHoraCriacao,dataPublicacao,nomeImagem,titulo)
    VALUES (1, 'O time HANDMAX conquistou o título do Campeonato Estadual de Handebol, após uma performance impressionante ao longo do torneio. A equipe demonstrou habilidade, trabalho em equipe e determinação, superando todas as adversárias e garantindo o primeiro lugar na competição. Durante a final, o HANDMAX enfrentou um time forte e experiente, mas conseguiu impor seu ritmo e dominar o jogo desde o início. A vitória foi resultado de uma estratégia bem elaborada e da excelente atuação dos jogadores, que se destacaram em todas as áreas do campo. O técnico do HANDMAX, João Silva, elogiou a dedicação e o esforço dos atletas, afirmando que "essa conquista é fruto de muita disciplina e trabalho árduo". Ele também destacou a importância da torcida e do apoio da comunidade, que foram fundamentais para motivar a equipe. Com este título, o HANDMAX garantiu uma vaga no Campeonato Nacional de Handebol, onde terá a oportunidade de enfrentar os melhores times do país. A equipe está confiante e animada com a perspectiva de continuar sua jornada de sucesso. A cidade celebra a conquista do HANDMAX com festas e homenagens aos atletas, que são agora heróis locais. A vitória não só traz orgulho à equipe, mas também inspira jovens talentos a se dedicarem ao esporte e a perseguirem seus sonhos.', NOW(), NOW(), NOW(), NULL, 'HANDMAX Campeão do Campeonato Estadual de Handebol');
