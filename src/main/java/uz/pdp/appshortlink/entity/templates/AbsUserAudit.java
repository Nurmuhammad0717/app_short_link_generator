package uz.pdp.appshortlink.entity.templates;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsUserAudit {

    @CreatedBy
    @Column(updatable = false)
    private UUID createdById;

    @LastModifiedBy
    private UUID updatedById;
}
