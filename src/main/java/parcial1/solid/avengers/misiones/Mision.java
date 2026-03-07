package parcial1.solid.avengers.misiones;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Representa una misión dentro del sistema de asignaciones.
 *
 * <p>Una misión tiene un nombre descriptivo y un conjunto de habilidades
 * que un héroe debe poseer para poder ser asignado a ella.</p>
 *
 * <p>La clase es <strong>inmutable</strong>: una vez creada, ni el nombre
 * ni las habilidades requeridas pueden cambiar. Esto garantiza coherencia
 * durante todo el flujo de validación y asignación.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class Mision {

    private final String nombre;
    private final Set<TipoHabilidad> habilidadesRequeridas;

    /**
     * Crea una nueva misión con su nombre y habilidades requeridas.
     *
     * @param nombre               nombre identificador de la misión (no puede ser {@code null} ni vacío).
     * @param habilidadesRequeridas conjunto de habilidades necesarias (no puede ser {@code null} ni vacío).
     * @throws NullPointerException     si algún parámetro es {@code null}.
     * @throws IllegalArgumentException si el nombre está vacío o no hay habilidades.
     */
    public Mision(String nombre, Set<TipoHabilidad> habilidadesRequeridas) {
        Objects.requireNonNull(nombre, "El nombre de la misión no puede ser null");
        Objects.requireNonNull(habilidadesRequeridas, "Las habilidades requeridas no pueden ser null");

        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la misión no puede estar vacío");
        }
        if (habilidadesRequeridas.isEmpty()) {
            throw new IllegalArgumentException("La misión debe requerir al menos una habilidad");
        }

        this.nombre = nombre;
        this.habilidadesRequeridas = Set.copyOf(habilidadesRequeridas);
    }

    /**
     * Obtiene el nombre identificador de la misión.
     *
     * @return el nombre de la misión.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si esta misión requiere una habilidad específica.
     *
     * @param habilidad la habilidad a consultar.
     * @return {@code true} si la misión requiere esa habilidad.
     */
    public boolean requiereHabilidad(TipoHabilidad habilidad) {
        return habilidadesRequeridas.contains(habilidad);
    }

    /**
     * Devuelve una vista inmodificable de las habilidades requeridas.
     *
     * @return conjunto inmutable de habilidades requeridas.
     */
    public Set<TipoHabilidad> getHabilidadesRequeridas() {
        return Collections.unmodifiableSet(habilidadesRequeridas);
    }

    /**
     * Representación textual de la misión.
     *
     * @return cadena con el nombre de la misión.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
