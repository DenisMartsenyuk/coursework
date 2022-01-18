package ru.lab.coursework.component;

import com.itextpdf.text.DocumentException;
import ru.lab.coursework.dto.ReportGeneratorDTO;

import java.io.IOException;
import java.util.List;

public interface DiaryGenerator {

    void setName(String name);
    void setStudentName(String studentName);
    void setSetDate(String date);
    void setReports(List<ReportGeneratorDTO> reports);
    String generateDiary() throws IOException, DocumentException;
}
