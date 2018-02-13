/*
 * PlanDTO
 * Objeto de transferencia de datos de planes.
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
 *      "cantidadPersonas": number,
 *      
 *   }
 * </pre>
*/
package co.edu.uniandes.csw.turismo.dtos;

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
    private long idPlan;

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
    private String longitud;

    /**
     * Atriburo que modela la latitud
     */
    private String latitud;

    /**
     * atributo que modela la duracion del plan
     */
    private int duracion;

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
    private double precio;

    /**
     * Atributo que modela la cantidad de personas recomendada para el plan
     */
    private int cantidadPersonas;

    /**
     * Atriburo que modela el nombre del plan
     */
    private String nombrePlan;

    //GETTERS Y SETTERS
    /**
     * @return el idPlan
     */
    public long getIdPlan()
    {
        return idPlan;
    }

    /**
     * @param idPlan el idPlan to set
     */
    public void setIdPlan(long idPlan)
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
    public String getLongitud() 
    {
        return longitud;
    }

    /**
     * @param longitud la longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @returnla latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud la latitud to set
     */
    public void setLatitud(String latitud)
    {
        this.latitud = latitud;
    }

    /**
     * @return la duracion
     */
    public int getDuracion()
    {
        return duracion;
    }

    /**
     * @param duracion la duracion to set
     */
    public void setDuracion(int duracion)
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
    public double getPrecio()
    {
        return precio;
    }

    /**
     * @param precio el precio to set
     */
    public void setPrecio(double precio) 
    {
        this.precio = precio;
    }

    /**
     * @return la de cantidadPersonas
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * @param cantidadPersonas la de cantidadPersonas to set
     */
    public void setCantidadPersonas(int cantidadPersonas)
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

}
