package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appshortlink.entity.Link;

import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {

    boolean existsByShortUrl(String shortUrl);

}