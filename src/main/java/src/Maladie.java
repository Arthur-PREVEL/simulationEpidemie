package src;


public class Maladie {

    //attributs
    private final float facteurTransmissionSensibles;
    private final float facteurTransmissionNeutres;
    private final float facteurTransmissionResistants;
    private int periodeIncubationEnNbCycles;
    private float probaGuerison;
    private float probaDeces;
    private final float distanceMaxTransmissionEnKM;
    private final float probaIniTransmission;

    //getters
    public float getFacteurTransmissionSensibles() {
        return facteurTransmissionSensibles;
    }
    public float getProbaIniTransmission() {
        return probaIniTransmission;
    }
    public float getDistanceMaxTransmissionEnKM() {
        return distanceMaxTransmissionEnKM;
    }
    public float getProbaDeces() {
        return probaDeces;
    }
    public float getProbaGuerison() {
        return probaGuerison;
    }
    public float getFacteurTransmissionNeutres() {
        return facteurTransmissionNeutres;
    }
    public float getFacteurTransmissionResistants() {
        return facteurTransmissionResistants;
    }
    public int getPeriodeIncubationEnNbCycles() {
        return periodeIncubationEnNbCycles;
    }

    //Setters
    public void setPeriodeIncubationEnNbCycles(int periodeIncubationEnNbCycles) {
        this.periodeIncubationEnNbCycles = periodeIncubationEnNbCycles;
    }
    public void setProbaGuerison(float probaGuerison) {
        this.probaGuerison = probaGuerison;
    }
    public void setProbaDeces(float probaDeces) {
        this.probaDeces = probaDeces;
    }

    //Constructeur
    public Maladie(float facteurTransmissionSensibles, float facteurTransmissionNeutres,
                    float facteurTransmissionResistants, int periodeIncubationEnNbCycles,
                    float probaGuerison, float probaDeces,
                    float distanceMaxTransmissionEnKM, float probaIniTransmission) {
        this.facteurTransmissionSensibles = facteurTransmissionSensibles;
        this.facteurTransmissionNeutres = facteurTransmissionNeutres;
        this.facteurTransmissionResistants = facteurTransmissionResistants;
        this.periodeIncubationEnNbCycles = periodeIncubationEnNbCycles;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMaxTransmissionEnKM = distanceMaxTransmissionEnKM;
        this.probaIniTransmission = probaIniTransmission;
    }
}