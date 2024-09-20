package controller.authentication;

import domain.UserLogin;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.AuthenticationService;

import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String loginType = request.getParameter("type");
        String id = request.getParameter("userid");
        String password = request.getParameter("password");

        try {
            UserLogin login = new UserLogin(loginType, id, password);
            boolean authentic = AuthenticationService.authenticate(login);

            if (authentic) {
                request.getSession().setAttribute("userId", id);
                request.getSession().setAttribute("userType", loginType);

                response.sendRedirect("/homePageDispatcher");
            } else {
                response.sendRedirect("/login");
            }
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Bad Request");
        }
    }
}
