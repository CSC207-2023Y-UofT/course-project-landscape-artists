//package d_frameworks_and_drivers.database_management.DBAdapters;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
///**
// *  CSVSearcher frameworks and driver class.
// */
//public class DBSearcher implements AutoCloseable {
//    private File csvFile;
//    private FileReader fileReader;
//    private CSVParser csvParser;
//
//    /**
//     * Creates a new CSVSearcher given the filePath to csv to read.
//     * @param filePath
//     */
//    public DBSearcher(String filePath) throws FileNotFoundException, IOException {
//        this.csvFile = new File(filePath);
//    }
//
//    /**
//     * Opens the resources and initializes the CSV parser.
//     *
//     * @throws IOException If an I/O error occurs.
//     */
//    private void openResources() throws IOException {
//        fileReader = new FileReader(this.csvFile);
//        csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""));
//    }
//
//    /**
//     * Searches for and returns csv record corresponding to field value given field/column index
//     *
//     * @param fieldIndex index of the header/field where to do search
//     * @param fieldValue the key value for which obtain corresponding csv record
//     * @return  one csv record - first one matching given value
//     */
//    public CSVRecord getRecord(int fieldIndex, String fieldValue) throws IOException {
//        // Try with resource: create FileWriter, CSVParser object as resources - closes automatically
//        try (FileReader fileReader = new FileReader(this.csvFile);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""))) {
//            // Iterate through each CSV record and find the matching record
//            for (CSVRecord csvRecord : csvParser) {
//                String value = csvRecord.get(fieldIndex);
//                if (value.equals(fieldValue)) {
//                    return csvRecord;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//
//    /**
//     * Searches for and returns csv record corresponding to field value given column header string
//     *
//     * @param keyHeader string name of the header/field where to do search
//     * @param fieldValue the key value for which obtain corresponding csv record
//     * @return one csv record - first one matching given value
//     */
//    public CSVRecord getRecord(String keyHeader, String fieldValue) throws IOException {
//        // Try with resource: create FileWriter, CSVParser object as resources - closes automatically
//        try (FileReader fileReader = new FileReader(csvFile);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""))) {
//            // Iterate through each CSV record and find the matching record
//            for (CSVRecord csvRecord : csvParser) {
//                String value = csvRecord.get(keyHeader);
//                if (value.equals(fieldValue)) {
//                    return csvRecord;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    /**
//     *  Returns a list of csv records given 0-based field/column index for a corresponding list of search keys, i.e.
//     *  string values in the field/column.
//     * @param fieldIndex 0-based field/column index - where to search keys are
//     * @param searchKeys corresponding list of search keys, i.e.  string values in the field/column.
//     * @return a list of csv records
//     */
//    public List<CSVRecord> getRecordsList(int fieldIndex, List<String> searchKeys){
//        List<CSVRecord> output = new ArrayList<>();
//        // Try with resource: create FileWriter, CSVParser object as resources - closes automatically
//        try (FileReader fileReader = new FileReader(csvFile);
//             DBMapper DBMapper = new DBMapper(csvFile.getPath());
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withNullString(""))) {
//            // first, generate map of from column with search keys to records
//            Map<String,CSVRecord> strRecordMap = DBMapper.getStringToRecordMap(fieldIndex);
//            // Generate a list of values from the map using searched keys
//            for (String key : searchKeys) {
//                CSVRecord record = strRecordMap.get(key);
//                if (record != null) {
//                    output.add(record);
//                }
//            }
//            return output;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Close the CSVParser when the CSVSearcher is no longer needed.
//     */
//    @Override
//    public void close() {
//        try {
//            if (this.csvParser != null) {
//                this.csvParser.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
//
//
