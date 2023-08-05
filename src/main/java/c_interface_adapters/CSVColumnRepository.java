package c_interface_adapters;

import a_enterprise_business_rules.entities.Column;
import b_application_business_rules.use_cases.interfaces.ColumnRepository;
import d_frameworks_and_drivers.database_management.DBAdapters.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * first draft
 */
public class CSVColumnRepository implements ColumnRepository {
    private CSVReader csvReader;

    public CSVColumnRepository(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public Column getColumnById(UUID columnId) {
        // Retrieve the CSV record using the columnId and convert it to a Column object
        // Implement the logic using the csvReader.getUUIDToRecordMap() method
        return null;
    }

    @Override
    public List<Column> getAllColumns() {
        // Retrieve all CSV records and convert them to a list of Column objects
        // Implement the logic using the csvReader.getUUIDToRecordMap() method
        return new ArrayList<>();
    }

    @Override
    public void saveColumn(Column column) {
        // Convert the Column object to a CSV record and save it to the CSV file
        // Implement the logic using the csvReader.getStringToStringMap() method
    }

    // Additional methods for managing columns in CSV file
}
