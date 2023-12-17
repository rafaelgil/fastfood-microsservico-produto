CREATE TABLE IF NOT EXISTS produto (
    id UUID NOT NULL default gen_random_uuid(),
    descricao VARCHAR(100),
    categoria VARCHAR(50),
    preco numeric(10,2),
    constraint pk_produto PRIMARY KEY (id)
);
