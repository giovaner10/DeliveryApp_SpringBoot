create table pedido
(
    id                   bigint not null auto_increment,
    data_cancelamento    datetime(6),
    data_confirmacao     datetime(6),
    data_criacao         datetime(6),
    data_entrega         datetime(6),
    endereco_bairro      varchar(60),
    endereco_cep         varchar(60),
    endereco_complemento varchar(60),
    endereco_logradouro  varchar(60),
    endereco_numero      varchar(7),
    status               integer,
    sub_total            decimal(19, 2),
    taxa_frete           decimal(19, 2),
    valor_total          decimal(19, 2),
    usuario_cliente_id   bigint not null,
    endereco_cidade_id   bigint,
    forma_pagamento_id   bigint not null,
    restaurante_id       bigint not null,
    primary key (id)
) engine=InnoDB default charset=utf8;


create table item_pedido
(
    id             bigint not null auto_increment,
    observacao     varchar(100),
    preco_total    integer,
    preco_unitario decimal(19, 2),
    quantidade     integer,
    pedido_id      bigint not null,
    produto_id     bigint not null,
    primary key (id)
) engine=InnoDB default charset=utf8;



alter table item_pedido
    add constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id);

alter table item_pedido
    add constraint fk_item_pedido_produto foreign key (produto_id) references produto (id);

alter table pedido
    add constraint fk_pedido_usuario foreign key (usuario_cliente_id) references usuario (id);

alter table pedido
    add constraint fk_pedido_cidade foreign key (endereco_cidade_id) references cidade (id);

alter table pedido
    add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table pedido
    add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id);


