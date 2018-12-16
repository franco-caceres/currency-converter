package pe.fcg.kth.id1212.hw4.presentation.viewmodel.currencyconversion;

import pe.fcg.kth.id1212.hw4.domain.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class ConversionForm {
    @NotNull(message = "Please specify an amount")
    @Positive(message = "Please specify a positive amount")
    private Double amount;

    @NotBlank(message = "Please specify a currency to convert from")
    private String fromCurrencyCode;

    @NotBlank(message = "Please specify a currency to convert to")
    private String toCurrencyCode;

    private List<Currency> currencies;

    public ConversionForm(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
