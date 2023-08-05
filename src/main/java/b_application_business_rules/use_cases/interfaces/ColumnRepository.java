package b_application_business_rules.use_cases.interfaces;

import a_enterprise_business_rules.entities.Column;

import java.util.List;
import java.util.UUID;
/**
 * first draft
 */
public interface ColumnRepository
{
    Column getColumnById(UUID columnId);

    List<Column> getAllColumns();

    void saveColumn(Column column);

    // Additional methods for managing columns
}

