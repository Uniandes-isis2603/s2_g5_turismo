/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuaro. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "apellido": string,
 *      "contrasenia": string,
 *      "correo": string,
 *      "telefono": number,
 *      "idioma": string,
 *      "esAdministrador": boolean
 *   }
 * </pre>
 * Por ejemplo un usuario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 534682,
 *      "name": "Juan",
 *      "apellido": "Perez",
 *      "contrasenia": "rplc6519",
 *      "correo": "jperez@gmail.com",
 *      "telefono": 3103334455,
 *      "idioma": "Ingles",
 *      "esAdministrador": FALSE
 *   }
 *
 * </pre>
 * @author jf.gutierrez13
 */
public class UsuarioDTO 
{
    private Long id;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String correo;
    private Integer telefono;
    private String idioma;
    private Boolean esAdministrador;
    
    /**
     * Constructor por defecto
     */
    public UsuarioDTO()
    {
        
    }

    /**
     * @return El id del usuario
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id Nuevo ID del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return El nombre del usuario 
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
     * @return La contraseña del usuario 
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia La nueva contraseña del usuario
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    /**
     * @return El correo del usuario 
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
     * @return El telefono del usuario 
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
     * @return true si es un usuario administrador, false de lo contrario
     */
    public Boolean isEsAdministrador() {
        return esAdministrador;
    }

    /**
     * @param esAdministrador El nuevo estado de la cuenta, true si es administrador, false de lo contrario.
     */
    public void setEsAdministrador(Boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setApellido(this.apellido);
        entity.setName(this.nombre);
        entity.setContrasenia(this.contrasenia);
        entity.setCorreo(this.correo);
        entity.setEsAdministrador(this.esAdministrador);
        entity.setTelefono(this.telefono);
        return entity;
    }
    
    public UsuarioDTO(UsuarioEntity entity)
    {
        if(entity != null)
        {
            this.nombre = entity.getName();
            this.apellido = entity.getApellido();
            this.correo = entity.getCorreo();
            this.contrasenia = entity.getContrasenia();
            this.idioma = entity.getIdioma();
            this.telefono = entity.getTelefono();
            this.id = entity.getId();
            this.esAdministrador = entity.getEsAdministrador();
        }
    }
}