/**
 * PlanDTO Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idPlan": number,
 *      "nombrePlan: string,
 *      "descripcion": string,
 *      "pais": string,
 *      "ciudad": string,
 *      "longitud": number,
 *      "latitud": number,
 *      "duracion":number,
 *      "restricciones": string,
 *      "archivo": string,
 *      "precio": number,
 *      "cantidadPersonas": number    
 *   }
 *   Por ejemplo un plan se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
       "idPlan": 1,
       "nombrePlan": "Visita a Monserrate",
       "descripcion": "Ir a monserrate subiendo por teleferico",
       "pais": "Colombia", //esto ahora va el la clase ubicacion
       "ciudad": "Bogota", //*2
       "longitud": -74.057615, //*3
       "latitud": 4.606492, //*4
       "duracion":180,
       "restricciones": "Menores deben ir a compañados",
       "archivo": "imagenLink",
       "precio": 20.000,
       "cantidadPersonas": 9999  
    }
 *
 * </pre>
*/
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.PlanEntity;

/**
 *
 * Objeto de transferencia de datos de planes
 * @author jc.montoyar
 */
public class PlanDTO
{

    //ATRIBUTOS 
    /**
     * Atributo que modela el id del plan
     */
    private Long idPlan;

    /**
     * Atributo que modela la descripcion del plan
     */
    private String descripcion;

    /**
     * Atriburo que modela el pais del plan
     */
    private String pais;

    /**
     * atributo que modela la ciudad del plan
     */
    private String ciudad;

    /**
     * Atributo que modela la longitud del plan
     */
    private Double longitud;

    /**
     * Atriburo que modela la latitud
     */
    private Double latitud;

    /**
     * atributo que modela la duracion del plan
     */
    private Integer duracion;

    /**
     * Atriburo que modela las restricciones del plan
     */
    private String restricciones;

    /**
     * Atributo que modela una Stringn del plan
     */
    private String archivo;

    /**
     * Atriburo que modela el precio del plan
     */
    private Double precio;

    /**
     * Atributo que modela la cantidad de personas recomendada para el plan
     */
    private Integer cantidadPersonas;

    /**
     * Atriburo que modela el nombre del plan
     */
    private String nombrePlan;

    //GETTERS Y SETTERS
    /**
     * @return el idPlan
     */
    public Long getIdPlan()
    {
        return idPlan;
    }

    /**
     * @param idPlan el idPlan to set
     */
    public void setIdPlan(Long idPlan)
    {
        this.idPlan = idPlan;
    }

    /**
     * @return la descripcion
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * @param descripcion la descripcion to set
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    /**
     * @return el pais
     */
    public String getPais()
    {
        return pais;
    }

    /**
     * @param pais el pais to set
     */
    public void setPais(String pais)
    {
        this.pais = pais;
    }

    /**
     * @return la ciudad
     */
    public String getCiudad() 
    {
        return ciudad;
    }

    /**
     * @param ciudad la ciudad to set
     */
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    /**
     * @return la longitud
     */
    public Double getLongitud() 
    {
        return longitud;
    }

    /**
     * @param longitud la longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return la latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud la latitud to set
     */
    public void setLatitud(Double latitud)
    {
        this.latitud = latitud;
    }

    /**
     * @return la duracion
     */
    public Integer getDuracion()
    {
        return duracion;
    }

    /**
     * @param duracion la duracion to set
     */
    public void setDuracion(Integer duracion)
    {
        this.duracion = duracion;
    }

    /**
     * @return las restricciones
     */
    public String getRestricciones()
    {
        return restricciones;
    }

    /**
     * @param restricciones las restricciones to set
     */
    public void setRestricciones(String restricciones)
    {
        this.restricciones = restricciones;
    }

    /**
     * @return el archivo
     */
    public String getArchivo()
    {
        return archivo;
    }

    /**
     * @param archivo el archivo to set
     */
    public void setArchivo(String archivo)
    {
        this.archivo = archivo;
    }

    /**
     * @return el precio
     */
    public Double getPrecio()
    {
        return precio;
    }

    /**
     * @param precio el precio to set
     */
    public void setPrecio(Double precio) 
    {
        this.precio = precio;
    }

    /**
     * @return la de cantidadPersonas
     */
    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * @param cantidadPersonas la de cantidadPersonas to set
     */
    public void setCantidadPersonas(Integer cantidadPersonas)
    {
        this.cantidadPersonas = cantidadPersonas;
    }

    /**
     * @return the nombrePlan
     */
    public String getNombrePlan() 
    {
        return nombrePlan;
    }

    /**
     * @param nombrePlan the nombrePlan to set
     */
    public void setNombrePlan(String nombrePlan) 
    {
        this.nombrePlan = nombrePlan;
    }
    
    public PlanDTO()
    {
        
    }
    
    /**
     * Constructor a partir de la entidad
     * @param planE  La entidad del plan
     */
    public PlanDTO(PlanEntity planE)
    {
        if (planE != null) 
        {
            this.idPlan = planE.getId();
            this.nombrePlan = planE.getName();
            this.archivo = planE.getArchivo();
            this.cantidadPersonas = planE.getCantidadPersonas();
            this.ciudad = planE.getCiudad();
            this.descripcion = planE.getDescripcion();
            this.duracion = planE.getDuracion();
            this.latitud = planE.getLatitud();
            this.longitud = planE.getLongitud();
            this.pais = planE.getPais();
            this.precio = planE.getPrecio();
            this.restricciones = planE.getRestricciones();   
        }
    }
        
    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del plan asociado.
     */
    public PlanEntity toEntity() 
    {
        PlanEntity PlanE = new PlanEntity();
        PlanE.setId(this.idPlan);
        PlanE.setName(this.nombrePlan);
        PlanE.setArchivo(this.archivo);
        PlanE.setCantidadPersonas(this.cantidadPersonas);
        PlanE.setCiudad(this.ciudad);
        PlanE.setDescripcion(this.descripcion);
        PlanE.setDuracion(this.duracion);
        PlanE.setLatitud(this.latitud);
        PlanE.setLongitud(this.longitud);
        PlanE.setPais(this.pais);
        PlanE.setRestricciones(this.restricciones);
                 
        return PlanE;
    }

}
