package uz.pdp.appshortlink.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;
import uz.pdp.appshortlink.entity.templates.AbsUUIDEntity;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "roles")
@SQLRestriction("deleted=false")//select * from roles; -> select * from roles where deleted=false
@SQLDelete(sql = "update roles set deleted=true where id=?")//
public class Role extends AbsUUIDEntity {

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;

//    @ElementCollection//role_permissions(role_id,permission)
    @JdbcTypeCode(SqlTypes.ARRAY)//
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissions;
}
