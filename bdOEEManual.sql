CREATE DATABASE IF NOT EXISTS oeemanual;
USE oeemanual;

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

INSERT INTO produto VALUES
(NULL, 'PARACETAMOL', '50037'),
(NULL, 'EXPEC', '1090');

-- SELECT * FROM produto;

DROP TABLE IF EXISTS maquina;
CREATE TABLE IF NOT EXISTS maquina(
	id INT AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    tag CHAR(11) NOT NULL,
    batelada BOOLEAN,
    PRIMARY KEY(id)
);

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
    podeAlterar BOOLEAN,
    PRIMARY KEY(id)
    -- REQUER ASSINATURA?
    -- FLAGS QUE AFETAM O OEE REAL/EFETIVO
);

INSERT INTO tipo_apontamento VALUES
(NULL, 'PRODUÇÃO', '1', 'PRODUÇÃO', 0),
(NULL, 'MANUTENÇÃO PREVENTIVA', '2', 'MANUTENÇÃO', 1),
(NULL, 'AJUSTE - DOBRADOR DE BULA', '3', 'PRODUÇÃO', 1);

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

SELECT * FROM usuario;

CREATE TABLE IF NOT EXISTS ordem_producao(
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
    PRIMARY KEY (numero_op, idMaquina, idProduto)
);

INSERT INTO ordem_producao(numero_op, idMaquina, idProduto, quantidade_planejada, idUsuario) VALUES
('7776669', 1, 1, 100000, '99999');

SELECT * FROM ordem_producao;

-- query inner joins

-- tabela lançamentos