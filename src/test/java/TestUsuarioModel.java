/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Class.Usuario;
import Model.UsuarioModel;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 *
 * @author Mario
 */
public class TestUsuarioModel {
    
    public TestUsuarioModel() {
    }
    
    public int suma(int num1, int num2){
        return num1+num2;
    }    
    
    @Test
    public void testSuma() {
        int num1 = 10;
        int num2 = 5;
        int resultado = suma(num1, num2);
        //     esperado, obtenido
        assertEquals(15, resultado);
    }
    
    public int resta(int num1, int num2){
        return num1-num2;
    }
    
    @Test
    public void testResta() {
        int num1 = 20;
        int num2 = 5;
        int resultado = resta(num1, num2);
        //     esperado, obtenido
        assertEquals(15, resultado);
    }
    
    @Test
    public void testCreate(){
        UsuarioModel usuario_model = new UsuarioModel();      
        Usuario u = new Usuario(0, "Francisco", "Tavarez", "Calle 18", "34578412");
        int resultado = usuario_model.Create(u);
        assertNotEquals(0, resultado);
        // assertNotEquals -> Diferente de 0 
    }
    
    @Test
    public void testUpdate(){
        UsuarioModel usuario_model = new UsuarioModel();      
        Usuario u = new Usuario(0, "Rodrigo", "Ulloa", "Calle 27", "457878");
        int resultado = usuario_model.Update(u, 7);
        assertEquals(1, resultado);
        //assertEquals -> Igual a 1
    }
    
    @Test
    public void testDelete() {
        UsuarioModel usuario_model = new UsuarioModel(); 
        int resultado = usuario_model.Delete(7);
        assertEquals(1, resultado);
    }
    
    @Test
    public void testDeleteOnFail() {
        UsuarioModel usuario_model = new UsuarioModel(); 
        int resultado = usuario_model.Delete(1);
        assertEquals(0, resultado);
    }
}
