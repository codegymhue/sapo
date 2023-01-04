package vn.sapo.configuration;

import vn.sapo.configuration.dto.ConfigurationResult;

public interface ConfigurationService {
    ConfigurationResult findByAppKey(String appKey);
}
