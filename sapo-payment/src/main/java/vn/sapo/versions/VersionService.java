package vn.sapo.versions;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.fx.qh.sapo.entities.Application;

import java.util.Optional;

@Component
public class VersionService  implements InitializingBean {
    @Autowired
    VersionRepository applicationRepository;
    @Value("${web.app.version-code}")
    int appVersionCode;
    @Value("${web.app.key}")
    String appKey;

    //ANH CU VIET CAM XOA
    @Override
    public void afterPropertiesSet() throws Exception {
        Optional<Application> appOpt = applicationRepository.findAllByAppKey(appKey);
        if (!appOpt.isPresent())
            throw new RuntimeException("Ban can cai dat mot ung dung");
        Application app = appOpt.get();
        if (!app.getVersionCode().equals(appVersionCode))
            throw new RuntimeException("BAN CAN CAI DAT PHIEN BAN CODE MOI (TOM LAI LA MERGE CODE)");
    }

}
