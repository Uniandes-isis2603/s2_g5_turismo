/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jc.montoyar
 */
@Entity
public class PlanEntity extends BaseEntity
{
    //ATRIBUTOS 
    
    /**
     * Modela los guias del plan
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST) 
    private List<GuiaEntity> guias;
    
    /**
     * Modela la ubicacion del plan
     */
    //@PodamExclude
    //@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST) 
    //private UbicacionEntity ubicacion;
    
    /**
     * Modela las categorias o tipos de plan asociado al plan
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST) 
    private List<PreferenciasEntity> preferenciasPlan = new ArrayList<>();
    
    /**
     * Modela las valoraciones del plan
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<ValoracionesEntity> valoracionesPlan;

    ///**
     //* @return ubicacion asociada al plan 
     //*/
    //public UbicacionEntity getUbicacion() 
    //{
    //    return ubicacion;
    //}

    ///**
     //* Cambia la ubicacion del plan por la dada por parametro
     //* @param ubicacion 
     //*/
    //public void setUbicacion(UbicacionEntity ubicacion)
    //{
      //  this.ubicacion = ubicacion;
    //}
    
    /**
     * @return valoraciones asociadas al plan 
     */
    public List<ValoracionesEntity> getValoracionesPlan() 
    {
        return valoracionesPlan;
    }

    /**
     * Cambia las valoraciones del plan por las dadas por parametro
     * @param valoracionesPlan 
     */
    public void setValoracionesPlan(List<ValoracionesEntity> valoracionesPlan) {
        this.valoracionesPlan = valoracionesPlan;
    }

    
    /**
     * @return preferencias/ categorias del plan
     */
    public List<PreferenciasEntity> getPreferenciasPlan() 
    {
        return preferenciasPlan;
    }

    /**
     * Cambia las preferencias del plan por las dadas por parametro
     * @param preferenciasPlan 
     */
    public void setPreferenciasPlan(List<PreferenciasEntity> preferenciasPlan) 
    {
        this.preferenciasPlan = preferenciasPlan;
    }

    
    /**
     * @return guias del plan
     */
    public List<GuiaEntity> getGuias() 
    {
        return guias;
    }

    /**
     * Cambia los guias por los dados por parametro
     * @param guias 
     */
    public void setGuias(List<GuiaEntity> guias) 
    {
        this.guias = guias;
    }
    
    /**
     * Atributo que modela la descripcion del plan
     */
    private String descripcion;

    /**
     * Atriburo que modela el pais del plan
     */
    private String pais;

    /**
     * atributo que modela la ciudad del plan
     */
    private String ciudad;

    /**
     * Atributo que modela la longitud del plan
     */
    private Double longitud;

    /**
     * Atriburo que modela la latitud
     */
    private Double latitud;

    /**
     * atributo que modela la duracion del plan
     */
    private Integer duracion;

    /**
     * Atriburo que modela las restricciones del plan
     */
    private String restricciones;

    /**
     * Atributo que modela una Stringn del plan
     */
    private String archivo;

    /**
     * Atriburo que modela el precio del plan
     */
    private Double precio;

    /**
     * Atributo que modela la cantidad de personas recomendada para el plan
     */
    private Integer cantidadPersonas;

    //GETTERS Y SETTERS
    
    /**
     * @return la descripcion 
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * Cambia la descripcion por la dada por parametro
     * @param descripcion 
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * @return el pais 
     */
    public String getPais()
    {
        return pais;
    }

    /**
     * Cambia el pais por la dada por parametro
     * @param pais 
     */
    public void setPais(String pais) 
    {
        this.pais = pais;
    }

    /**
     * @return la ciudad 
     */
    public String getCiudad()
    {
        return ciudad;
    }

    /**
     * Cambia la ciudad del plan por la dada por parametro
     * @param ciudad 
     */
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    /**
     * @return la longitud 
     */
    public Double getLongitud()
    {
        return longitud;
    }

    /**
     * Cambia la longitud por la dada por parametro
     * @param longitud 
     */
    public void setLongitud(Double longitud) 
    {
        this.longitud = longitud;
    }

    /**
     * @return la latitud del plan 
     */
    public Double getLatitud()
    {
        return latitud;
    }

    /**
     * Cambia la latitud por la dada por parametro
     * @param latitud 
     */
    public void setLatitud(Double latitud)
    {
        this.latitud = latitud;
    }

    /**
     * @return la duracion del plan
     */
    public Integer getDuracion()
    {
        return duracion;
    }

    /**
     * Cambia la duracion por la dada por parametro
     * @param duracion 
     */
    public void setDuracion(Integer duracion)
    {
        this.duracion = duracion;
    }

    /**
     * @return las restricciones 
     */
    public String getRestricciones()
    {
        return restricciones;
    }

    /**
     * Cambia las restricciones por las dadas por parametro
     * @param restricciones
     */
    public void setRestricciones(String restricciones) 
    {
        this.restricciones = restricciones;
    }

    /**
     * @return el archivo 
     */
    public String getArchivo()
    {
        return archivo;
    }

    /**
     * cambia el archivo por el dado por parametro
     * @param archivo 
     */
    public void setArchivo(String archivo) 
    {
        this.archivo = archivo;
    }

    /**
     * @return el precio 
     */
    public Double getPrecio()
    {
        return precio;
    }

    /**
     * Cambia el precio por el dado por parametro
     * @param precio 
     */
    public void setPrecio(Double precio) 
    {
        this.precio = precio;
    }

    /**
     * @return la cantidad de personas 
     */
    public Integer getCantidadPersonas()
    {
        return cantidadPersonas;
    }

    /**
     * Cambia la cantidad de personas por las dadas por parametro
     * @param cantidadPersonas
     */
    public void setCantidadPersonas(Integer cantidadPersonas) 
    {
        this.cantidadPersonas = cantidadPersonas;
    }

    

    
}
