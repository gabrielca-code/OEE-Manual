CREATE DATABASE IF NOT EXISTS oeemanual;
USE oeemanual;

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
(NULL, 'MANUTENÇÃO PREVENTIVA', '2', 'MANUTENÇÃO', 1);