package pl.edu.agh.bankosdelakolunios;

import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.bankosdelakolunios.db.TransactionRepository;
import pl.edu.agh.bankosdelakolunios.db.TransactionService;

import java.io.File;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    @Bean
    public FileSystemWatcher fileSystemWatcher(TransactionService transactionService) {
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\storage";
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(1000L), Duration.ofMillis(500L));
        fileSystemWatcher.addSourceDirectory(new File(dir));
        fileSystemWatcher.addListener(new MyFileChangeListener(transactionService));
        fileSystemWatcher.start();
        System.out.println("started fileSystemWatcher");
        return fileSystemWatcher;
    }
}
