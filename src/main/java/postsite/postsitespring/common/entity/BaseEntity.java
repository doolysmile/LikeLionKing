package postsite.postsitespring.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
public abstract class BaseEntity {
    private final Long id;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    protected BaseEntity(Long id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
