/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.List;

/**
 * Clase que extiende de {@link UsuarioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del usuario, vaya a la documentacion de {@link UsuarioDTO}
 * 
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "apellido": string,
 *      "contrasenia": string,
 *      "correo": string,
 *      "telefono": number,
 *      "idioma": string,
 *      "esAdministrador": boolean,
 *      "listaFacturas": [{
 *      "id":number,
 *      "costo": number
 *      }],
 *      "tarjetaDeCredito": [{
 *      "name": String,
 *      "numero": number,
 *      "CDV":numero
 *      }],
 *      "paqueteTuristico": [{
 *      "id": number,
 *      }],
 *      "blogs":[{
 *      "tema": string,
 *      "descripcion: string,
 *      "likes": number
 *      "comentarios": list
 *      }],
 *      "comentarios": [{
 *      "comentario: string
 *      }],
 *      "preferencias": [{ 
 *      "tiposPlan":[string,string,string]                     
 *      }]
 *      }
 * </pre>
 * Por ejemplo un usuario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 534682,
 *      "nombre": Juan,
 *      "apellido": Perez,
 *      "contrasenia": rplc6519,
 *      "correo": jperez@gmail.com,
 *      "telefono": 3103334455,
 *      "idioma": Ingles,
 *      "esAdministrador": false,
 *      "listaFacturas": [{
 *      "id":23456,
 *      "costo": 10000
 *      }],
 *      "tarjetaDeCredito": [{
 *      "name": Juan,
 *      "numero": 3456789,
 *      "CDV":123
 *      }],
 *      "paqueteTuristico": [{
 *      "id": 654,
 *      }],
 *      "blogs":[{
 *      "tema": Viaje 1,
 *      "descripcion: Paseo por Sudamerica,
 *      "likes": 43
 *      "comentarios": list
 *      }],
 *      "comentarios": [{
 *      "comentario: Muy buen paquete
 *      }],
 *      "preferencias": [{ 
 *      "tiposPlan":[religioso,deportivo,familia]                     
 *      }]
 *      }
 *   }
 *  </pre>
 * @author jf.gutierrez13
 */
public class UsuarioDetailDTO extends UsuarioDTO
{
    //TODO Listas de Preferencias, Comentarios, Blog
    //TODO PaqueteTuristico, Valoracion
    
    private List<FacturaDTO> listaFacturas;
    private List<TarjetaDeCreditoDTO> listaTarjetas;
    private PaqueteTuristicoDTO paquete;
    private List<BlogDTO> listaBlogs;
    private List<ComentarioDTO> listaComentarios;
    private List<PreferenciasDTO> listaPreferencias;
    
    
    /**
     * Constructor
     */
    public UsuarioDetailDTO()
    {
        
    }
    
    /**
     * Crea un UsuarioDetailDTO con los elementos recibidos por parametro.
     * @param listaFacturas La lista de facturas del usuario
     * @param listaTarjetas La lista de tarjetas de credito del usuario
     * @param paquete El paquete turistico actual del usuario
     * @param listaBlogs La lista de blogs creados por el usuario
     * @param listaComentarios La lista de comentarios publicados por el usuario
     * @param listaPreferencias La lista de preferencias del usuario
     */
    public UsuarioDetailDTO(List<FacturaDTO> listaFacturas, List<TarjetaDeCreditoDTO> listaTarjetas, PaqueteTuristicoDTO paquete, List<BlogDTO> listaBlogs, List<ComentarioDTO> listaComentarios, List<PreferenciasDTO> listaPreferencias)
    {
        this.listaFacturas = listaFacturas;
        this.listaTarjetas = listaTarjetas;
        this.paquete = paquete;
        this.listaBlogs = listaBlogs;
        this.listaComentarios = listaComentarios;
        this.listaPreferencias = listaPreferencias;
    }

    /**
     * @return La lista de facturas del usuario.
     */
    public List<FacturaDTO> getListaFacturas() {
        return listaFacturas;
    }

    /**
     * @return El paquete turistico del usuario
     */
    public PaqueteTuristicoDTO getPaquete() {
        return paquete;
    }

    /**
     * @param paquete El nuevo paquete turistico del usuario
     */
    public void setPaquete(PaqueteTuristicoDTO paquete) {
        this.paquete = paquete;
    }

    /**
     * @return La lista de blogs del usuario
     */
    public List<BlogDTO> getListaBlogs() {
        return listaBlogs;
    }

    /**
     * @param listaBlogs La lista de blogs del usuario actualizada
     */
    public void setListaBlogs(List<BlogDTO> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    /**
     * @return La lista de comentarios creados por un usuario
     */
    public List<ComentarioDTO> getListaComentarios() {
        return listaComentarios;
    }

    /**
     * @param listaComentarios La lista de comentarios del usuario actualizada
     */
    public void setListaComentarios(List<ComentarioDTO> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    /**
     * @return La lista de preferencias del usuario
     */
    public List<PreferenciasDTO> getListaPreferencias() {
        return listaPreferencias;
    }

    /**
     * @param listaPreferencias La lista de preferencias del usuario actualizada
     */
    public void setListaPreferencias(List<PreferenciasDTO> listaPreferencias) {
        this.listaPreferencias = listaPreferencias;
    }

    /**
     * @param listaFacturas La lista de facturas del usuario actualizada
     */
    public void setListaFacturas(List<FacturaDTO> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    /**
     * @return La lista de tarjetas de crédito de un usuario
     */
    public List<TarjetaDeCreditoDTO> getListaTarjetas() {
        return listaTarjetas;
    }

    /**
     * @param listaTarjetas La lista de tarjetas de crédito del usuario actualizada
     */
    public void setListaTarjetas(List<TarjetaDeCreditoDTO> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }
    
    
}