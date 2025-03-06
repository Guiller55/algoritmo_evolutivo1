package mx.unam.ciencias;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

class Poblacion {
    private Individuo[] individuos;
    private static final int TAMANO_POBLACION = 10;

    public Poblacion(){
        individuos = new Individuo[TAMANO_POBLACION];
        for(int i = 0; i<10; i++){
            individuos[i] = new Individuo();
        }
    }

    public void evalua(){
        Arrays.sort(individuos, Comparator.comparingInt(Individuo::getFitness).reversed());
    }

    public Individuo getMejor(){
        return individuos[0];
    }

    public Individuo selNatural(){
        Random rand = new Random();
        int i = rand.nextInt(TAMANO_POBLACION / 2);
        return individuos[i];
    }

    public Individuo[] getIndividuos(){
        return individuos;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Individuo ind : individuos){
            sb.append(ind).append("\n");
        }
        return sb.toString();
    }

}