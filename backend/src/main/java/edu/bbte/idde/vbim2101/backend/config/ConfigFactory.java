package edu.bbte.idde.vbim2101.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigFactory {
    private static Config config;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFactory.class);

    public static synchronized Config getConfig() {
        if (config == null) {
            InputStream inputStream = Config.class.getResourceAsStream("/application.yaml");
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                config = objectMapper.readValue(inputStream, Config.class);
            } catch (IOException e) {
                LOGGER.error("Getting config failed. Using 'mem' instead...");
                config = new Config();
                config.setDaoType("mem");
            }
        }
        LOGGER.info("[CONFIG] Dao type: " + config.getDaoType());
        return config;
    }
}
