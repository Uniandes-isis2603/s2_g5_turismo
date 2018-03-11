delete from PlanEntity_PreferenciasEntity;
delete from PlanEntity_GuiaEntity;
delete from PlanEntity;
delete from PreferenciasEntity;
delete from UBICACIONENTITY;
delete from GuiaEntity;
delete from FACTURAENTITY;
delete from PAQUETETURISTICOENTITY;
delete from TARJETADECREDITOENTITY;
delete from BLOGENTITY_COMENTARIOENTITY;
delete from COMENTARIOENTITY;
delete from BLOGENTITY;
delete from USUARIOENTITY;
delete from PAGOENTITY;

/*Parte Ubicacion*/
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10000,'Colombia','Bogota',10,-10);
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10001,'Francia','Paris',10,-10);
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10002,'España','Madrid',10,-10);

insert into BlogEntity (id, descripcion, likes, tema) values (10000,'primer blog',0,'este es mi primer blog');
insert into BlogEntity (id, descripcion, likes, tema) values (20000,'segundo blog',20,'este es mi segundo blog');
insert into BlogEntity (id, descripcion, likes, tema) values (30000,'tercer blog',30,'este es mi tercero blog');

insert into ComentarioEntity (id, comentario) values (10000,'me gusta comentar');
insert into ComentarioEntity (id, comentario) values (20000,'comentar o no comentar esa es la cuestion');
insert into ComentarioEntity (id, comentario) values (30000,'taque taraque taque');

insert into BLOGENTITY_COMENTARIOENTITY (BLOGENTITY_ID, COMENTARIOS_ID) values (10000,10000);
insert into BLOGENTITY_COMENTARIOENTITY (BLOGENTITY_ID, COMENTARIOS_ID) values (20000,20000);
insert into BLOGENTITY_COMENTARIOENTITY (BLOGENTITY_ID, COMENTARIOS_ID) values (30000,30000);

insert into PreferenciasEntity (id, tipoPlan) values (10000,'Familiar');
insert into PreferenciasEntity (id, tipoPlan) values (10001,'Compras');
insert into PreferenciasEntity (id, tipoPlan) values (10002,'Museo');

insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10000, 'linkDeUnaImagen', 1,'Subir a monserrate', 180, 'Monserrate', 20000,'restriccion shida',10000);
insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10001, 'linkDeUnaImagen', 2,'Recorrido por sagrada familia', 180, 'Sagrada familia', 60000,'restriccion shida',10000);
insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10002, 'linkDeUnaImagen', 4,'Recorrer muro de berlin', 180, 'Muro de berlin', 20000,'restriccion shida',10002);

insert into GuiaEntity (id, idiomaguia, name) values (10000,'espaniol','w');
insert into GuiaEntity (id, idiomaguia, name) values (10001,'espaniol','t');
insert into GuiaEntity (id, idiomaguia, name) values (10002,'espaniol','f');


/* Parte que se nececita para tajetas*/
insert into UsuarioEntity (id,apellido,contrasenia,correo,esAdministrador,idioma,name,nombre,telefono) values (10001,'benitez','1234','benitez@gmail.com',0,'español','a','sebastian',4276067);


insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10000,123,1016078997,'sebastian',1234123412341234,10001);
insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10001,123,1016078997,'sebastian',1234123412341235,10001);
insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10002,123,1016078997,'sebastian',1234123412341236,10001);


/* Parte Facturas*/

insert into PAQUETETURISTICOENTITY(id,Name) values (10000,'a');
insert into PAQUETETURISTICOENTITY(id,Name) values (10001,'b');
insert into PAQUETETURISTICOENTITY(id,Name) values (10002,'c');

insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10000,800000,'a',10001,10000);
insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10001,900000,'b',10001,10000);
insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10002,1000000,'c',10001,10000);

insert into PAGOENTITY (id,costo, name) values (10000,80.33,'monserrate');
insert into PAGOENTITY (id,costo, name) values (10003,80.33,'monserrate');
insert into PAGOENTITY (id,costo, name) values (10005,80.33,'monserrate');