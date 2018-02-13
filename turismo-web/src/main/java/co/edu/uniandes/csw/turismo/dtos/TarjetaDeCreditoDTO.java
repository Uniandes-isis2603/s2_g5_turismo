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
    private String name;
    private long  numero;
    private int CVD;
    

    public TarjetaDeCreditoDTO(){
        
    }

    public TarjetaDeCreditoDTO(String name, long numero, int CVD, String otro) {
        this.name = name;
        this.numero = numero;
        this.CVD = CVD;
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
