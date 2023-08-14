//package d_frameworks_and_drivers.database_management.DBControllers;
//
//import d_frameworks_and_drivers.database_management.DBAdapters.DBMapper;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Map;
//
///**
// *  Class not used. For future extension
// */
//public class UniqueIDsReader {
//    /**
//     * Returns a Mapping from the string of key column field to the string of the value column field.
//     * aka convertCsvToHashmap
//     * @return Mapping from the string of key column field to the string of the value column field.
//     */
//    public Map<String, String> getMap() {
//        // Use the first header as the key and the second header as the value in the HashMap
//        // Assuming the first header is in the 0th column, the second header is in the 1st column
//        try (DBMapper DBMapper = new DBMapper("src/main/java/d_frameworks_and_drivers/database_management/DatabaseFiles/UniqueIDs/UniqueIDs.csv")){
//
//            return DBMapper.getStringToStringMap(0, 1);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
