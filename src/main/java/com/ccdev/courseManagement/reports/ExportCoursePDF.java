package com.ccdev.courseManagement.reports;

import com.ccdev.courseManagement.entity.Course;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ExportCoursePDF {
    private List<Course> courses;

    public ExportCoursePDF(List<Course> courses){
        this.courses = courses;
    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        List<String> headers = Arrays.asList("ID","TITLE","DESCRIPTION","LEVEL","SEMESTER","PUBLISHED");
        for(String header: headers){
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }

    }
}
