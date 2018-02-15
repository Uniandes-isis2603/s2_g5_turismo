package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.GuiaDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PlanDetailDTO;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "guides".
 * URL: /api/guides
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "guias".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 */
@Path("guides")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class GuiaResource {

    /**
     * <h1>POST /api/guides : Crear un Guia.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link GuiaDetailDTO}.
     *
     * Crea un nuevo Guia con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo Guia .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Guia.
     * </code>
     * </pre>
     *
     * @param guia {@link GuiaDetailDTO} - el Guia que se desea guardar.
     * @return JSON {@link GuiaDetailDTO} - el Guia guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el Guia.
     */
    @POST
    public GuiaDetailDTO createGuia(GuiaDetailDTO guia) throws BusinessLogicException
    {
        return guia;
    }

    /**
     * <h1>GET /api/guides : Obtener todos los Guias.</h1>
     *
     * <pre>Busca y devuelve todos los Guias que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los Guias de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link GuiaDetailDTO} - Los Guias encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<GuiaDetailDTO> getGuias() 
    {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/guides/{id} : Obtener Guia por id.</h1>
     *
     * <pre>Busca el Guia con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Guia correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Guia con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Guia que se esta buscando. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link GuiaDetailDTO} - el Guia buscado
     */
    @GET
    @Path("{id: \\d+}")
    public GuiaDetailDTO getGuia(@PathParam("id") Long id)
    {
        return null;
    }

    /**
     * <h1>PUT /api/guides/{id} : Actualizar Guia con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link GuiaDetailDTO}.
     *
     * Actualiza el Guia con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el Guia con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Guia con el id dado.
     * </code>
     * </pre>
     *
     * @param idGuide Identificador del Guia que se desea actualizar.Este debe ser
     * una cadena de dígitos.
     * @param guia {@link GuiaDetailDTO} el Guia que se desea guardar.
     * @return JSON {@link GuiaDetailDTO} - el Guia guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el Guia porque ya
     * existe uno con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public GuiaDetailDTO updateGuia(@PathParam("id")Long idGuide, GuiaDetailDTO guia) throws BusinessLogicException 
    {
        return guia;
    }

    /**
     * <h1>DELETE /api/guides/{id} : Borrar Guia por id.</h1>
     *
     * <pre>Borra el Guia con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Guia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Guia con el id dado.
     * </code>
     * </pre>
     *
     * @param idGuia Identificador de el Guia que se desea borrar. Este debe ser una
     * cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteGuia(@PathParam("id")Long idGuia) 
    {
        // Void
    }
       
    /**
     * <h1>GET /api/guides/{id}/plans : Obtener planes asociados al guia).</h1>
     *
     * <pre>Retorna los planes asociados de el Guia con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se obtienen los planes de el Guia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Guia con el id dado.
     * </code>
     * </pre>
     *
     * @param idGuia Identificador de el Guia del cual se desean obtener los planes. Este debe ser una
     * cadena de dígitos.
     * @return List PlanDetailDTO , esta contiene los planes asociados del Guia
     */
     @GET
     @Path("{id: \\d+}/plans")
     public List<PlanDetailDTO> getPlanesGuia(@PathParam("id") Long idGuia)
     {
         return new ArrayList<>();
     }
}
