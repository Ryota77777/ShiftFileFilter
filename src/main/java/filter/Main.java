package filter;

public final class Main {
    public static void main(String[] args) {
        try {
            Arguments arguments = new ArgumentParser().parse(args);
            new FileProcessor(arguments).process();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка запуска: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}
