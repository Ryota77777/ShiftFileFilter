package filter.statistics;

public final class IntegerStatistics implements Statistics {

    private long count;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;
    private long sum;
    private final boolean full;

    public IntegerStatistics(boolean full) {
        this.full = full;
    }

    @Override
    public void accept(String value) {
        long v = Long.parseLong(value);
        count++;

        if (full) {
            min = Math.min(min, v);
            max = Math.max(max, v);
            sum += v;
        }
    }

    @Override
    public void print() {
        System.out.println("Integers:");
        System.out.println("  count = " + count);

        if (full && count > 0) {
            System.out.println("  min   = " + min);
            System.out.println("  max   = " + max);
            System.out.println("  sum   = " + sum);
            System.out.println("  avg   = " + (sum / (double) count));
        }
    }
}
