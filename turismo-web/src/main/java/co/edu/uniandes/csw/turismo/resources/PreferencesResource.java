/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;


import co.edu.uniandes.csw.turismo.dtos.PreferenciasDetailDTO;

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
 *
 * @author jc.montoyar
 */
@Path("/users/{idUser: \\d+}/preferences")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PreferencesResource
{
/**
     * <h1>GET /api/users/{idUser}/preferences : Obtener preferencias del usuario con id dado.</h1>
     * 
     * <pre>Obtener preferencias de usuario dado su id.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK se obtienen las preferencias del usuario.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una usuario con el id dado.
     * </code>
     * </pre>
     * @param idUser Identificador del usuario del cual se desea obtener las pref. Este debe ser una cadena de dígitos.
     * @return  PreferenciasDetailDTO, este tiene las preferencias del usuario con el id ingresado por parametro
     */
    @GET
    public PreferenciasDetailDTO getPreferenciasUsuario(@PathParam("idUser") Long idUser)
    {
        return new PreferenciasDetailDTO();
    }
   
    /**
     * <h1>PUT /api/users/{idUser}/preferences : Actualizar preferencias usuario con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PreferenciasDetailDTO}.
     *
     * Actualiza las preferencias de usuario con el idUsuario recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza las preferencias del usuario con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una usuario con el id dado.
     * </code>
     * </pre>
     *
     * @param idUser Identificador de el plan que se desea actualizar.Este debe ser
     * una cadena de dígitos.
     * @param preferencias {@link PreferenciasDetailDTO} las preferencias que se desean guardar.
     * @return JSON {@link PreferenciasDetailDTO} - las preferencias guardadas.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} se ingresan tipos de plan que no existen, o datos irrelevantes
     */
    @PUT
    public PreferenciasDetailDTO updatePreferenciaUser(@PathParam("idUser")Long idUser, PreferenciasDetailDTO preferencias) throws BusinessLogicException 
    {
        return preferencias;
    }
    
    /**
     * <h1>POST /api/users/{idUser}/preferences: Crear unas preferencias para el usuario con id dado.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link PreferencesDetailDTO}.
     *
     * Crea unas preferencias para un usuario con id dado con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó preferencias para el usuario con id dado.
     * </code>
     * </pre>
     *
     * @param preferencias {@link PreferenciasDetailDTO} - las preferencias que se desea guardar.
     * @param idUser al que se le añadiran las preferencias
     * @return JSON {@link PrefernciasDetailDTO} - las preferencias guardadas para usuario con id dado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando se ingresan tipos/ categorias plan que no existen o informacion irrelevante
     */
    @POST
    public PreferenciasDetailDTO createPreferencesForUser(PreferenciasDetailDTO preferencias, @PathParam("idUser")Long idUser) throws BusinessLogicException
    {
        return preferencias;
    }
    
    /**
     * <h1>DELETE /api/users/{idUser}/preferences : Borrar preferencias de usuario por id.</h1>
     * 
     * <pre>Borra las preferencias del usuario con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina las preferencias correspondiente al id de usuario dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un usuario con el id dado.
     * </code>
     * </pre>
     * @param idUser Identificador de la ciudad que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
     public void deletePreferenceUser(@PathParam("idUser") Long idUser)
    {
        // Void
    }
}