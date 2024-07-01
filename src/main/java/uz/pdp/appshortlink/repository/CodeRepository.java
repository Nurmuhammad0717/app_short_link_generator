package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appshortlink.entity.Code;

import java.util.UUID;
@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {
}