package com.gmakris.matchmanager.core;

import com.gmakris.matchmanager.api.ApiConfiguration;
import com.gmakris.matchmanager.entity.EntityConfiguration;
import com.gmakris.matchmanager.mapper.MapperConfiguration;
import com.gmakris.matchmanager.repository.RepositoryConfiguration;
import com.gmakris.matchmanager.service.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan
@Configuration
@Import({
    EntityConfiguration.class,
    RepositoryConfiguration.class,
    MapperConfiguration.class,
    ServiceConfiguration.class,
    ApiConfiguration.class
})
public class ApplicationConfiguration {
}
