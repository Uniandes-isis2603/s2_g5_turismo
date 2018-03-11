/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;

 /** UbicacionDTO Objeto de transferencia de datos de ubicacion. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "pais": String,
 *      "ciudad": String,
 *      "latitud":numero,
 *      "longitud":numero
 *   }
 * </pre>
 * Por ejemplo una tarjeta de credito se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "pais": Colombia,
 *      "ciudad": Bogota,
 *      "latitud": -1,
 *      "longitud":10
 *   }
 *
 * </pre>
 * @author s.benitez10
 */
public class UbicacionDTO 
{
    private String pais;
    private String ciudad;
    private Double latitud;
    private Double longitud;

    public UbicacionDTO() 
    {
        
    }
     public UbicacionDTO(UbicacionEntity entity) 
    {
         if(entity != null)
         {
             this.pais = entity.getPais();
             this.ciudad= entity.getCiudad();
             this.latitud=entity.getLatitud();
             this.longitud= entity.getLongitud();
         }
    }
     
    public UbicacionEntity toEntity()
    {
        UbicacionEntity entity = new UbicacionEntity();
        entity.setPais(this.pais);
        entity.setCiudad(this.getCiudad());
        entity.setLatitud(this.getLatitud());
        entity.setLongitud(this.getLongitud());
        return entity;
    }

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
