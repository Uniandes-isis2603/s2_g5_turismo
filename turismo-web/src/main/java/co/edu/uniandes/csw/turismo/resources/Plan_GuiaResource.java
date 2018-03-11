/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.GuiaDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
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
 * <pre>Clase que implementa el recurso "plans/guides".
 * URL: /api/plans/guides
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Plans/guides".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 * @plan jc.montoyar
 */
@Path("plans/{plansId: \\d+}/guides")
public class Plan_GuiaResource 
{
    @Inject private PlanLogic planLogic;
    
    /**
     * Convierte una lista de GuiaEntity a una lista de GuiaDetailDTO.
     *
     * @param entityList Lista de GuiaEntity a convertir.
     * @return Lista de GuiaDetailDTO convertida.
     * 
     */
    private List<GuiaDetailDTO> guidesListEntity2DTO(List<GuiaEntity> entityList) 
    {
        List<GuiaDetailDTO> list = new ArrayList<>();
        for (GuiaEntity entity : entityList) 
        {
            list.add(new GuiaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de GuiaDetailDTO a una lista de GuiaEntity.
     *
     * @param dtos Lista de guiaDetailDTO a convertir.
     * @return Lista de guiaEntity convertida.
     * 
     */
    private List<GuiaEntity> guidesListDTO2Entity(List<GuiaDetailDTO> dtos) 
    {
        List<GuiaEntity> list = new ArrayList<>();
        for (GuiaDetailDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     /**
     * <h1>GET /api/plans/{plansId}/guides : Obtener todos los guias de un plan.</h1>
     *
     * <pre>Busca y devuelve todos los guias que existen en un plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los guias del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se buscan los guias
     * @return JSONArray {@link BookDetailDTO} - Los guias encontrados en el plan. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<GuiaDetailDTO> listGuides(@PathParam("plansId") Long plansId)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return guidesListEntity2DTO(planLogic.listGuias(plansId));
    }

    /**
     * <h1>GET /api/plans/{plansId}/guides/{GuidesId} : Obtener un guia de un plan.</h1>
     *
     * <pre>Busca y devuelve el guia con el ID recibido en la URL, relativo a un
     * plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el guia del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se busca el guia
     * @param guidesId El ID del guia que se busca
     * @return {@link BookDetailDTO} - El guia encontrado en el plan.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    @Path("{GuidesId: \\d+}")
    public GuiaDetailDTO getGuides(@PathParam("plansId") Long plansId, @PathParam("guidesId") Long guidesId) throws BusinessLogicException 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return new GuiaDetailDTO(planLogic.getGuia(plansId, guidesId));
    }

    /**
     * <h1>POST /api/plans/{plansId}/guides/{GuidesId} : Aociar un guia a un plan.</h1>
     *
     * <pre> Asocia un guia existente con un plan existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el guia .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el guia o el plan
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el guia
     * @param guidesId El ID del guia que se asocia
     * @return JSON {@link BookDetailDTO}  - El guia asociado.
     */
    @POST
    @Path("{GuidesId: \\d+}")
    public GuiaDetailDTO addGuides(@PathParam("plansId") Long plansId, @PathParam("guidesId") Long guidesId) 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return new GuiaDetailDTO(planLogic.addGuia(guidesId, plansId));
    }

    /**
     * <h1>PUT /api/plans/{plansId}/guides/ : Actualizar los guias de un plan..</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link BookDetailDTO}.
     * 
     * Actualiza la lista de guias de un plan con la lista que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista de guias
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el guia
     * @param guides JSONArray {@link BookDetailDTO} - La lista de guias que se desea guardar.
     * @return JSONArray {@link BookDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<GuiaDetailDTO> replaceGuides(@PathParam("plansId") Long plansId, List<GuiaDetailDTO> guides)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return guidesListEntity2DTO(planLogic.replaceGuias(plansId, guidesListDTO2Entity(guides)));
    }

    /**
     * <h1>DELETE /api/plans/{plansId}/guides/{id} : Desasociar guia por id.</h1>
     *
     * <pre>Elimina la conexión entre el guia y e plan recibidos en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia al guia.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un guia con el id dado en el plan.
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a desasociar el guia
     * @param guidesId El ID del guia que se desasocia
     */
    @DELETE 
    @Path("{guidesId: \\d+}")
    public void removeGuides(@PathParam("plansId") Long plansId, @PathParam("guidesId") Long guidesId)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        planLogic.removeGuia(guidesId, plansId);
    }

}
