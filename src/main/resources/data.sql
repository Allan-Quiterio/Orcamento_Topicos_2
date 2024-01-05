-- Tabela Lançamentos
INSERT INTO lancamentos (
    id, lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 2, 2, 2, '2024-01-10',
    2, 1, 'Hello Guys', 2, 2,
    1, 2, 1, 2, 2,
    2, '2', 'Contratado aqui', 2, 1.3, 2,
	'05/01/2024 22:29:37', '05/01/2024 22:29:37', 2024
);
INSERT INTO lancamentos (
    id, lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    2, 2, 2, 2, '2024-01-10',
    2, 1, 'Hello Guys', 2, 2,
    1, 2, 1, 2, 2,
    2, '2', 'Contratado aqui', 2, 1.3, 2,
	'05/01/2024 22:29:37', '05/01/2024 22:29:37', 2024
);
INSERT INTO lancamentos (
    id, lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    3, 2, 2, 2, '2024-01-10',
    2, 1, 'Hello Guys', 2, 2,
    1, 2, 1, 2, 2,
    2, '2', 'Contratado aqui', 2, 1.3, 2,
	'05/01/2024 22:29:37', '05/01/2024 22:29:37', 2024
);

-- Tabela Ação
INSERT INTO tb_acao (id, codigo, data_alteracao, data_cadastro, nome)
    VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José da Silva');
INSERT INTO tb_acao (id, codigo, data_alteracao, data_cadastro, nome)
    VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José da Silva');
INSERT INTO tb_acao (id, codigo, data_alteracao, data_cadastro, nome)
    VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José da Silva');

-- Tabela de Elemento Despesa
INSERT INTO tb_elemento_despesa (id, codigo, data_alteracao, data_cadastro, nome )
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Mario da Silva');
INSERT INTO tb_elemento_despesa (id, codigo, data_alteracao, data_cadastro, nome )
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Mario da Silva');
INSERT INTO tb_elemento_despesa (id, codigo, data_alteracao, data_cadastro, nome )
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Mario da Silva');

-- Tabela Fonte de Recursos
INSERT INTO tb_fonte_recurso (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Casimiro da Silva');
INSERT INTO tb_fonte_recurso (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Casimiro da Silva');
INSERT INTO tb_fonte_recurso (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Casimiro da Silva');

-- Tabela Grupo de Despesa
INSERT INTO tb_grupo_despesa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Leonardo Cavalcante');
INSERT INTO tb_grupo_despesa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Leonardo Cavalcante');
INSERT INTO tb_grupo_despesa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Leonardo Cavalcante');

-- Tabela Modalidade de Aplicação
INSERT INTO tb_modalidade_aplicacao (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Cristiano Ronaldo');
INSERT INTO tb_modalidade_aplicacao (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Cristiano Ronaldo');
INSERT INTO tb_modalidade_aplicacao (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Cristiano Ronaldo');

-- Tabela Objetivo Estratégico
INSERT INTO tb_objetivo_estrategico (id, data_alteracao, data_cadastro, nome)
VALUES (1, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Taylor Swift');
INSERT INTO tb_objetivo_estrategico (id, data_alteracao, data_cadastro, nome)
VALUES (2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Taylor Swift');
INSERT INTO tb_objetivo_estrategico (id, data_alteracao, data_cadastro, nome)
VALUES (3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Taylor Swift');

-- Tabela Programa
INSERT INTO tb_programa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Jorge Amado');
INSERT INTO tb_programa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Jorge Amado');
INSERT INTO tb_programa (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Jorge Amado');

-- Tabela Solicitante
INSERT INTO tb_solicitante (id, data_alteracao, data_cadastro, nome)
VALUES (1, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Ivete Sangalo');
INSERT INTO tb_solicitante (id, data_alteracao, data_cadastro, nome)
VALUES (2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Ivete Sangalo');
INSERT INTO tb_solicitante (id, data_alteracao, data_cadastro, nome)
VALUES (3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Ivete Sangalo');

-- Tabela Tipo Lançamento
INSERT INTO tb_tipo_lancamento (id, data_alteracao, data_cadastro, nome)
VALUES (1, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José Alencar');
INSERT INTO tb_tipo_lancamento (id, data_alteracao, data_cadastro, nome)
VALUES (2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José Alencar');
INSERT INTO tb_tipo_lancamento (id, data_alteracao, data_cadastro, nome)
VALUES (3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'José Alencar');

-- Tabela Tipo Transação
INSERT INTO tb_tipo_transacao (id, data_alteracao, data_cadastro, nome)
VALUES (1, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Maria Dolores');
INSERT INTO tb_tipo_transacao (id, data_alteracao, data_cadastro, nome)
VALUES (2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Maria Dolores');
INSERT INTO tb_tipo_transacao (id, data_alteracao, data_cadastro, nome)
VALUES (3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Maria Dolores');

-- Tabela Unidade
INSERT INTO tb_unidade (id, data_alteracao, data_cadastro, nome)
VALUES (1, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Gilberto Gil');
INSERT INTO tb_unidade (id, data_alteracao, data_cadastro, nome)
VALUES (2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Gilberto Gil');
INSERT INTO tb_unidade (id, data_alteracao, data_cadastro, nome)
VALUES (3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Gilberto Gil');

-- Tabela Unidade Orçamentária
INSERT INTO tb_unidade_orcamentaria (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (1, 2, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Josefa Paz');
INSERT INTO tb_unidade_orcamentaria (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (2, 3, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Josefa Paz');
INSERT INTO tb_unidade_orcamentaria (id, codigo, data_alteracao, data_cadastro, nome)
VALUES (3, 4, '05/01/2024 10:01:27', '05/01/2024 10:01:27', 'Josefa Paz');