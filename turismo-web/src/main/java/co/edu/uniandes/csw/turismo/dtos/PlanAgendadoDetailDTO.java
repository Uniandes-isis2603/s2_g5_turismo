/**
 * PaqueteTuristicoDetailDTO
 * Objeto de transferencia de datos de paquetes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
   "id":number,
    "fecha":date,
    "guia":{
     	"idGuia":number}
,
	"plan":{
		"idPlan":number	
	}
}
 * Por ejemplo un plan agendado detallado se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
   "id":9999,
    "fecha":"1998-04-07T00:00:00-05:00",
    "guia":{
     	"idGuia":10000}
,
	"plan":{
		"idPlan":10000	
	}
}
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanAgendadoEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dl.avendano
 */
public class PlanAgendadoDetailDTO extends PlanAgendadoDTO {
    /**
     * Atributo que modela el guia que hay para el plan agendado.
     */
    private GuiaDTO guia;
    /**
     * Atributo que modela el plan que hay para el plan agendado.
     */
    private PlanDTO plan;

    /**
     *Constructor por defecto.
     */
    public PlanAgendadoDetailDTO()
    {
        super();
    }
    /**
     *Convierte un entity a DTO.
     * @param entity del plan agendado
     */
    public PlanAgendadoDetailDTO(PlanAgendadoEntity entity) {
        super(entity);
        if (entity != null) {
            if( entity.getGuia() != null)
        {
            guia = new GuiaDTO(entity.getGuia());
        }
            if( entity.getPlan() != null)
        {
            plan = new PlanDTO(entity.getPlan());
        }
        }
    }
    
    /**
     *Convierte de DTO a entity.
     * @return PlanAgendadoEntity
     */
    @Override
    public PlanAgendadoEntity toEntity() {
        
        PlanAgendadoEntity entity = super.toEntity();
        if (plan != null) {
            entity.setPlan(this.getPlan().toEntity());
        }
        if (guia!= null) {
            entity.setGuia(this.getGuia().toEntity());
        }

        return entity;
    }
    /**
     * @return guia del plan agendado
     */
    public GuiaDTO getGuia() {
        return guia;
    }
    /**
     * asigna un guia nuevo
     * @param guia nuevo
     */
    public void setGuia(GuiaDTO guia) {
        this.guia = guia;
    }
    /**
     * @return plan del plan agendado
     */
    public PlanDTO getPlan() {
        return plan;
    }
    /**
     * asigna un plan nuevo
     * @param plan nuevo
     */
    public void setPlan(PlanDTO plan) {
        this.plan = plan;
    }
    
}
