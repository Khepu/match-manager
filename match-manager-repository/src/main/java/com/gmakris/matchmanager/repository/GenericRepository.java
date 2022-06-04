package com.gmakris.matchmanager.repository;

import java.util.Optional;
import java.util.UUID;
import com.gmakris.matchmanager.entity.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<Entity extends AbstractEntity> extends JpaRepository<Entity, UUID> {

    Optional<Entity> findOneById(UUID entityId);
}
