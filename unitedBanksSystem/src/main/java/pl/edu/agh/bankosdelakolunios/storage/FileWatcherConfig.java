package pl.edu.agh.bankosdelakolunios.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;

import java.io.File;
import java.sql.SQLOutput;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    static final long POLLINTERVAL = 1000L;
    static final long QUIETPERIOD = 500L;
    @Value("${storage.path}")
    private String path;
    @Bean
    public FileSystemWatcher fileSystemWatcher(TransactionService transactionService) {
        String dir = System.getProperty("user.dir") + path;
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(POLLINTERVAL), Duration.ofMillis(QUIETPERIOD));
        fileSystemWatcher.addSourceDirectory(new File(dir));
        fileSystemWatcher.addListener(new MyFileChangeListener(transactionService));
        fileSystemWatcher.start();
        System.out.println("started fileSystemWatcher");
        return fileSystemWatcher;
    }
}
