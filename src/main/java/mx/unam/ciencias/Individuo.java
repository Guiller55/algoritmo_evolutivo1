package mx.unam.ciencias;
import java.util.Random;

class Individuo {
    private String genes;
    private int fitness;
    private static final int LONGITUD = 10; 

    public Individuo() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LONGITUD; i++){
            sb.append(rand.nextBoolean() ? "1" : "0");
        }
        this.genes = sb.toString();
        calculaFitness();
    }

    public void calculaFitness(){
        this.fitness =  (int) genes.chars().filter(ch -> ch == '1').count();
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