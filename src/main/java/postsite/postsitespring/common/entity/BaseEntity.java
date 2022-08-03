package postsite.postsitespring.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    protected BaseEntity(Long id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
