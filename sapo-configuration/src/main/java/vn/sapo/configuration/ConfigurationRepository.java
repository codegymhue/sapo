package vn.sapo.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sapo.entities.configuration.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, String> {
}




