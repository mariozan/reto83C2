/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Class.Servicio;
import Controller.Conn;
import java.sql.*;

/**
 *
 * @author Mario
 */
public class ServicioModel {
    
    Conn conexion = new Conn();
    
    public int Create(Servicio serv){
        Connection conn = conexion.getConnection();
        String query = "INSERT INTO servicio(origen, destino, fecha, hora, encomienda) VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement statment = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statment.setInt(1, serv.getOrigen());
            statment.setInt(2, serv.getDestino());
            statment.setString(3, serv.getFecha());
            statment.setString(4, serv.getHora());
            statment.setInt(5, serv.getEncomienda());
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
    
}
