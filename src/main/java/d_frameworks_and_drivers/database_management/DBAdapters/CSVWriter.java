package d_frameworks_and_drivers.database_management.DBAdapters;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVWriter implements AutoCloseable {
    protected File csvFile;
    private FileWriter fileWriter;
    private CSVPrinter csvPrinter;

    /**
     * Creates a new CSVRemover given the filePath to csv to read.
     *
     * @param filePath
     */
    public CSVWriter(String filePath) throws FileNotFoundException {
        this.csvFile = new File(filePath);
    }

    /**
     * Opens the resources and initializes the fields.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void openResources() throws IOException {
        fileWriter = new FileWriter(csvFile);
        csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader().withNullString(""));
    }

    /**
     * Adds a list of String as a record into csv file.
     *
     * @param inputRecord a list of strings
     */
    public void insert(List<String> inputRecord) {
        // Get the header size from the CSV file, assuming the CSV file has a header
        int headerNumber = -1;

        try (CSVParser csvParser = new CSVParser(new FileReader(csvFile), CSVFormat.DEFAULT.withHeader().withNullString(""))) {
            headerNumber = csvParser.getHeaderMap().size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the input and csv file dont match
        if (headerNumber != inputRecord.size()) {
            throw new IllegalArgumentException("Number of inputted fields doesn't match number of csv headers.");
        }

        // Try with resource: create FileWriter and csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter(csvFile);
             CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader().withNullString(""))) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(inputRecord);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a list of String as a record into csv file.
     *
     * @param strings one or more string parameters
     */
    public void insert(String... strings) {
        if (strings.length == 0) {
            throw new IllegalArgumentException("At least one argument is required.");
        }

        List<String> inputRecord = new ArrayList<>();

        for (String s : strings) {
            inputRecord.add(s);
        }

        // Get the header size from the CSV file, assuming the CSV file has a header
        int headerNumber = -1;

        try (CSVParser csvParser = new CSVParser(new FileReader(csvFile), CSVFormat.DEFAULT.withHeader().withNullString(""))) {
            headerNumber = csvParser.getHeaderMap().size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the input and csv file dont match
        if (headerNumber != inputRecord.size()) {
            throw new IllegalArgumentException("Number of inputted fields doesn't match number of csv headers.");
        }

        // Try with resource: create FileWriter and csvPrinter object as resources - closes automatically
        try (FileWriter outputfile = new FileWriter(csvFile);
             CSVPrinter csvPrinter = new CSVPrinter(outputfile, CSVFormat.DEFAULT.withHeader().withNullString(""))) {

            // writes a new row/record to the CSV file
            csvPrinter.printRecord(inputRecord);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Updates the tempFile to newFile by removing one record with a given ID.
//     *
//     * @param uuid
//     * @param tempFile
//     * @param newFile
//     */
//    private void remove(UUID uuid) {
//        try (FileReader fileReader = new FileReader(csvFile);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""))) {
//
//            List<CSVRecord> records = csvParser.getRecords();
//
//            if (recordIndex < 0 || recordIndex >= records.size()) {
//                throw new IllegalArgumentException("Invalid recordIndex: " + recordIndex);
//            }
//
//            records.remove(recordIndex);
//
//            FileWriter fileWriter = new FileWriter(csvFile);
//            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);
//            csvPrinter.printRecords(records);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    /**
     * Closes the resources and performs cleanup.
     *
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        // Close the CSVPrinter and FileWriter
        if (csvPrinter != null) {
            csvPrinter.close();
        }
        if (fileWriter != null) {
            fileWriter.close();
        }
        // Set to null to indicate t closed
        csvPrinter = null;
        fileWriter = null;
    }
}





