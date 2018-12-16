package pe.fcg.kth.id1212.hw4.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "EXCHANGE_RATE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CURRENCY_ID_FROM", "CURRENCY_ID_TO"})})
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID_FROM", nullable = false)
    private CurrencyEntity fromEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID_TO", nullable = false)
    private CurrencyEntity toEntity;

    @Basic
    @Positive(message = "Exchange rate should be positive")
    private Double rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyEntity getFromEntity() {
        return fromEntity;
    }

    public void setFromEntity(CurrencyEntity fromEntity) {
        this.fromEntity = fromEntity;
    }

    public CurrencyEntity getToEntity() {
        return toEntity;
    }

    public void setToEntity(CurrencyEntity toEntity) {
        this.toEntity = toEntity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRateEntity that = (ExchangeRateEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fromEntity, that.fromEntity) &&
                Objects.equals(toEntity, that.toEntity) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fromEntity, toEntity, rate);
    }
}
