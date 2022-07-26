/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Class.Encomienda;
import Class.Usuario;
import Controller.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class EncomiendaModel {
    
    Conn conexion = new Conn();
    
    public int Create(Encomienda en){
        Connection conn = conexion.getConnection();
        String query = "INSERT INTO encomienda(descripcion, peso, tipo, presentacion) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statment = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setString(1, en.getDescripcion());
            statment.setInt(2, en.getPeso());
            statment.setString(3, en.getTipo());
            statment.setString(4, en.getPresentacion());
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
    
    public ArrayList<Encomienda> Read() {
        Connection conn = conexion.getConnection();
        ArrayList<Encomienda> lista_encomienda = new ArrayList();
        String query = "SELECT * FROM encomienda;";
        try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            ResultSet resultados = newStatement.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt(1);
                String descripcion = resultados.getString(2);
                int peso = resultados.getInt(3);
                String tipo = resultados.getString(4);
                String presentacion = resultados.getString(5);   
                Encomienda en = new Encomienda(id, descripcion, peso, presentacion, tipo);
                lista_encomienda.add(en);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista_encomienda;
    }
    
    
    public ArrayList<Encomienda> GetByPresentacion() {
        Connection conn = conexion.getConnection();
        ArrayList<Encomienda> lista_encomienda = new ArrayList();
        String query = "SELECT presentacion, COUNT(presentacion) AS cantidad "
                     + "FROM encomienda "
                     + "GROUP BY presentacion";
        try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            ResultSet resultados = newStatement.executeQuery();
            while (resultados.next()) {
                String presentacion = resultados.getString(1);
                int cantidad = resultados.getInt(2); 
                Encomienda en = new Encomienda(presentacion, cantidad);
                lista_encomienda.add(en);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista_encomienda;
    }
    
}
