package controller.admin;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.*;

import java.io.IOException;

public class DeletionServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String entity = request.getParameter("entity");
        String id = request.getParameter("id");

        boolean success = false;
        
        if (entity.equals("student")) {
            success = deleteStudent(id);
        } else if (entity.equals("instructor")) {
            success = deleteInstructor(id);
        } else if (entity.equals("course")) {
            success = deleteCourse(id);
        } else if (entity.equals("lecture")) {
            success = deleteLecture(id);
        }

        response.sendRedirect("/homePageDispatcher");
    }

    private boolean deleteStudent(String id) {
        return StudentsService.deleteStudent(id)
                && UsersService.deleteUser(id);
    }

    private boolean deleteInstructor(String id) {
        return InstructorsService.deleteInstructor(id)
                && UsersService.deleteUser(id);
    }

    private boolean deleteCourse(String id) {
        return CoursesService.deleteCourse(id);
    }

    private boolean deleteLecture(String id) {
        return LecturesService.deleteLecture(id);
    }
}
