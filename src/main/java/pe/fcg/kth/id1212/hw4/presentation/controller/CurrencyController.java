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
import pe.fcg.kth.id1212.hw4.domain.Currency;
import pe.fcg.kth.id1212.hw4.presentation.viewmodel.currency.CurrencyForm;
import pe.fcg.kth.id1212.hw4.util.Util;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/admin/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
    public String getIndex() {
        return "redirect:/admin/currency/list";
    }

    @GetMapping("/list")
    public String list(Map<String, Object> model) {
        model.put("currencies", currencyService.findAll());
        return "currency/list";
    }

    @GetMapping("/create")
    public String create(Map<String, Object> model) {
        model.put("currencyForm", new CurrencyForm());
        return "currency/create";
    }

    @PostMapping("/save")
    public String save(@Valid CurrencyForm currencyForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes ra) {
        if(bindingResult.hasErrors()) {
            return "currency/create";
        }
        currencyService.save(currencyForm.getCode(), currencyForm.getName());
        ra.addFlashAttribute("successMessage", "Currency created successfully.");
        return "redirect:/admin/currency/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("currencyForm", new CurrencyForm(currencyService.findById(id)));
        return "currency/edit";
    }

    @PostMapping("/update")
    public String update(@Valid CurrencyForm currencyForm, BindingResult bindingResult, Map<String, Object> model, RedirectAttributes ra) {
        if(bindingResult.hasErrors()) {
            return "currency/edit";
        }
        Currency currency = Util.copyBeanProperties(new Currency(), currencyForm);
        currencyService.update(currency);
        ra.addFlashAttribute("successMessage", "Currency updated successfully.");
        return "redirect:/admin/currency/list";
    }

    @PostMapping("/delete")
    public String delete(CurrencyForm currencyForm, Map<String, Object> model, RedirectAttributes ra) {
        currencyService.deleteById(currencyForm.getId());
        ra.addFlashAttribute("successMessage", "Currency deleted successfully.");
        return "redirect:/admin/currency/list";
    }
}
