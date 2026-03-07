package parcial1.solid.avengers.interfaces;

/**
 * Interfaz que define el comportamiento de un ser que puede trepar superficies.
 *
 * <p>Aplica el <strong>Principio de Segregación de Interfaces (ISP)</strong>:
 * solo los héroes capaces de escalar implementan esta interfaz.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface ITrepamuros {

    /**
     * Ejecuta la acción de trepar por una superficie vertical.
     */
    void trepar();
}
