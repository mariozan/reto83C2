/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Class.Servicio;
import Class.Usuario;
import Controller.Conn;
import java.sql.*;
import java.util.ArrayList;

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
    
    
    public ArrayList<Servicio> Read() {
        Connection conn = conexion.getConnection();
        ArrayList<Servicio> lista_servicio = new ArrayList();
        String query = "SELECT servicio.*, "
                       + "CONCAT(origen.nombre,' ', origen.apellidos) AS nombre_origen,"
                       + "CONCAT(destino.nombre,' ', destino.apellidos) AS nombre_destino,"
                       + "encomienda.descripcion "
                       + "FROM servicio "
                       + "INNER JOIN usuario AS origen ON origen.id = servicio.origen "
                       + "INNER JOIN usuario as destino ON destino.id = servicio.destino "
                       + "INNER JOIN encomienda ON encomienda.id = servicio.encomienda";
                       
        try {
            PreparedStatement newStatement = conn.prepareStatement(query);
            ResultSet resultados = newStatement.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt(1);
                int origen = resultados.getInt(2);
                int destino = resultados.getInt(3);
                String fecha = resultados.getString(4);
                String hora = resultados.getString(5);  
                int encomienda = resultados.getInt(6);
                String nombre_origen = resultados.getString(7);
                String nombre_destino = resultados.getString(8);
                String nombre_encomienda = resultados.getString(9);
                
                Servicio serv = new Servicio(id, origen, destino, fecha, hora, encomienda, nombre_origen, nombre_destino, nombre_encomienda);
                lista_servicio.add(serv);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return lista_servicio;
    }
    
}
