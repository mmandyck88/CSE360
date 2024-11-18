import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Technician {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("CT Scan Technician View");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblPatientID = new Label("Patient ID:");
        TextField txtPatientID = new TextField();

        Label lblTotalCAC = new Label("The total Agatston CAC score:");
        TextField txtTotalCAC = new TextField();

        Label lblVesselLevel = new Label("Vessel level Agatston CAC Score:");

        Label lblLM = new Label("LM:");
        TextField txtLM = new TextField();

        Label lblLAD = new Label("LAD:");
        TextField txtLAD = new TextField();

        Label lblLCX = new Label("LCX:");
        TextField txtLCX = new TextField();

        Label lblRCA = new Label("RCA:");
        TextField txtRCA = new TextField();

        Label lblPDA = new Label("PDA:");
        TextField txtPDA = new TextField();

        Label lblStatus = new Label(); 

        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnSave.setOnAction(e -> {
            String patientID = txtPatientID.getText();
            File patientFile = new File(patientID + "_PatientInfo.txt");

            if (!patientFile.exists()) {
                lblStatus.setText("Error: Patient ID not found"); //Error Message
                return;
            }

            if (txtTotalCAC.getText().isEmpty() || txtLM.getText().isEmpty() ||
                txtLAD.getText().isEmpty() || txtLCX.getText().isEmpty() ||
                txtRCA.getText().isEmpty() || txtPDA.getText().isEmpty()) {
                lblStatus.setText("Error: All fields must be filled out."); //Error Message
                return;
            }

            try {
                double totalCAC = Double.parseDouble(txtTotalCAC.getText());
                double lm = Double.parseDouble(txtLM.getText());
                double lad = Double.parseDouble(txtLAD.getText());
                double lcx = Double.parseDouble(txtLCX.getText());
                double rca = Double.parseDouble(txtRCA.getText());
                double pda = Double.parseDouble(txtPDA.getText());
             // Checks For Negative Numbers
                if (totalCAC < 0 || lm < 0 || lad < 0 || lcx < 0 || rca < 0 || pda < 0) {
                    lblStatus.setText("Error: Results cannot be negative. Please verify results again.");
                    return;
                }

                try (FileWriter writer = new FileWriter(patientID + "_CTResults.txt")) {
                    writer.write("Total CAC Score: " + totalCAC + "\n");
                    writer.write("LM: " + lm + "\n");
                    writer.write("LAD: " + lad + "\n");
                    writer.write("LCX: " + lcx + "\n");
                    writer.write("RCA: " + rca + "\n");
                    writer.write("PDA: " + pda + "\n");
                    lblStatus.setText("CT scan data saved successfully for Patient ID: " + patientID);
                }
            } catch (NumberFormatException ex) {
                lblStatus.setText("Error: Please enter valid numeric values."); //Error Message
            } catch (IOException ex) {
                lblStatus.setText("Error: Unable to save data."); //Error Message
                ex.printStackTrace();
            }
        });

        grid.add(lblPatientID, 0, 0);
        grid.add(txtPatientID, 1, 0);
        grid.add(lblTotalCAC, 0, 1);
        grid.add(txtTotalCAC, 1, 1);
        grid.add(lblVesselLevel, 0, 2);
        grid.add(lblLM, 0, 3);
        grid.add(txtLM, 1, 3);
        grid.add(lblLAD, 0, 4);
        grid.add(txtLAD, 1, 4);
        grid.add(lblLCX, 0, 5);
        grid.add(txtLCX, 1, 5);
        grid.add(lblRCA, 0, 6);
        grid.add(txtRCA, 1, 6);
        grid.add(lblPDA, 0, 7);
        grid.add(txtPDA, 1, 7);
        grid.add(lblStatus, 0, 8, 2, 1);

     // Save is a Blue Button Bottom-Right Placement
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setCenter(grid);
        layout.setBottom(btnSave);
        BorderPane.setAlignment(btnSave, Pos.BOTTOM_RIGHT); 

        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
    }
}
