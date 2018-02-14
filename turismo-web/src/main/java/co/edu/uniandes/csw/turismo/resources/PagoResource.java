/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PagoDetailDTO;
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
 *
 * @author dl.avendano
 */
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    /**
     * <h1>POST /api/pagos : Crear un nuevo pago.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * 
     * Crea una nueva ciudad con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva ciudad .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la ciudad.
     * </code>
     * </pre>
     * @param Pago {@link PagoDetailDTO} - El pago que se desea guardar.
     * @return JSON {@link PagoDetailDTO}  - El pago guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public PagoDetailDTO createPago(PagoDetailDTO pago) throws BusinessLogicException {
        return pago;
    }

    /**
     * <h1>GET /api/pagos : Obtener todos los pagos.</h1>
     * 
     * <pre>Busca y devuelve todas las ciudades que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las ciudades de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PagoDetailDTO} - Las ciudades encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PagoDetailDTO> getPagos() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/pagos/{id} : Obtener pago por id.</h1>
     * 
     * <pre>Busca el pago con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la ciudad correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una ciudad con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del pago que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@linkpagoDetailDTO} - La ciudad buscada
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDetailDTO getPago(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/pagos/{id} : Actualizar el pago con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PagoDetailDTO}.
     * 
     * Actualiza la ciudad con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la ciudad con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la ciudad que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param pago {@link PagoDetailDTO} La ciudad que se desea guardar.
     * @return JSON {@link PagoDetailDTO} - La ciudad guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la ciudad porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDetailDTO updatePago(@PathParam("id") Long id, PagoDetailDTO pago) throws BusinessLogicException {
        return pago;
    }
    
    /**
     * <h1>DELETE /api/pagos/{id} : Borrar pago por id.</h1>
     * 
     * <pre>Borra el pago con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la ciudad correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una ciudad con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del pago que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE 
    @Path("{id: \\d+}")
     public void deletePago(@PathParam("id") Long id) {
        // Void
    }
}
