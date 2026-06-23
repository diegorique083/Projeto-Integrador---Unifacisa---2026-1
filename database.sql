CREATE TABLE IF NOT EXISTS cliente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    telefone TEXT,
    cnh TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL UNIQUE,
    telefone TEXT,
    matricula TEXT NOT NULL UNIQUE,
    cargo TEXT
);

CREATE TABLE IF NOT EXISTS veiculo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    placa TEXT NOT NULL UNIQUE,
    marca TEXT NOT NULL,
    modelo TEXT NOT NULL,
    valor_diaria REAL NOT NULL,
    disponivel INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS aluguel (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_cliente INTEGER NOT NULL,
    id_veiculo INTEGER NOT NULL,
    quantidade_dias INTEGER NOT NULL,
    valor_total REAL NOT NULL,
    data_aluguel TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_veiculo) REFERENCES veiculo(id)
);
