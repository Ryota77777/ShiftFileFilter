package filter.statistics;

public final class StringStatistics implements Statistics {

    private long count;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;
    private final boolean full;

    public StringStatistics(boolean full) {
        this.full = full;
    }

    @Override
    public void accept(String value) {
        count++;

        if (full) {
            int len = value.length();
            minLength = Math.min(minLength, len);
            maxLength = Math.max(maxLength, len);
        }
    }

    @Override
    public void print() {
        System.out.println("Strings:");
        System.out.println("  count = " + count);

        if (full && count > 0) {
            System.out.println("  min length = " + minLength);
            System.out.println("  max length = " + maxLength);
        }
    }
}
