package mx.unam.ciencias;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

class Poblacion {
    private Individuo[] individuos;
    private static final int TAMANO_POBLACION = 10;
    public int contadorMutaciones = 0;
    public int contadorMutacionMala = 0;

    public Poblacion(){
        individuos = new Individuo[TAMANO_POBLACION];
        for(int i = 0; i<10; i++){
            individuos[i] = new Individuo();
        }
    }

    /**
     * Ordena a los individuos en funcion de su fitness de mayor a menor
     */
    public void evalua(){
        Arrays.sort(individuos, Comparator.comparingInt(Individuo::getFitness).reversed());
    }

    public Individuo getMejor(){
        return individuos[0];
    }

    /**
     * Metodo que selecciona aleatoriamente a un individuo, los individuos con mayor fitness tienen más probabilidad de ser elegidos
     * @return
     */
    private Individuo selNatural(){
        double probaAcumulada = 0;
        int total = 0;

        //Se calcula la suma total de los valores en fitness de cada individuo
        for(int i = 0; i<TAMANO_POBLACION; i++){
            total += individuos[i].getFitness();
        }
        
        /**A cada individuo le asigna un valor de probabilidad entre 0 y 1, ese valor se calcula en base al
         * fitness, individuos con el mismo fitness tendran la misma probabilidad de ser elegidos y la suma
         * de las probabilidades de cada individuo es 1
         */
        for(Individuo individuo : individuos){
            individuo.setProba((double)individuo.getFitness()/total);
        }

        /**Se genera un numero al azar entre 0 y 1 y se itera por cada individuo en el arreglo,
         * si el numero es mayor que su probabilidad, se pasa al siguiente y se suma su probabilidad a la acumulada
         * se repite el proceso hasta que el numero sea menor que probabilidad acumulada
         */
        double numero = Math.random();
        for (int i = 0; i<TAMANO_POBLACION; i++){
            probaAcumulada += individuos[i].getProba();
            if (numero <= probaAcumulada){
                return individuos[i];
            }
        }

        /**
         * Si llega a ver problemas al redondear se devolvera el ultimo individuo
         */
        return individuos[TAMANO_POBLACION-1];
    }

    public Individuo[] getIndividuos(){
        return individuos;
    }

    public String toString(){
        // StringBuilder sb = new StringBuilder();
        // for(int i = 1; i<TAMANO_POBLACION; i++){
        //     sb.append(selNatural()).append("\n");
        // }
        // return sb.toString();
        
        StringBuilder sb = new StringBuilder();
        for (Individuo ind : individuos){
            sb.append(ind).append("\n");
        }
        return sb.toString();
    }

    /**
     * Toma dos individuos, aleatoriamente elige en que punto cortar, toma sus mitades y le da esos
     * genes al nuevo individuo
     * @param padre
     * @param madre
     * @return hijo nuevo
     */
    private Individuo cruzar(Individuo padre, Individuo madre){
        Random rand = new Random();
        int puntoCorte = rand.nextInt(Individuo.LONGITUD);
        String genesHijo = padre.getGenes().substring(0,puntoCorte) + madre.getGenes().substring(puntoCorte);

        Individuo hijo = new Individuo();
        hijo.setGenes(genesHijo);

        return hijo;
    }

    /**
     * Convierte sus genes en un arreglo de caracteres, elige al azar un caracter y lo invierte
     * @param individuo
     */
    private void mutar(Individuo individuo){
        char[] genes = individuo.getGenes().toCharArray();
        Random rand = new Random();
        int indice = rand.nextInt(individuo.LONGITUD);
        if(genes[indice] == '1'){
            contadorMutacionMala +=1;
        }
        genes[indice] = (genes[indice] == '1') ? '0' : '1';

        individuo.setGenes(new String(genes));
    }

    /**
     * Se toma al mejor individuo de la generacion actual y se guarda, luego se selecciona un individuo y tambien se guarda
     * los 8 restantes son hijos de individuos seleccionados naturalmente, además hay una probabilidad de 5% que un hijo mute
     */
    public void nuevaGeneracion(){
        Individuo[] nuevos = new Individuo[TAMANO_POBLACION];
        nuevos[0] = getMejor();
        nuevos[1] = selNatural();
        for(int i = 2; i < 10; i++){
            double probMutar = Math.random();
            nuevos[i] = cruzar(selNatural(), selNatural());
            if(probMutar < 0.01){
                contadorMutaciones += 1;
                System.out.println("M U T A C I O N #" + contadorMutaciones);
                mutar(nuevos[i]);
            }
        }
        
        individuos = nuevos;
    }

}