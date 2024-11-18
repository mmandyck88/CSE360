import java.io.File;

public class Admin {

    public static String manageStorage(String patientID) {
        File patientFile = new File(patientID + "_PatientInfo.txt");
        File ctScanFile = new File(patientID + "_CTResults.txt");

        if (patientFile.exists() && ctScanFile.exists()) {
            return "All data for Patient ID: " + patientID + " is available.";
        } else {
            return "Error: Missing files for Patient ID: " + patientID;
        }
    }

    public static void deletePatientData(String patientID) {
        new File(patientID + "_PatientInfo.txt").delete();
        new File(patientID + "_CTResults.txt").delete();
        new File(patientID + "_Report.txt").delete();
    }
}
