/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 *
 * @author s.benitez10
 */

public class FacturaDTO  {
    
    private long ID;
    private long Costo;

    public FacturaDTO() {
        
    }
    
     public FacturaDTO(long ID, long Costo) {
        this.ID = ID;
        this.Costo = Costo;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCosto() {
        return Costo;
    }

    public void setCosto(long Costo) {
        this.Costo = Costo;
    }
    
   
    
}
