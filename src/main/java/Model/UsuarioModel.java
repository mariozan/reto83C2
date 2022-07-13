/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Class.Usuario;
import Controller.Conn;
import java.sql.*;

/**
 *
 * @author Mario
 */
public class UsuarioModel {
    
    Conn conexion = new Conn();
    
    public int Create(Usuario user){
        Connection conn = conexion.getConnection();
        String query = "INSERT INTO usuario(nombre, apellidos, direccion, telefono) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statment = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setString(1, user.getNombre());
            statment.setString(2, user.getApellidos());
            statment.setString(3, user.getDireccion());
            statment.setString(4, user.getTelefono());
            int result = statment.executeUpdate();
            if (result > 0) {
                return 1; // Todo salio bien
            }
        }catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }        
        return 0; // Algo salio mal
    }
    
    public int Delete(int id) {
        Connection conn = conexion.getConnection();
        String query = "DELETE FROM usuario WHERE id = ?;";
        try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            newStatement.setInt(1, id);
            newStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

}

