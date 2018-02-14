/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.BlogDTO;
import co.edu.uniandes.csw.turismo.dtos.ComentariosDTO;
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
 ** <pre>Clase que implementa el recurso "Blog".
 * URL: /api/blogs
 * </pre>
 *
 * @author lf.rivera10
 */

@Path("blogs")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BlogResource {
    
    /**
     * <h1>GET /api/blogs : Obtener todos los blogs.</h1>
     * 
     * <pre>Busca y devuelve todos los blogs que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los blogs de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link BlogDetailDTO} - los blogs encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    
    @GET
  public List<BlogDTO> GetBlogs ()
  {
       return new ArrayList<BlogDTO>();
      
  }
  
  /**
     * <h1>GET /api/blogs/{id} : Obtener blog por id.</h1>
     * 
     * <pre>Busca el blog con el id asociado recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el blog correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un blog con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del blog que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link BlogDetailDTO} - el blog buscado
     */
  
  @GET
  @Path("{id: \\d+}") 
public BlogDTO GetBlog (@PathParam("id") long id)  
{
   return new BlogDTO();
}

  
  
     /**
     * <h1>POST /api/blogs : Crear un blog.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link BlogDetailDTO}.
     * 
     * Crea un nuevo blog con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico 
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo Blog .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el blog.
     * </code>
     * </pre>
     * @param Blog {@link BlogDetailDTO} - el blog que se desea guardar.
     * @return JSON {@link BlogDetailDTO}  - el blog creado con el atributo id autogenerado.
     */
 
  @POST
  public BlogDTO CrearBlog (BlogDTO blog)
  {
  return blog;
  }
/**
     * <h1>PUT /api/blogs/{id} : Actualizar blog con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BlogDetailDTO}.
     * 
     * Actualiza el blog con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el blog con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un blog con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del blog que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param blog {@link BlogDetailDTO} el blog que se desea actualizar.
     * @return JSON {@link BlogDetailDTO} - el blog guardado.
     */
  @PUT
  @Path("{id: \\d+}") 
  public BlogDTO ActualizarBlog (@PathParam("id") long id, BlogDTO blog)
  {
  return blog;
  }
  /**
     * <h1>DELETE /api/blogs/{id} : Borrar blog por id.</h1>
     * 
     * <pre>Borra el blog con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el blog correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un blog con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del blog que se desea borrar. Este debe ser una cadena de dígitos.
     */
  @DELETE
  @Path("{id: \\d+}") 
  public boolean BorrarBlog (@PathParam("id") long id)
  {
  return true;
  }
  

 









}
