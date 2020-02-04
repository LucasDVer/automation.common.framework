package com.travelocity.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.travelocity.framework.exceptions.DirOrFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.currentThread;
import static java.util.Optional.empty;


public final class FileUtils {

    private static final Configuration conf = Configuration.builder()
            .jsonProvider(new JacksonJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .options(Option.SUPPRESS_EXCEPTIONS).build();

    private FileUtils() {
    }

    public static <T> Optional<T> loadFromJson(String jsonPath, String file, Class<T> clazz) {
        InputStream input = currentThread().getContextClassLoader().getResourceAsStream(file + ".json");
        T obj = JsonPath.using(conf).parse(input).read(jsonPath, clazz);
        return Optional.of(obj);
    }

    public static <T> Optional<T> loadFromJson(String file, Class<T> clazz) {
        InputStream input = currentThread().getContextClassLoader().getResourceAsStream(file + ".json");
        T obj = JsonPath.using(conf).parse(input).read("$..*", clazz);
        return Optional.of(obj);
    }

    public static <T> Optional<T> loadFromYML(String file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try (InputStream input = currentThread().getContextClassLoader().getResourceAsStream(file + ".yml")) {
            T obj = objectMapper.readValue(input, clazz);
            return Optional.of(obj);
        } catch (IOException e) {
            return empty();
        }
    }

    public static Properties loadFromProperties(String file) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = currentThread().getContextClassLoader()
                .getResourceAsStream(file + ".properties")) {
            properties.load(input);
            return properties;
        } catch (NullPointerException | FileNotFoundException e) {
            throw new DirOrFileNotFoundException(e.getMessage());
        }
    }

    public static void setDownloadDirectoryForFile(File tempDir) {
        if (!tempDir.exists()) {
            try {
                Files.createDirectories(tempDir.toPath());
            } catch (IOException e) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, e.getMessage());
            }
        }
        System.setProperty("wdm.targetPath", tempDir.getAbsolutePath());
    }
}
