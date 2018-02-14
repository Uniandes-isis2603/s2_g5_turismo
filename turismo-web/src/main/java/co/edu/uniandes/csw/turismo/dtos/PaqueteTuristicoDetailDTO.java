/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.ArrayList;


/**
 *
 * Clase que extiende de {@link ComentariosDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.
 * 
 * @author dl.avendano
 */
public class PaqueteTuristicoDetailDTO  extends PaqueteTuristicoDTO{
    private ArrayList<PagoDTO> Pagos; 
    private ArrayList<PlanDTO> Planes; 
    
    public PaqueteTuristicoDetailDTO(){
        
    }

    public ArrayList<PagoDTO> getPagos() {
        return Pagos;
    }

    public void setPagos(ArrayList<PagoDTO> Pagos) {
        this.Pagos = Pagos;
    }

    public ArrayList<PlanDTO> getPlanes() {
        return Planes;
    }

    public void setPlanes(ArrayList<PlanDTO> Planes) {
        this.Planes = Planes;
    }
    
}
