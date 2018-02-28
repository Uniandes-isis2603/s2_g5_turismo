/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;

/**
 ** TarjetaDeCreditoDTO Objeto de transferencia de datos de tarjetas de credito. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "name": String,
 *      "numero": number,
 *      "CDV":numero
 *   }
 * </pre>
 * Por ejemplo una tarjeta de credito se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "name": Sebastian,
 *      "numero": 1231415,
 *      "CDV":321
 *   }
 *
 * </pre>
 * @author s.benitez10
 */
public class TarjetaDeCreditoDTO 
{
    private String name;
    private Long  numero;
    private Long CVD;
    private Long cedula;
     /*
    Constructor por defecto
    */

    public TarjetaDeCreditoDTO(){
        
    }

    public TarjetaDeCreditoDTO(TarjetaDeCreditoEntity entity) {
        if (entity != null) 
        {    
            this.name = entity.getName();
            this.CVD = entity.getCDV();
            this.numero = entity.getNumero();
            this.cedula= entity.getCedula();
            
            
        }
    }

 
    
     public TarjetaDeCreditoEntity toEntity() {
        TarjetaDeCreditoEntity entity = new TarjetaDeCreditoEntity();
        entity.setCDV(this.getCVD());
        entity.setName(this.getName());
        entity.setNumero(this.getNumero());
        entity.setCedula(this.getCedula());
        return entity;
    }
     
        public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
/*  
    retorna el nombre del dueño de la tarjeta
    */
    public String getName() {
        return name;
    }
/*
    retorna el nombre del dueño de la tarjeta
    */
    public void setName(String name) {
        this.name = name;
    }
/*
    retorna el numero de la tarjeta
    */
    public long getNumero() {
        return numero;
    }
/*
    modifica el numero de la tarjeta
    */
    public void setNumero(long numero) {
        this.numero = numero;
    }
/*
    retorna el codigo CVD de la tarjeta
    */
    public Long getCVD() {
        return CVD;
    }
/*
    modeifica el CVD de la tarjeta
    */
    public void setCVD(Long CVD)
    {
        this.CVD = CVD;
    }
    
   
    
    
}
