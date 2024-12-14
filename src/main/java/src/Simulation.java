package src;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    //attributs
    private int nbCycles;
    private ArrayList<Cycle> Cycles;
    private Population pop;
    private Maladie maladie;

    //méthodes
    public void lancer() {
        //on créait nbCycles cycles
        Cycles.add(new Cycle(maladie, 0, pop));
        Cycles.getLast().bilan();
        //affichage du cycle 0
        JFrame frame = new JFrame("Cycle n°" + 0);
        MapPanel mapPanel = new MapPanel(Cycles.getLast().getPopulation().getListePersonne());
        frame.add(mapPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        for (int i = 1; i <= nbCycles; i++) {

            Cycles.add(new Cycle(maladie, i , pop));
            Cycles.getLast().actualisationContamination();

            //ACTUALISATION DES NB DE CYCLES DES PERSONNES
            for(Personne p : pop.getListePersonne()){

                //ACTUALISATION DU NB DE CYCLE D'INCUBATION
                if (p.getEtat() == EtatSante.Contagieux) {
                    if(p.getNbCycleIncubation() >= 2){
                        p.setEtat(EtatSante.Malade);
                        p.setNbCycleIncubation( 0 );
                    }
                    else {
                        p.setNbCycleIncubation( p.getNbCycleIncubation() + 1);
                    }
                }
                //ACTUALISATION DE LA GUERISON DES MALADES

                else if(p.getEtat() == EtatSante.Malade){

                    //TIRAGE AU SORT POUR DETERMINER SI LA PERSONNE DECEDE OU NON
                    boolean resultat1 = new Random().nextDouble() <= maladie.getProbaDeces(); // TIRAGE ALEATOIRE AVEC LA PROBA DE GUERISON
                    if (resultat1) {
                        p.setEtat(EtatSante.Decede);
                    }

                    // TIRAGE AU SORT POUR DETERMINER SI LA PERSONNE GUERI OU NON
                    boolean resultat2 = new Random().nextDouble() <= maladie.getProbaGuerison(); // TIRAGE ALEATOIRE AVEC LA PROBA DE GUERISON
                    if (resultat2 && p.getEtat() != EtatSante.Decede) { //si la personne p n'est pas décédée et que le résultat du tirage est positif
                        p.setEtat(EtatSante.Gueri);
                        p.setType(TypePersonne.Resistante);
                    }
                }

                //ACTUALISATION DU NB DE CYCLE APRES GUERISON
                else if (p.getEtat() == EtatSante.Gueri) {
                    if(p.getNbCycleDepuisGuerison() >= 3){
                        p.setEtat(EtatSante.Sain);
                        p.setType(p.getTypeInitial());
                    }
                    else {
                        p.setNbCycleDepuisGuerison( p.getNbCycleDepuisGuerison() + 1);
                    }
                }
            }
            //Bilan final du cycle
            Cycles.getLast().bilan();
            //affichage du cycle actuel
            frame = new JFrame("Cycle n°" +Cycles.getLast().getNumCycle());
            mapPanel = new MapPanel(Cycles.getLast().getPopulation().getListePersonne());
            frame.add(mapPanel);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        //affichage du graphe evolution
        this.grapheEvolutionNbMalades();
    }

    public void grapheEvolutionNbMalades() {

        List<Integer> maladesParCycle = new ArrayList<>();

        for(Cycle c : Cycles){
        maladesParCycle.add( c.getNbMalades() );


        }
        // Créer une série pour les données
        XYSeries series = new XYSeries("Nombre de malades par cycle");
        for (int i = 0; i < maladesParCycle.size(); i++) {
            series.add(i, maladesParCycle.get(i)); // Cycle en X, Nombre de malades en Y
        }

        // Ajouter la série à une collection
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        // Créer le graphique
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Évolution du nombre de malades", // Titre
                "Cycle", // Axe X
                "Nombre de malades", // Axe Y
                dataset
        );

        // Afficher le graphique dans une fenêtre
        JFrame frame = new JFrame("Graphique d'évolution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.setVisible(true);
    }

    //Constructeur
    //Lors de la création, on veut juste un nombre de cycles
    public Simulation(int nbCycles, Population pop, Maladie maladie ) {
        this.nbCycles = nbCycles;
        this.pop = pop;
        this.maladie = maladie;
        ArrayList<Cycle> Cycles = new ArrayList<>();
        this.Cycles = Cycles;
    }




    //toString
    public ArrayList<Cycle> compteRendu(){
        String chaine = "";
        return Cycles;
    }
}
