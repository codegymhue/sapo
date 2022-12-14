package vn.sapo.versions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.Application;

import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findAllByAppKey(String appKey);
}
