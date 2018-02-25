/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author jf.gutierrez13
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable
{
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String correo;
    private String idioma;
    private Integer telefono;
    private Boolean esAdministrador;
    
    @OneToMany
    private List<FacturaEntity> listaFacturas;
    
    @PodamExclude
    @OneToMany
    private List<TarjetaDeCreditoEntity> listaTarjetas;
    
    @PodamExclude
    @OneToOne
    private PaqueteTuristicoEntity paquete;
    
    @OneToMany
    private List<BlogEntity> listaBlogs;
    
    @OneToMany
    private List<ComentariosEntity> listaComentarios;
    
    @PodamExclude
    @OneToMany
    private List<PreferenciasEntity> listaPreferencias;

    /**
     * @return Retorna el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido El nuevo apellido del usuario
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return Retorna la contrasenia del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia La nueva contrasenia del usuario
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return Retorna el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo El nuevo correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @return El idioma del usuario
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma El nuevo idioma del usuario
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return Retorna el telefono del usuario
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * @param telefono El nuevo telefono del usuario
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * @return Retorna true si el usuario es administrador, false de lo contrario
     */
    public Boolean getEsAdministrador() {
        return esAdministrador;
    }

    /**
     * @param esAdministrador El nuevo estado del usuario. True si es administrador, false de lo contrario
     */
    public void setEsAdministrador(Boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public List<FacturaEntity> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<FacturaEntity> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public List<TarjetaDeCreditoEntity> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(List<TarjetaDeCreditoEntity> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public PaqueteTuristicoEntity getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteTuristicoEntity paquete) {
        this.paquete = paquete;
    }

    public List<BlogEntity> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<BlogEntity> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public List<ComentariosEntity> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<ComentariosEntity> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public List<PreferenciasEntity> getListaPreferencias() {
        return listaPreferencias;
    }

    public void setListaPreferencias(List<PreferenciasEntity> listaPreferencias) {
        this.listaPreferencias = listaPreferencias;
    }
    
    
}