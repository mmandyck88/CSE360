import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Patient {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("Patient View");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblPatientID = new Label("Enter Patient ID:");
        TextField txtPatientID = new TextField();

        Button btnView = new Button("View Results");
        Label lblResults = new Label();
        lblResults.setWrapText(true); // Enables text wrapping
        lblResults.setMaxWidth(350); // Limits width for readability

        btnView.setOnAction(e -> {
            String patientID = txtPatientID.getText();
            File patientFile = new File(patientID + "_PatientInfo.txt");
            File ctResultsFile = new File(patientID + "_CTResults.txt");

            if (!patientFile.exists()) {
                lblResults.setText("Error: Incorrect Patient ID");
            } else if (!ctResultsFile.exists()) {
                lblResults.setText("Error: No CT scan results available for Patient ID: " + patientID);
            } else {
                try (Scanner scanner = new Scanner(ctResultsFile)) {
                    StringBuilder results = new StringBuilder("Hello " + getPatientName(patientFile) + "," + "\n\n");
                    String totalCACLine = scanner.nextLine();
                    double totalCACScore = Double.parseDouble(totalCACLine.split(": ")[1]);

                    results.append(totalCACLine).append("\n");

                    // Appends Vessel scores
                    while (scanner.hasNextLine()) {
                        results.append(scanner.nextLine()).append("\n");
                    }
                    results.append("\n");
                    // Adds Risk Assessment Message Below
                    results.append(getRiskAssessmentMessage(totalCACScore));

                    lblResults.setText(results.toString());
                } catch (Exception ex) {
                    lblResults.setText("Error: Unable to read CT scan results.");
                }
            }
        });

        grid.add(lblPatientID, 0, 0);
        grid.add(txtPatientID, 1, 0);
        grid.add(btnView, 1, 1);
        grid.add(lblResults, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 400, 500); // Dimensions of Risk Message
        window.setScene(scene);
        window.show();
    }

    // Retrieves patient's Name from PatientInfo file
    private static String getPatientName(File patientFile) {
        try (Scanner scanner = new Scanner(patientFile)) {
            String firstName = scanner.nextLine().split(": ")[1];
            String lastName = scanner.nextLine().split(": ")[1];
            return firstName + " " + lastName;
        } catch (Exception e) {
            return "<Unknown>";
        }
    }

    // Message Prints based on Risk Assessment from Total CAC Score
    private static String getRiskAssessmentMessage(double totalCACScore) {
        if (totalCACScore == 0) {
            return "No plaque. Your risk of heart attack is low.\n";
        } else if (totalCACScore <= 10) {
            return "Small amount of plaque.\nYou have less than a 10 percent chance of having heart disease,\nand your risk of heart attack is low.\n";
        } else if (totalCACScore <= 100) {
            return "Some plaque.\nYou have mild heart disease and a moderate chance of heart attack.\nYour doctor may recommend other treatment in addition to lifestyle changes.\n";
        } else if (totalCACScore <= 400) {
            return "Moderate amount of plaque.\nYou have heart disease and plaque may be blocking an artery.\nYour chance of having a heart attack is moderate to high.\nYour health professional may want more tests and may start treatment.\n";
        } else {
            return "Large amount of plaque.\nYou have more than a 90 percent chance that plaque is blocking one of your arteries.\nYour chance of heart attack is high.\nYour health professional will want more tests and will start treatment.\n";
        }
    }
}
