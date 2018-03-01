/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dl.avendano
 */
@Entity
public class PaqueteTuristicoEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToMany
    private List<PagoEntity> pagos = new ArrayList<PagoEntity>();
    
    @PodamExclude
    @OneToMany
    private List<PlanAjendadoEntity> planes = new ArrayList<PlanAjendadoEntity>();

    public List<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

    public List<PlanAjendadoEntity> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlanAjendadoEntity> planes) {
        this.planes = planes;
    }
    
    
}
