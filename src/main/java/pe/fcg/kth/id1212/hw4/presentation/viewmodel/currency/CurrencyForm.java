package pe.fcg.kth.id1212.hw4.presentation.viewmodel.currency;

import pe.fcg.kth.id1212.hw4.domain.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CurrencyForm {
    private Long id;

    @Pattern(regexp = "[A-Z]{3}", message = "Please specify a 3-uppercase-letter code")
    private String code;

    @NotBlank(message = "Please specify a name")
    private String name;

    public CurrencyForm() {

    }

    public CurrencyForm(Currency currency) {
        id = currency.getId();
        code = currency.getCode();
        name = currency.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
