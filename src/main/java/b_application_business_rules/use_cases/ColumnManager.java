package b_application_business_rules.use_cases;

import a_enterprise_business_rules.entities.Column;
import b_application_business_rules.use_cases.interfaces.ColumnRepository;

import java.util.List;
import java.util.UUID;
/**
 * first draft
 */
public class ColumnManager {
    private ColumnRepository columnRepository;

    public ColumnManager(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public Column getColumnById(UUID columnId) {
        return columnRepository.getColumnById(columnId);
    }

    public List<Column> getAllColumns() {
        return columnRepository.getAllColumns();
    }

    public void saveColumn(Column column) {
        // Perform any business logic validation before saving the column
        columnRepository.saveColumn(column);
    }

    // Additional use cases for column manipulation
}
