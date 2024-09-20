package controller.admin;

import domain.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.*;

import java.io.IOException;

public class CreationServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String entityType = request.getParameter("entity");

        boolean success = false;

        if (entityType.equals("student")) {
            success = createStudent(request);
        } else if (entityType.equals("instructor")) {
            success = createInstructor(request);
        } else if (entityType.equals("course")) {
            success = createCourse(request);
        } else if (entityType.equals("lecture")) {
            success = createLecture(request);
        }

        response.sendRedirect("/homePageDispatcher");
    }

    private boolean createLecture(HttpServletRequest request) {
        String id = request.getParameter("id");
        String courseId = request.getParameter("courseId");
        String instructorId = request.getParameter("coordinatorId");

        try {
            Instructor instructor = InstructorsService.getInstructorById(instructorId);
            Course course = CoursesService.getCourseById(courseId);
            Lecture lecture = new Lecture(id, course, instructor);

            return LecturesService.createLecture(lecture);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean createCourse(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String instructorId = request.getParameter("instructorId");

        try {
            Instructor instructor = InstructorsService.getInstructorById(instructorId);
            Course course = new Course(id, name, instructor);
            return CoursesService.createCourse(course);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean createInstructor(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            Instructor instructor = new Instructor(id, name);
            UserLogin login = new UserLogin("instructor", id, password);

            if (UsersService.createUser(login)) {
                return InstructorsService.createInstructor(instructor);
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    private boolean createStudent(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            Student student = new Student(id, name);
            UserLogin login = new UserLogin("student", id, password);

            if (UsersService.createUser(login)) {
                return StudentsService.createStudent(student);
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
