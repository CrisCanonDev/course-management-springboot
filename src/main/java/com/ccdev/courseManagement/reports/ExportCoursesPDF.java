package com.ccdev.courseManagement.reports;

import com.ccdev.courseManagement.entity.Course;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExportCoursesPDF {
    private List<Course> courses;
    List<String> attributes = Arrays.asList("ID","TITLE","DESCRIPTION","LEVEL","SEMESTER","PUBLISHED");

    public ExportCoursesPDF(List<Course> courses){
        this.courses = courses;
    }

    public void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        for(String attribute: attributes){
            cell.setPhrase(new Phrase(attribute, font));
            table.addCell(cell);
        }
    }
    public void writeTableData(PdfPTable table){
        for(Course course: courses){
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getTitle());
            table.addCell(course.getDescription());
            table.addCell(String.valueOf(course.getLevel()));
            table.addCell(course.getSemester());
            table.addCell(String.valueOf(course.isPublished()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("Course List", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable( attributes.size());
        table.setWidthPercentage(100f);
        table.setWidths(new float []{1.2f,3.0f, 2.0f, 1.3f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

    }
}
