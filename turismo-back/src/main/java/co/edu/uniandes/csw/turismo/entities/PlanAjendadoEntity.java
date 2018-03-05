/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dl.avendano
 */
@Entity
public class PlanAjendadoEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne
    private GuiaEntity guia = new GuiaEntity();
    
    @PodamExclude
    @OneToOne
    private PlanEntity plan = new PlanEntity();

    @Temporal(TemporalType.TIMESTAMP)
    Date fecha = new Date();

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    public GuiaEntity getGuia() {
        return guia;
    }

    public void setGuia(GuiaEntity guia) {
        this.guia = guia;
    }

    public PlanEntity getPlan() {
        return plan;
    }

    public void setPlan(PlanEntity plan) {
        this.plan = plan;
    }

    
}
