CREATE TABLE tb_unidade (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_Unidade PRIMARY KEY (ID)
);

CREATE TABLE tb_tipo_lancamento (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_TipoLancamento PRIMARY KEY (ID)
);

CREATE TABLE tb_solicitante (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_Solicitante PRIMARY KEY (ID)
);

CREATE TABLE tb_objetivo_estrategico (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_ObjetivoEstrategico PRIMARY KEY (ID)
);

CREATE TABLE tb_unidade_orcamentaria (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_UnidadeOrcamentaria PRIMARY KEY (ID)
);

CREATE TABLE tb_programa (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_Programa PRIMARY KEY (ID)
);

CREATE TABLE tb_fonte_recurso (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_FonteRecurso PRIMARY KEY (ID)
);

CREATE TABLE tb_acao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_Acao PRIMARY KEY (ID)
);

CREATE TABLE tb_grupo_despesa (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_GrupoDespesa PRIMARY KEY (ID)
);

CREATE TABLE tb_modalidade_aplicacao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_ModalidadeAplicacao PRIMARY KEY (ID)
);

CREATE TABLE tb_elemento_despesa (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  codigo INT NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_ElementoDespesa PRIMARY KEY (ID)
);

CREATE TABLE tb_tipo_transacao (
  ID int AUTO_INCREMENT(1,1) NOT NULL,
  Nome varchar(255) NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255) NULL,
  CONSTRAINT PK_TipoTransacao PRIMARY KEY (ID)
);

CREATE TABLE Lancamentos (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  lancamento_invalido BIT NOT NULL,
  numero_lancamento INT,
  id_tipo_lancamento INT NOT NULL,
  data_lancamento DATE NOT NULL,
  id_lancamento_pai INT,
  id_unidade INT NOT NULL,
  Descricao VARCHAR(255) NOT NULL,
  id_unidade_orcamentaria INT NOT NULL,
  id_programa INT NOT NULL,
  id_acao INT NOT NULL,
  id_fonte_recurso INT NOT NULL,
  id_grupo_despesa INT NOT NULL,
  id_modalidade_aplicacao INT NOT NULL,
  id_elemento_despesa INT NOT NULL,
  id_solicitante INT,
  GED CHAR(27),
  Contratado VARCHAR(255),
  id_objetivo_estrategico INT,
  Valor REAL NOT NULL,
  id_tipo_transacao INT NOT NULL,
  data_cadastro VARCHAR(255) NOT NULL,
  data_alteracao VARCHAR(255),
  ano_orcamento SMALLINT NOT NULL,
  CONSTRAINT PK_Lancamentos PRIMARY KEY (ID),
  CONSTRAINT fk_Lancamentos_TipoLancamento FOREIGN KEY (id_tipo_lancamento) REFERENCES tb_tipo_lancamento (ID),
  CONSTRAINT fk_Lancamentos_Unidade FOREIGN KEY (id_unidade) REFERENCES tb_unidade (ID),
  CONSTRAINT fk_Lancamentos_UnidadeOrcamentaria FOREIGN KEY (id_unidade_orcamentaria) REFERENCES tb_unidade_orcamentaria (ID),
  CONSTRAINT fk_Lancamentos_ElementoDespesa FOREIGN KEY (id_elemento_despesa) REFERENCES tb_elemento_despesa (ID),
  CONSTRAINT fk_Lancamentos_Acao FOREIGN KEY (id_acao) REFERENCES tb_acao (ID),
  CONSTRAINT fk_Lancamentos_Programa FOREIGN KEY (id_programa) REFERENCES tb_programa (ID),
  CONSTRAINT fk_Lancamentos_Solicitante FOREIGN KEY (id_solicitante) REFERENCES tb_solicitante (ID),
  CONSTRAINT fk_Lancamentos_ObjetivoEstrategico FOREIGN KEY (id_objetivo_estrategico) REFERENCES tb_objetivo_estrategico (ID),
  CONSTRAINT fk_Lancamentos_GrupoDespesa FOREIGN KEY (id_grupo_despesa) REFERENCES tb_grupo_despesa (ID),
  CONSTRAINT fk_Lancamentos_ModalidadeAplicacao FOREIGN KEY (id_modalidade_aplicacao) REFERENCES tb_modalidade_aplicacao (ID),
  CONSTRAINT fk_Lancamentos_TipoTransacao FOREIGN KEY (id_tipo_transacao) REFERENCES tb_tipo_transacao (ID),
  CONSTRAINT fk_Lancamentos_FonteRecurso FOREIGN KEY (id_fonte_recurso) REFERENCES tb_fonte_recurso (ID),
  CONSTRAINT fk_Lancamentos_Lancamentos FOREIGN KEY (id_lancamento_pai) REFERENCES Lancamentos (ID)
);


