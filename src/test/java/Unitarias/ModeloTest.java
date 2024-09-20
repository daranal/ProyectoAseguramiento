/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unitarias;

import Models.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivanp
 */
public class ModeloTest {

    private Clientes clientes;
    private DocumentoRenta documentorenta;
    private Marcas marcas;
    private Modulos modulos;
    
    public ModeloTest() {
        marcas = new Marcas();
        clientes = new Clientes();
        documentorenta = new DocumentoRenta();
        modulos = new Modulos();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSettersGetters() {
        clientes.setIdCliente(1);
        clientes.setNit("90429400");
        clientes.setNombre("Douglas");
        clientes.setApellido("Arana");
        clientes.setTelefono("55162967");
        clientes.setDireccion("Ciudad");
        assertEquals(1, clientes.getIdCliente());
        assertEquals("90429400", clientes.getNit());
        assertEquals("Douglas", clientes.getNombre());
        assertEquals("Arana", clientes.getApellido());
        assertEquals("55162967", clientes.getTelefono());
        assertEquals("Ciudad", clientes.getDireccion());
        
        
        marcas.setIdmarca(1);
        marcas.setDescripcion("VolksWagen");
        assertEquals(1, marcas.getIdmarca());
        assertEquals("VolksWagen", marcas.getDescripcion());
    }
    
    @Test
    public void testConstructores()
    {
        clientes = new Clientes(1,"90429400","Douglas","Arana","55162967","Ciudad");
        marcas = new Marcas(1, "VolksWagen");
        
        assertEquals(1, clientes.getIdCliente());
        assertEquals("90429400", clientes.getNit());
        assertEquals("Douglas", clientes.getNombre());
        assertEquals("Arana", clientes.getApellido());
        assertEquals("55162967", clientes.getTelefono());
        assertEquals("Ciudad", clientes.getDireccion());
        
        assertEquals("VolksWagen", marcas.getDescripcion());
    }
}
