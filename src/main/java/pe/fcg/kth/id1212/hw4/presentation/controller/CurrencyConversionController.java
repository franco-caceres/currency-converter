package pe.fcg.kth.id1212.hw4.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.fcg.kth.id1212.hw4.application.CurrencyConversionService;
import pe.fcg.kth.id1212.hw4.application.CurrencyService;
import pe.fcg.kth.id1212.hw4.presentation.viewmodel.currencyconversion.ConversionForm;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("")
    public String index(Map<String, Object> model) {
        model.put("conversionForm", new ConversionForm(currencyService.findAll()));
        return "currency-conversion/index";
    }

    @GetMapping("/convert")
    public String convert(@Valid ConversionForm conversionForm, BindingResult bindingResult, Map<String, Object> model) {
        if(bindingResult.hasErrors()) {
            conversionForm.setCurrencies(currencyService.findAll());
            return "currency-conversion/index";
        }
        Double result = currencyConversionService.convert(
                conversionForm.getAmount(), conversionForm.getFromCurrencyCode(), conversionForm.getToCurrencyCode());
        model.put("result", result);
        model.put("amount", conversionForm.getAmount());
        model.put("fromCurrency", currencyService.findByCode(conversionForm.getFromCurrencyCode()));
        model.put("toCurrency", currencyService.findByCode(conversionForm.getToCurrencyCode()));
        return "currency-conversion/result";
    }
}
