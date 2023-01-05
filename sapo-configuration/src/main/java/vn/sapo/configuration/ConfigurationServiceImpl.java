package vn.sapo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.configuration.dto.ConfigurationResult;
import vn.sapo.entities.configuration.Configuration;
import vn.sapo.shared.exceptions.NotFoundException;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    @Autowired
    private ConfigurationMapper configurationMapper;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Override
    @Transactional(readOnly = true)
    public ConfigurationResult findByAppKey(String appKey) {
        Configuration configuration = configurationRepository.findById(appKey).orElseThrow(() -> new NotFoundException("Configuration not found"));
        return configurationMapper.toDTO(configuration);
    }
}
