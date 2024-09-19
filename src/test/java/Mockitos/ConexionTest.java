/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mockitos;
import Conexion.Conexion;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class ConexionTest {

    private Conexion mockConexion;

    @Before
    public void setUp() {
        // Simular la conexión a la base de datos
        mockConexion = mock(Conexion.class);
    }

    @Test
    public void testAgregarCliente() throws ClassNotFoundException, Exception {
        verify(mockConexion).open();
        verify(mockConexion).executeQuery("SELECT * FROM MARCAS");
        verify(mockConexion).close();
    }
}
