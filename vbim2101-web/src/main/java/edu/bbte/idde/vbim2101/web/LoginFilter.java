package edu.bbte.idde.vbim2101.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/list")
public class LoginFilter extends HttpFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
        LOGGER.info("[FILTER] " + req.getRequestURI());
        try {
            HttpSession session = req.getSession();

            Boolean loggedIn;
            if (session.getAttribute("loggedIn") == null) {
                session.setAttribute("loggedIn", "false");
                loggedIn = false;
            } else {
                loggedIn = Objects.equals(session.getAttribute("loggedIn").toString(), "true");
            }

            if (loggedIn) {
                chain.doFilter(req, res);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
                dispatcher.forward(req, res);
                chain.doFilter(req, res);
            }
        } catch (ServletException | IOException e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().println("Something went wrong. Please try again.");
        }
    }
}
