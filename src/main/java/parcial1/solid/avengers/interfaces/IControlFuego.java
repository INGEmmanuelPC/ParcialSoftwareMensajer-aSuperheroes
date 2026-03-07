package parcial1.solid.avengers.interfaces;

/**
 * Interfaz que define el comportamiento de un ser que controla el fuego.
 *
 * <p>Aplica el <strong>Principio de Segregación de Interfaces (ISP)</strong>:
 * solo los héroes con dominio del fuego o energía térmica implementan esta interfaz.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface IControlFuego {

    /**
     * Ejecuta la acción de lanzar o manipular fuego.
     */
    void lanzarFuego();
}
