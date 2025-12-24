package filter;

public final class LineTypeDetector {

    public DataType detect(String line) {
        if (isInteger(line)) {
            return DataType.INTEGER;
        }
        if (isFloat(line)) {
            return DataType.FLOAT;
        }
        return DataType.STRING;
    }

    private boolean isInteger(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

