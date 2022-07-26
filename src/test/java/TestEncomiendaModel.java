/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import Class.Encomienda;
import Class.Usuario;
import Model.EncomiendaModel;
import Model.UsuarioModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @author Mario
 */
public class TestEncomiendaModel {
    
    public TestEncomiendaModel() {
    }
    
    @Test
    public void testCreate(){
        EncomiendaModel encomienda_model = new EncomiendaModel();      
        Encomienda e = new Encomienda(0, "Paquete de Medias", 15, "Bolsa", "Ropa");
        int resultado = encomienda_model.Create(e);
        assertNotEquals(0, resultado);
        // assertNotEquals -> Diferente de 0 
    }
    
  
}
