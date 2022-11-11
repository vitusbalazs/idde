package edu.bbte.idde.vbim2101.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.DaoFactory;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/advertisements")
public class AdvertisementsServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementsServlet.class);
    private static final DaoFactory DAOFACTORY = DaoFactory.getInstance();
    private AdvertisementsDao AdvertisementsDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        LOGGER.info("/advertisements endpoint initializing...");
        AdvertisementsDao = DAOFACTORY.getAdvertisementDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
        LOGGER.info("/advertisements endpoint initialization completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("GET /advertisements arrived...");
        resp.setHeader("Content-Type", "application/json");
        try {
            String advID = req.getParameter("id");
            if (advID == null) {
                objectMapper.writeValue(resp.getOutputStream(), AdvertisementsDao.findAllAdvertisements());
            } else {
                try {
                    Long id = Long.parseLong(advID);
                    Advertisement advertisement = AdvertisementsDao.findById(id);
                    if (advertisement == null) {
                        // nem talalhato
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        resp.getWriter().println("There's no book with this ID!");
                    } else {
                        objectMapper.writeValue(resp.getOutputStream(), advertisement);
                    }
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().println("Cannot parse ID into long. Bad ID!");
                    LOGGER.info("GET /advertisements failed");
                }
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your GET request on /advertisements endpoint.");
            LOGGER.info("GET /advertisements failed");
        }
        LOGGER.info("GET /advertisements ended");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("POST /advertisements arrived...");
        try {
            Advertisement data = objectMapper.readValue(req.getReader(), Advertisement.class);
            if (data.getTitle() == null || data.getAddress() == null || data.getPrice() == null
                    || data.getSurfaceArea() == null || data.getRooms() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (POST)");
            } else {
                AdvertisementsDao.createAdvertisement(data);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("New advertisement added succesfully. Title: " + data.getTitle());
                LOGGER.info("POST /advertisements OK");
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (POST)");
            LOGGER.info("POST /advertisements failed");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("DELETE /advertisements arrived...");
        try {
            Long advID = Long.parseLong(req.getParameter("id"));
            if (AdvertisementsDao.findById(advID) == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("There was no advertisement with the given ID! (DELETE)");
            } else {
                AdvertisementsDao.deleteAdvertisement(advID);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("DELETE completed successfully!");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your DELETE request on /advertisements endpoint.");
            LOGGER.info("DELETE /advertisements failed");
        }
        LOGGER.info("DELETE /advertisements ended");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("PUT /advertisements arrived...");
        try {
            Long advID = Long.parseLong(req.getParameter("id"));
            Advertisement data = objectMapper.readValue(req.getReader(), Advertisement.class);

            if (data.getTitle() == null || data.getAddress() == null || data.getPrice() == null
                    || data.getSurfaceArea() == null || data.getRooms() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad input format. Maybe a field is missing or is not the right type. (PUT)");
            } else {
                if (AdvertisementsDao.findById(advID) == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("There was no advertisement with the given ID (PUT)!");
                } else {
                    AdvertisementsDao.updateAdvertisement(advID, data);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("PUT completed successfully!");
                }
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong with your PUT request on /advertisements endpoint.");
            LOGGER.info("PUT /advertisements failed");
        }
        LOGGER.info("PUT /advertisements ended");
    }
}
