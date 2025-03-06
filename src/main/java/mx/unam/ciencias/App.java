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

        System.out.println("Mejor: " + poblacion.getMejor());
    }
}
