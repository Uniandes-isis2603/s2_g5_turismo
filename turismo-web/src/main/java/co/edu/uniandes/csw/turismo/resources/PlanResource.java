package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.GuiaDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PlanDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.PreferenciasDetailDTO;
import co.edu.uniandes.csw.turismo.dtos.ValoracionesDetailDTO;
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
 * <pre>Clase que implementa el recurso "plans".
 * URL: /api/plans
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Plans".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 */
@Path("plans")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlanResource {

    /**
     * <h1>POST /api/Planes : Crear un Plan.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link PlanDetailDTO}.
     *
     * Crea un nuevo Plan con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo Plan .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Plan.
     * </code>
     * </pre>
     *
     * @param Plan {@link PlanDetailDTO} - el Plan que se desea guardar.
     * @return JSON {@link PlanDetailDTO} - el Plan guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el Plan.
     */
    @POST
    public PlanDetailDTO createPlan(PlanDetailDTO Plan) throws BusinessLogicException
    {
        return Plan;
    }

    /**
     * <h1>GET /api/plans : Obtener todos los Planes.</h1>
     *
     * <pre>Busca y devuelve todos los Planes que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los Planes de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link PlanDetailDTO} - Los Planes encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PlanDetailDTO> getPlans() 
    {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/plans/{id} : Obtener Plan por id.</h1>
     *
     * <pre>Busca el Plan con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Plan correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se esta buscando. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link PlanDetailDTO} - el Plan buscado
     */
    @GET
    @Path("{id: \\d+}")
    public PlanDetailDTO getPlan(@PathParam("id") Long id)
    {
        return null;
    }

    /**
     * <h1>GET /api/plans/{tiposPlan} : Obtener Planes segun tipos de plan.</h1>
     *
     * <pre>Busca planes con los tipos de plan asociados recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los Planes correspondiente al los tipos de plan ingresados.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existen planes con los tipos dados.
     * </code>
     * </pre>
     *
     * @param tiposPlan, tipos de plan de interes. Este debe ser una cadena de
     * caracteres.
     * @return JSON {@link PlanDetailDTO} - el Plan buscado
     */
    @GET
    @Path("{tiposPlan: [a-zA-Z][a-zA-Z]*}}")
    public List<PlanDetailDTO> getPlanType(@PathParam("tiposPlan")String tiposPlan)
    {
        return new ArrayList<>();
    }

    /**
     * <h1>PUT /api/plans/{id} : Actualizar Plan con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PlanDetailDTO}.
     *
     * Actualiza el Plan con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el Plan con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se desea actualizar.Este debe ser
     * una cadena de dígitos.
     * @param Plan {@link PlanDetailDTO} el Plan que se desea guardar.
     * @return JSON {@link PlanDetailDTO} - el Plan guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el Plan porque ya
     * existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PlanDetailDTO updatePlan(@PathParam("id")Long id, PlanDetailDTO Plan) throws BusinessLogicException 
    {
        return Plan;
    }

    /**
     * <h1>DELETE /api/plans/{id} : Borrar Plan por id.</h1>
     *
     * <pre>Borra el Plan con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se desea borrar. Este debe ser una
     * cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePlan(@PathParam("id")Long id) 
    {
        // Void
    }
    
    /**
     * <h1>GET /api/plans/{id}/preferences : Obtener preferencias (tipos/categorias del plan).</h1>
     *
     * <pre>Retorna las preferencias de el Plan con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se obtienen las preferencias de el Plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan del cual se desean obtener las pref. Este debe ser una
     * cadena de dígitos.
     * @return PreferenciasDetailDTO , este contien las preferencias (tipos/categorias) del plan
     */
    @GET
    @Path("{id: \\d+}/preferences")
    public PreferenciasDetailDTO getPreferencesPlan(@PathParam("id")Long id)
    {
        return new PreferenciasDetailDTO();
    }
       
    /**
     * <h1>GET /api/plans/{id}/guides : Obtener guias posibles para el plan).</h1>
     *
     * <pre>Retorna los guias de el Plan con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se obteienen los guias de el Plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan del cual se desean obtener los guias. Este debe ser una
     * cadena de dígitos.
     * @return List<GuiaDetailDTO> , esta contiene los guias del plan
     */
     @GET
     @Path("{id: \\d+}/guides")
     public List<GuiaDetailDTO> getGuiasPlan(@PathParam("id") Long id)
     {
         return new ArrayList<>();
     }
     
      /**
     * <h1>GET /api/plans/{id}/valoraciones: Obtener valoraciones del plan).</h1>
     *
     * <pre>Retorna las valoraciones de el Plan con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se obteienen las valoraciones de el Plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan del cual se desean obtener las valoraciones. Este debe ser una
     * cadena de dígitos.
     * @return List<> , esta contiene las valoraciones del plan
     */
     @GET
     @Path("(id:\\d+)/valoraciones")
     public List<ValoracionesDetailDTO> getValoracionesPlan(@PathParam("id") Long id)
     {
         return new ArrayList<>();
     }
}
