import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class Receptionist {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("Patient Intake Form");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblFirstName = new Label("First Name:");
        TextField txtFirstName = new TextField();

        Label lblLastName = new Label("Last Name:");
        TextField txtLastName = new TextField();

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblPhone = new Label("Phone Number:");
        TextField txtPhone = new TextField();

        Label lblHistory = new Label("Health History:");
        TextField txtHistory = new TextField();

        Label lblInsurance = new Label("Insurance ID:");
        TextField txtInsurance = new TextField();

        Label lblStatus = new Label(); 
     // Checks Display Save Status or Error Messages
        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); 
        btnSave.setOnAction(e -> {
            if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtPhone.getText().isEmpty() || txtHistory.getText().isEmpty() || txtInsurance.getText().isEmpty()) {
                lblStatus.setText("Error: All fields must be filled out."); //Error Messages
                return;
            }

            String patientID = generatePatientID();
            try (FileWriter writer = new FileWriter(patientID + "_PatientInfo.txt")) {
                writer.write("First Name: " + txtFirstName.getText() + "\n");
                writer.write("Last Name: " + txtLastName.getText() + "\n");
                writer.write("Email: " + txtEmail.getText() + "\n");
                writer.write("Phone Number: " + txtPhone.getText() + "\n");
                writer.write("Health History: " + txtHistory.getText() + "\n");
                writer.write("Insurance ID: " + txtInsurance.getText() + "\n");
                lblStatus.setText("Data saved successfully!\nPatient ID: " + patientID);
            } catch (IOException ex) {
                lblStatus.setText("Error: Unable to save data.");
                ex.printStackTrace();
            }
        });

        grid.add(lblFirstName, 0, 0);
        grid.add(txtFirstName, 1, 0);
        grid.add(lblLastName, 0, 1);
        grid.add(txtLastName, 1, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(lblPhone, 0, 3);
        grid.add(txtPhone, 1, 3);
        grid.add(lblHistory, 0, 4);
        grid.add(txtHistory, 1, 4);
        grid.add(lblInsurance, 0, 5);
        grid.add(txtInsurance, 1, 5);
        grid.add(lblStatus, 0, 6, 2, 1); 

        // Save is a Blue Button Bottom-Right Placement
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setCenter(grid);
        layout.setBottom(btnSave);
        BorderPane.setAlignment(btnSave, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }

    // Generates Unique Patient ID
    private static String generatePatientID() {
        return String.format("%05d", (int) (Math.random() * 100000));
    }
}
