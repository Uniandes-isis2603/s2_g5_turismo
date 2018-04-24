package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PaqueteTuristicoDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PaqueteTuristicoLogic;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "paqueteTuristicos".
 * URL: /api/paqueteTuristicos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "PaqueteTuristicos".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 */
@Path("paquetes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PaqueteTuristicoResource 
{
    @Inject
    PaqueteTuristicoLogic paqueteLogic;
    
    /**
     * Recibe una lista de paqueteTuristico entities y la convierte a dtos
     * @param entityList
     * @return dtos lista
     */
    private List<PaqueteTuristicoDetailDTO> listPaqueteTuristicoEntity2DetailDTO(List<PaqueteTuristicoEntity> entityList) 
    {
        List<PaqueteTuristicoDetailDTO> list = new ArrayList<>();
        for (PaqueteTuristicoEntity entity : entityList) 
        {
            list.add(new PaqueteTuristicoDetailDTO(entity));
        }
        return list;
    }

    /**
     * <h1>POST /api/paqueteTuristicos : Crear un PaqueteTuristico.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     *
     * Crea un nuevo PaqueteTuristico con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo PaqueteTuristico .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el PaqueteTuristico.
     * </code>
     * </pre>
     *
     * @param paqueteTuristico {@link PaqueteTuristicoDetailDTO} - el PaqueteTuristico que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO} - el PaqueteTuristico guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el PaqueteTuristico.
     */
    @POST
    public PaqueteTuristicoDetailDTO createPaqueteTuristico(PaqueteTuristicoDetailDTO paqueteTuristico) throws BusinessLogicException
    {
        return new PaqueteTuristicoDetailDTO(paqueteLogic.createPaqueteTuristico(paqueteTuristico.toEntity()));
    }

    /**
     * <h1>GET /api/paqueteTuristicos : Obtener todos los PaqueteTuristicoes.</h1>
     *
     * <pre>Busca y devuelve todos los PaqueteTuristicoes que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los PaqueteTuristicoes de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link PaqueteTuristicoDetailDTO} - Los PaqueteTuristicoes encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PaqueteTuristicoDetailDTO> getPaqueteTuristicos() 
    {
        return listPaqueteTuristicoEntity2DetailDTO(paqueteLogic.getPaquetes());
    }

    /**
     * <h1>GET /api/paqueteTuristicos/{id} : Obtener PaqueteTuristico por id.</h1>
     *
     * <pre>Busca el PaqueteTuristico con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el PaqueteTuristico correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un PaqueteTuristico con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el PaqueteTuristico que se esta buscando. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link PaqueteTuristicoDetailDTO} - el PaqueteTuristico buscado
     */
    @GET
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO getPaqueteTuristico(@PathParam("id") Long id)
    {
        PaqueteTuristicoEntity entity = paqueteLogic.getPaquete(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /paqueteTuristicos/" + id + " no existe.", 404);
        }
        return new PaqueteTuristicoDetailDTO(entity);
    }

    /**
     * <h1>PUT /api/paqueteTuristicos/{id} : Actualizar PaqueteTuristico con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PaqueteTuristicoDetailDTO}.
     *
     * Actualiza el PaqueteTuristico con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el PaqueteTuristico con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un PaqueteTuristico con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el PaqueteTuristico que se desea actualizar.Este debe ser
     * una cadena de dígitos.
     * @param paqueteTuristico {@link PaqueteTuristicoDetailDTO} el PaqueteTuristico que se desea guardar.
     * @return JSON {@link PaqueteTuristicoDetailDTO} - el PaqueteTuristico guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el PaqueteTuristico porque ya
     * existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PaqueteTuristicoDetailDTO updatePaqueteTuristico(@PathParam("id")Long id, PaqueteTuristicoDetailDTO paqueteTuristico) throws BusinessLogicException 
    {
        paqueteTuristico.setId(id);
        PaqueteTuristicoEntity entity = paqueteLogic.getPaquete(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /paqueteTuristicos/" + id + " no existe.", 404);
        }
        return new PaqueteTuristicoDetailDTO(paqueteLogic.updatePaquete(paqueteTuristico.toEntity()));
    }

    /**
     * <h1>DELETE /api/paqueteTuristicos/{id} : Borrar PaqueteTuristico por id.</h1>
     *
     * <pre>Borra el PaqueteTuristico con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el PaqueteTuristico correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un PaqueteTuristico con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el PaqueteTuristico que se desea borrar. Este debe ser una
     * cadena de dígitos.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePaqueteTuristico(@PathParam("id")Long id) throws BusinessLogicException 
    {
        PaqueteTuristicoEntity entity = paqueteLogic.getPaquete(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /paquetes/" + id + " no existe.", 404);
        }
        paqueteLogic.deletePaqueteTuristico(id);
    }
}