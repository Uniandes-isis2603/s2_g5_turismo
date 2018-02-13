/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.dtos;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuaro. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
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
 *      "nombre": Juan,
 *      "apellido": Perez,
 *      "contrasenia": rplc6519,
 *      "correo": jperez@gmail.com,
 *      "telefono": 3103334455,
 *      "idioma": Ingles,
 *      "esAdministrador": false
 *   }
 *
 * </pre>
 * @author jf.gutierrez13
 */
public class UsuarioDTO 
{
    private long id;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String correo;
    private int telefono;
    private String idioma;
    private boolean esAdministrador;
    
    /**
     * Constructor por defecto
     */
    public UsuarioDTO()
    {
        
    }
    
    /**
     * Crea un nuevo UsuarioDTO con los valores recibidos por parametro.
     * @param id id del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param contrasenia contrasenia del usuario
     * @param correo correo del usuario
     * @param telefono telefono del usuario
     * @param idioma idioma del usuario
     * @param esAdministrador indica si el usuario es o no administrador
     */
    public UsuarioDTO(long id, String nombre, String apellido, String contrasenia, String correo, int telefono, String idioma, boolean esAdministrador)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.telefono = telefono;
        this.idioma = idioma;
        this.esAdministrador =esAdministrador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
}

