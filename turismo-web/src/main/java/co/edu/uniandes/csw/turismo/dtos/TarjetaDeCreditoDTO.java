/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

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
    private long  numero;
    private int CVD;
     /*
    Constructor por defecto
    */

    public TarjetaDeCreditoDTO(){
        
    }

    public TarjetaDeCreditoDTO(String name, long numero, int CVD, String otro) {
        this.name = name;
        this.numero = numero;
        this.CVD = CVD;
       
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
    public int getCVD() {
        return CVD;
    }
/*
    modeifica el CVD de la tarjeta
    */
    public void setCVD(int CVD) {
        this.CVD = CVD;
    }
    
    
    
    
}
