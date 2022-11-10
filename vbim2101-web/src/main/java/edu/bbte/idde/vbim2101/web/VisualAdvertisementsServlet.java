package edu.bbte.idde.vbim2101.web;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.DaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.memory.MemAdvertisementDao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/list")
public class VisualAdvertisementsServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(VisualAdvertisementsServlet.class);
    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private AdvertisementsDao advertisementsDao;
    private Template template;

    @Override
    public void init() throws ServletException {
        LOGGER.info("/list endpoint initializing...");
        super.init();
        advertisementsDao = daoFactory.getAdvertisementDao();

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassForTemplateLoading(VisualAdvertisementsServlet.class, "/");
        try {
            template = cfg.getTemplate("list.ftl");
        } catch (IOException e) {
            throw new ServletException();
        }
        LOGGER.info("/list endpoint initialization completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, Object> data = new ConcurrentHashMap<>();
            data.put("advertisements", advertisementsDao.findAllAdvertisements());
            template.process(data, resp.getWriter());
        } catch (TemplateException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Something went wrong. Please try again!");
        }
    }
}
