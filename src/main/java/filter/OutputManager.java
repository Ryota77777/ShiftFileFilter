package filter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.EnumMap;
import java.util.Map;

public final class OutputManager implements AutoCloseable {

    private final Map<DataType, BufferedWriter> writers = new EnumMap<>(DataType.class);
    private final Path directory;
    private final String prefix;
    private final boolean append;

    public OutputManager(Path directory, String prefix, boolean append) {
        this.directory = directory;
        this.prefix = prefix;
        this.append = append;
    }

    public void write(DataType type, String value) throws IOException {
        BufferedWriter writer = writers.computeIfAbsent(type, this::createWriter);
        writer.write(value);
        writer.newLine();
    }

    private BufferedWriter createWriter(DataType type) {
        try {
            Files.createDirectories(directory);
            Path file = directory.resolve(prefix + type.fileName());

            OpenOption[] options = append
                    ? new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND}
                    : new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

            return Files.newBufferedWriter(file, options);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать файл для " + type, e);
        }
    }

    @Override
    public void close() throws IOException {
        for (BufferedWriter writer : writers.values()) {
            writer.close();
        }
    }
}
