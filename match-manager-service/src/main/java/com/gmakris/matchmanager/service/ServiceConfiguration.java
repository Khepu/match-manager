package com.gmakris.matchmanager.service;

import static reactor.core.scheduler.Schedulers.newBoundedElastic;

import com.gmakris.matchmanager.repository.RepositoryConfiguration;
import com.gmakris.matchmanager.service.properties.ServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactor.core.scheduler.Scheduler;

@ComponentScan
@Configuration
@Import(RepositoryConfiguration.class)
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfiguration {

    @Bean("db-scheduler")
    public Scheduler dbScheduler(final ServiceProperties properties) {
        return newBoundedElastic(
            properties.getDbScheduler().getThreads(),
            properties.getDbScheduler().getBufferSize(),
            "db-scheduler",
            properties.getDbScheduler().getSecondsToLive());
    }
}
