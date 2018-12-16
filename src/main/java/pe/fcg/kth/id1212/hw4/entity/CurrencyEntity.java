package pe.fcg.kth.id1212.hw4.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "CURRENCY")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "CODE", unique = true)
    @Size(min = 3, max = 3, message = "Currency code should be 3 characters long")
    private String code;

    @Basic
    @Column(name = "NAME")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyEntity currencyEntity = (CurrencyEntity) o;
        return Objects.equals(id, currencyEntity.id) &&
                Objects.equals(code, currencyEntity.code) &&
                Objects.equals(name, currencyEntity.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name);
    }
}
