package pe.fcg.kth.id1212.hw4.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.fcg.kth.id1212.hw4.application.AppInfoService;
import pe.fcg.kth.id1212.hw4.application.CurrencyService;
import pe.fcg.kth.id1212.hw4.application.ExchangeRateService;
import pe.fcg.kth.id1212.hw4.domain.AppInfo;
import pe.fcg.kth.id1212.hw4.domain.Currency;
import pe.fcg.kth.id1212.hw4.domain.ExchangeRate;

@Component
@Transactional(rollbackFor = Exception.class)
public class StartupBean implements InitializingBean {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    ExchangeRateService exchangeRateService;
    @Autowired
    AppInfoService appInfoService;

    @Override
    public void afterPropertiesSet() {
        insertCurrencies();
        insertExchangeRates();
        insertAppInfos();
    }

    private void insertCurrencies() {
        Currency sek = new Currency();
        sek.setCode("SEK");
        sek.setName("Swedish Krona");
        Currency usd = new Currency();
        usd.setCode("USD");
        usd.setName("United States Dollar");
        Currency pen = new Currency();
        pen.setCode("PEN");
        pen.setName("Peruvian Sol");
        Currency cad = new Currency();
        cad.setCode("CAD");
        cad.setName("Canadian Dollar");
        currencyService.save(sek);
        currencyService.save(usd);
        currencyService.save(pen);
        currencyService.save(cad);
    }

    private void insertExchangeRates() {
        Currency sek = currencyService.findByCode("SEK");
        Currency usd = currencyService.findByCode("USD");
        Currency pen = currencyService.findByCode("PEN");
        Currency cad = currencyService.findByCode("CAD");
        ExchangeRate sekToUsd = new ExchangeRate();
        sekToUsd.setFrom(sek);
        sekToUsd.setTo(usd);
        sekToUsd.setRate(0.11);
        ExchangeRate sekToPen = new ExchangeRate();
        sekToPen.setFrom(sek);
        sekToPen.setTo(pen);
        sekToPen.setRate(0.37);
        ExchangeRate sekToCad = new ExchangeRate();
        sekToCad.setFrom(sek);
        sekToCad.setTo(cad);
        sekToCad.setRate(0.15);
        ExchangeRate usdToPen = new ExchangeRate();
        usdToPen.setFrom(usd);
        usdToPen.setTo(pen);
        usdToPen.setRate(3.35);
        ExchangeRate usdToCad = new ExchangeRate();
        usdToCad.setFrom(usd);
        usdToCad.setTo(cad);
        usdToCad.setRate(1.34);
        ExchangeRate penToCad = new ExchangeRate();
        penToCad.setFrom(pen);
        penToCad.setTo(cad);
        penToCad.setRate(0.40);
        exchangeRateService.save(sekToUsd);
        exchangeRateService.save(sekToUsd.getReverse());
        exchangeRateService.save(sekToPen);
        exchangeRateService.save(sekToPen.getReverse());
        exchangeRateService.save(sekToCad);
        exchangeRateService.save(sekToCad.getReverse());
        exchangeRateService.save(usdToPen);
        exchangeRateService.save(usdToPen.getReverse());
        exchangeRateService.save(usdToCad);
        exchangeRateService.save(usdToCad.getReverse());
        exchangeRateService.save(penToCad);
        exchangeRateService.save(penToCad.getReverse());
    }

    private void insertAppInfos() {
        AppInfo appInfo = new AppInfo();
        appInfo.setName(AppInfo.CONVERSION_COUNT);
        appInfo.setValue(0L);
        appInfoService.save(appInfo);
    }
}
