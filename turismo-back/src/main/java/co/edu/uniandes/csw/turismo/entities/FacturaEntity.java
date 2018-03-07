/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.benitez10
 */
@Entity
public class FacturaEntity extends  BaseEntity implements Serializable
{
    
    private Long costo; // atributo del costo de la factura
   
    @PodamExclude
    @ManyToOne
    private TarjetaDeCreditoEntity tarjetadecredito;
    
    @OneToOne( cascade=CascadeType.PERSIST, orphanRemoval = true)
    private PaqueteTuristicoEntity paqueteturistico;
    

    public TarjetaDeCreditoEntity getTarjetadecredito() {
        return tarjetadecredito;
    }

    public void setTarjetadecredito(TarjetaDeCreditoEntity tarjetadecredito) {
        this.tarjetadecredito = tarjetadecredito;
    }

    public PaqueteTuristicoEntity getPaqueteturistico() {
        return paqueteturistico;
    }

    public void setPaqueteturistico(PaqueteTuristicoEntity paqueteturistico) {
        this.paqueteturistico = paqueteturistico;
    }

    
    public Long getCosto() {
        return costo;
    }
     /**
     * @Param costo
     */
    public void setCosto(Long costo) {
        this.costo = costo;
    }
    
    
}
