/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * Objeto de transferencia de los guias
 * @author jc.montoyar
 */
public class GuiaDTO
{
    //ATRIBUTOS

    /**
     * Atriburo que modela el nombre del guia
     */
    private String nombreGuia;

    /**
     * Atriburo que modela el idioma del guia
     */
    private String idiomaGuia;

    /**
     * Atriburo que modela el id del guia
     */
    private String idGuia;

    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public GuiaDTO() {}

    //METODOS
    /**
     * @return the nombreGuia
     */
    public String getNombreGuia()
    {
        return nombreGuia;
    }

    /**
     * @param nombreGuia the nombreGuia to set
     */
    public void setNombreGuia(String nombreGuia)
    {
        this.nombreGuia = nombreGuia;
    }

    /**
     * @return the idiomaGuia
     */
    public String getIdiomaGuia() 
    {
        return idiomaGuia;
    }

    /**
     * @param idiomaGuia the idiomaGuia to set
     */
    public void setIdiomaGuia(String idiomaGuia) 
    {
        this.idiomaGuia = idiomaGuia;
    }

    /**
     * @return the idGuia
     */
    public String getIdGuia() 
    {
        return idGuia;
    }

    /**
     * @param idGuia the idGuia to set
     */
    public void setIdGuia(String idGuia)
    {
        this.idGuia = idGuia;
    }
}
