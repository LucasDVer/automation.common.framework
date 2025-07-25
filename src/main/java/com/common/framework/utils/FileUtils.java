package com.common.framework.utils;

import com.common.framework.configuration.ConfigFile;
import com.common.framework.configuration.SystemVariablesProvider;
import com.common.framework.exceptions.DirOrFileNotFoundException;
import com.common.framework.exceptions.FailedOrInterruptedIOOperations;
import com.common.framework.logger.Loggable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.currentThread;


public final class FileUtils implements Loggable {

    private static final String CONFIG_FILE_SUFFIX = "config";

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

    public static <T> Optional<T> loadFromYML(String file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try (InputStream input = currentThread().getContextClassLoader().getResourceAsStream(file + ".yml")) {
            T obj = objectMapper.readValue(input, clazz);
            return Optional.of(obj);
        } catch (IOException e) {
            throw new FailedOrInterruptedIOOperations(e.getMessage());
        }
    }

    public static Properties loadFromProperties(String file) {
        Properties properties = new Properties();
        try (InputStream input = currentThread().getContextClassLoader()
                .getResourceAsStream(file + ".properties")) {
            properties.load(input);
            return properties;
        } catch (NullPointerException | FileNotFoundException e) {
            throw new DirOrFileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new FailedOrInterruptedIOOperations(e.getMessage());
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

    public static String getConfigFileNameByType(ConfigFile fileType) {
        return String.format("%s-%s-%s", fileType.getValue(), SystemVariablesProvider.getEnvironmentValue(), CONFIG_FILE_SUFFIX);
    }

    public static <T> Optional<T> loadFromConfigFile(ConfigFile fileType, Class<T> clazz) {
        String file = FileUtils.getConfigFileNameByType(fileType);
        return FileUtils.loadFromYML(file, clazz);
    }

    /**
     * This method allows the user to obtain the Json from a Json file.
     * 
     * @param path The path of the File you want to extract from the String.
     * @param subPath The part of the jsonFile we need.
     * @return The jsonData we need.
     */
    public static JsonObject getStringFromJsonFile(String path, String subPath){
        JsonObject jsonObject = null;
        try(FileReader reader = new FileReader(path)){
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            jsonObject = jsonArray.get(0).getAsJsonObject();
            return jsonObject.get(subPath).getAsJsonObject();
        }catch (IOException e){
            e.getMessage();
        }
        return jsonObject;
    }

    public static String getStringFromJsonFileWithoutStatusCode(String path, String subPath){
        JsonObject jsonData = getStringFromJsonFile(path, subPath);
        jsonData.remove("statusCode");
        return jsonData.toString();
    }

}
