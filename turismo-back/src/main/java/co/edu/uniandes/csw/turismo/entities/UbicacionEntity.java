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
    private String pais;
    private String ciudad;
    private Double latitud;
    private Double longitud;
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pPais) {
        this.pais = pPais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double pLatitud) {
        this.latitud = pLatitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double pLongitud) {
        this.longitud = pLongitud;
    }
    
    
}
