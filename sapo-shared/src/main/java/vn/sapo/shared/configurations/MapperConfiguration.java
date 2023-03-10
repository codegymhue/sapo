package vn.sapo.shared.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.InheritingConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfigure {

    public static final String MODEL_MAPPER_SKIP_NULL_ENABLED = "MODEL_MAPPER_SKIP_NULL_ENABLED";
    public static final String MODEL_MAPPER_SKIP_NULL_DISABLED = "MODEL_MAPPER_SKIP_NULL_DISABLED";

    @Bean(MODEL_MAPPER_SKIP_NULL_ENABLED)
    @Primary
    public ModelMapper getModelMapperSkipNullEnabled() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean(MODEL_MAPPER_SKIP_NULL_DISABLED)
    public ModelMapper getModelMapperSkipNullDisabled() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

}
