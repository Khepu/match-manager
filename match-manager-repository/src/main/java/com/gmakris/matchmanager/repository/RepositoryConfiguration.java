package com.gmakris.matchmanager.repository;

import com.gmakris.matchmanager.entity.EntityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@Import(EntityConfiguration.class)
public class RepositoryConfiguration {
}
