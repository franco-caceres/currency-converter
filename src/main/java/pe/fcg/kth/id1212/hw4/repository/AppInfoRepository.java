package pe.fcg.kth.id1212.hw4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.entity.AppInfoEntity;

@Transactional(propagation = Propagation.MANDATORY)
public interface AppInfoRepository extends JpaRepository<AppInfoEntity, Long> {
    AppInfoEntity findByName(String name);

    @Modifying
    @Query("update AppInfoEntity a set a.value=a.value+:delta where a.name=:name")
    void increaseByNameAndDelta(@Param("name") String name, @Param("delta") Long delta);
}
