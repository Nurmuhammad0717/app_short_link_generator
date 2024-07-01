package uz.pdp.appshortlink.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;
import uz.pdp.appshortlink.repository.RoleRepository;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        createUserRole();

        createAdminRole();

    }

    private void createAdminRole() {
        Optional<Role> optionalRole = roleRepository.findByType(RoleTypeEnum.ADMIN);
        if (optionalRole.isPresent())
            return;

        Role role = new Role(
                "Admin",
                "Admin",
                RoleTypeEnum.ADMIN,
                Arrays.stream(PermissionEnum.values()).toList()
        );

        roleRepository.save(role);
    }

    private void createUserRole() {
        Optional<Role> optionalRole = roleRepository.findByType(RoleTypeEnum.USER);
        if (optionalRole.isPresent())
            return;

        Role role = new Role(
                "User",
                "User",
                RoleTypeEnum.USER,
                PermissionEnum.userPermissions()
        );

        roleRepository.save(role);
    }
}
