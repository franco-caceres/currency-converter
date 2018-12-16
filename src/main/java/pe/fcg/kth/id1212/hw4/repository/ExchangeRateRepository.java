package pe.fcg.kth.id1212.hw4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.entity.ExchangeRateEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
    @Query("select er from ExchangeRateEntity er where er.fromEntity.code=:fromCode and er.toEntity.code=:toCode")
    ExchangeRateEntity findByFromCodeAndToCode(@Param("fromCode") String from, @Param("toCode") String to);

    @Modifying
    @Query("delete from ExchangeRateEntity er where er.id in (select distinct e.id from ExchangeRateEntity e where e.fromEntity.code=:code or e.toEntity.code=:code)")
    void deleteAllWithCode(@Param("code") String code);
}
