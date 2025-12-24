package filter;

import filter.statistics.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.EnumMap;
import java.util.Map;

public final class FileProcessor {

    private final Arguments arguments;
    private final LineTypeDetector detector = new LineTypeDetector();

    public FileProcessor(Arguments arguments) {
        this.arguments = arguments;
    }

    public void process() {
        Map<DataType, Statistics> statistics = new EnumMap<>(DataType.class);
        statistics.put(DataType.INTEGER, new IntegerStatistics(arguments.fullStatistics()));
        statistics.put(DataType.FLOAT, new FloatStatistics(arguments.fullStatistics()));
        statistics.put(DataType.STRING, new StringStatistics(arguments.fullStatistics()));

        try (OutputManager output = new OutputManager(
                arguments.outputDirectory(),
                arguments.filePrefix(),
                arguments.appendMode()
        )) {
            for (var file : arguments.inputFiles()) {
                processFile(file, output, statistics);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи результатов: " + e.getMessage());
            return;
        }

        statistics.values().forEach(Statistics::print);
    }

    private void processFile(
            java.nio.file.Path file,
            OutputManager output,
            Map<DataType, Statistics> statistics
    ) {
        try (var lines = Files.lines(file)) {
            lines.forEach(line -> handleLine(line, output, statistics));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + file + ": " + e.getMessage());
        }
    }

    private void handleLine(
            String line,
            OutputManager output,
            Map<DataType, Statistics> statistics
    ) {
        DataType type = detector.detect(line);

        try {
            output.write(type, line);
            statistics.get(type).accept(line);
        } catch (Exception e) {
            System.err.println("Ошибка обработки строки: " + line);
        }
    }
}
