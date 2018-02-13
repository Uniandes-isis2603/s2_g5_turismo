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
    private ValoracionesDTO valoracion;
    private PaqueteTuristicoDTO paquete;
    private List<BlogDTO> listaBlogs;
    private List<ComentariosDTO> listaComentarios;
    private List<PreferenciasDTO> listaPreferencias;
    
    
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
    public UsuarioDetailDTO(List<FacturaDTO> listaFacturas, List<TarjetaDeCreditoDTO> listaTarjetas, ValoracionesDTO valoracion, PaqueteTuristicoDTO paquete, List<BlogDTO> listaBlogs, List<ComentariosDTO> listaComentarios, List<PreferenciasDTO> listaPreferencias)
    {
        this.listaFacturas = listaFacturas;
        this.listaTarjetas = listaTarjetas;
        this.valoracion = valoracion;
        this.paquete = paquete;
        this.listaBlogs = listaBlogs;
        this.listaComentarios = listaComentarios;
        this.listaPreferencias = listaPreferencias;
    }

    public List<FacturaDTO> getListaFacturas() {
        return listaFacturas;
    }

    public ValoracionesDTO getValoracion() {
        return valoracion;
    }

    public void setValoracion(ValoracionesDTO valoracion) {
        this.valoracion = valoracion;
    }

    public PaqueteTuristicoDTO getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteTuristicoDTO paquete) {
        this.paquete = paquete;
    }

    public List<BlogDTO> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<BlogDTO> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public List<ComentariosDTO> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<ComentariosDTO> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<PreferenciasDTO> getListaPreferencias() {
        return listaPreferencias;
    }

    public void setListaPreferencias(List<PreferenciasDTO> listaPreferencias) {
        this.listaPreferencias = listaPreferencias;
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
