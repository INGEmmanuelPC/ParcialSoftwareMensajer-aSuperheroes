package parcial1.solid.avengers.interfaces;

/**
 * Interfaz que define el comportamiento de un ser acuático.
 *
 * <p>Aplica el <strong>Principio de Segregación de Interfaces (ISP)</strong>:
 * solo los héroes que pueden operar bajo el agua implementan esta interfaz.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface IAcuatico {

    /**
     * Ejecuta la acción de nadar o desplazarse en un entorno acuático.
     */
    void nadar();
}
