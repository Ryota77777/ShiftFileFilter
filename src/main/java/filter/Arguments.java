package filter;

import java.nio.file.Path;
import java.util.List;

public record Arguments (
        boolean shortStatistics,
        boolean fullStatistics,
        boolean appendMode,
        Path outputDirectory,
        String filePrefix,
        List<Path> inputFiles
) {

}
