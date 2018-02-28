/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PreferenciasDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
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
 * <pre>Clase que implementa el recurso "plans/Preferences".
 * URL: /api/plans/preferences
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Plans/Preferences".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que las servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 * @plan jc.montoyar
 */
@Path("plans/{plansId: \\d+}/preferences")
public class Plan_PreferenciasResource 
{
    @Inject private PlanLogic planLogic;
    
    /**
     * Convierte una lista de PreferenciasEntity a una lista de PreferenciasDetailDTO.
     *
     * @param entityList Lista de PreferenciasEntity a convertir.
     * @return Lista de PreferenciasDetailDTO convertida.
     * 
     */
    private List<PreferenciasDetailDTO> PreferencesListEntity2DTO(List<PreferenciasEntity> entityList) 
    {
        List<PreferenciasDetailDTO> list = new ArrayList<>();
        for (PreferenciasEntity entity : entityList) 
        {
            list.add(new PreferenciasDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PreferenciasDetailDTO a una lista de PreferenciasEntity.
     *
     * @param dtos Lista de PreferenciasDetailDTO a convertir.
     * @return Lista de PreferenciasEntity convertida.
     * 
     */
    private List<PreferenciasEntity> PreferencesListDTO2Entity(List<PreferenciasDetailDTO> dtos) 
    {
        List<PreferenciasEntity> list = new ArrayList<>();
        for (PreferenciasDetailDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     /**
     * <h1>GET /api/plans/{plansId}/preferences : Obtener todas las Preferencias de un plan.</h1>
     *
     * <pre>Busca y devuelve todos las Preferenciass que existen en un plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos las Preferenciass del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se buscan las Preferenciass
     * @return JSONArray {@link BookDetailDTO} - las Preferenciass encontrados en el plan. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PreferenciasDetailDTO> listPreferences(@PathParam("plansId") Long plansId)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return PreferencesListEntity2DTO(planLogic.listCategorias(plansId));
    }

    /**
     * <h1>GET /api/plans/{plansId}/preferences/{PreferencesId} : Obtener un Preferencias de un plan.</h1>
     *
     * <pre>Busca y devuelve el Preferencias con el ID recibido en la URL, relativo a un
     * plan.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Preferencias del plan.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un plan con el id dado.
     * </code>
     * @param plansId El ID del plan del cual se busca el Preferencias
     * @param PreferencesId El ID del Preferencias que se busca
     * @return {@link BookDetailDTO} - El Preferencias encontrado en el plan.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @GET
    @Path("{PreferencesId: \\d+}")
    public PreferenciasDetailDTO getPreferences(@PathParam("plansId") Long plansId, @PathParam("PreferencesId") Long PreferencesId) throws BusinessLogicException 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return new PreferenciasDetailDTO(planLogic.getPreferencias(plansId, PreferencesId));
    }

    /**
     * <h1>POST /api/plans/{plansId}/Preferences/{PreferencesId} : Aociar un Preferencias a un plan.</h1>
     *
     * <pre> Asocia un Preferencias existente con un plan existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el Preferencias .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el Preferencias o el plan
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el Preferencias
     * @param PreferencesId El ID del Preferencias que se asocia
     * @return JSON {@link BookDetailDTO}  - El Preferencias asociado.
     */
    @POST
    @Path("{PreferencesId: \\d+}")
    public PreferenciasDetailDTO addPreferences(@PathParam("plansId") Long plansId, @PathParam("PreferencesId") Long PreferencesId) 
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return new PreferenciasDetailDTO(planLogic.addPreferencia(PreferencesId, plansId));
    }

    /**
     * <h1>PUT /api/plans/{plansId}/Preferences/ : Actualizar las Preferencias de un plan..</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link BookDetailDTO}.
     * 
     * Actualiza la lista de Preferenciass de un plan con la lista que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista de Preferenciass
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a asociar el Preferencias
     * @param Preferences JSONArray {@link BookDetailDTO} - La lista de Preferenciass que se desea guardar.
     * @return JSONArray {@link BookDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<PreferenciasDetailDTO> replacePreferences(@PathParam("plansId") Long plansId, List<PreferenciasDetailDTO> Preferences)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        return PreferencesListEntity2DTO(planLogic.replacePreferencias(plansId, PreferencesListDTO2Entity(Preferences)));
    }

    /**
     * <h1>DELETE /api/plans/{plansId}/Preferences/{id} : Desasociar Preferencias por id.</h1>
     *
     * <pre>Elimina la conexión entre el Preferencias y e plan recibidos en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia al Preferencias.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Preferencias con el id dado en el plan.
     * </code>
     * </pre>
     * @param plansId El ID del plan al cual se le va a desasociar el Preferencias
     * @param PreferencesId El ID del Preferencias que se desasocia
     */
    @DELETE 
    @Path("{PreferencesId: \\d+}")
    public void removePreferences(@PathParam("plansId") Long plansId, @PathParam("PreferencesId") Long PreferencesId)
    {
        PlanEntity entity = planLogic.getPlan(plansId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + plansId + " no existe.", 404);
        }
        planLogic.removePreferencia(PreferencesId, plansId);
    }

}

