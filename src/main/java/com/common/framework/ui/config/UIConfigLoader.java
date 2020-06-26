package com.common.framework.ui.config;

import com.common.framework.configuration.ConfigFile;
import com.common.framework.logger.Loggable;
import com.common.framework.utils.FileUtils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum UIConfigLoader implements Loggable {

    CONFIG;

    private UIConfiguration config;

    UIConfigLoader() {
        Optional<UIConfiguration> configData = FileUtils.loadFromConfigFile(ConfigFile.UI, UIConfiguration.class);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Initializing framework configData for services...");
        if (configData.isPresent()) {
            this.config = configData.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public UIConfiguration getConfig() {
        return config;
    }

}
