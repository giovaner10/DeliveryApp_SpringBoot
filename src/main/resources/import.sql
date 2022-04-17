insert into cozinha (nome) values ('chinesa')
insert into cozinha (nome) values ('tailandesa')
insert into cozinha (nome) values ('russa')

insert into restaurante (nome, taxa_frete, id_cozinha) values ('bar do cuscuz', 100, 1)
insert into restaurante (nome, taxa_frete, id_cozinha) values ('Imperio', 10, 2)

insert into estado (nome) values ('Pernanbuco')
insert into estado (nome) values ('Paraiba')

insert into forma_pagamento (id, descricao) values (1, 'Cartao de credito');
insert into forma_pagamento (id, descricao) values (2, 'Cartao de debito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurnte_forma_de_pagamento (restaurante_id, forma_pagamento_id) values (1, 3), (2,1), (1,2);
/*isso aqui vai servir para criarmos as instancias no banco de dados*/