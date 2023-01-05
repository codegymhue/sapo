package vn.sapo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.configuration.dto.ConfigurationResult;
import vn.sapo.entities.configuration.Configuration;


@Component
public class ConfigurationMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ConfigurationResult toDTO(Configuration configuration) {
        return modelMapper.map(configuration, ConfigurationResult.class);
    }
}
