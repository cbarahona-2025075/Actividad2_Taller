drop database if exists tienda_db_in5bm;
create database tienda_db_in5bm;
drop database tienda_db_in5bm;

create table usuario(
	id_usuario int auto_increment not null  primary key,
    nombre_usuario varchar(60) not null,
    apellido_usuario varchar(60) not null,
    edad_usuario int not null,
    correo_usuario varchar(100) not null,
    password varchar(60) not null,
    rol_usuario varchar(60) not null
);

create table categoria(
	id_categoria int auto_increment not null primary key,
    nombre_categoria varchar(60) not null,
    descripcion_categoria varchar(150)
);
 
create table producto(
	id_producto int auto_increment not null primary key,
    nombre_producto varchar(80) not null,
    precio_producto decimal(10,2) not null,
    stock int not null,
    id_categoria int not null,
    constraint fk_producto_categoria
        foreign key (id_categoria)
        references categoria(id_categoria)
        on delete cascade
        on update cascade 
);
 
create table pedido(
	id_pedido int auto_increment not null primary key,
    fecha_pedido date not null,
    total_pedido decimal(10,2) not null,
    id_usuario int not null,
    constraint fk_pedido_usuario
        foreign key (id_usuario)
        references usuario(id_usuario)
		on delete cascade
        on update cascade 
);
 
create table detalle_pedido(
	id_detalle int auto_increment not null primary key,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    id_pedido int not null,
    id_producto int not null,
    constraint fk_detalle_pedido
        foreign key (id_pedido)
        references pedido(id_pedido)
        on delete cascade
        on update cascade,
    constraint fk_detalle_producto
        foreign key (id_producto)
        references producto(id_producto)
		on delete cascade
        on update cascade 
);

insert into usuario (nombre_usuario, apellido_usuario, edad_usuario, correo_usuario, password, rol_usuario) values
('Juan', 'Perez', 30, 'juan.perez@gmail.com', '123456', 'ADMIN'),
('Maria', 'Lopez', 25, 'maria.lopez@gmail.com', '123456', 'USUARIO'),
('Carlos', 'Gomez', 40, 'carlos.gomez@gmail.com', '123456', 'USUARIO');

insert into categoria (nombre_categoria, descripcion_categoria) values
('Electrónica', 'Productos electrónicos'),
('Ropa', 'Prendas de vestir'),
('Alimentos', 'Productos alimenticios');

insert into producto (nombre_producto, precio_producto, stock, id_categoria) values
('Laptop', 750.00, 10, 1),
('Camiseta', 15.50, 50, 2),
('Arroz', 1.25, 100, 3),
('Smartphone', 500.00, 20, 1);

insert into pedido (fecha_pedido, total_pedido, id_usuario) values
('2026-04-10', 765.50, 1),
('2026-04-11', 16.75, 2),
('2026-04-12', 501.25, 3);

insert into detalle_pedido (cantidad, precio_unitario, id_pedido, id_producto) values
(1, 750.00, 1, 1),
(1, 15.50, 1, 2),
(1, 15.50, 2, 2),
(1, 1.25, 2, 3),
(1, 500.00, 3, 4),
(1, 1.25, 3, 3);
 