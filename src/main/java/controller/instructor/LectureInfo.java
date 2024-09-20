package controller.instructor;

import domain.Grade;
import domain.Lecture;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.GradesService;
import model.service.LecturesService;

import java.io.IOException;
import java.util.List;

public class LectureInfo extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");

        Lecture lecture = LecturesService.getLectureById(id);
        List<Grade> grades = GradesService.getGrades(lecture);

        request.setAttribute("lecture", lecture);
        request.setAttribute("grades", grades);

        request.getRequestDispatcher("/instructor/lectureInfoPage")
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
