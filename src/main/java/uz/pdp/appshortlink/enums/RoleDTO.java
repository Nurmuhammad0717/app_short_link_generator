package uz.pdp.appshortlink.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private RoleTypeEnum type;
    private List<PermissionEnum> permissions;
}