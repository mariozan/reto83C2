/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author Mario
 */
public class Servicio {
    private int id;
    private int origen;
    private int destino;
    private String fecha;
    private String hora;
    private int encomienda;    
    private String nombre_origen;
    private String nombre_destino;
    private String nombre_encomienda;

    public Servicio(int id, int origen, int destino, String fecha, String hora, int encomienda, String nombre_origen, String nombre_destino, String nombre_encomienda) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.encomienda = encomienda;
        this.nombre_origen = nombre_origen;
        this.nombre_destino = nombre_destino;
        this.nombre_encomienda = nombre_encomienda;
    }
    
    public Servicio(int id, int origen, int destino, String fecha, String hora, int encomienda) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.encomienda = encomienda;
    }

    public Servicio() {
    }    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the origen
     */
    public int getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(int origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public int getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the encomienda
     */
    public int getEncomienda() {
        return encomienda;
    }

    /**
     * @param encomienda the encomienda to set
     */
    public void setEncomienda(int encomienda) {
        this.encomienda = encomienda;
    }

    /**
     * @return the nombre_origen
     */
    public String getNombre_origen() {
        return nombre_origen;
    }

    /**
     * @param nombre_origen the nombre_origen to set
     */
    public void setNombre_origen(String nombre_origen) {
        this.nombre_origen = nombre_origen;
    }

    /**
     * @return the nombre_destino
     */
    public String getNombre_destino() {
        return nombre_destino;
    }

    /**
     * @param nombre_destino the nombre_destino to set
     */
    public void setNombre_destino(String nombre_destino) {
        this.nombre_destino = nombre_destino;
    }

    /**
     * @return the nombre_encomienda
     */
    public String getNombre_encomienda() {
        return nombre_encomienda;
    }

    /**
     * @param nombre_encomienda the nombre_encomienda to set
     */
    public void setNombre_encomienda(String nombre_encomienda) {
        this.nombre_encomienda = nombre_encomienda;
    }
}
