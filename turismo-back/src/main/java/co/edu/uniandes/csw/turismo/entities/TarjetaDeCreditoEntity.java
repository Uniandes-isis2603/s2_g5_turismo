/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.benitez10
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity implements Serializable
{
    /*
     * atributo que se encarga del numero de la tarjeta
    */
    private Long numero;
    /*
     * atributo que se encarga del nombre dueño de la tarjeta
    */
    private String name;
    /*
     * atributo que se encarga del numero de seguridad de la tarjeta o CDV
    */
    private Long CDV;
    /*
     * atributo que se encarga de la cedula de la tarjeta
    */
    private Long cedula;
    
    /*
     * asociación con el usuario que es dueño de la tarjeta
    */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    /**
     *@return cedula de la tarjeta
    */
    public Long getCedula() {
        return cedula;
    }
    /**
     * Establece la cedula de la tarjeta
     * @param cedula 
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

     /**
     *@return usuario de la tarjeta
    */
    public UsuarioEntity getUsuario() {
        return usuario;
    }
     /**
      * Establece el usuario de la tarjeta
      * @param usuario 
      */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

     /**
      * 
      * @return el numero de la tarjeta 
      */
    public Long getNumero() 
    {
        return numero;
    }
    /**
     * Establece el numero de la tarjeta
     * @param numero 
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
     * Establece el nombre que contiene la tarjeta
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return del numero de CDV
     */
    public Long getCDV() {
        return CDV;
    }
    /**
     * Establece el numero de seguridad para realizar los pagos
     * @param CDV 
     */
    public void setCDV(Long CDV) {
        this.CDV = CDV;
    }
    
    
    
}
