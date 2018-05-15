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
    /**
     * representa el id de la ubicacionDTO
     */
    private Long id;
    /**
     * representa el pais de la ubicacionDTO
     */
    private String pais;
    /**
     * representa el ciudad de la ubicacionDTO
     */
    private String ciudad;
    /**
     * representa el latitud de la ubicacionDTO
     */
    private Double latitud;
    /**
     * representa el longitud de la ubicacionDTO
     */
    private Double longitud;
    /**
     * constructor por defecto
     */
    public UbicacionDTO() 
    {
        
    }
    /**
     * Constructor apartir de un entity
     * @param entity 
     */
     public UbicacionDTO(UbicacionEntity entity) 
    {
         if(entity != null)
         {
             this.id = entity.getId();
             this.pais = entity.getPais();
             this.ciudad= entity.getCiudad();
             this.latitud=entity.getLatitud();
             this.longitud= entity.getLongitud();
         }
    }
     /**
      * metodo que se encarga de pasar de DTO a Entity
      * @return Ubicacion en formato Entity
      */
    public UbicacionEntity toEntity()
    {
        UbicacionEntity entity = new UbicacionEntity();
        entity.setId(this.id);
        entity.setPais(this.pais);
        entity.setCiudad(this.getCiudad());
        entity.setLatitud(this.getLatitud());
        entity.setLongitud(this.getLongitud());
        return entity;
    }
    /**
     * 
     * @return id de ubicacionDTO
     */
    public Long getId() {
        return id;
    }
    /**
     * establece el id de la ubicacionDTO
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 
     * @return el pais de la ubicacionDTO 
     */
    public String getPais() {
        return pais;
    }
    /**
     * establece el pais de la ubicacionDTO
     * @param pPais 
     */
    public void setPais(String pPais) {
        this.pais = pPais;
    }
    /**
     * 
     * @return la ciudad de la ubicacionDTO 
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * Establece la ciudad de la ubicacionDTO
     * @param pCiudad 
     */
    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }
    /**
     * 
     * @return la latitud de la ubicacionDTO 
     */
    public Double getLatitud() {
        return latitud;
    }
    /**
     * establece la latitud de la ubicacionDTO
     * @param pLatitud 
     */
    public void setLatitud(Double pLatitud) {
        this.latitud = pLatitud;
    }
    /**
     * 
     * @return la longitud de la ubicacionDTO 
     */
    public Double getLongitud() {
        return longitud;
    }
    /**
     * establece la longitud de la ubicacionDTO
     * @param pLongitud 
     */
    public void setLongitud(Double pLongitud) {
        this.longitud = pLongitud;
    }
    
   
    
    
    
}
