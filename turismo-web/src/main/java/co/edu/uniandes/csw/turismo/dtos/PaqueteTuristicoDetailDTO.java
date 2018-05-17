/**
 * PaqueteTuristicoDetailDTO
 * Objeto de transferencia de datos de paquetes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
	"id":number,
	"completado":boolean,
	"pagos":[id:numbre],
	"planes":[id:number]
}
 * Por ejemplo un paquete detallado se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
	"id":1023,
	"completado":false,
	"pagos":[id:10000],
	"planes":[id:10000]
}
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PagoEntity;
import co.edu.uniandes.csw.turismo.entities.PaqueteTuristicoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Clase que extiende de {@link PaqueteTuristicoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 * 
 * @author dl.avendano
 */
public class PaqueteTuristicoDetailDTO  extends PaqueteTuristicoDTO{
   /**
     * Atributo que modela la lista de pagos que hay para el paquete
     */
    private List<PagoDTO> pagos; 
    /**
     * Atributo que modela la lista de pagos que hay para el paquete
     */
    private List<PlanAgendadoDetailDTO> planes; 
    
    /**
     * Constructor por defecto
     */
    public PaqueteTuristicoDetailDTO ()
    {
        super();

    }
    /**
     * Constructor a partir de una entity de paquete
     * @param entity 
     */
    public PaqueteTuristicoDetailDTO(PaqueteTuristicoEntity entity) {
        
        super(entity);
        
        if(entity.getPagos()!=null)
        {
            pagos = new ArrayList<>();
            for (PagoEntity entityPagos : entity.getPagos()) {
                pagos.add(new PagoDTO(entityPagos));
            }
        }
        if( entity.getPlanes()!=null)
        {
            planes = new ArrayList<>();
            
            for (PlanAgendadoEntity entityPlanes : entity.getPlanes()) {
                planes.add(new PlanAgendadoDetailDTO(entityPlanes));
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     * @return La entidad que representa el paquete.
     */
     @Override
    public PaqueteTuristicoEntity toEntity() {
        
        PaqueteTuristicoEntity entity = super.toEntity();
        if (planes != null) {
            List<PlanAgendadoEntity> planesEntity = new ArrayList<>();
            for (PlanAgendadoDetailDTO dtoPlan : planes) {
                planesEntity.add(dtoPlan.toEntity());
            }
            entity.setPlanes(planesEntity);
        }
        if (pagos != null) {
            List<PagoEntity> pagosEntity = new ArrayList<>();
            for (PagoDTO dtoPago : pagos) {
                pagosEntity.add(dtoPago.toEntity());
            }
            entity.setPagos(pagosEntity);
        }

        return entity;
    }
    /**
    * @return pagos del paquete
    */
    public List<PagoDTO> getPagos() {
        return pagos;
    }

    /**
    *cambia los pagos del paquete.
    * @param Pagos nueva lista de pagos
    */
    public void setPagos(List<PagoDTO> Pagos) {
        this.pagos = Pagos;
    }
    /**
    * @return planes del paquete
    */
    public List<PlanAgendadoDetailDTO> getPlanes() {
        return planes;
    }
    /**
    *cambia los planes del paquete.
    * @param Planes nueva lista de planes
    */
    public void setPlanes(List<PlanAgendadoDetailDTO> Planes) {
        this.planes = Planes;
    }
    
}
