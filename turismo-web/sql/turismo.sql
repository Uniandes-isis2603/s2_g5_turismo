delete from PlanEntity_PreferenciasEntity;
delete from PlanEntity_GuiaEntity;
delete from PlanEntity;
delete from PreferenciasEntity;
delete from GuiaEntity;

insert into PreferenciasEntity (id, tipoPlan) values (10000,'Familiar');
insert into PreferenciasEntity (id, tipoPlan) values (10001,'Compras');
insert into PreferenciasEntity (id, tipoPlan) values (10002,'Museo');

insert into PlanEntity (id, archivo, cantidadpersonas, ciudad, descripcion, duracion, latitud, longitud, name, pais, precio, restricciones) values (10000, 'linkDeUnaImagen', 1, 'Bogota','Subir a monserrate', 180, 41235, 423423, 'Monserrate', 'Colombia', 20000,'restriccion shida');
insert into PlanEntity (id, archivo, cantidadpersonas, ciudad, descripcion, duracion, latitud, longitud, name, pais, precio, restricciones) values (10001, 'linkDeUnaImagen', 2, 'Barcelona','Recorrido por sagrada familia', 180, 5473, 52352, 'Sagrada familia', 'Espania', 60000,'restriccion shida');
insert into PlanEntity (id, archivo, cantidadpersonas, ciudad, descripcion, duracion, latitud, longitud, name, pais, precio, restricciones) values (10002, 'linkDeUnaImagen', 4, 'Berlin','Recorrer muro de berlin', 180, 41235, 423423, 'Muro de berlin', 'Alemania', 20000,'restriccion shida');

insert into GuiaEntity (id, idiomaguia, name) values (10000,'espaniol','w');
insert into GuiaEntity (id, idiomaguia, name) values (10001,'espaniol','t');
insert into GuiaEntity (id, idiomaguia, name) values (10002,'espaniol','f');
