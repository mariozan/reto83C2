/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Class.Usuario;
import Controller.Conn;
import java.sql.*;
import java.util.ArrayList;

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
            statment.executeUpdate();
            ResultSet generatedKey = statment.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1); // Todo salio bien
            }
        }catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }        
        return 0; // Algo salio mal
    }
    
    public ArrayList<Usuario> Read() {
        Connection conn = conexion.getConnection();
        ArrayList<Usuario> lista_usuarios = new ArrayList();
        String query = "SELECT * FROM usuario;";
        try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            ResultSet resultados = newStatement.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt(1);
                String nombre = resultados.getString(2);
                String apellidos = resultados.getString(3);
                String direccion = resultados.getString(4);
                String telefono = resultados.getString(5);   
                Usuario u = new Usuario(id, nombre, apellidos, direccion, telefono);
                lista_usuarios.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista_usuarios;
    }
    
    public int Update(Usuario u, int id) {
        Connection conn = conexion.getConnection();
        String query = "UPDATE usuario "
                + "SET nombre = ?, "
                + "apellidos = ?, "
                + "direccion = ?, "
                + "telefono = ? "
                + "WHERE id = ?";       
         try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            newStatement.setString(1, u.getNombre());
            newStatement.setString(2, u.getApellidos());
            newStatement.setString(3, u.getDireccion());
            newStatement.setString(4, u.getTelefono());
            newStatement.setInt(5, id);
            newStatement.executeUpdate();
            return 1;
        } catch (Exception exp) {
            System.out.println("Error: " + exp.getMessage());
        }
        return 0;
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

