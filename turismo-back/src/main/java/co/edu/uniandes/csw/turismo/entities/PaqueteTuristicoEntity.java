 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dl.avendano
 */
@Entity
public class PaqueteTuristicoEntity  {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    private Boolean completado;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @PodamExclude
    @OneToMany
    private List<PagoEntity> pagos ;
    
    @PodamExclude
    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = true) 
    private List<PlanAgendadoEntity> planes;


    public List<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    public List<PlanAgendadoEntity> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlanAgendadoEntity> planes) {
        this.planes = planes;
    }

    public void setCompletado(Boolean completado) {
        this.completado=completado;
    }
    
    public Boolean getCompletado() {
        return completado;
    }
    
    
}
