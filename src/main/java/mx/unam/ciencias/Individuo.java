package mx.unam.ciencias;
import java.util.Random;

class Individuo {
    private String genes;
    private int fitness;
    private double proba; //Probabilidad que se le asignará a cada individuo para formar una funcion de distribucion
    public static final int LONGITUD = 50; 

    public Individuo() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LONGITUD; i++){
            sb.append(rand.nextBoolean() ? "1" : "0");
        }
        this.genes = sb.toString();
        calculaFitness();
    }

    /**
     * Asigna al individuo un valor entero del 0 al 10
     */
    public void calculaFitness(){
        this.fitness =  (int) genes.chars().filter(ch -> ch == '1').count();
    }

    public void setProba(double proba){
        this.proba = proba;
    }

    public double getProba(){
        return this.proba;
    }

    public int getFitness(){
        return this.fitness;
    }

    public String getGenes(){
        return this.genes;
    }

    public void setGenes(String genes){
        this.genes = genes;
        calculaFitness();
    }

    @Override
    public String toString(){
        return genes + " (Fitness: " + fitness + ")";
    }
}