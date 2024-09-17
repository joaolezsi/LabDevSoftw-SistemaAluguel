-- Criando o banco de dados
CREATE DATABASE IF NOT EXISTS aluguel_automoveis;
USE aluguel_automoveis;

-- Tabela de Usuários
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rg VARCHAR(12) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    profissao VARCHAR(100)
);

-- Tabela de Empregadores
CREATE TABLE Empregador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    endereco VARCHAR(255)
);

-- Tabela de Clientes
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

-- Tabela de Agentes (Empresas ou Bancos)
CREATE TABLE Agente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

-- Tabela de Automóveis
CREATE TABLE Automovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    ano INT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    placa VARCHAR(8) NOT NULL
);

-- Tabela de Pedidos de Aluguel
CREATE TABLE Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    agente_id INT,
    automovel_id INT NOT NULL,
    data_pedido DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (agente_id) REFERENCES Agente(id),
    FOREIGN KEY (automovel_id) REFERENCES Automovel(id)
);

-- Tabela de Contratos
CREATE TABLE Contrato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    data_contrato DATE NOT NULL,
    duracao_meses INT CHECK (duracao_meses IN (12, 24, 36, 48)),
    opcao_compra BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (pedido_id) REFERENCES Pedido(id)
);

-- Tabela de Contratos de Crédito (associado a um contrato de aluguel)
CREATE TABLE ContratoCredito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contrato_id INT NOT NULL,
    agente_id INT NOT NULL,
    valor_credito DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (contrato_id) REFERENCES Contrato(id),
    FOREIGN KEY (agente_id) REFERENCES Agente(id)
);

-- Relacionamento entre Clientes e Empregadores (um cliente pode ter até 3 empregadores)
CREATE TABLE Cliente_Empregador (
    cliente_id INT NOT NULL,
    empregador_id INT NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (cliente_id, empregador_id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (empregador_id) REFERENCES Empregador(id)
);

-- Inserção de dados mockados
-- Inserindo usuários
INSERT INTO Usuario (rg, cpf, nome, endereco, profissao) VALUES
('123456789', '123.456.789-00', 'João Silva', 'Rua A, 123', 'Engenheiro'),
('987654321', '987.654.321-00', 'Maria Souza', 'Rua B, 456', 'Professora'),
('555444333', '555.444.333-22', 'Carlos Pereira', 'Rua C, 789', 'Analista de TI'),
('222333444', '222.333.444-55', 'Empresa X', 'Avenida D, 101', 'Agente'),
('333444555', '333.444.555-66', 'Banco Y', 'Avenida E, 202', 'Agente');

-- Inserindo clientes
INSERT INTO Cliente (usuario_id) VALUES
(1), (2), (3);

-- Inserindo agentes (empresas e bancos)
INSERT INTO Agente (usuario_id) VALUES
(4), (5);

-- Inserindo empregadores
INSERT INTO Empregador (nome, cnpj, endereco) VALUES
('Empresa A', '00.111.222/0001-33', 'Rua X, 123'),
('Empresa B', '11.222.333/0001-44', 'Rua Y, 456'),
('Empresa C', '22.333.444/0001-55', 'Rua Z, 789');

-- Relacionando clientes com empregadores (com salários)
INSERT INTO Cliente_Empregador (cliente_id, empregador_id, salario) VALUES
(1, 1, 5000.00),
(1, 2, 3000.00),
(2, 2, 4500.00),
(3, 3, 6000.00);

-- Inserindo automóveis
INSERT INTO Automovel (matricula, ano, marca, modelo, placa) VALUES
('ABC1234', 2020, 'Toyota', 'Corolla', 'ABC-1234'),
('XYZ5678', 2021, 'Honda', 'Civic', 'XYZ-5678'),
('LMN9876', 2022, 'Ford', 'Fiesta', 'LMN-9876');    

-- Inserindo pedidos de aluguel
INSERT INTO Pedido (cliente_id, agente_id, automovel_id, data_pedido, status) VALUES
(1, 1, 1, '2024-09-01', 'Em análise'),
(2, 2, 2, '2024-09-10', 'Aprovado'),
(3, NULL, 3, '2024-09-15', 'Pendente');

-- Inserindo contratos
INSERT INTO Contrato (pedido_id, data_contrato, duracao_meses, opcao_compra) VALUES
(1, '2024-09-05', 24, TRUE),
(2, '2024-09-12', 36, FALSE);

-- Inserindo contratos de crédito
INSERT INTO ContratoCredito (contrato_id, agente_id, valor_credito) VALUES
(1, 1, 30000.00),
(2, 2, 45000.00);