package controller.homepage;

import domain.Instructor;
import domain.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.*;

import java.io.IOException;

public class HomePageDispatcher extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = (String) request.getSession().getAttribute("userId");
        String loginType = (String) request.getSession().getAttribute("userType");

        if (loginType.equals("student")) {
            Student student = StudentsService.getStudentById(id);
            request.setAttribute("name", student.name());
            request.setAttribute("grades", GradesService.getGrades(student));

            request.getRequestDispatcher("/student/home").forward(request, response);
        } else if (loginType.equals("instructor")) {
            Instructor instructor = InstructorsService.getInstructorById(id);

            request.setAttribute("name", instructor.name());
            request.setAttribute("lectures",
                    LecturesService.getLecturesInstructedBy(instructor));

            request.getRequestDispatcher("/instructor/home")
                    .forward(request, response);
        } else if (loginType.equals("admin")) {
            request.setAttribute("id", id);
            request.setAttribute("studentsIds", UsersService.getStudentsIds());
            request.setAttribute("instructorsIds", UsersService.getInstructorsIds());

            request.getRequestDispatcher("/admin/home")
                    .forward(request, response);
        } else {
            throw new RuntimeException("Bad Request: unknown user type.");
        }
    }
}
