package edu.bbte.idde.vbim2101.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;
import edu.bbte.idde.vbim2101.backend.model.Owner;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/owners")
public class OwnersServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementsServlet.class);
    private static final AbstractDaoFactory DAOFACTORY = AbstractDaoFactory.getInstance();
    private OwnersDao ownersDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        LOGGER.info("/owners endpoint initializing...");
        ownersDao = DAOFACTORY.getOwnersDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
        LOGGER.info("/owners endpoint initialization completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("GET /owners arrived...");
        resp.setHeader("Content-Type", "application/json");
        try {
            String advID = req.getParameter("id");
            if (advID == null) {
                objectMapper.writeValue(resp.getOutputStream(), ownersDao.findAll());
            } else {
                try {
                    Long id = Long.parseLong(advID);
                    Owner owner = ownersDao.findById(id);
                    if (owner == null) {
                        // nem talalhato
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        resp.getWriter().println("There's no owner with this ID!");
                    } else {
                        objectMapper.writeValue(resp.getOutputStream(), owner);
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().println("Cannot parse ID into long. Bad ID!");
                    LOGGER.info("GET /owners failed");
                }
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your GET request on /owners endpoint.");
            LOGGER.info("GET /owners failed");
        }
        LOGGER.info("GET /owners ended");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("POST /owners arrived...");
        try {
            Owner data = objectMapper.readValue(req.getReader(), Owner.class);
            if (data.getName() == null || data.getEmail() == null || data.getAge() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (POST)");
            } else {
                ownersDao.create(data);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("New owner added successfully. Name: " + data.getName());
                LOGGER.info("POST /advertisements OK");
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (POST): " + e);
            LOGGER.info("POST /owners failed");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("DELETE /owners arrived...");
        try {
            Long advID = Long.parseLong(req.getParameter("id"));
            if (ownersDao.findById(advID) == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("There was no owner with the given ID! (DELETE)");
            } else {
                ownersDao.delete(advID);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("DELETE completed successfully!");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your DELETE request on /owners endpoint.");
            LOGGER.info("DELETE /owners failed");
        }
        LOGGER.info("DELETE /owners ended");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("PUT /owners arrived...");
        try {
            Long advID = Long.parseLong(req.getParameter("id"));
            Owner data = objectMapper.readValue(req.getReader(), Owner.class);

            if (data.getName() == null || data.getEmail() == null || data.getAge() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (PUT)");
            } else {
                if (ownersDao.findById(advID) == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("There was no advertisement with the given ID (PUT)!");
                } else {
                    ownersDao.update(advID, data);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("PUT completed successfully!");
                }
            }
        } catch (NumberFormatException | IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your PUT request on /owners endpoint.");
            LOGGER.info("PUT /owners failed");
        }
        LOGGER.info("PUT /owners ended");
    }
}
