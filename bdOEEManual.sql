CREATE DATABASE IF NOT EXISTS oeemanual;
USE oeemanual;

DROP TABLE IF EXISTS lancamento;
DROP TABLE IF EXISTS ordem_producao;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS maquina_apontamento;
DROP TABLE IF EXISTS estrutura;

DROP TABLE IF EXISTS produto;
CREATE TABLE IF NOT EXISTS produto(
	id INT AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    codigo CHAR(7) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE produto ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

select * from produto;

INSERT INTO produto VALUES
(NULL, 'PARACETAMOL', '50037', TRUE),
(NULL, 'EXPEC', '1090', TRUE);

DROP TABLE IF EXISTS maquina;
CREATE TABLE IF NOT EXISTS maquina(
	id INT AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    tag CHAR(11) NOT NULL,
    batelada BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(id)
);

ALTER TABLE maquina ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

INSERT INTO maquina VALUES
(NULL, 'CAM 2', 'EBS-ECT-02', FALSE),
(NULL, 'COP 10', 'MAS-COP-10', FALSE);

-- SELECT * FROM maquina;

CREATE TABLE IF NOT EXISTS estrutura(
	idProduto INT NOT NULL,
    idMaquina INT NOT NULL,
    velocidade DOUBLE(11,3) NOT NULL,
    FOREIGN KEY (idProduto) REFERENCES produto (id),
    FOREIGN KEY (idMaquina) REFERENCES maquina (id),
    PRIMARY KEY(idProduto, idMaquina)
);

INSERT INTO estrutura VALUES
(1, 1, 5000),
(1, 2, 100000),
(2, 2, 60000);

SELECT 
	p.descricao AS descricaoProduto, 
    p.codigo AS codigoProduto, 
    m.descricao AS descricaoMaquina, 
    m.tag AS tagMaquina, 
    m.batelada AS ehBatelada, 
    e.velocidade AS velocidadeProdutoEquipamento
FROM produto AS p
INNER JOIN estrutura AS e ON p.id = e.idProduto
INNER JOIN maquina AS m ON e.idMaquina = m.id;

DROP TABLE IF EXISTS tipo_apontamento;
CREATE TABLE IF NOT EXISTS tipo_apontamento(
	id INT AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    codigo CHAR(4) UNIQUE NOT NULL,
    areaResponsavel VARCHAR(255) NOT NULL,
    podeAlterar BOOLEAN DEFAULT TRUE,
    requerAssinatura BOOLEAN DEFAULT FALSE,
    afetaOEEEfetivo BOOLEAN DEFAULT TRUE,    
    PRIMARY KEY(id)
);

INSERT INTO tipo_apontamento VALUES
(NULL, 'PRODUÇÃO', '1', 'PRODUÇÃO', 0, false, true),
(NULL, 'MANUTENÇÃO PREVENTIVA', '2', 'MANUTENÇÃO', 1, true, false),
(NULL, 'AJUSTE - DOBRADOR DE BULA', '3', 'PRODUÇÃO', 1, false, true);

CREATE TABLE IF NOT EXISTS maquina_apontamento(
	idMaquina INT NOT NULL,
    idTipoApontamento INT NOT NULL,
    FOREIGN KEY (idMaquina) REFERENCES maquina (id),
    FOREIGN KEY (idTipoApontamento) REFERENCES tipo_apontamento (id),
    PRIMARY KEY(idMaquina, idTipoApontamento)
);

INSERT INTO maquina_apontamento VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2);

SELECT 
	m.descricao AS nomeMaquina,
	ta.descricao AS descricaoApontamento,
    ta.codigo AS codigoApontamento
FROM maquina AS m
INNER JOIN maquina_apontamento AS ma ON ma.idMaquina = m.id
INNER JOIN tipo_apontamento AS ta ON ma.idTipoApontamento = ta.id
ORDER BY nomeMaquina ASC, descricaoApontamento ASC;

