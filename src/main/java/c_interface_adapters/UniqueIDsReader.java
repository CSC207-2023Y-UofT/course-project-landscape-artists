package c_interface_adapters;

import org.apache.commons.csv.CSVRecord;

import java.util.Map;
import java.util.UUID;
/**
 * first draft
 */
public interface UniqueIDsReader {
    Map<String, String> getMap();
}