/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * Clase que extiende de {@link ValoracionesDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la valoracion, vaya a la documentacion de {@link ValoracionesDTO}
 * 
 *  *Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "calificacion": number,
 *      "comentario": string
 *   }
 * </pre>
 * Por ejemplo un usuario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 534682,
 *      "calificacion": 5,
 *      "comentario": Muy buena
 *   }
 *
 * </pre>
 * 
 * @author jf.gutierrez13
 */
public class ValoracionesDetailDTO extends ValoracionesDTO
{
    /**
     * Constructor por defecto
     */
    public ValoracionesDetailDTO()
    {
        
    }
    
    
    
}