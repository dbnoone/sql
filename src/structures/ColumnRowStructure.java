package structures;

public class ColumnRowStructure {
    private final String key;
    private final String value;

    public ColumnRowStructure(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String get_key() {
        return this.key;
    }

    public String get_value() {
        return this.value;
    }
}
