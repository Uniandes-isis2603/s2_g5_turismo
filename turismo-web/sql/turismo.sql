
delete from PAQUETETURISTICOENTITY_PLANAGENDADOENTITY;
delete from PLANAGENDADOENTITY;
delete from PlanEntity_PreferenciasEntity;
delete from PlanEntity_GuiaEntity;
delete from PAQUETETURISTICOENTITY_PAGOENTITY;
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
delete from VALORACIONESENTITY;

/*Parte Ubicacion*/
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10000,'Colombia','Bogota',10,-10);
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10001,'Francia','Paris',10,-10);
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10002,'España','Madrid',10,-10);
insert into UBICACIONENTITY (id,pais,ciudad,latitud,longitud) values (10003,'Holanda','Amsterdam',10,-10);

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

insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (9999, 'http://static.alfabetajuega.com/abj_public_files/multimedia/imagenes/201601/133204.alfabetajuega-mordor-070116.jpg', 4,'Recorrer mordor. Este se puede borrar porque no tiene asociacion con plan agendado', 180, 'Mordor', 666,'restriccionxyz',10002);
insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10000, 'https://cdn2.gbot.me/photos/dt/Db/1515289189/-_Iglesia_de_Monserrate_in-20000000016204685-500x375.jpg', 1,'Subir a monserrate', 180, 'Monserrate', 20000,'restriccion shida',10000);
insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10001, 'https://upload.wikimedia.org/wikipedia/commons/2/26/%CE%A3%CE%B1%CE%B3%CF%81%CE%AC%CE%B4%CE%B1_%CE%A6%CE%B1%CE%BC%CE%AF%CE%BB%CE%B9%CE%B1_2941.jpg', 2,'Recorrido por sagrada familia', 180, 'Sagrada familia', 60000,'restriccion shida',10000);
insert into PlanEntity (id, archivo, cantidadpersonas, descripcion, duracion, name, precio, restricciones, ubicacion_id) values (10002, 'http://elmerey.com/wp-content/uploads/2017/08/14/Muro-de-Berlin.jpg', 4,'Recorrer muro de berlin', 180, 'Muro de berlin', 20000,'restriccion shida',10002);


insert into GuiaEntity (id, idiomaguia, name) values (10000,'espaniol','w');
insert into GuiaEntity (id, idiomaguia, name) values (10001,'espaniol','t');
insert into GuiaEntity (id, idiomaguia, name) values (10002,'espaniol','f');


/* Parte que se nececita para tajetas*/
insert into UsuarioEntity (id,apellido,contrasenia,correo,esAdministrador,idioma,name,telefono) values (10001,'benitez','1234','benitez@gmail.com',0,'español','sebastian',4276067);
insert into UsuarioEntity (id,apellido,contrasenia,correo,esAdministrador,idioma,name,telefono) values (10002,'ramirez','5678','ramirez@gmail.com',0,'español','juan',1234567);
insert into UsuarioEntity (id,apellido,contrasenia,correo,esAdministrador,idioma,name,telefono) values (10003,'gutierrez','9012','gutierrez@gmail.com',0,'español','francisco',8901223);


insert into ValoracionesEntity (id, calificacion, comentario) values (10001, 5, 'Buena');
insert into ValoracionesEntity (id, calificacion, comentario) values (10002, 3, 'Regular');
insert into ValoracionesEntity (id, calificacion, comentario) values (10003, 1, 'Mala');

insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10000,123,1016078997,'sebastian',1234123412341234,10001);
insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10001,123,1016078997,'sebastian',1234123412341235,10001);
insert into TarjetaDeCreditoEntity (id,cdv,cedula,name,numero,Usuario_id) values (10002,123,1016078997,'sebastian',1234123412341236,10001);


/* Parte Facturas*/

insert into PAQUETETURISTICOENTITY(id,Completado) values (10000,1);
insert into PAQUETETURISTICOENTITY(id,Completado) values (10001,0);
insert into PAQUETETURISTICOENTITY(id,Completado) values (10002,0);

insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10000,800000,'a',10001,10000);
insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10001,900000,'b',10001,10000);
insert into FACTURAENTITY (id,costo,name,TARJETADECREDITO_ID,PAQUETETURISTICO_ID) values (10002,1000000,'c',10001,10000);

insert into PAGOENTITY (id,costo, name) values (10000,80.33,'monserrate');
insert into PAGOENTITY (id,costo, name) values (10003,80.33,'monserrate');
insert into PAGOENTITY (id,costo, name) values (10005,80.33,'monserrate');

insert into PLANAGENDADOENTITY (id,fecha,name,plan_id) values (10000,'2014-07-02 06:14:00','f',10000);
insert into PLANAGENDADOENTITY (id,fecha,name,plan_id) values (10020,'2010-06-22 04:30:00','a',10001);
insert into PLANAGENDADOENTITY (id,fecha,name,plan_id) values (10030,'2011-04-16 11:50:00','s',10002);

insert into PAQUETETURISTICOENTITY_PAGOENTITY (paqueteturisticoentity_id, pagos_id) values (10000,10000);
insert into PAQUETETURISTICOENTITY_PAGOENTITY (paqueteturisticoentity_id, pagos_id) values (10000,10003);
insert into PAQUETETURISTICOENTITY_PLANAGENDADOENTITY (paqueteturisticoentity_id, planes_id) values (10000,10000);
insert into PAQUETETURISTICOENTITY_PLANAGENDADOENTITY (paqueteturisticoentity_id, planes_id) values (10000,10020);

insert into PLANENTITY_GUIAENTITY (PLANENTITY_ID, GUIAS_ID) values (10000, 10000);
insert into PLANENTITY_GUIAENTITY (PLANENTITY_ID, GUIAS_ID) values (10000, 10001);
insert into PLANENTITY_GUIAENTITY (PLANENTITY_ID, GUIAS_ID) values (9999, 10000);
insert into PLANENTITY_GUIAENTITY (PLANENTITY_ID, GUIAS_ID) values (9999, 10001);
insert into PLANENTITY_PREFERENCIASENTITY (PLANENTITY_ID, PREFERENCIASPLAN_ID) values (9999, 10000);
insert into PLANENTITY_PREFERENCIASENTITY (PLANENTITY_ID, PREFERENCIASPLAN_ID) values (9999, 10001);
