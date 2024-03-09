package com.ccdev.courseManagement.reports;

import com.ccdev.courseManagement.entity.Course;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExportCoursesExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Course> courses;
    List<String> attributes = Arrays.asList("ID","TITLE","DESCRIPTION","LEVEL","SEMESTER","PUBLISHED");


    public ExportCoursesExcel(List<Course> courses){
        this.courses = courses;
        workbook = new XSSFWorkbook();
    }

    public void writeHeader(){
        sheet = workbook.createSheet("Courses");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for(int i=0; i<attributes.size(); i++) {
            createCell(row, i, attributes.get(i), style);
        }
    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        setValueOfInstance(row, columnCount, value);
        cell.setCellStyle(style);


    }
    public void setValueOfInstance(Row row, int columnCount,Object value){
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }else{
            cell.setCellValue((String) value);
        }
    }

    private void writeDataLines(){
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(14);
        style.setFont(font);

        for(Course course:courses){
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            createCell(row, columnCount++, course.getId(), style);
            createCell(row, columnCount++, course.getTitle(), style);
            createCell(row, columnCount++, course.getDescription(), style);
            createCell(row, columnCount++, course.getLevel(), style);
            createCell(row, columnCount++, course.getSemester(), style);
            createCell(row, columnCount++, course.isPublished(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeader();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
