package co.edu.uniandes.csw.turismo.dtos;

import java.util.ArrayList;

/*
 * Objeto de transferencia detallado de datos de planes
 * @author jc.montoyar
 */
public class PlanDetailDTO extends PlanDTO {

    //ATRIBUTOS 
    /**
     * Atributo que modela la lista de guias que hay para el plan
     */
    private ArrayList<GuiaDTO> guiasPlan;

    /**
     * Atributo que modela una lista con los tipos de plan de el plan
     */
    private ArrayList<PreferenciasDTO> tiposPlan;

    /**
     * Atributo que modela las valoraciones del plan
     */
    private ArrayList<ValoracionesDTO> valoraciones; //TODO, ES DE TIPO ValoracionDTO

    //CONSTRUCTOR
    /**
     * Constructor poe defecto
     */
    public PlanDetailDTO() { }

    //METODOS
    /**
     * @return los guias asociados del plan
     */
    public ArrayList<GuiaDTO> getGuiasPlan()
    {
        return this.guiasPlan;
    }

    public void setGuiasPlan(ArrayList<GuiaDTO> guiasPlan) 
    {
        this.guiasPlan = guiasPlan;
    }

    /**
     * @return los tipos de plan del plan
     */
    public ArrayList<PreferenciasDTO> getTiposPlan() 
    {
        return this.tiposPlan;
    }

    public void setTiposPlan(ArrayList<PreferenciasDTO> tiposPlan)
    {
        this.tiposPlan = tiposPlan;
    }

    /**
     * @return the valoraciones
     */
    public ArrayList getValoraciones() 
    {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(ArrayList valoraciones) 
    {
        this.valoraciones = valoraciones;
    }
}
