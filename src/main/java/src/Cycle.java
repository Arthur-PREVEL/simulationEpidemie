package src;

import static java.lang.Math.max;

public class Cycle {

    //attributs
    private final Maladie maladie;
    private final Population population;
    private int nbMalades;
    private int nbGueris;
    private int nbDeces;
    private int nbContagieux;
    private int nbSains;
    private final int numCycle;


    //Getters
    public int getNumCycle() {
        return numCycle;
    }
    public Population getPopulation() {
        return population;
    }
    public int getNbMalades() {
        return nbMalades;
    }


    //méthodes
    public void actualisationContamination(){
        for (Personne p : this.population.getListePersonne()){
            // SI LA PERSONNE N'EST PAS VACCINE
            if ( p.getVaccin() == Vaccin.Aucun ) {

                //SI LA PERSONNE EST SAINE OU GUERIE
                if(p.getEtat() == EtatSante.Sain || p.getEtat() == EtatSante.Gueri){

                    for (Personne pers : this.population.getListePersonne() ) {
                        if (pers != p) {
                            //calcul distance entre p et pers
                            double distance = p.calculDistance(pers);
                            //calcul probaContamination
                            double probaContamination = p.calculProbaContamination( maladie, distance );
                            //actualisation de la probaContamination en fonction de lu type de personne
                            probaContamination = p.actualisationProbaContamination( maladie, probaContamination) ;
                            // ON A OBTENU LA PROBA QUE p SOIT CONTAMINE par pers, ON FAIT LE TIRAGE POUR SAVOIR SI p DEVIENT MALADE OU NON
                            p.tirageContamination(probaContamination);
                        }
                    }
                }

            }
        }
    }

    public void bilan(){
        for (Personne p : this.population.getListePersonne()){
            if (p.getEtat() == EtatSante.Malade){
                nbMalades++;
            }
            else if(p.getEtat() == EtatSante.Decede ){
                nbDeces++;
            }
            else if (p.getEtat() == EtatSante.Gueri ){
                nbGueris++;
            }
            else if (p.getEtat() == EtatSante.Contagieux){
                nbContagieux++;
            }
            else if (p.getEtat() == EtatSante.Sain ){
                nbSains++;
            }
        }
    }

    // Constructeurs
    public Cycle(Maladie maladie, int numCycle, Population population){
        this.maladie = maladie;
        this.numCycle = numCycle;
        this.population = population;
    }

    //toString
    public String toString(){
        return "----------NOUVEAU CYCLE----------\n" +"Nb de sains :" + this.nbSains + "\n" +"Nb de contagieux :" + this.nbContagieux + "\n"  +"Nb de malade(s) :" + this.nbMalades + "\n" +" Nb de guéri(s) :" +  this.nbGueris + "\n" + " Nb de décès(s) :" + this.nbDeces + "\n" + "---------------------\n";
    }


}
