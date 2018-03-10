/**
 * PlanDTO Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idPlan": number,
 *      "name: string,
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
       "name": "Visita a Monserrate",
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
    private String name;

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
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * Constructor por defecto
     */
    public PlanDTO()
    {
        //constructor vacio
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
            this.name = planE.getName();
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
        PlanEntity planE = new PlanEntity();
        planE.setId(this.idPlan);
        planE.setName(this.name);
        planE.setArchivo(this.archivo);
        planE.setCantidadPersonas(this.cantidadPersonas);
        planE.setCiudad(this.ciudad);
        planE.setDescripcion(this.descripcion);
        planE.setDuracion(this.duracion);
        planE.setLatitud(this.latitud);
        planE.setLongitud(this.longitud);
        planE.setPais(this.pais);
        planE.setRestricciones(this.restricciones);
                 
        return planE;
    }

}