-- Tabela Ação
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (47, '05/01/2024 10:11:47', '05/01/2024 10:01:27', 'Modernização da Infraestrutura Tecnológica');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (83, '05/01/2024 11:34:12', '03/01/2024 06:44:35', 'Pagamento de Pessoal');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (87, '12/12/2023 09:45:21', '06/03/2023 11:34:12', 'Adequação da Infraestrutura Física');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (88, '05/09/2023 02:37:53', '22/07/2023 18:56:45', 'Auxílios para Membros e Servidores');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (89, '30/10/2023 14:19:14', '14/04/2023 04:28:29', 'Operacionalização das Atividades');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (91, '17/08/2023 22:10:07', '01/06/2023 03:21:26', 'Capacitação de Recursos Humanos');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (114, '11/11/2023 12:42:50', '03/05/2023 07:43:38', 'Ações Institucionais');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (115, '18/03/2023 01:45:02', '09/01/2023 19:44:15', 'Implementação da Política Institucional');
INSERT INTO tb_acao (codigo, data_alteracao, data_cadastro, nome)
    VALUES (777, '07/12/2023 20:47:01', '23/06/2023 08:46:29', 'Reconstituição de Bens e Direitos Difusos Lesados');

-- Tabela de Elemento Despesa
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (7, '26/09/2023 06:48:09', '20/07/2023 05:49:33', 'Contribuição a Entidades Fechadas de Previdência');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (8, '02/10/2023 11:51:16', '15/02/2023 14:50:25', 'Outros Benefícios Assistenciais');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (9, '28/11/2023 21:52:43', '04/08/2023 16:53:20', 'Salário Família');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (11, '09/05/2023 23:55:12', '19/01/2023 01:54:44', 'Vencimentos e Vantagens Fixas – Pessoal Civil');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (13, '21/08/2023 10:57:04', '08/07/2023 07:56:30', 'Obrigações Patronais');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (14, '13/04/2023 15:58:21', '25/01/2023 00:59:33', 'Diárias-civil ');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (16, '29/06/2023 17:01:07', '16/02/2023 22:00:14', 'Outras Despesa Variáveis – Pessoal Civil');
INSERT INTO tb_elemento_despesa (codigo, data_alteracao, data_cadastro, nome )
VALUES (30, '01/12/2023 10:03:19', '05/04/2023 09:02:22', 'Material de Consumo');

-- Tabela Fonte de Recursos
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1500, '11/10/2023 12:04:05', '17/09/2023 22:05:30', 'Recursos do Tesouro do Estado');
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1501, '09/08/2023 15:07:08', '13/01/2023 12:06:45', 'Outros Recursos não Vinculados');
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1700, '07/05/2023 20:09:02', '14/03/2023 13:08:16', 'Recursos de Convênio - Governo Federal');
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1703, '30/11/2023 00:10:23', '23/02/2023 21:11:38', 'Recursos de Convênio - Privado');
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1755, '01/07/2023 23:12:11', '19/04/2023 09:13:26', 'Receita de Alienação de Bens');
INSERT INTO tb_fonte_recurso (codigo, data_alteracao, data_cadastro, nome)
VALUES (1759, '28/08/2023 11:14:15', '25/06/2023 08:15:37', 'Recursos Próprios (Fundos)');

