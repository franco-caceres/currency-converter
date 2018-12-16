package pe.fcg.kth.id1212.hw4.domain;

public class ExchangeRate {
    private Long id;
    private Currency from;
    private Currency to;
    private Double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public ExchangeRate getReverse() {
        ExchangeRate reverse = new ExchangeRate();
        reverse.setFrom(to);
        reverse.setTo(from);
        reverse.setRate(1 / rate);
        return reverse;
    }
}
