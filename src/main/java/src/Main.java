package src;


public class Main {
    public static void main(String[] args) {
        //création d'une population de 20 personnes
        Population pop = new Population(38, 1);
        //on souhaite avoir 20 pourcents de malades
        pop.setPopMalade(0.2);
        //on créait la maladie
        Maladie maladie = new Maladie(1, 0.9F, 0.8F, 2, 0.4F , 0.1F , 10, 0.5F);
        Simulation simulation = new Simulation( 15, pop, maladie);
        simulation.lancer();
        System.out.println( simulation.compteRendu() );
}
}
