package DatabaseInitializer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class ProjectDBInitializer {
    String [] ProjectHeaders = {"ProjectID", "Name", "Description", "Column ID's"};

    public ProjectDBInitializer() {
        // create CSVWriter object filewriter object as parameter
        File file = new File("DatabaseFiles/Projects/Projects.csv");
        FileWriter outputfile = null;
        try {
            outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(ProjectHeaders);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
