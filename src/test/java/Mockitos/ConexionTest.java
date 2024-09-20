/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mockitos;

import Conexion.Conexion;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;

public class ConexionTest {

    private Conexion conexion;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        conexion = new Conexion(mockConnection);
    }

    @Test
    public void testOpenConnection() throws Exception {
        conexion.open();
        
    }

    @Test
    public void testCloseConnection() throws Exception {
        conexion.close();
        verify(mockConnection).close();
    }

    @Test
    public void testExecuteSql() throws Exception {
        boolean result = conexion.executeSql("INSERT INTO CLIENTE VALUES (...)");
        // Verificar que se ha llamado a prepareStatement() y executeUpdate()
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void testExecuteQuery() throws SQLException {
        ResultSet result = conexion.executeQuery("SELECT * FROM CLIENTE");
        verify(mockConnection).prepareStatement(anyString());
        verify(mockPreparedStatement).executeQuery();
        assertNotNull(result);
    }
}
