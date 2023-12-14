package com.common.framework.ui.config;

import com.common.framework.configuration.ConfigFile;
import com.common.framework.logger.Loggable;
import com.common.framework.utils.FileUtils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIConfigLoader implements Loggable {

    private static UIConfiguration config;

    private UIConfigLoader() {
    }

    private static void loadConfigData() {
        Optional<UIConfiguration> configData = FileUtils.loadFromConfigFile(ConfigFile.UI, UIConfiguration.class);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Initializing framework configData for services...");
        if (configData.isPresent()) {
            config = configData.get();
        } else {
            throw new NoSuchElementException("An error occurred trying to get the request config");
        }
    }

    public static UIConfiguration getConfig() {
        if(config == null){
            loadConfigData();
        }
        return config;
    }

}
