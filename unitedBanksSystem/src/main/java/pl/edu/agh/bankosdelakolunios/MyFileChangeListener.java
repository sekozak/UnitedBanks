package pl.edu.agh.bankosdelakolunios;

import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import pl.edu.agh.bankosdelakolunios.db.TransactionService;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

@Component
public class MyFileChangeListener implements FileChangeListener {

    private final TransactionHistoryReader reader;
    private final TransactionService transactionService;

    public MyFileChangeListener(TransactionService transactionService) {
        this.reader = new TransactionHistoryReader();
        this.transactionService = transactionService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles : changeSet) {
            for(ChangedFile cfile: cfiles.getFiles()) {
                if (cfile.getType().equals(ChangedFile.Type.ADD)){
                    System.out.println("File added");
                    String fileType = cfile.getRelativeName().substring(0,3);
                    List<Transaction> transactions = reader.readFromFile(cfile.getFile().getAbsolutePath(), fileType);

                    // save to db
                    transactions.forEach(transactionService::addTransaction);
                    // notify UI

                    transactions.forEach(System.out::println);
                }
                else if (cfile.getType().equals(ChangedFile.Type.DELETE)) {
                    System.out.println("File deleted");
                }
                else if (cfile.getType().equals(ChangedFile.Type.MODIFY)) {
                    System.out.println("File modified");
                }else {
                    System.out.println("XD");
                }
            }
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock = ch.tryLock()) {
            return lock == null;
        } catch (IOException e) {
            return true;
        }
    }

}