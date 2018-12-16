package pe.fcg.kth.id1212.hw4.domain;

public class AppInfo {
    public static final String CONVERSION_COUNT = "CONVERSION_COUNT";

    private Long id;
    private String name;
    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
