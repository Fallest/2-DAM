/*
Base de datos:
Tablas:
    Cliente (A):
    Clientes que van a realizar pedidos.
        -NIF            integer         PK
        -foto           varchar2(30)
        -fecha_registro date
        -dir            varchar2(30)
        -pw             varchar2(20)
    
    Pedido (B):
    Pedidos de los clientes, que pueden contener productos de varias tiendas.
        -localizador    integer         PK
        -nif_cli        integer         FK (Cliente)
        -precio         real

    Repartidor (D):
    Repartidor que visitará varias tiendas para recoger los pedidos
        -cod_rep        integer         PK
        -empresa        varchar2(30)
        -nombre         varchar2(30)
        -pw             varchar2(20)

    Tienda (C):
    Esta tabla recoge las "visitas" que realizarán los repartidores en cada 
    tienda para recoger los pedidos.
        -localizador *  integer         FK
        -cod_rep     *  integer         FK
        -pedidos_rep *  integer         
        -nombre         varchar2(30)
        -precio_envio   real
*/

/* Creación de tablas */
create table clients(
    nif         integer        not null    primary key,
    pic         varchar(30)    default 'default',
    reg_date    date           default '01/01/2020' not null,
    dir         varchar(30)    not null,
    pw          varchar(20)    not null    
);

create table orders(
    loc         integer         not null    primary key,
    nif_cli     integer         not null,
    price       real            not null,
    constraint fk_orders 
        foreign key (nif_cli) 
        references clients(nif)
        on delete cascade
);

create table delivery(
    del_cod     integer         not null primary key,
    company     varchar(30),
    del_name    varchar(30),
    pw          varchar(20)     not null
);

create table shops(
    loc                     integer         not null,
    del_cod                 integer         not null,
    order_delivery_code     integer         default 0,
    shop_name               varchar(30)    not null,
    del_costs               real            default 0,
    constraint pk_shops
        primary key (loc, del_cod, order_delivery_code),
    constraint fk_shops_loc
        foreign key (loc)
        references orders(loc)
        on delete cascade,
    constraint fk_shops_del_cod
        foreign key (del_cod)
        references delivery(del_cod)
        on delete cascade,
    constraint ck_del_costs check (del_costs > 0)
);

/* Trigger para asignar un código de envío para la tienda */
create trigger assign_order_del_code
after insert on shops
referencing new as n
for each row 
mode DB2SQL
update shops set order_delivery_code = (n.loc / 7) + (n.del_cod / 3) + ((n.loc + n.del_cod) / 5)
    where loc = n.loc and del_cod = n.del_cod;

/* Inserción de datos */
insert into clients values (51600278, '1002', '2020-06-10', '6980 Pearson Alley', '7wSSt0v');
insert into clients values (44351392, '1013', '2020-07-15', '4 Saint Paul Park', 'FlATNciIk');
insert into clients values (98992125, '1012', '2021-03-12', '09 Emmet Trail', '4w4rFiyw');
insert into clients values (13344267, '1021', '2021-05-28', '480 Warner Park', 'bs7JROwQp');
insert into clients values (71593828, '1022', '2021-08-21', '6 Bultman Plaza', '8O03Lsbq0');

insert into orders values (3569, 44351392, 71.98);
insert into orders values (9854, 44351392, 10.32);
insert into orders values (6983, 13344267, 148.49);
insert into orders values (3013, 51600278, 13.48);
insert into orders values (2484, 51600278, 128.1);
insert into orders values (8452, 13344267, 12.2);
insert into orders values (2326, 98992125, 49.46);
insert into orders values (2690, 13344267, 12.75);
insert into orders values (1848, 51600278, 103.57);
insert into orders values (9907, 71593828, 70.6);

insert into delivery values (771, 'fedex', 'Briana', 'GqRUWfB');
insert into delivery values (369, 'amazon', 'Lindsay', 'KM98h571yxKi');
insert into delivery values (371, 'ups', 'Herman', 'qZ8wn1Xu');

insert into shops values (3569, 771, 0, 'Drive Shack Inc.', 1.02);
insert into shops values (9854, 369, 0, 'ADMA Biologics Inc', 3.53);
insert into shops values (6983, 369, 0, 'Neuralstem, Inc.', 2.01);
insert into shops values (6983, 369, 0, 'ADMA Biologics Inc', 2.23);
insert into shops values (3013, 771, 0, 'Wells Fargo & Company', 3.31);
insert into shops values (3013, 369, 0, 'Drive Shack Inc.', 3.12);
insert into shops values (2484, 369, 0, 'Drive Shack Inc.', 1.02);
insert into shops values (8452, 771, 0, 'ADMA Biologics Inc', 3.53);
insert into shops values (2326, 771, 0, 'Neuralstem, Inc.', 2.01);
insert into shops values (2690, 371, 0, 'Wells Fargo & Company', 3.31);
insert into shops values (9854, 771, 0, 'Wells Fargo & Company', 3.53);
insert into shops values (1848, 371, 0, 'Drive Shack Inc.', 1.02);
insert into shops values (9907, 371, 0, 'ADMA Biologics Inc', 3.53);
insert into shops values (9907, 369, 0, 'Drive Shack Inc.', 1.02);

alter table orders drop constraint fk_orders;