package vn.sapo.controllers.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sapo.configuration.ConfigurationService;
import vn.sapo.configuration.dto.ConfigurationResult;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationAPI {
    @Autowired
    ConfigurationService configurationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByAppKey(@PathVariable("id") String appKey) {
        ConfigurationResult configuration = configurationService.findByAppKey(appKey);
        return new ResponseEntity<>(configuration, HttpStatus.OK);
    }

}

