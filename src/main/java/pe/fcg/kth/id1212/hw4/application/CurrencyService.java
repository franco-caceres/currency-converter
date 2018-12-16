package pe.fcg.kth.id1212.hw4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.domain.ApplicationException;
import pe.fcg.kth.id1212.hw4.domain.Currency;
import pe.fcg.kth.id1212.hw4.entity.CurrencyEntity;
import pe.fcg.kth.id1212.hw4.repository.CurrencyRepository;
import pe.fcg.kth.id1212.hw4.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ExchangeRateService exchangeRateService;

    public Currency save(Currency currency) {
        if(currencyRepository.findCurrencyEntityByCode(currency.getCode()) != null) {
            throw new ApplicationException(String.format("A currency with code %s already exists.", currency.getCode()));
        }
        CurrencyEntity currencyEntity = makeFromModel(currency);
        currencyEntity = currencyRepository.save(currencyEntity);
        return makeFromEntity(currencyEntity);
    }

    public Currency save(String code, String name) {
        Currency currency = new Currency();
        currency.setCode(code);
        currency.setName(name);
        return save(currency);
    }

    public Currency update(Currency currency) {
        CurrencyEntity currencyEntity = currencyRepository.findById(currency.getId()).orElse(null);
        currencyEntity = Util.copyBeanProperties(currencyEntity, currency);
        currencyRepository.save(currencyEntity);
        return makeFromEntity(currencyEntity);
    }

    public void deleteById(Long id) {
        CurrencyEntity currencyEntity = currencyRepository.findById(id).orElse(null);
        if(currencyEntity == null) {
            return;
        }
        exchangeRateService.deleteAllWithCode(currencyEntity.getCode());
        currencyRepository.deleteById(id);
    }

    public Long count() {
        return currencyRepository.count();
    }

    public List<Currency> findAll() {
        List<CurrencyEntity> entities = currencyRepository.findAll();
        return entities.stream().map(this::makeFromEntity).collect(Collectors.toList());
    }

    public Currency findByCode(String code) {
        CurrencyEntity currencyEntity = currencyRepository.findCurrencyEntityByCode(code);
        return makeFromEntity(currencyEntity);
    }

    public Currency findById(Long id) {
        CurrencyEntity currencyEntity = currencyRepository.findById(id).orElse(null);
        return makeFromEntity(currencyEntity);
    }

    CurrencyEntity makeFromModel(Currency model) {
        if(model == null) {
            return null;
        }
        return Util.copyBeanProperties(new CurrencyEntity(), model);
    }

    Currency makeFromEntity(CurrencyEntity entity) {
        if(entity == null) {
            return null;
        }
        return Util.copyBeanProperties(new Currency(), entity);
    }
}
