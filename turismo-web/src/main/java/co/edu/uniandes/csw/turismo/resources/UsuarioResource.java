/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * <pre>Clase que implementa el recurso "usuario".
 * URL: /api/usuario
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "usuario".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author jf.gutierrez13  
 * @version 1.0
 */
@Path("usuario")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UsuarioResource 
{
    /**
     * <h1>POST /api/usuario : Crear un usuario.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link UsuarioDetailDTO}.
     * 
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo usuario .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el usuario.
     * </code>
     * </pre>
     * @param usuario {@link UsuarioDetailDTO} - El usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO}  - El usuario guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el usuario.
     */
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {
        return usuario;
    }
    
    /**
     * <h1>GET /api/usuario : Obtener todos los usuarios.</h1>
     * 
     * <pre>Busca y devuelve todos los usuarios que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los usuarios de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link UsuarioDetailDTO} - los usuarios encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        return new ArrayList<>();
    }
    
    /**
     * <h1>GET /api/usuarios/{id} : Obtener usuario por id.</h1>
     * 
     * <pre>Busca el usuario con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el usuario correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un usuario con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del usuario que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link UsuarioDetailDTO} - El usuario buscado
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/usuario/{id} : Actualizar usuario con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link UsuarioDetailDTO}.
     * 
     * Actualiza el usuario con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el usuario con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un usuario con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del usuario que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param usuario {@link UsuarioDetailDTO}el usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO} - El usuario guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el usuario porque ya existe uno con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws BusinessLogicException {
        return usuario;
    }
    
    /**
     * <h1>DELETE /api/usuario/{id} : Borrar usuario por id.</h1>
     * 
     * <pre>Borra el usuario con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el usuario correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un usuario con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del usuario que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteUsuario(@PathParam("id") Long id) {
        // Void
    }
}
