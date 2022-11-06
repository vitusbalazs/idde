package edu.bbte.idde.vbim2101.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/loginUser")
public class LoginUserServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserServlet.class);

    private static final String WHITELISTUSERNAME = "sarolta";
    private static final String WHITELISTPASSWORD = "unicorn";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Login POST arrived...");
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (Objects.equals(username, WHITELISTUSERNAME) && Objects.equals(password, WHITELISTPASSWORD)) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                resp.sendRedirect("/vbim2101-web/list");
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("You are not authorized to visit this website."
                        + "Maybe you've misspelled your username or password.");
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong. Please try again.");
        }
    }
}
