package pl.edu.agh.bankosdelakolunios.storage;

import pl.edu.agh.bankosdelakolunios.domain.ports.TransactionHistoryFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements TransactionHistoryFileReader {

    @Override
    public List<List<String>> loadTransactionHistory(String file) {
        return readFromCSV(file);
    }

    private static List<List<String>> readFromCSV(String fileName) {
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            String line = br.readLine(); // read the first line from the csv file
            List<List<String>> finalData = new ArrayList<>();
            line = br.readLine();
            while (line != null) {

                String[] data = line.split(",");
                List<String> formattedData = new ArrayList<>();
                for (String d : data) {
                    formattedData.add(d.replaceAll("\"", ""));
                }
                finalData.add(formattedData);
                line = br.readLine();
            }
            return finalData;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new IllegalStateException("Failed to read CSV file");
    }

    public static List<String> readFromPostReq(String content) {
        String[] data = content.split(",");
        List<String> formattedData = new ArrayList<>();
        for (String d : data) {
            formattedData.add(d.replaceAll("\"", ""));
        }
        return formattedData;
    }
}
