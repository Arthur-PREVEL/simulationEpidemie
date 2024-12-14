package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Population {

    //attributs
    private ArrayList<Personne> listePersonne;
    private final int idPopulation;

    //constructeurs
    public Population(int nbPersonnes, int idPopulation) {
        this.listePersonne = new ArrayList<>();
        this.idPopulation = idPopulation;
        this.listePersonne = this.popAleatoire(nbPersonnes);
    }
    //constructeur sans automation si besoin
    public Population(ArrayList<Personne> listePersonne, int idPopulation) {
        this.listePersonne = listePersonne;
        this.idPopulation = idPopulation;
    }
    //méthodes
    public void setPopMalade(double pourcentageMalade) {
        int nbMaladesAAjouter = Integer.valueOf((int) Math.round(this.listePersonne.size() * pourcentageMalade));

        for (int i = 0; i < nbMaladesAAjouter; i++) {
            Random rand = new Random();
            rand.nextInt(listePersonne.size());
            listePersonne.get(rand.nextInt(listePersonne.size())).setEtat(EtatSante.Malade);
        }
    }

    private static String creerPair() {
        Random rand = new Random();
        return rand.nextInt(10) + "," + rand.nextInt(10);
    }

    public static Personne personneAleatoire(int idPersonne) {
        Random rand = new Random();
        boolean accesVaccin = rand.nextBoolean(); // on donne des valeurs aléatoires pour chaque personne

        Set<String> coupleXY = new HashSet<>(); // on crée une collection qui va stocker des couples xy

        String pair = creerPair(); // on crée le couple
        while (coupleXY.contains(pair)) {
            pair = creerPair(); // si la collection contient le couple alors on en recrée un nouveau jusqu'à y arriver
        }

        String[] coord = pair.split(",");
        int x = Integer.valueOf(coord[0]);
        int y = Integer.valueOf(coord[1]);

        TypePersonne type = TypePersonne.values()[rand.nextInt(TypePersonne.values().length)]; // on donne des valeurs aléatoires pour chaque personnes
        Vaccin vaccin = Vaccin.values()[rand.nextInt(Vaccin.values().length)];// on donne des valeurs aléatoires pour chaque personnes
        boolean possedeMasque = rand.nextBoolean(); // on donne des valeurs aléatoires pour chaque personnes


        return new Personne(idPersonne, accesVaccin, x, y, type, vaccin, possedeMasque);
    }

    public static ArrayList<Personne> popAleatoire(int nbIndividus) {
        ArrayList<Personne> listePersonnes = new ArrayList<>(); // on créé une liste pour stocker les personnes créées

        for (int i = 0; i < nbIndividus; i++) {
            listePersonnes.add(Population.personneAleatoire(i+1)); // on ajoute le nombre de personnes générées par la méthode "personneAleatoire" pour la taille de la liste
        }

        return listePersonnes;
    }

    //getters
    public ArrayList<Personne> getListePersonne() {
        return listePersonne;
    }

    public String toString() {
        return "----------Population-----------" + "\n" + listePersonne;
    }
}
