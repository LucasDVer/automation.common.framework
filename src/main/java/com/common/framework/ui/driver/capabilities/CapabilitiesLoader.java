package com.common.framework.ui.driver.capabilities;


import com.common.framework.exceptions.CapabilitiesLoaderException;
import com.common.framework.logger.Loggable;
import com.common.framework.utils.FileUtils;

import java.util.Map;
import java.util.Optional;


/**
 * CapabilitiesLoader loads a set of optional capabilities (could be empty) specified in a JSON file per browser or device.
 */
public enum CapabilitiesLoader implements Loggable {

    CAPABILITIES;

    /**
     * Reads capabilities from file.
     *
     * @param file the file name
     * @return a {@link Map} with the capabilities, key value pair
     */
    public Map<String, String> readCapabilities(String file) {
        Optional<Capabilities> capabilities = FileUtils.loadFromYML(file, Capabilities.class);
        if (capabilities.isPresent()) {
            info(String.format("Reading capabilities for '%s' ...", file));
            return capabilities.get().getCapabilities();
        } else {
            throw new CapabilitiesLoaderException(file);
        }
    }

}