-- Tabela Grupo de Despesa
INSERT INTO tb_grupo_despesa (codigo, data_alteracao, data_cadastro, nome)
VALUES (3, '12/09/2023 12:17:30', '04/03/2023 00:16:05', 'Pessoal');
INSERT INTO tb_grupo_despesa (codigo, data_alteracao, data_cadastro, nome)
VALUES (4, '10/10/2023 19:18:24', '06/08/2023 09:19:41', 'Outras Despesas Correntes');
INSERT INTO tb_grupo_despesa (codigo, data_alteracao, data_cadastro, nome)
VALUES (5, '19/11/2023 14:20:19', '02/03/2023 02:21:36', 'Investimentos');

-- Tabela Modalidade de Aplicação
INSERT INTO tb_modalidade_aplicacao (codigo, data_alteracao, data_cadastro, nome)
VALUES (30, '21/06/2023 13:22:07', '05/01/2023 18:23:32', 'Transferências a Estados e ao Distrito Federal');
INSERT INTO tb_modalidade_aplicacao (codigo, data_alteracao, data_cadastro, nome)
VALUES (40, '24/11/2023 03:25:19', '16/04/2023 21:24:47', 'Transferências a Municípios');
INSERT INTO tb_modalidade_aplicacao (codigo, data_alteracao, data_cadastro, nome)
VALUES (50, '09/09/2023 06:27:01', '20/02/2023 14:26:35', 'Transferências a Instituições Privadas sem Fins Lucrativos');
INSERT INTO tb_modalidade_aplicacao (codigo, data_alteracao, data_cadastro, nome)
VALUES (90, '18/12/2023 16:29:02', '03/07/2023 08:28:19', 'Aplicação Direta');
INSERT INTO tb_modalidade_aplicacao (codigo, data_alteracao, data_cadastro, nome)
VALUES (91, '26/10/2023 03:30:28', '01/08/2023 12:31:23', 'Aplicação Indireta');

-- Tabela Objetivo Estratégico
INSERT INTO tb_objetivo_estrategico (data_alteracao, data_cadastro, nome)
VALUES ('14/11/2023 03:33:31', '12/03/2023 22:32:11', 'Incrementar o diálogo e a atuação conjunta das demais instituições');
INSERT INTO tb_objetivo_estrategico (data_alteracao, data_cadastro, nome)
VALUES ('26/04/2023 04:35:19', '19/02/2023 17:34:52', 'Fortalecer as redes de atendimento a grupos vulneráveis junto aos Órgãos Públicos');
INSERT INTO tb_objetivo_estrategico (data_alteracao, data_cadastro, nome)
VALUES ('03/10/2023 06:36:45', '10/07/2023 23:37:22', 'Atuar na universalização do acesso à educação e à saúde com a prestação de serviços de qualidade');
INSERT INTO tb_objetivo_estrategico (data_alteracao, data_cadastro, nome)
VALUES ('26/04/2023 04:35:19', '19/02/2023 17:34:52', 'Aprimorar as atividades de combate à corrupção, defesa do patrimônio público e fiscalização do terceiro setor');
INSERT INTO tb_objetivo_estrategico (data_alteracao, data_cadastro, nome)
VALUES ('30/04/2023 09:39:56', '08/03/2023 11:38:38', 'Intensificar o combate à criminalidade e o efetivo controle externo da atividade policial');

-- Tabela Programa
INSERT INTO tb_programa (codigo, data_alteracao, data_cadastro, nome)
VALUES (4, '24/09/2023 20:40:43', '28/05/2023 12:41:22', 'Defesa da Ordem Jurídica e Social');
INSERT INTO tb_programa (codigo, data_alteracao, data_cadastro, nome)
VALUES (31, '09/11/2023 04:42:37', '15/08/2023 08:43:51', 'Gestão e Manutenção da Instituição');

