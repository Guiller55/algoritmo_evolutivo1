package mx.unam.ciencias;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Poblacion poblacion = new Poblacion();
        poblacion.evalua();
        System.out.println("Poblacion inicial: \n");
        System.out.println(poblacion);
        int contador = 2;

        System.out.println("Mejor: " + poblacion.getMejor());

        //for(int i = 0; i<200; i++){
        while(poblacion.getMejor().getFitness() < Individuo.LONGITUD){
            contador += 1;
            poblacion.nuevaGeneracion();
            System.out.println("\n Generacion " + contador + "\n" + poblacion.toString());
            poblacion.evalua();
        }
        
        System.out.println("\nIndividuo definitivo:" + poblacion.getMejor() + " Encontrado después de " + contador + " generaciones");
        System.out.println("Mutaciones malas: "+ poblacion.contadorMutacionMala);
        System.out.println("Mutaciones buenas: "+ poblacion.contadorMutaciones);
    }
}