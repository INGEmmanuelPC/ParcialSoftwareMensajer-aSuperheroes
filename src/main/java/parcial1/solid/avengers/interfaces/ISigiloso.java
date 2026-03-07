package parcial1.solid.avengers.interfaces;

/**
 * Interfaz que define el comportamiento de un ser capaz de actuar sigilosamente.
 *
 * <p>Aplica el <strong>Principio de Segregación de Interfaces (ISP)</strong>:
 * solo los héroes que dominan el sigilo implementan esta interfaz.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface ISigiloso {

    /**
     * Ejecuta la acción de infiltrarse o moverse sin ser detectado.
     */
    void infiltrar();
}
