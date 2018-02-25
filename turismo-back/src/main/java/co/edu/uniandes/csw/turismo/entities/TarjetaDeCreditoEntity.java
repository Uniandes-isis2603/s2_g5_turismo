/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.benitez10
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity implements Serializable
{
    
    private Long numero;
    private String name;
    private Integer CDV;
    
    @PodamExclude
    @OneToMany(mappedBy = "facturas")
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }
    

     /**
     * @return del numero de la tarjete
     */
    public Long getNumero() 
    {
        return numero;
    }
    /**
     * @Param numero
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }
    /**
     * @return del nombre de la tarjeta
     */
    public String getName() {
        return name;
    }
    /**
     * @Param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return del numero de CDV
     */
    public Integer getCDV() {
        return CDV;
    }
    /**
     * @Param CDV
     */
    public void setCDV(Integer CDV) {
        this.CDV = CDV;
    }
    
    
    
}
