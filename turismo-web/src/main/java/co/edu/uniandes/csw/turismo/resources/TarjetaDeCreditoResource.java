/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.TarjetaDeCreditoDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "TarjetasDeCredito".
 * URL: /api/tarjetas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "facturas".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author s.benitez10
 */
@Path("tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaDeCreditoResource 
{
    private TarjetaDeCreditoLogic tarjetadecreditologic;
     /**
     * <h1>POST /api/tarjetas : Crear una TarjetaDeCredito.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link TarjetaDeCreditoDetailDTO}.
     * 
     * Crea una nueva Tarjeta de credito con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva tarjeta de credito .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la Tarjeta de credito.
     * </code>
     * </pre>
     * @param TarjetaDecredito {@link TarjetaDeCreditoDetailDTO} - La factura que se desea guardar.
     * @return JSON {@link TarjetaDeCreditoDetailDTO}  - La factura guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public TarjetaDeCreditoDetailDTO createTarjetaDecredito(TarjetaDeCreditoDetailDTO TarjetaDecredito) throws BusinessLogicException {
        return new  TarjetaDeCreditoDetailDTO(tarjetadecreditologic.createTarjetaDeCredito(TarjetaDecredito.toEntity()));
    }  
     /**
     * <h1>GET /api/tarjetas : Obtener todas las Tarjetas de Credito.</h1>
     * 
     * <pre>Busca y devuelve todas las Tarjeta de Credito que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las Tarjeta de credito de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link TarjetaDeCreditoDetailDTO} - Las Tarjeta de credito encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    
    @GET
    public List<TarjetaDeCreditoDetailDTO> geTarjetaDecredito() {
        return new ArrayList<>();
    }
    
     /**
     * <h1>GET /api/tarjetas/{id} : Obtener la tarjeta de credito por id.</h1>
     * 
     * <pre>Busca la tarjeta de credito con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la la tarjeta de credito correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una la tarjeta de credito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la tarjeta de credito que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link TarjetaDeCreditoDetailDTO} - La factura buscada
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO getTarjetaDecredito(@PathParam("id") Long id) {
        return null;
    }
    /**
     * <h1>PUT /api/tarjetas/{id} : Actualizar tarjeta de credito con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link TarjetaDeCreditoDetailDTO}.
     * 
     * Actualiza la tarjeta de credito con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la tarjeta de credito con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una tarjeta de credito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la tarjeta de credito que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param TarjetaDeCredito {@link TarjetaDeCreditoDetailDTO} La tarjeta de credito que se desea guardar.
     * @return JSON {@link TarjetaDeCreditoDetailDTO} - La tarjeta de credito guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la tarjeta de credito porque ya existe una con ese nombre.
     */
    
     @PUT
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO updateTarjetaDeCredito(@PathParam("id") Long id, TarjetaDeCreditoDetailDTO TarjetaDeCredito) throws BusinessLogicException {
        return TarjetaDeCredito;
    }
    /**
     * <h1>DELETE /api/tarjetas/{id} : Borrar tarjeta de credito por id.</h1>
     * 
     * <pre>Borra la tarjeta de credito con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la tarjeta de credito correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una tarjeta de credito con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la tarjeta de credito que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteTarjetaDeCredito(@PathParam("id") Long id) {
        // Void
    }
    
}
