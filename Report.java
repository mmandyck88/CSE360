import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;

public class Report {

    public static void generateReport(String patientID, String riskLevel) throws IOException {
        String fileName = patientID + "_CTResults.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Patient ID: " + patientID + "\n");
            writer.write("Risk Level: " + riskLevel + "\n");
        }
    }

    public static String viewReport(String patientID) {
        File reportFile = new File(patientID + "_CTResults.txt");
        if (reportFile.exists()) {
            return "Report available for Patient ID: " + patientID;
        } else {
            return "Error: No report found for Patient ID: " + patientID;
        }
    }
}
