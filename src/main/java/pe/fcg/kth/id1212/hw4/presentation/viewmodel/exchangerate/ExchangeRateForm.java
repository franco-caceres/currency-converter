package pe.fcg.kth.id1212.hw4.presentation.viewmodel.exchangerate;

import pe.fcg.kth.id1212.hw4.domain.Currency;
import pe.fcg.kth.id1212.hw4.domain.ExchangeRate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class ExchangeRateForm {
    private Long id;

    @NotNull(message = "Please specify a rate")
    @Positive(message = "Please specify a positive rate")
    private Double rate;

    @NotBlank(message = "Please specify a currency")
    private String fromCurrencyCode;

    @NotBlank(message = "Please specify a currency")
    private String toCurrencyCode;

    private List<Currency> currencies;

    public ExchangeRateForm() {

    }

    public ExchangeRateForm(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public ExchangeRateForm(ExchangeRate exchangeRate, List<Currency> currencies) {
        id = exchangeRate.getId();
        rate = exchangeRate.getRate();
        fromCurrencyCode = exchangeRate.getFrom().getCode();
        toCurrencyCode = exchangeRate.getTo().getCode();
        this.currencies = currencies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
