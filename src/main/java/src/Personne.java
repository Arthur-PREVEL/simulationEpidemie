package src;

import java.util.Random;

import static java.lang.Math.max;

public class Personne {

    //attributs
    private int idPersonne;
    private boolean accesVaccin;
    private int x;
    private int y;
    private EtatSante etat;
    private TypePersonne type;
    private TypePersonne typeInitial;
    private Vaccin vaccin;
    private int nbCycleIncubation;
    private int nbCycleDepuisGuerison;
    private boolean possedeMasque;

    //Constructeur
    public Personne(int idPersonne, boolean accesVaccin, int x, int y, TypePersonne type, Vaccin vaccin, boolean possedeMasque) {
        this.idPersonne = idPersonne;
        this.accesVaccin = accesVaccin;
        this.x = x;
        this.y = y;
        this.etat = EtatSante.Sain;
        this.type = type;
        this.typeInitial = type;
        this.vaccin = vaccin;
        this.nbCycleIncubation = 0;
        this.possedeMasque = possedeMasque;
    }

    //getters
    public int getIdPersonne() {
        return idPersonne;
    }
    public int getNbCycleIncubation() { return nbCycleIncubation; }
    public boolean aAccesVaccin() {
        return accesVaccin;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public EtatSante getEtat() {
        return etat;
    }
    public TypePersonne getType() {
        return type;
    }
    public Vaccin getVaccin() {
        return vaccin;
    }
    public int getNbCycleDepuisGuerison() { return nbCycleDepuisGuerison; }
    public TypePersonne getTypeInitial() { return typeInitial; }

    //setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) {
        this.y = y;
    }
    public void setEtat(EtatSante etat) {
        this.etat = etat;
    }
    public void setType(TypePersonne type) {
        this.type = type;
    }
    public void setAccesVaccin(boolean accesVaccin) {
        this.accesVaccin = accesVaccin;
    }
    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    public void setVaccin(Vaccin vaccin) {
        this.vaccin = vaccin;
    }
    public void setNbCycleIncubation(int nbCycleApresContamination) { this.nbCycleIncubation = nbCycleApresContamination; }
    public void setNbCycleDepuisGuerison(int nbCycleDepuisGuerison) { this.nbCycleDepuisGuerison = nbCycleDepuisGuerison; }

    //méthodes
    public double calculDistance(Personne p2) {
        return Math.sqrt(Math.pow(p2.getX() - this.getX(), 2) + Math.pow(p2.getY() - this.getY(), 2)); // à l'a puissance 2 puis racine carré
    }
    public double calculProbaContamination(Maladie maladie, double distance) {
        if (possedeMasque) {
            return Math.max(0, (maladie.getProbaIniTransmission() / 2) * (1 - distance / maladie.getDistanceMaxTransmissionEnKM())); //correspond à la proba de contagion spécifique entre les personnes concernées/ calcul de p(d)
        } else {
            return Math.max(0, maladie.getProbaIniTransmission() * (1 - distance / maladie.getDistanceMaxTransmissionEnKM())); //correspond à la proba de contagion spécifique entre les personnes concernées/ calcul de p(d)
        }
    }
    public void tirageContamination( double probaContagion) {
        boolean resultat = new Random().nextDouble() <= probaContagion; // TIRAGE ALEATOIRE AVEC LA PROBA probaContagion
        if (resultat && ( this.getEtat() != EtatSante.Decede && this.getEtat() != EtatSante.Contagieux && this.getEtat() != EtatSante.Malade ) ) { //si la personne p est saine
            this.setEtat(EtatSante.Contagieux);
        }
    }
    public double actualisationProbaContamination(Maladie maladie, double probaContamination) {
        // 3 CAS POSSIBLES
        if (this.getType() == TypePersonne.Resistante) {
            probaContamination = probaContamination * maladie.getFacteurTransmissionResistants(); //p(d) * facteurTransmission...

        } else if (this.getType() == TypePersonne.Neutre) {
            probaContamination = probaContamination * maladie.getFacteurTransmissionNeutres();

        } else {      //si Sensible
            probaContamination = probaContamination * maladie.getFacteurTransmissionSensibles();
        }
        return probaContamination;
    }

    //toString
    public String toString() {
        return "Personne = " + idPersonne + ", accesVaccin = " + accesVaccin + ", x = " + x + ", y = " + y + ", etat = " + etat + ", typeInitial=" + typeInitial + ", vaccin = " + vaccin + ", possedeMasque = " + possedeMasque + "\n";
    }
}
