package uz.pdp.appshortlink.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appshortlink.entity.templates.AbsUUIDEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = "update code set deleted=true where id=?")
public class Code extends AbsUUIDEntity {

    private String code;

    private UUID userId;

}
