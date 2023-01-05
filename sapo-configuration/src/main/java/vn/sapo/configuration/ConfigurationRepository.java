package vn.sapo.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.configuration.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, String> {
    
}




