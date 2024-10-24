-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO usuario (datahoraatualizacao, datahoracriacao, email, login, senha)
VALUES
    --SENHA: senha1
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', 'lucasdaniel@unitins.br', 'usuario1', 'Csp1B3FE7ExIsmLUCT0FVSjsEviRumTRIAa6xujepRcSSx4iEKX96DaaeJlahnZQJWNSeAcK+wMLHEjK6QEGQw=='),
    --SENHA: senha2
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', 'joaopaulo@gmail.com', 'usuario2', '0hA2bYWKI8y54epeHAaLy6dtROowgFxQWaz1nqQo8/I3RJ/607nbFlzICXKB8LiduecPFH9tcY/GbfewNimxtw==');

INSERT INTO endereco (datahoraatualizacao, datahoracriacao, cep, logradouro, numerolote, complemento, localidade, UF)
VALUES
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-906', 'Praça dos Girassóis', 'S/N', 'Esplanada das Secretarias', 'Palmas', 'TO'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', '77001-906', 'Praça dos Girassóis', 'S/N', 'Esplanada das Secretarias', 'Palmas', 'TO');

INSERT INTO questionariosocial (datahoraatualizacao, datahoracriacao, cadastronis, pessoasemcasa, rendafamiliar, condicoesMORADIA) 
VALUES 
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 3, 200.00, 'PRECÁRIAS'),
    ('2024-09-23 14:41:29', '2024-09-23 14:41:29', true, 3, 200.00, 'PRECÁRIAS');


INSERT INTO atleta (categoria, datanascimento, dadossociais_id, datahoraatualizacao, datahoracriacao, endereco_id, cpf, nome, sexo, cadastrocompleto, telefone)
	VALUES (0, '2004-10-20', 1, '2024-09-23 14:41:29', '2024-09-23 14:41:29', 1, '07863820154', 'Álvaro Calebe', 'MASCULINO', false, '63984294658'),
           (1, '2001-09-27', 2, '2024-09-23 14:41:30', '2024-09-23 14:41:30', 2, '03809323910', 'Marcelo Alves', 'MASCULINO', false, '63992154511');