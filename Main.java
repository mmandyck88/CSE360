// Melissa Mandyck
// CSE 360 Introduction to Software Engineering
// HW4
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        prepopulateData(); // Pre-Populated Patient Data

        primaryStage.setTitle("Heart Health Imaging and Recording System");

        Button btnReceptionist = new Button("Patient Intake");
        btnReceptionist.setOnAction(e -> Receptionist.display());

        Button btnTechnician = new Button("CT Scan Tech View");
        btnTechnician.setOnAction(e -> Technician.display());

        Button btnPatient = new Button("Patient View");
        btnPatient.setOnAction(e -> Patient.display());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(btnReceptionist, btnTechnician, btnPatient);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Scene scene = new Scene(layout, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepopulateData() {
        try {
            // Pre-written Patient Information
            FileWriter patientWriter = new FileWriter("12345_PatientInfo.txt");
            patientWriter.write("First Name: Adam\n");
            patientWriter.write("Last Name: Smith\n");
            patientWriter.write("Email: adam.smith@example.com\n");
            patientWriter.write("Phone Number: 123-456-7890\n");
            patientWriter.write("Health History: No known issues. \n");
            patientWriter.write("Insurance ID: INS-12345\n");
            patientWriter.close();

            // Pre-written CT Results file
            FileWriter ctWriter = new FileWriter("12345_CTResults.txt");
            ctWriter.write("Total Agatston CAC Score: 132.5\n");
            ctWriter.write("LM: 0.0\n");
            ctWriter.write("LAD: 82.9\n");
            ctWriter.write("LCX: 8.3\n");
            ctWriter.write("RCA: 41.2\n");
            ctWriter.write("PDA: 0.0\n");
            ctWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
