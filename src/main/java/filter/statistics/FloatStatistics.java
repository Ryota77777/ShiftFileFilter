package filter.statistics;

public final class FloatStatistics implements Statistics {

    private long count;
    private double min = Double.MAX_VALUE;
    private double max = -Double.MAX_VALUE;
    private double sum;
    private final boolean full;

    public FloatStatistics(boolean full) {
        this.full = full;
    }

    @Override
    public void accept(String value) {
        double v = Double.parseDouble(value);
        count++;

        if (full) {
            min = Math.min(min, v);
            max = Math.max(max, v);
            sum += v;
        }
    }

    @Override
    public void print() {
        System.out.println("Floats:");
        System.out.println("  count = " + count);

        if (full && count > 0) {
            System.out.println("  min   = " + min);
            System.out.println("  max   = " + max);
            System.out.println("  sum   = " + sum);
            System.out.println("  avg   = " + (sum / count));
        }
    }
}
