package com.travelocity.framework.ui.driver.capabilities;

import java.util.Map;

public class Capabilities {

    private Map<String, String> capabilitiesMap;

    Map<String, String> getCapabilities() {
        return capabilitiesMap;
    }

    public void setCapabilities(Map<String, String> capabilities) {
        this.capabilitiesMap = capabilities;
    }
}
