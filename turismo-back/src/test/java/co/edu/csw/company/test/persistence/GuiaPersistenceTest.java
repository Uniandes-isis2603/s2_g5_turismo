/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.csw.company.test.persistence;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.persistence.GuiaPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author  jc.montoyar
 */
@RunWith(Arquillian.class)
public class GuiaPersistenceTest {

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Guia, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GuiaEntity.class.getPackage())
                .addPackage(GuiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase GuiaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private GuiaPersistence GuiaPersistence;

    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from GuiaEntity").executeUpdate();
    }

    /**
     *
     */
    private List<GuiaEntity> data = new ArrayList<GuiaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            GuiaEntity entity = factory.manufacturePojo(GuiaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Guia.
     *
     *
     */
    @Test
    public void createGuiaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);
        GuiaEntity result = GuiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        GuiaEntity entity = em.find(GuiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getIdiomaGuia(), entity.getIdiomaGuia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Guias.
     *
     *
     */
    @Test
    public void getGuiasTest() {
        List<GuiaEntity> list = GuiaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GuiaEntity ent : list) {
            boolean found = false;
            for (GuiaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Guia.
     *
     *
     */
    @Test
    public void getGuiaTest() {
        GuiaEntity entity = data.get(0);
        GuiaEntity newEntity = GuiaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getIdiomaGuia(), entity.getIdiomaGuia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar un Guia.
     *
     *
     */
    @Test
    public void deleteGuiaTest() {
        GuiaEntity entity = data.get(0);
        GuiaPersistence.delete(entity.getId());
        GuiaEntity deleted = em.find(GuiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Guia.
     *
     *
     */
    @Test
    public void updateGuiaTest() {
        GuiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GuiaEntity newEntity = factory.manufacturePojo(GuiaEntity.class);

        newEntity.setId(entity.getId());

        GuiaPersistence.update(newEntity);

        GuiaEntity resp = em.find(GuiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getIdiomaGuia(), resp.getIdiomaGuia());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
