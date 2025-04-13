package com.starter.apilogger.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="api.logging")
@Component
public class ApiLoggingProperties {
    /*
    * Включение или выключение логирования.
    */
    private boolean enabled = true;

    /*
    * Уровень логирования. info, debug, warn, error
    */

    private String level = "info";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
