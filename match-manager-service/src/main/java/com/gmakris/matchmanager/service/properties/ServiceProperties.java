package com.gmakris.matchmanager.service.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("match-manager.service")
public class ServiceProperties {

    /**
     * Used to handle all database queries in a pseudo non-blocking manner.
     */
    private SchedulerProperties dbScheduler;
}
