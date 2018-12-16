package pe.fcg.kth.id1212.hw4.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.domain.AppInfo;
import pe.fcg.kth.id1212.hw4.domain.ApplicationException;
import pe.fcg.kth.id1212.hw4.domain.ExchangeRate;

@Service
@Transactional(rollbackFor = Exception.class)
public class CurrencyConversionService {
    @Autowired
    ExchangeRateService exchangeRateService;
    @Autowired
    AppInfoService appInfoService;

    public Double convert(Double amount, String fromCurrencyCode, String toCurrencyCode) {
        if(fromCurrencyCode.equals(toCurrencyCode)) {
            return amount;
        }
        ExchangeRate exchangeRate = exchangeRateService.findByFromCodeAndToCode(fromCurrencyCode, toCurrencyCode);
        if(exchangeRate == null) {
            throw new ApplicationException(String.format("No exchange rate found from %s to %s", fromCurrencyCode, toCurrencyCode));
        }
        appInfoService.increaseByNameAndDelta(AppInfo.CONVERSION_COUNT, 1L);
        return amount*exchangeRate.getRate();
    }
}
