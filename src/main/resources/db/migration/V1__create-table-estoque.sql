create table estoque(
    id bigint not null auto_increment,
    produto_id bigint not null unique,
    quantidade numeric(8, 3) not null,
    reservado numeric(8, 3) not null,
    constraint estoquepk primary key(id)
);