package edu.bbte.idde.vbim2101.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ConfigFactory {
    private static Config config;

    public static synchronized Config getConfig() {
        if (config == null) {
            InputStream inputStream = Config.class.getResourceAsStream("/application.yaml");
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            try {
                config = objectMapper.readValue(inputStream, Config.class);
            } catch (IOException e) {
                log.error("Getting config failed. Using 'mem' instead...");
                config = new Config();
                config.setDaoType("mem");
            }
        }
        log.info("[CONFIG] Dao type: " + config.getDaoType());
        return config;
    }
}
