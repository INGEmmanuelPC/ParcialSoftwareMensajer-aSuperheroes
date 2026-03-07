package parcial1.solid.avengers.interfaces;

/**
 * Interfaz que define el comportamiento de un ser capaz de volar.
 *
 * <p>Aplica el <strong>Principio de Segregación de Interfaces (ISP)</strong>:
 * solo los héroes que pueden volar implementan esta interfaz.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface IVolador {

    /**
     * Ejecuta la acción de volar o desplazarse por el aire.
     */
    void volar();
}
