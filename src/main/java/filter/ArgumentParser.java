package filter;

import  java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ArgumentParser {

    public Arguments parse(String[] args) {
        boolean shortStats = false;
        boolean fullStats = false;
        boolean append = false;

        Path outputDir = Path.of(".");
        String prefix = "";

        List<Path> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s" -> shortStats = true;
                case "-f" -> fullStats = true;
                case "-a" -> append = true;
                case "-o" -> outputDir = Path.of(nextArg(args, ++i, "-o"));
                case "-p" -> prefix = nextArg(args, ++i, "-p");
                default -> inputFiles.add(Path.of(args[i]));
            }
        }

        if (!shortStats && !fullStats) {
            shortStats = true;
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("Не указаны входные файлы");
        }

        return new Arguments(
                shortStats,
                fullStats,
                append,
                outputDir,
                prefix,
                inputFiles
        );
    }

    private String nextArg(String[] args, int index, String option) {
        if (index >= args.length) {
            throw new IllegalArgumentException("Опция " + option + " требует аргумент");
        }
        return args[index];
    }
}
