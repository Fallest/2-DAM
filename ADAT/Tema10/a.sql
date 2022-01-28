/*
Base de datos:
Tablas:
    Cliente (A):
        -NIF            integer         PK
        -foto           varchar2(30)
        -fecha_registro date
        -dir            varchar2(30)
        -pw             varchar2(20)
    
    Pedido (B):
        -localizador    integer         PK
        -nif_cli        integer         FK (Cliente)
        -precio         real

    Repartidor (D):
        -cod_rep        integer         PK
        -empresa        varchar2(30)
        -nombre         varchar2(30)
        -pw             varchar2(20)

    Tienda (C):
        -localizador *  integer         FK
        -cod_rep     *  integer         FK
        -pedidos_rep *  integer         
        -nombre         varchar2(30)
        -precio_envio   real
*/