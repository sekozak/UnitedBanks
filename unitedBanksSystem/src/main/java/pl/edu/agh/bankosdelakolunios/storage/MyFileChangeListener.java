package pl.edu.agh.bankosdelakolunios.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import pl.edu.agh.bankosdelakolunios.domain.TransactionService;

import java.util.Set;


@Component
public class MyFileChangeListener implements FileChangeListener {
    private final TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(MyFileChangeListener.class);


    public MyFileChangeListener(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles cfiles : changeSet) {
            for(ChangedFile cfile: cfiles.getFiles()) {
                if (cfile.getType().equals(ChangedFile.Type.ADD)){
                    logger.info("File added");
                    String fileName = cfile.getFile().getName();
                    if(fileName.endsWith("csv")){ transactionService.uploadTransactions(cfile.getFile()); }
                    else{ logger.warn("Wrong file type"); }
                }
                else if (cfile.getType().equals(ChangedFile.Type.DELETE)) {
                    logger.info("File deleted");
                }
                else if (cfile.getType().equals(ChangedFile.Type.MODIFY)) {
                    logger.info("File modified");
                }else {
                    logger.info("Unknown operation on file!");
                }
            }
        }
    }
}