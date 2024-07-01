package uz.pdp.appshortlink.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.PermissionEnum;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCrudDTO implements Serializable {
    private String name;
    private String description;
    private List<PermissionEnum> permissions;
}