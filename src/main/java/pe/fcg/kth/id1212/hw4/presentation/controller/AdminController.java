package pe.fcg.kth.id1212.hw4.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.fcg.kth.id1212.hw4.application.AppInfoService;
import pe.fcg.kth.id1212.hw4.application.CurrencyService;
import pe.fcg.kth.id1212.hw4.application.ExchangeRateService;
import pe.fcg.kth.id1212.hw4.domain.AppInfo;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping("")
    public String index(Map<String, Object> model) {
        model.put("conversionCount", appInfoService.getValue(AppInfo.CONVERSION_COUNT));
        model.put("currencyCount", currencyService.count());
        model.put("exchangeRateCount", exchangeRateService.count());
        return "admin/index";
    }
}
