create table servico(
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(60) NOT NULL,
	valor NUMERIC(15,2),
	status boolean
);

create table pessoa(
	id SERIAL PRIMARY KEY,
	nomePessoa VARCHAR(60) NOT NULL,
	telefone VARCHAR(60),
	email VARCHAR(60) 
);

create table endereco(
	id SERIAL PRIMARY KEY,
	nomeRua VARCHAR(60),
	bairro VARCHAR(60),
	numero INTEGER,
	cep VARCHAR(60),
	complemento VARCHAR(60),
	stAtivo boolean,
	id_pessoa INTEGER REFERENCES pessoa(id)
);

create table pet(
	id SERIAL PRIMARY KEY,
	nomePet VARCHAR(60) NOT NULL,
	genero VARCHAR(60),
	peso INTEGER,
	porte VARCHAR(60),
	dono_id SERIAL REFERENCES pessoa(id) NOT NULL
);