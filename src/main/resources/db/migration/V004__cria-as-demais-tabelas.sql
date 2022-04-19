create table forma_pagamento
(
    id        bigint       not null auto_increment,
    descricao varchar(100) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo
(
    id   bigint      not null auto_increment,
    nome varchar(60) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissoes
(
    grupo_id     bigint not null,
    permissao_id bigint not null
) engine=InnoDB default charset=utf8;

create table permissao
(
    id        bigint       not null auto_increment,
    descricao varchar(100) not null,
    nome      varchar(60)  not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table produto
(
    id             bigint         not null auto_increment,
    ativo          bit            not null,
    descricao      varchar(255)   not null,
    nome           varchar(60)    not null,
    preco          decimal(19, 2) not null,
    id_restaurante bigint,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante
(
    id                   bigint         not null auto_increment,
    data_atualizacao     datetime(6) not null,
    data_cadastro        datetime(6) not null,
    endereco_bairro      varchar(60),
    endereco_cep         varchar(60),
    endereco_complemento varchar(60),
    endereco_logradouro  varchar(60),
    endereco_numero      varchar(60),
    nome                 varchar(60)    not null,
    taxa_frete           decimal(19, 2) not null,
    id_cozinha           bigint         not null,
    endereco_cidade_id   bigint,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurnte_forma_pagamento
(
    restaurante_id     bigint not null,
    forma_pagamento_id bigint not null
) engine=InnoDB default charset=utf8;

create table usuario
(
    id            bigint      not null auto_increment,
    data_cadastro datetime(6),
    email         varchar(60) not null,
    nome          varchar(60) not null,
    senha         varchar(50) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo
(
    usuario_id bigint not null,
    grupo_id   bigint not null
) engine=InnoDB default charset=utf8;

alter table grupo_permissoes
    add constraint fk_grupo_perimissao_permissao
        foreign key (permissao_id) references permissao (id);

alter table grupo_permissoes
    add constraint fk_grupo_permissao_grupo
        foreign key (grupo_id) references grupo (id);

alter table produto
    add constraint fk_produto_restaurante
        foreign key (id_restaurante) references restaurante (id);

alter table restaurante
    add constraint fk_restaurante_cozinha
        foreign key (id_cozinha) references cozinha (id);

alter table restaurante
    add constraint fk_restaurante_cidade
        foreign key (endereco_cidade_id) references cidade (id);

alter table restaurnte_forma_pagamento
    add constraint fk_restaurante_forma_pagamento_forma_pagamento
        foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurnte_forma_pagamento
    add constraint fk_restaurante_forma_pagamento_restaurante
        foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo
    add constraint fk_usuario_grupo_grupo
        foreign key (grupo_id) references grupo (id);

alter table usuario_grupo
    add constraint fk_usuario_grupo_usuario
        foreign key (usuario_id) references usuario (id);