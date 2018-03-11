/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.ValoracionesDTO;
import co.edu.uniandes.csw.turismo.dtos.ValoracionesDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "plans/Valoraciones".
 * URL: /api/plans/Valoraciones
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Plans/Valoraciones".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 * @plan jc.montoyar
 */
@Path("plans/{plansId: \\d+}/valoraciones")
public class Plan_ValoracionesResource 
{
    @Inject private PlanLogic planLogic;
    
    /**
     * Convierte una lista de ValoracionesEntity a una lista de ValoracionesDetailDTO.
     *
     * @param entityList Lista de ValoracionesEntity a convertir.
     * @return Lista de ValoracionesDetailDTO convertida.
     * 
     */
    private List<ValoracionesDTO> ValoracionesListEntity2DTO(List<ValoracionesEntity> entityList) 
    {
        List<ValoracionesDTO> list = new ArrayList<>();
        for (ValoracionesEntity entity : entityList) 
        {
            list.add(new ValoracionesDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ValoracionesDetailDTO a una lista de ValoracionesEntity.
     *
     * @param dtos Lista de ValoracionesDetailDTO a convertir.
     * @return Lista de ValoracionesEntity convertida.
     * 
     */
    private List<ValoracionesEntity> ValoracionesListDTO2Entity(List<ValoracionesDetailDTO> dtos) 
    {
        List<ValoracionesEntity> list = new ArrayList<>();
        for (ValoracionesDetailDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     /**
     * <h1>GET /api/plans/{plansId}/valoraciones : Obtener todas las Valoraciones de un plan.</h1>
     *
     * <pre>Busca y devuelve todas las Valoracioness que existen en un plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los Valoracioness del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se buscan la valoracion
     * @return JSONArray {@link BookDetailDTO} - Las valoraciones encontradas en el plan. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ValoracionesDTO> listValoraciones(@PathParam("plansId") Long plansId)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return ValoracionesListEntity2DTO(planLogic.listValoraciones(plansId));
    }

    /**
     * <h1>GET /api/plans/{plansId}/valoraciones/{valoracionesID} : Obtener un Valoraciones de un plan.</h1>
     *
     * <pre>Busca y devuelve la Valoracion con el ID recibido en la URL, relativo a un
     * plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Valoraciones del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se busca el Valoraciones
     * @param valoracionesID El ID del Valoraciones que se busca
     * @return {@link BookDetailDTO} - El Valoraciones encontrado en el plan.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    @Path("{valoracionesID: \\d+}")
    public ValoracionesDTO getValoraciones(@PathParam("plansId") Long plansId, @PathParam("valoracionesID") Long valoracionesID) throws BusinessLogicException 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
      return new ValoracionesDTO(planLogic.getVal(plansId, valoracionesID));
    }

    /**
     * <h1>POST /api/plans/{plansId}/Valoraciones/{valoracionesID} : Aociar un Valoraciones a un plan.</h1>
     *
     * <pre> Asocia un Valoraciones existente con un plan existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el Valoraciones .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el Valoraciones o el plan
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el Valoraciones
     * @param valoracionesID El ID del Valoraciones que se asocia
     * @return JSON {@link BookDetailDTO}  - El Valoraciones asociado.
     */
    @POST
    @Path("{valoracionesID: \\d+}")
    public ValoracionesDTO addValoraciones(@PathParam("plansId") Long plansId, @PathParam("valoracionesID") Long valoracionesID) 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
      return new ValoracionesDTO(planLogic.addValoracion(valoracionesID, plansId));
    }

    /**
     * <h1>PUT /api/plans/{plansId}/Valoraciones/ : Actualizar los Valoracioness de un plan..</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link BookDetailDTO}.
     * 
     * Actualiza la lista de Valoracioness de un plan con la lista que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista de Valoracioness
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el Valoraciones
     * @param valoraciones JSONArray {@link BookDetailDTO} - La lista de Valoracioness que se desea guardar.
     * @return JSONArray {@link BookDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<ValoracionesDTO> replaceValoraciones(@PathParam("plansId") Long plansId, List<ValoracionesDetailDTO> valoraciones)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
       return ValoracionesListEntity2DTO(planLogic.replaceValoraciones(plansId, ValoracionesListDTO2Entity(valoraciones)));
    }

    /**
     * <h1>DELETE /api/plans/{plansId}/valoraciones/{id} : Desasociar Valoraciones por id.</h1>
     *
     * <pre>Elimina la conexión entre la valoracion y el plan recibidos en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia al Valoraciones.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Valoraciones con el id dado en el plan.
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a desasocia la Valoracion
     * @param valoracionesID El ID de la Valoracion que se desasocia
     */
    @DELETE 
    @Path("{valoracionesID: \\d+}")
    public void removeValoraciones(@PathParam("plansId") Long plansId, @PathParam("valoracionesID") Long valoracionesID)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        planLogic.removeValoracion(valoracionesID, plansId);
    }

}
