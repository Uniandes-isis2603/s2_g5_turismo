/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author s.benitez10
 */
@Entity
public class UbicacionEntity extends BaseEntity implements Serializable
{
    /*
    * atributo del pais de la ubicacion
    */
    private String pais;
     /*
    * atributo de la ciudad de la ubicacion
    */
    private String ciudad;
     /*
    * atributo de la latitud de la ubicacion
    */
    private Double latitud;
     /*
    * atributo de la longitud de la ubicacion
    */
    private Double longitud;
    /**
     * 
     * @return pais de la ubicacion
     */
    public String getPais() {
        return pais;
    }
    /**
     * Establece en que pais se encuentra la ubicacion
     * @param pPais 
     */
    public void setPais(String pPais) {
        this.pais = pPais;
    }
    /**
     * 
     * @return la ciudad de la ubicacion 
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * Establece la ciudad en donde se encuentra la ubicacion
     * @param pCiudad 
     */
    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }
    /**
     * 
     * @return la latitud de la ubicacion 
     */
    public Double getLatitud() {
        return latitud;
    }
    /**
     * Establece la latitud de la ubicacion
     * @param pLatitud 
     */
    public void setLatitud(Double pLatitud) {
        this.latitud = pLatitud;
    }
    /**
     * 
     * @return la longitud de la ubicacion 
     */
    public Double getLongitud() {
        return longitud;
    }
    /**
     * Establece la longitud de la ubicacion
     * @param pLongitud 
     */
    public void setLongitud(Double pLongitud) {
        this.longitud = pLongitud;
    }
    
    
}
