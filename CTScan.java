import java.io.FileWriter;
import java.io.IOException;

public class CTScan {

    private String patientID;
    private double totalCACScore;
    private double lm; 
    private double lad; 
    private double lcx; 
    private double rca; 
    private double pda;

 
    public CTScan(String patientID, double totalCACScore, double lm, double lad, double lcx, double rca, double pda) {
        this.patientID = patientID;
        this.totalCACScore = totalCACScore;
        this.lm = lm;
        this.lad = lad;
        this.lcx = lcx;
        this.rca = rca;
        this.pda = pda;
    }

    // Getters
    public String getPatientID() {
        return patientID;
    }

    public double getTotalCACScore() {
        return totalCACScore;
    }

    public double getLm() {
        return lm;
    }

    public double getLad() {
        return lad;
    }

    public double getLcx() {
        return lcx;
    }

    public double getRca() {
        return rca;
    }

    public double getPda() {
        return pda;
    }

    // Save CTScan to file
    public void saveToFile() throws IOException {
        String fileName = patientID + "_CTResults.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Total CAC Score: " + totalCACScore + "\n");
            writer.write("LM: " + lm + "\n");
            writer.write("LAD: " + lad + "\n");
            writer.write("LCX: " + lcx + "\n");
            writer.write("RCA: " + rca + "\n");
            writer.write("PDA: " + pda + "\n");
        }
    }

    // Validation: Ensure all scores are non-negative
    public boolean isValid() {
        return totalCACScore >= 0 && lm >= 0 && lad >= 0 && lcx >= 0 && rca >= 0 && pda >= 0;
    }
}
