/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.List;

/**
 *
 * @author jf.gutierrez13
 */
public class UsuarioDetailDTO extends UsuarioDTO
{
    //TODO Listas de Preferencias, Comentarios, Blog
    //TODO PaqueteTuristico, Valoracion
    
    private List<FacturaDTO> listaFacturas;
    private List<TarjetaDeCreditoDTO> listaTarjetas;
    
    /**
     * Constructor
     */
    public UsuarioDetailDTO()
    {
        
    }
    
    /**
     * Crea un UsuarioDetailDTO con los elementos recibidos por parametro.
     * @param listaFacturas
     * @param listaTarjetas 
     */
    public UsuarioDetailDTO(List<FacturaDTO> listaFacturas, List<TarjetaDeCreditoDTO> listaTarjetas)
    {
        this.listaFacturas = listaFacturas;
        this.listaTarjetas = listaTarjetas;
    }

    public List<FacturaDTO> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<FacturaDTO> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public List<TarjetaDeCreditoDTO> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(List<TarjetaDeCreditoDTO> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }
    
    
}
