import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Doctor {

    public static String evaluateRisk(double totalCACScore) {
        if (totalCACScore < 100) {
            return "Low Risk";
        } else if (totalCACScore < 400) {
            return "Moderate Risk";
        } else {
            return "High Risk";
        }
    }

    public static void sendReportToPatient(String patientID, String email, String riskLevel) throws IOException {
        File patientFile = new File(patientID + "_CTResults.txt");
        if (patientFile.exists()) {
            try (FileWriter writer = new FileWriter(patientID + "_Report.txt")) {
                writer.write("Patient ID: " + patientID + "\n");
                writer.write("Risk Level: " + riskLevel + "\n");
                writer.write("Detailed CT Scan Results sent to: " + email + "\n");
            }
        }
    }
}
