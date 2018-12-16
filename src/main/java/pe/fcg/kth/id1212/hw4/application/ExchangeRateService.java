package pe.fcg.kth.id1212.hw4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.domain.ApplicationException;
import pe.fcg.kth.id1212.hw4.domain.ExchangeRate;
import pe.fcg.kth.id1212.hw4.entity.ExchangeRateEntity;
import pe.fcg.kth.id1212.hw4.repository.ExchangeRateRepository;
import pe.fcg.kth.id1212.hw4.util.Util;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExchangeRateService {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public ExchangeRate save(ExchangeRate exchangeRate) {
        ExchangeRateEntity existing = exchangeRateRepository.findByFromCodeAndToCode(
                exchangeRate.getFrom().getCode(),
                exchangeRate.getTo().getCode()
        );
        if(existing != null) {
            throw new ApplicationException(
                    String.format("An exchange rate from %s to %s already exists.",
                        exchangeRate.getFrom().getCode(),
                            exchangeRate.getTo().getCode())
            );
        }
        ExchangeRateEntity exchangeRateEntity = makeFromModel(exchangeRate);
        exchangeRateEntity = exchangeRateRepository.save(exchangeRateEntity);
        return makeFromEntity(exchangeRateEntity);
    }

    public ExchangeRate saveWithReverse(String fromCurrencyCode, String toCurrencyCode, Double rate) {
        save(toCurrencyCode, fromCurrencyCode, 1 / rate);
        return save(fromCurrencyCode, toCurrencyCode, rate);
    }

    public ExchangeRate save(String fromCurrencyCode, String toCurrencyCode, Double rate) {
        if (fromCurrencyCode.equals(toCurrencyCode)) {
            throw new ApplicationException("Only exchange rates between different currencies allowed");
        }
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setFrom(currencyService.findByCode(fromCurrencyCode));
        exchangeRate.setTo(currencyService.findByCode(toCurrencyCode));
        exchangeRate.setRate(rate);
        return save(exchangeRate);
    }

    public ExchangeRate update(ExchangeRate exchangeRate) {
        ExchangeRateEntity entity = exchangeRateRepository.findById(exchangeRate.getId()).orElse(null);
        if(entity == null) {
            return null;
        }
        entity.setRate(exchangeRate.getRate());
        exchangeRateRepository.save(entity);
        return makeFromEntity(entity);
    }

    public ExchangeRate updateWithReverse(ExchangeRate exchangeRate) {
        ExchangeRateEntity entity = exchangeRateRepository.findById(exchangeRate.getId()).orElse(null);
        if(entity == null) {
            return null;
        }
        entity.setRate(exchangeRate.getRate());
        ExchangeRateEntity entityReverse = exchangeRateRepository.findByFromCodeAndToCode(entity.getToEntity().getCode(), entity.getFromEntity().getCode());
        entityReverse.setRate(1 / exchangeRate.getRate());
        exchangeRateRepository.save(entityReverse);
        exchangeRateRepository.save(entity);
        return makeFromEntity(entity);
    }

    public void deleteById(Long id) {
        exchangeRateRepository.deleteById(id);
    }

    public void deleteByIdWithReverse(Long id) {
        ExchangeRate exchangeRate = findById(id);
        ExchangeRateEntity entityReverse = exchangeRateRepository.findByFromCodeAndToCode(exchangeRate.getTo().getCode(), exchangeRate.getFrom().getCode());
        deleteById(id);
        deleteById(entityReverse.getId());
    }

    public void deleteAllWithCode(String code) {
        exchangeRateRepository.deleteAllWithCode(code);
    }

    public Long count() {
        return exchangeRateRepository.count();
    }

    public ExchangeRate findById(Long id) {
        ExchangeRateEntity entity = exchangeRateRepository.findById(id).orElse(null);
        return makeFromEntity(entity);
    }

    public List<ExchangeRate> findAll() {
        List<ExchangeRateEntity> entities = exchangeRateRepository.findAll();
        return entities.stream().map(this::makeFromEntity).collect(Collectors.toList());
    }

    public ExchangeRate findByFromCodeAndToCode(String fromCurrencyCode, String toCurrencyCode) {
        ExchangeRateEntity entity = exchangeRateRepository.findByFromCodeAndToCode(fromCurrencyCode, toCurrencyCode);
        return makeFromEntity(entity);
    }

    ExchangeRateEntity makeFromModel(ExchangeRate model) {
        if(model == null) {
            return null;
        }
        ExchangeRateEntity entity = Util.copyBeanProperties(new ExchangeRateEntity(), model);
        entity.setFromEntity(currencyService.makeFromModel(model.getFrom()));
        entity.setToEntity(currencyService.makeFromModel(model.getTo()));
        return entity;
    }

    ExchangeRate makeFromEntity(ExchangeRateEntity entity) {
        if(entity == null) {
            return null;
        }
        ExchangeRate model = Util.copyBeanProperties(new ExchangeRate(), entity);
        model.setFrom(currencyService.makeFromEntity(entity.getFromEntity()));
        model.setTo(currencyService.makeFromEntity(entity.getToEntity()));
        return model;
    }
}
