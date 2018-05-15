/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.persistence;

import co.edu.uniandes.csw.turismo.entities.UbicacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class UbicacionPersistence 
{
    /**
     * modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(UbicacionPersistence.class.getName());
    /**
     * establece el entityManager que se va encargar del manejo de la base de datos
     */
    @PersistenceContext(unitName = "TurismoPU")
    protected EntityManager em;

     /**
     *
     * @param entity objeto ubicacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public UbicacionEntity create(UbicacionEntity entity) {
        LOGGER.info("Creando una Ubicacion nueva");
        em.persist(entity);
        LOGGER.info("Creando una Ubicacion nueva");
        return entity;
    }
    /**
    *Metodo que se encarga de traer todas la ubicaciones de la base de datos
    *@return devuelve una lista de ubicaciones.
    */
     public List<UbicacionEntity> findAll() {
        LOGGER.info("Consultando todas las Ubicacion");
        TypedQuery query = em.createQuery("select u from UbicacionEntity u", UbicacionEntity.class);
        return query.getResultList();
    }
      /**
    *Metodo que se encarga de traer una ubicacion de la base de datos
    *@param  id es el id de la ubicacion que se quiere
    *@return devuelve una lista de ubicaciones.
    */
     public UbicacionEntity find(Long id)
    {
        return em.find(UbicacionEntity.class, id);
    }
     /**
      * Actualiza la informacion de una ubicacion por la que se pasa por parametro
      * @param entity
      * @return ubicacion actualizada
      */
    public UbicacionEntity update(UbicacionEntity entity) {
         return em.merge(entity);
    }
    /**
     * Elimina una ubicacion con el id pasado por parametro dela base de datos
     * @param id 
     */
   public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Ubicacion con id={0}", id);
        UbicacionEntity entity = em.find(UbicacionEntity.class, id);
        em.remove(entity);
    }
}
