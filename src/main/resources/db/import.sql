#Criação do Banco de dados
create database financas_online;

#Criação da tabela Despesas
create table financas_online.tb_despesas (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo varchar(60),
    valor double,
    data_despesa date
);

#Criação da tabela ContaBancaria
create table financas_online.tb_contas_bancarias (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titular varchar(60),
    tipo_conta varchar(20),
    saldo double
);

#Associação de tabelas
alter table financas_online.tb_despesas add column conta_bancaria_id BIGINT;
alter table financas_online.tb_despesas add constraint fk_conta_bancaria foreign key (conta_bancaria_id) references financas_online.tb_contas_bancarias(id);