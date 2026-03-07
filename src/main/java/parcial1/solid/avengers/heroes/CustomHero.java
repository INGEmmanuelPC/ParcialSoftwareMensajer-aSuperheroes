package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * Héroe personalizado creado dinámicamente por el usuario a través de la GUI.
 *
 * <p>A diferencia de los héroes predefinidos (Aquaman, Thor, etc.), esta clase
 * permite que el usuario defina el nombre y las habilidades en tiempo de ejecución.
 * Aplica el <strong>Principio Abierto/Cerrado (OCP)</strong>: el sistema acepta
 * nuevos héroes sin modificar las clases existentes.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class CustomHero extends SuperHeroe {

    /**
     * Crea un héroe personalizado con nombre y habilidades definidos por el usuario.
     *
     * @param nombre      nombre del héroe.
     * @param habilidades conjunto de habilidades seleccionadas.
     */
    public CustomHero(String nombre, Set<TipoHabilidad> habilidades) {
        super(nombre, habilidades);
    }
}
