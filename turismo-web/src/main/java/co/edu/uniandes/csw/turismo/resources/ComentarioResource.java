/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.ComentarioDTO;
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
import co.edu.uniandes.csw.turismo.dtos.ComentariosDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.ComentarioLogic;
import co.edu.uniandes.csw.turismo.entities.ComentarioEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import javax.inject.Inject;

/**
 *
 ** <pre>Clase que implementa el recurso "Comentarios".
 * URL: /api/BlogsYComentarios/{id: \\d+}/Comentarios"
 * </pre>
 *
 * @author lf.rivera10
 */

@Path("blogs/{blogId: \\d+}/comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    
     @Inject
    ComentarioLogic comentarioLogic;
     
     
      
    /**
     * <h1>GET /api/blogs : Obtener todos los comentarios.</h1>
     * 
     * <pre>Busca y devuelve todos los comentarios que pertenecen a un blog y existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los comentarios del blog </code> 
     * </pre>
     * @param blogId
     * @return JSONArray {@link ComentariosDetailDTO} - los comentarios encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios (@PathParam("blogId") long blogId) throws BusinessLogicException
    {
       List<ComentarioEntity> lista =comentarioLogic.getComentarios(blogId);
       ArrayList<ComentarioDTO> respuesta = new ArrayList<>();
         for (ComentarioEntity Entity : lista) {
             respuesta.add(new ComentarioDTO(Entity));
         }
       return respuesta;
    }
    
     /**
     * <h1>GET /api/BlogsYComentarios/{id}/Comentarios/{id} : Obtener comentarios por id.</h1>
     * 
     * <pre>Busca el comentario con el id asociado recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el comentario correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un comentario con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del comentario que se esta buscando. Este debe ser una cadena de dígitos.
     * @param blogId
     * @return JSON {@link ComentariosDetailDTO} - el comentario buscado
     */
    
    @GET
    @Path("{id2: \\d+}") 
    public ComentarioDTO getComentario (@PathParam("id2") long id,@PathParam("blogId") long blogId ) throws BusinessLogicException
{
       return new ComentarioDTO(comentarioLogic.getComentario(id, blogId));
    
}
    
      /**
     * <h1>POST /api/BlogsYComentarios/{id}/Comentarios/{id} : Crear un comentario.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ComentariosDetailDTO}.
     * 
     * Crea un nuevo comentario con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico 
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo comentario .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el comentario.
     * </code>
     * </pre>
     * @param comentario {@link ComentariosDetailDTO} - el comentario que se desea guardar.
     * @param blogId
     * @return JSON {@link ComentariosDetailDTO}  - el comentario creado con el atributo id autogenerado.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    
    @POST
    public ComentarioDTO crearComentario (ComentarioDTO comentario, @PathParam("blogId") long blogId) throws BusinessLogicException
    {
        return new ComentarioDTO (comentarioLogic.createComentario(comentario.toEntity(), blogId));
    }
    
    /**
     * <h1>PUT /api/BlogsYComentarios/{id}/Comentarios/{id} : Actualizar comentario con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ComentariosDetailDTO}.
     * 
     * Actualiza el comentario con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el comentario con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un comentario con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del comentario que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param comentario {@link ComentariosDetailDTO} el comentario que se desea actualizar.
     * @return JSON {@link ComentariosDetailDTO} - el comentario guardado.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    
    @PUT
    public ComentarioDTO actualizarComentario (ComentarioDTO comentario, @PathParam("blogId") long blogId ) throws BusinessLogicException
    {
        comentarioLogic.updateComentario(comentario.toEntity(), blogId);
        return comentario;
    }
    
     /**
     * <h1>DELETE /api/BlogsYComentarios/{id}/Comentarios/{id} : Borrar comentario por id.</h1>
     * 
     * <pre>Borra el comentario con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el comentario correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un comentario con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del comentario que se desea borrar. Este debe ser una cadena de dígitos.
     * @param blogId
     * @return 
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    
    @DELETE
    @Path("{id2: \\d+}") 
    public void  borrarComentario (@PathParam("id2") long id, @PathParam("blogId") long blogId) throws BusinessLogicException
    {
        ComentarioEntity borrar = comentarioLogic.getComentario(id, blogId);
        comentarioLogic.deleteComentario(borrar, blogId);
        
    }
    
    
}