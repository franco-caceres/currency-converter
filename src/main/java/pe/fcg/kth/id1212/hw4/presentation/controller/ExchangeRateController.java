package pe.fcg.kth.id1212.hw4.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.fcg.kth.id1212.hw4.application.CurrencyService;
import pe.fcg.kth.id1212.hw4.application.ExchangeRateService;
import pe.fcg.kth.id1212.hw4.domain.ExchangeRate;
import pe.fcg.kth.id1212.hw4.presentation.viewmodel.exchangerate.ExchangeRateForm;
import pe.fcg.kth.id1212.hw4.util.Util;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/admin/exchange-rate")
public class ExchangeRateController {
    @Autowired
    ExchangeRateService exchangeRateService;
    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
    public String getIndex() {
        return "redirect:/admin/exchange-rate/list";
    }

    @GetMapping("/list")
    public String list(Map<String, Object> model) {
        model.put("exchangeRates", exchangeRateService.findAll());
        return "exchange-rate/list";
    }

    @GetMapping("/create")
    public String create(Map<String, Object> model) {
        model.put("exchangeRateForm", new ExchangeRateForm(currencyService.findAll()));
        return "exchange-rate/create";
    }

    @PostMapping("/save")
    public String save(@Valid ExchangeRateForm exchangeRateForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes ra) {
        if(bindingResult.hasErrors()) {
            exchangeRateForm.setCurrencies(currencyService.findAll());
            return "exchange-rate/create";
        }
        exchangeRateService.saveWithReverse(exchangeRateForm.getFromCurrencyCode(), exchangeRateForm.getToCurrencyCode(), exchangeRateForm.getRate());
        ra.addFlashAttribute("successMessage", "Exchange rate created successfully.");
        return "redirect:/admin/exchange-rate/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("exchangeRateForm", new ExchangeRateForm(exchangeRateService.findById(id), currencyService.findAll()));
        return "exchange-rate/edit";
    }

    @PostMapping("/update")
    public String update(@Valid ExchangeRateForm exchangeRateForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes ra) {
        if(bindingResult.hasErrors()) {
            exchangeRateForm.setCurrencies(currencyService.findAll());
            return "exchange-rate/edit";
        }
        ExchangeRate exchangeRate = Util.copyBeanProperties(new ExchangeRate(), exchangeRateForm);
        exchangeRateService.updateWithReverse(exchangeRate);
        ra.addFlashAttribute("successMessage", "Exchange rate updated successfully.");
        return "redirect:/admin/exchange-rate/list";
    }

    @PostMapping("/delete")
    public String delete(ExchangeRateForm exchangeRateForm, Map<String, Object> model, RedirectAttributes ra) {
        exchangeRateService.deleteByIdWithReverse(exchangeRateForm.getId());
        ra.addFlashAttribute("successMessage", "Exchange rate deleted successfully.");
        return "redirect:/admin/exchange-rate/list";
    }
}