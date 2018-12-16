package pe.fcg.kth.id1212.hw4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.entity.CurrencyEntity;

@Transactional(propagation = Propagation.MANDATORY)
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    CurrencyEntity findCurrencyEntityByCode(String code);
}
