/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * Objeto de transferencia de las preferencias(tipos de plan)
 * @author jc.montoyar
 */
public class PreferenciasDTO 
{
    //ATRIBUTOS
    
    /**
     * Atriburo que modela un arreglo tipo string que contiene tipos de plan
     */
    private String[] tiposPlan;
    
    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public PreferenciasDTO(){}

    //GETTERS AND SETTERS
    
    /**
     * @return the tiposPlan
     */
    public String[] getTiposPlan() 
    {
        return tiposPlan;
    }

    /**
     * @param tiposPlan the tiposPlan to set
     */
    public void setTiposPlan(String[] tiposPlan)
    {
        this.tiposPlan = tiposPlan;
    }
}
