package controller.instructor;

import domain.Lecture;
import domain.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.GradesService;
import model.service.LecturesService;
import model.service.StudentsService;

import java.io.IOException;

public class GradeUpdater extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String studentId = request.getParameter("studentId");
        String lectureId = request.getParameter("lectureId");
        String exam = request.getParameter("exam");
        Float newGrade = Float.parseFloat(request.getParameter("newGrade"));

        Student student = StudentsService.getStudentById(studentId);
        Lecture lecture = LecturesService.getLectureById(lectureId);

        boolean success = false;
        if (exam.equals("midterm")) {
            success = GradesService.updateMidtermGrade(student, lecture, newGrade);
        } else if (exam.equals("final")) {
            success = GradesService.updateFinalsGrade(student, lecture, newGrade);
        }

        response.sendRedirect("/homePageDispatcher");
    }
}
