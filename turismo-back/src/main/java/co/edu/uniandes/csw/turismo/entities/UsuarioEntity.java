/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author jf.gutierrez13
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable
{
    private Long id;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String correo;
    private Integer telefono;
    private Boolean esAdministrador;

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
    
    
}