-- Tabela Solicitante
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('04/06/2023 05:45:48', '11/01/2023 14:44:27', 'Diretoria Administrativa');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('06/12/2023 17:46:03', '13/09/2023 06:47:25', 'Diretoria de Recursos Humanos');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('10/11/2023 12:48:08', '24/03/2023 14:49:18', 'Diretoria de Tecnologia da Informação e Comunicação');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('29/12/2023 09:51:32', '23/07/2023 21:50:44', 'Diretoria de Gestão Estratégica e Orçamentária');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('05/10/2023 03:53:06', '09/02/2023 21:52:45', 'Divisão de Auditoria Interna');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('20/11/2023 15:54:29', '17/01/2023 03:55:37', 'Ouvidoria');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('25/08/2023 10:56:03', '02/04/2023 12:57:28', 'Procuradoria-Geral de Justiça');
INSERT INTO tb_solicitante (data_alteracao, data_cadastro, nome)
VALUES ('06/06/2023 21:59:15', '12/02/2023 19:58:43', 'Coordenadoria Recursal');

-- Tabela Tipo Lançamento
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('09/12/2023 01:00:25', '07/03/2023 08:01:43', 'Proposta Inicial da Unidade');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('13/10/2023 18:02:12', '01/05/2023 06:03:30', 'Aprovação do Colégio de Procuradores de Justiça');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('19/11/2023 11:05:37', '10/01/2023 19:04:22', 'Aprovação do Governo (Cota Orçamentária)');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('14/09/2023 10:07:24', '03/06/2023 23:06:08', 'Alteração da Revisão da Proposta Inicial da Unidade');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('17/08/2023 09:15:19', '08/04/2023 18:14:45', 'Remanejamento');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('22/06/2023 20:16:34', '14/01/2023 02:17:42', 'Crédito Extraordinário Saída');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('13/04/2023 15:18:19', '06/02/2023 04:19:43', 'Apostilamento');
INSERT INTO tb_tipo_lancamento (data_alteracao, data_cadastro, nome)
VALUES ('07/12/2023 14:21:36', '18/07/2023 01:20:22', 'Despesa Não Prevista');

-- Tabela Tipo Transação
INSERT INTO tb_tipo_transacao (data_alteracao, data_cadastro, nome)
VALUES ('13/04/2023 15:18:19', '06/02/2023 04:19:43', 'Conta Corrente');
INSERT INTO tb_tipo_transacao (data_alteracao, data_cadastro, nome)
VALUES ('07/12/2023 14:21:36', '18/07/2023 01:20:22', 'Conta Poupança');

-- Tabela Unidade
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('09/11/2023 19:23:30', '20/09/2023 00:22:54', 'Centro de Apoio Operacional Infância e Adolescência');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('02/08/2023 14:25:17', '26/01/2023 08:24:49', 'Centro de Apoio Operacional Meio Ambiente');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('11/05/2023 22:27:09', '09/03/2023 20:26:35', 'Centro de Apoio Operacional Mulher');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('21/10/2023 09:28:23', '08/02/2023 19:29:45', 'Centro de Apoio Operacional Patrimônio Público');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('14/11/2023 03:31:38', '28/04/2023 01:30:12', 'Centro de Apoio Operacional Saúde');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('09/06/2023 18:32:26', '23/03/2023 03:33:45', 'Centro de Apoio Operacional Segurança Pública');
INSERT INTO tb_unidade (data_alteracao, data_cadastro, nome)
VALUES ('20/09/2023 04:35:29', '18/05/2023 01:34:17', 'Centro de Apoio Operacional Atividade Criminal');

-- Tabela Unidade Orçamentária
INSERT INTO tb_unidade_orcamentaria (codigo, data_alteracao, data_cadastro, nome)
VALUES (11101, '13/11/2023 21:36:48', '19/07/2023 05:37:05', 'Procuradoria-Geral de Justiça');
INSERT INTO tb_unidade_orcamentaria (codigo, data_alteracao, data_cadastro, nome)
VALUES (11402, '08/12/2023 13:38:23', '17/03/2023 05:39:02', 'Fundo para Reconstituição de Bens Lesados');
INSERT INTO tb_unidade_orcamentaria (codigo, data_alteracao, data_cadastro, nome)
VALUES (11401, '29/08/2023 15:40:19', '15/06/2023 16:41:37', 'Fundo Especial da Instituição');

