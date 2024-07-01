package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByType(RoleTypeEnum type);

}