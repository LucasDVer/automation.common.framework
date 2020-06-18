package com.common.framework.ui.config;

import com.common.framework.configuration.ConfigFile;
import com.common.framework.logger.Loggable;
import com.common.framework.utils.FileUtils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum UIConfigLoader implements UIConfigurable, Loggable {

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
