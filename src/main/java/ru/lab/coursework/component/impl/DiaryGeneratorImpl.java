package ru.lab.coursework.component.impl;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.lab.coursework.component.DiaryGenerator;
import ru.lab.coursework.dto.ReadingSessionGeneratorDTO;
import ru.lab.coursework.dto.ReportGeneratorDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DiaryGeneratorImpl implements DiaryGenerator {

    private final Environment environment;

    private String name;
    private String studentName;
    private String date;
    private List<ReportGeneratorDTO> reports;

    private Document document;
    private Font font;


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public void setSetDate(String date) {
        this.date = date;
    }

    @Override
    public void setReports(List<ReportGeneratorDTO> reports) {
        this.reports = reports;
    }

    @Override
    public String generateDiary() throws IOException, DocumentException {
        String resourcePath = initDocumentAndFont();
        document.open();
        addTitle();
        for (ReportGeneratorDTO report : reports) {
            addReport(report);
        }
        document.close();
        return resourcePath;
    }

    private String initDocumentAndFont() throws IOException, DocumentException {
        this.document = new Document();
        String path = environment.getProperty("generator.diaries-path") + name + date + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(path));
        BaseFont baseFont = BaseFont.createFont(environment.getProperty("generator.font-path"), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        this.font = new Font(baseFont, 11, Font.NORMAL, BaseColor.BLACK);
        return path;
    }

    private void addTitle() throws DocumentException {
        Paragraph name = new Paragraph(this.name, font);
        Paragraph studentName = new Paragraph(this.studentName, font);
        Paragraph date = new Paragraph(this.date, font);
        document.add(name);
        document.add(studentName);
        document.add(date);
    }

    private void addReport(ReportGeneratorDTO report) throws DocumentException {

        PdfPTable reportTable = new PdfPTable(2);
        reportTable.setWidthPercentage(100);
        reportTable.setSpacingBefore(10f);
        reportTable.setSpacingAfter(10f);

        reportTable.addCell(getDefaultCell("Произведение"));
        reportTable.addCell(getDefaultCell(report.getName()));
        reportTable.addCell(getDefaultCell("Автор"));
        reportTable.addCell(getDefaultCell(report.getAuthor()));
        reportTable.addCell(getDefaultCell("Дата начала чтения"));
        reportTable.addCell(getDefaultCell(report.getDateStart()));
        reportTable.addCell(getDefaultCell("Дата окончания чтения"));
        reportTable.addCell(getDefaultCell(report.getDateEnd()));
        reportTable.addCell(getDefaultCell("Главные герои"));
        reportTable.addCell(getDefaultCell(report.getCharacters()));
        reportTable.addCell(getDefaultCell("Сюжет (краткое содержание)"));
        reportTable.addCell(getDefaultCell(report.getPlot()));
        reportTable.addCell(getDefaultCell("Мое мнение (отзыв)"));
        reportTable.addCell(getDefaultCell(report.getReview()));

        document.add(reportTable);

        addSessions(report.getReadingSessions());
    }

    private PdfPCell getDefaultCell(String data) {
        PdfPCell cell = new PdfPCell(new Phrase(data, font));
        cell.setPaddingTop(4f);
        cell.setPaddingBottom(7f);
        cell.setPaddingLeft(4f);
        cell.setPaddingRight(4f);
        return cell;
    }

    private void addSessions(List<ReadingSessionGeneratorDTO> sessions) throws DocumentException {
        Paragraph paragraph = new Paragraph("Сеансы чтения", font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable sessionTable = new PdfPTable(4);
        sessionTable.setWidthPercentage(100);
        sessionTable.setSpacingBefore(10f);
        sessionTable.setSpacingAfter(10f);

        sessionTable.addCell(getDefaultCell("Дата"));
        sessionTable.addCell(getDefaultCell("Начало"));
        sessionTable.addCell(getDefaultCell("Завершение"));
        sessionTable.addCell(getDefaultCell("Продолжительность"));

        for (ReadingSessionGeneratorDTO session : sessions) {
            sessionTable.addCell(getDefaultCell(session.getDate()));
            sessionTable.addCell(getDefaultCell(session.getReadingStart()));
            sessionTable.addCell(getDefaultCell(session.getReadingStart()));
            sessionTable.addCell(getDefaultCell(session.getDuration()));
        }

        document.add(sessionTable);
    }

}
