package edu.bbte.idde.vbim2101.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/logout")
public class LogoutUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();

            Boolean loggedIn;
            if (session.getAttribute("loggedIn") == null) {
                loggedIn = false;
            } else {
                loggedIn = Objects.equals(session.getAttribute("loggedIn").toString(), "true");
            }

            if (loggedIn) {
                session.setAttribute("loggedIn", "false");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("You've successfully logged out!");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("You are not logged in");
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong. Please try again.");
        }
    }
}
