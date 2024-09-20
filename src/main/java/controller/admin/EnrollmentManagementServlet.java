package controller.admin;

import domain.Lecture;
import domain.Student;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.EnrollmentService;
import model.service.LecturesService;
import model.service.StudentsService;

import java.io.IOException;

public class EnrollmentManagementServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter("studentId");
        String lectureId = request.getParameter("lectureId");
        String action = request.getParameter("action");

        Student student = StudentsService.getStudentById(studentId);
        Lecture lecture = LecturesService.getLectureById(lectureId);

        boolean success = false;

        if (action.equals("enrol")) {
            success = EnrollmentService.enrol(student, lecture);
        } else if (action.equals("expel")) {
            success = EnrollmentService.expel(student, lecture);
        }

        response.sendRedirect("/homePageDispatcher");
    }
}