-- Tabela Lançamentos
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    0, 1, 4, '2023-12-28',
    null, 5, 'Auxílio-Creche', 2, 1,
    3, 6, 3, 2, 7,
    4, '20.27.0066.0000137/2023-02', 'Josefa Maia dos Santos', 1, 89.346, 2,
	'05/12/2023 22:29:37', '05/12/2023 13:42:57', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 2, 7, '2023-07-15',
    1, 3, 'Auxílio-Saúde de Membros e Servidores - Retroativo', 3, 2,
    9, 3, 2, 5, 3,
    1, '20.27.0063.0000188/2023-02', 'Alírio Gonçalves Mota', 3, 7.896, 1,
	'29/06/2023 14:39:12', '28/06/2023 13:55:12', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 3, 2, '2023-08-18',
    null, 2, 'Auxílio-Alimentação de Membros e Servidores Ativos', 1, 2,
    4, 5, 1, 4, 8,
    2, '20.27.0033.4400892/2023-02', 'Jorge Batista', 1, 22.453, 1,
	'16/08/2023 12:31:52', '15/08/2023 09:45:28', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    0, 4, 6, '2024-01-08',
    null, 6, 'Auxílio-Interiorização de Servidores Ativos', 3, 1,
    5, 2, 3, 2, 4,
    1, '22.37.0063.4420881/2023-02', 'Marcos Góis da Silva', 4, 15.083, 2,
	'05/01/2024 08:42:12', '05/01/2024 08:02:31', 2024
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    0, 5, 2, '2023-11-20',
    null, 3, 'Auxílio-Saúde de Membros e Servidores', 1, 1,
    9, 1, 2, 4, 8,
    3, '21.44.0072.3820887/2023-02', 'Flávia Neves Mota', 1, 7.442, 2,
	'19/11/2023 10:25:21', '19/11/2023 09:51:18', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 6, 3, '2023-05-02',
    null, 7, 'Auxílio-Funeral', 3, 1,
    6, 3, 3, 5, 2,
    6, '32.44.0044.3820694/2023-02', 'Rafaela Matos Santos', 4, 3.292, 1,
	'01/05/2023 11:51:03', '01/05/2023 08:44:33', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 7, 3, '2023-09-21',
    null, 3, 'Auxílio-Creche', 1, 2,
    4, 2, 1, 2, 7,
    3, '11.44.0075.3820991/2023-02', 'Micael Mota Gonçalo', 5, 2.319, 2,
	'21/09/2023 17:49:53', '21/09/2023 09:34:18', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    0, 8, 7, '2023-10-13',
    null, 6, 'Auxílio-Alimentação de Membros e Servidores Ativos', 3, 1,
    8, 4, 3, 4, 5,
    6, '24.44.0093.3820742/2023-02', 'Bernardo Silva Rodrigues', 4, 5.881, 1,
	'13/10/2023 16:22:51', '13/10/2023 12:39:21', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 9, 5, '2023-02-01',
    null, 2, 'Auxílio-Saúde de Membros e Servidores', 2, 1,
    5, 6, 1, 2, 3,
    7, '89.21.0093.3820232/2023-02', 'Yasmin Souza Cruz', 2, 9.754, 2,
	'01/02/2023 16:51:49', '01/02/2023 15:21:29', 2023
);
INSERT INTO lancamentos (
    lancamento_invalido, numero_lancamento, id_tipo_lancamento, data_lancamento,
    id_lancamento_pai, id_unidade, descricao, id_unidade_orcamentaria, id_programa,
    id_acao, id_fonte_recurso, id_grupo_despesa, id_modalidade_aplicacao, id_elemento_despesa,
    id_solicitante, ged, contratado, id_objetivo_estrategico, valor, id_tipo_transacao,
    data_cadastro, data_alteracao, ano_orcamento
) VALUES (
    1, 10, 4, '2023-05-24',
    null, 1, 'Auxílio-Creche', 1, 2,
    9, 6, 1, 1, 1,
    3, '42.27.0094.3820876/2023-02', 'Yrlan Ramos Santana', 3, 1.143, 1,
	'24/05/2023 13:03:25', '23/05/2023 17:55:48', 2023
);