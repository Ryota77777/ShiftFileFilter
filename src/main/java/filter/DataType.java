package filter;

public enum DataType {
    INTEGER("integers.txt"),
    FLOAT("floats.txt"),
    STRING("strings.txt");

    private final String fileName;

    DataType(String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }
}
