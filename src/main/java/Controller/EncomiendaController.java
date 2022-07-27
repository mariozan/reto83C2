/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Class.Encomienda;
import Model.EncomiendaModel;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Mario
 */
public class EncomiendaController {
    
    EncomiendaModel modelo_encomienda = new EncomiendaModel();
    ArrayList<Encomienda> listaEncomiendas = modelo_encomienda.Read();
    
    public void ExportData(){
    
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        book.setSheetName(0, "Lista de Encomiendas");
        String[] headers = new String[]{
            "Id",
            "Descripcion",
            "Peso",
            "Presentacion",
            "Tipo"
        };
        
        CellStyle headerStyle = book.createCellStyle();
        HSSFFont font = book.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        
        HSSFRow headersRow = sheet.createRow(0);
        //Actulizo el estilo del encabezado
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            HSSFCell cell = headersRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
        
        // Llenar el Excel
        for (int i = 0; i < listaEncomiendas.size(); i++) {
            HSSFRow row = sheet.createRow(i+1);
            int id = listaEncomiendas.get(i).getId();
            String descripcion = listaEncomiendas.get(i).getDescripcion();
            int peso = listaEncomiendas.get(i).getPeso();
            String presentacion = listaEncomiendas.get(i).getPresentacion();
            String tipo = listaEncomiendas.get(i).getTipo();
            
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(descripcion);
            row.createCell(2).setCellValue(peso);
            row.createCell(3).setCellValue(presentacion);
            row.createCell(4).setCellValue(tipo);
        }
        
        //Exportar la informacion
        try{
            FileOutputStream file = new FileOutputStream("Encomiendas.xls");
            book.write(file);
            file.close();
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
}
