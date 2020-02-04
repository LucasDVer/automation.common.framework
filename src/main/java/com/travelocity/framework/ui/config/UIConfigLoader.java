package com.travelocity.framework.ui.config;

import com.travelocity.framework.configuration.ConfigFile;
import com.travelocity.framework.exceptions.ConfigLoaderException;
import com.travelocity.framework.logger.Loggable;
import com.travelocity.framework.utils.ConfigUtils;
import com.travelocity.framework.utils.FileUtils;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum UIConfigLoader implements UIConfigurable, Loggable {

    CONFIG;

    private UIConfiguration config;

    UIConfigLoader() {
        String file = ConfigUtils.getConfigFileNameByType(ConfigFile.UI);
        Optional<UIConfiguration> configData = FileUtils.loadFromYML(file, UIConfiguration.class);
        if (configData.isPresent()) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Initializing framework configData for services...");
            this.config = configData.get();
        } else {
            throw new ConfigLoaderException(file);
        }
    }

    public UIConfiguration get() {
        return config;
    }

    @Override
    public String getBaseURL() {
        return config.getBaseURL();
    }

    public String getRemoteServerURL() {
        return get().getRemoteServerURL();
    }

    public String getAppiumURL() {
        return get().getAppiumURL();
    }

    public Integer getImplicitWait() {
        return get().getImplicitWait();
    }

    public Integer getExplicitWait() {
        return get().getExplicitWait();
    }

    public Integer getPollingEvery() {
        return get().getPollingEvery();
    }

    public Integer getPageLoadTimeout() {
        return get().getPageLoadTimeout();
    }

    public Integer getScriptTimeout() {
        return get().getScriptTimeout();
    }


}
