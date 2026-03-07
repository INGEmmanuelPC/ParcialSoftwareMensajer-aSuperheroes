package parcial1.solid.avengers.misiones;

import parcial1.solid.avengers.heroes.SuperHeroe;

import java.util.List;
import java.util.Objects;

/**
 * Resultado inmutable de una asignación automática de misión.
 *
 * <p>Encapsula toda la información resultante del proceso de asignación:
 * si fue exitosa, cuál misión se procesó, qué héroe fue asignado (si alguno)
 * y un mensaje descriptivo.</p>
 *
 * <p>Esta clase evita que {@code AsignadorMisiones} devuelva un simple
 * {@code boolean}, enriqueciendo la respuesta para uso en la GUI y en logs.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class ResultadoAsignacion {

    private final boolean exitosa;
    private final Mision mision;
    private final SuperHeroe heroeAsignado;
    private final String mensaje;
    private final List<SuperHeroe> heroesEvaluados;

    /**
     * Crea un resultado de asignación.
     *
     * @param exitosa         {@code true} si la asignación fue exitosa.
     * @param mision          la misión procesada.
     * @param heroeAsignado   el héroe asignado (puede ser {@code null} si falló).
     * @param mensaje         mensaje descriptivo del resultado.
     * @param heroesEvaluados lista de héroes que fueron evaluados durante el proceso.
     */
    public ResultadoAsignacion(boolean exitosa, Mision mision, SuperHeroe heroeAsignado,
                                String mensaje, List<SuperHeroe> heroesEvaluados) {
        Objects.requireNonNull(mision, "La misión no puede ser null");
        Objects.requireNonNull(mensaje, "El mensaje no puede ser null");
        Objects.requireNonNull(heroesEvaluados, "La lista de héroes evaluados no puede ser null");

        this.exitosa = exitosa;
        this.mision = mision;
        this.heroeAsignado = heroeAsignado;
        this.mensaje = mensaje;
        this.heroesEvaluados = List.copyOf(heroesEvaluados);
    }

    /**
     * Indica si la asignación fue exitosa.
     *
     * @return {@code true} si un héroe fue asignado correctamente.
     */
    public boolean isExitosa() {
        return exitosa;
    }

    /**
     * Obtiene la misión procesada.
     *
     * @return la misión.
     */
    public Mision getMision() {
        return mision;
    }

    /**
     * Obtiene el héroe asignado a la misión.
     *
     * @return el héroe asignado, o {@code null} si ninguno fue compatible.
     */
    public SuperHeroe getHeroeAsignado() {
        return heroeAsignado;
    }

    /**
     * Obtiene el mensaje descriptivo del resultado.
     *
     * @return mensaje legible sobre el resultado de la asignación.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Obtiene la lista de héroes que fueron evaluados.
     *
     * @return lista inmutable de héroes evaluados.
     */
    public List<SuperHeroe> getHeroesEvaluados() {
        return heroesEvaluados;
    }
}
