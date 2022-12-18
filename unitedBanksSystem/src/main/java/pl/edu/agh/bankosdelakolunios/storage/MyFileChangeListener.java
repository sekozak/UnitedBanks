package pl.edu.agh.bankosdelakolunios.storage;

import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;

import java.util.Set;

@Component
public class MyFileChangeListener implements FileChangeListener {
    private final TransactionService transactionService;

    public MyFileChangeListener(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles : changeSet) {
            for(ChangedFile cfile: cfiles.getFiles()) {
                if (cfile.getType().equals(ChangedFile.Type.ADD)){
                    System.out.println("File added");
                    transactionService.uploadTransactions(cfile.getFile());
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
}