CREATE TABLE IF NOT EXISTS usuario(
	matricula VARCHAR(10),
    usuario VARCHAR(30) NOT NULL UNIQUE, 
    senha VARCHAR(30) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (matricula)
);

INSERT INTO usuario VALUES
('99999', 'gabrielca', 'senha123', 'Gabriel de Carvalho Antunes'),
('98760', 'fulano', 'senha123', 'Fulano da Silva'),
('12345', 'beltrano', 'senha123', 'Beltrano Nogueira');

CREATE TABLE IF NOT EXISTS ordem_producao(
	id INT AUTO_INCREMENT,
    numero_op VARCHAR(10) NOT NULL,
    idMaquina INT NOT NULL,
    idProduto INT NOT NULL,
    data_inicio DATETIME DEFAULT NULL,
    data_fim DATETIME DEFAULT NULL,
    quantidade_planejada INT NOT NULL,
    quantidade_realizada INT NOT NULL DEFAULT 0,
    tempo_hipotetico DOUBLE(4,3) NOT NULL DEFAULT 0,
    idUsuario VARCHAR(10) NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario (matricula),
    FOREIGN KEY (idMaquina, idProduto) REFERENCES estrutura (idMaquina, idProduto),
    PRIMARY KEY (id),
    CONSTRAINT CHK_quantidadePlanejada CHECK (quantidade_planejada > 0)
);

INSERT INTO ordem_producao(numero_op, idMaquina, idProduto, quantidade_planejada, idUsuario) VALUES
('7776669', 1, 1, 100000, '99999');

SELECT * FROM ordem_producao;

SELECT 
	op.numero_op AS OP,
    m.descricao AS Maquina,
    m.tag AS TagMaquina,
    p.codigo AS CodigoProduto,
    p.descricao AS DescricaoProduto,
    op.data_inicio AS Inicio,
    op.data_fim AS Fim,
    op.quantidade_planejada AS QtdPlanejada,
    op.quantidade_realizada AS QtdRealizada,
    u.matricula AS MatriculaUsuario,
    u.nome AS NomeUsuario
FROM ordem_producao AS op
INNER JOIN usuario AS u ON u.matricula = op.idUsuario
INNER JOIN estrutura AS e ON e.idMaquina = op.idMaquina AND e.idProduto = op.idMaquina
INNER JOIN maquina AS m ON m.id = e.idMaquina
INNER JOIN produto AS p ON p.id = e.idProduto;

CREATE TABLE IF NOT EXISTS lancamento(
    idOP INT NOT NULL,
    idTipoApontamento INT NOT NULL,
    data_inicio DATETIME NOT NULL,
    data_fim DATETIME DEFAULT NULL,
    idUsuario VARCHAR(10) NOT NULL,
	PRIMARY KEY (idOP, data_inicio),
    FOREIGN KEY (idOP) REFERENCES ordem_producao (id),
    FOREIGN KEY (idUsuario) REFERENCES usuario (matricula)
);

INSERT INTO lancamento (idOP, idTipoApontamento, data_inicio, idUsuario) VALUES
(1, 1, '2024-02-13 10:00', '99999');

SELECT 
	op.numero_op AS OP,
    m.descricao AS NomeMaquina,
    m.tag AS TagMaquina,
    p.codigo AS CodigoProduto,
    p.descricao AS DescricaoProduto,
    l.data_inicio AS InicioLancamento,
    l.data_fim AS FimLancamento,
    u.matricula AS MatriculaUsario,
    u.nome AS NomeUsuario
FROM lancamento AS l
INNER JOIN ordem_producao AS op ON l.idOP = op.id
INNER JOIN estrutura AS e ON op.idMaquina = e.idMaquina AND op.idProduto = e.idProduto
INNER JOIN maquina AS m ON m.id = e.idMaquina
INNER JOIN produto AS p ON p.id = e.idProduto
INNER JOIN usuario AS u ON l.idUsuario = u.matricula