/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.ArrayList;

/**
 * Objeto de transferencia detallado de los guias
 * @author jc.montoyar
 */
public class GuiaDetailDTO 
{
    
    //ATRIBURTOS

    /**
     * Modela los planes a los cuales el guia esta asociado
     */
    private ArrayList<PlanDTO> planesGuia;

    //Constructor 
    /**
     * Constructor por defecto
     */
    public GuiaDetailDTO(){}

    //METODOS
    /**
     * @return the planesGuia
     */
    public ArrayList<PlanDTO> getPlanesGuia() 
    {
        return planesGuia;
    }

    /**
     * @param planesGuia the planesGuia to set
     */
    public void setPlanesGuia(ArrayList<PlanDTO> planesGuia)
    {
        this.planesGuia = planesGuia;
    }
}
