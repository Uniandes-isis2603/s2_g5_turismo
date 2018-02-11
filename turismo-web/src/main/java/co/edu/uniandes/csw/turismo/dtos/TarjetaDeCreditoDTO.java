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
public class TarjetaDeCreditoDTO 
{
    private String nombreDelDueño;
    private long  numero;
    private int CVD;

    public TarjetaDeCreditoDTO(){
        
    }

    public TarjetaDeCreditoDTO(String nombreDelDueño, long numero, int CVD) {
        this.nombreDelDueño = nombreDelDueño;
        this.numero = numero;
        this.CVD = CVD;
    }

    public String getNombreDelDueño() {
        return nombreDelDueño;
    }

    public void setNombreDelDueño(String nombreDelDueño) {
        this.nombreDelDueño = nombreDelDueño;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public int getCVD() {
        return CVD;
    }

    public void setCVD(int CVD) {
        this.CVD = CVD;
    }
    
    
    
    
}
