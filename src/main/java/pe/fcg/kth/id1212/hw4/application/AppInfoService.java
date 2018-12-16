package pe.fcg.kth.id1212.hw4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.domain.AppInfo;
import pe.fcg.kth.id1212.hw4.entity.AppInfoEntity;
import pe.fcg.kth.id1212.hw4.repository.AppInfoRepository;
import pe.fcg.kth.id1212.hw4.util.Util;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppInfoService {
    @Autowired
    AppInfoRepository appInfoRepository;

    public AppInfo save(AppInfo appInfo) {
       AppInfoEntity entity = makeFromModel(appInfo);
       entity = appInfoRepository.save(entity);
       return makeFromEntity(entity);
    }

    public Long getValue(String name) {
        AppInfoEntity entity = appInfoRepository.findByName(name);
        return entity.getValue();
    }

    public void increaseByNameAndDelta(String name, Long delta) {
        appInfoRepository.increaseByNameAndDelta(name, delta);
    }

    AppInfoEntity makeFromModel(AppInfo model) {
        return Util.copyBeanProperties(new AppInfoEntity(), model);
    }

    AppInfo makeFromEntity(AppInfoEntity entity) {
        return Util.copyBeanProperties(new AppInfo(), entity);
    }
}
