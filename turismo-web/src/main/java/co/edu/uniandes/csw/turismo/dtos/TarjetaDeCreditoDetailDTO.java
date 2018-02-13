/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.List;

/**
 *
 * @author s.benitez10
 */
public class TarjetaDeCreditoDetailDTO extends TarjetaDeCreditoDTO
{
    private List<FacturaDTO> Factura; 
    private UsuarioDTO usuario;

    public TarjetaDeCreditoDetailDTO() 
    {
        
    }
    
    
}
