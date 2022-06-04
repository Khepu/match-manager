package com.gmakris.matchmanager.entity.model;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode
public abstract class AbstractEntity {

    @Id
    private UUID id = UUID.randomUUID();
}
