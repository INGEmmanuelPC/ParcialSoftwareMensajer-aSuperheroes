package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Clase abstracta que representa a cualquier superhéroe del sistema.
 *
 * <p>Es abstracta porque encapsula estado (nombre, habilidades) y
 * comportamiento compartido ({@link #tieneHabilidad}, {@link #tieneTodasLasHabilidades}).
 * Cada héroe concreto hereda toda esta lógica y solo define sus habilidades
 * específicas en su propio constructor.</p>
 *
 * <p>La clase es <strong>inmutable</strong>: una vez creado el héroe, ni su
 * nombre ni sus habilidades cambian.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public abstract class SuperHeroe {

    private final String nombre;
    private final Set<TipoHabilidad> habilidades;

    /**
     * Constructor protegido, invocado únicamente por las subclases.
     *
     * @param nombre      nombre del héroe (no puede ser {@code null} ni vacío).
     * @param habilidades conjunto de habilidades del héroe (no puede ser {@code null} ni vacío).
     * @throws NullPointerException     si algún parámetro es {@code null}.
     * @throws IllegalArgumentException si el nombre está vacío o no hay habilidades.
     */
    protected SuperHeroe(String nombre, Set<TipoHabilidad> habilidades) {
        Objects.requireNonNull(nombre, "El nombre del héroe no puede ser null");
        Objects.requireNonNull(habilidades, "Las habilidades no pueden ser null");

        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del héroe no puede estar vacío");
        }
        if (habilidades.isEmpty()) {
            throw new IllegalArgumentException("El héroe debe tener al menos una habilidad");
        }

        this.nombre = nombre;
        this.habilidades = Set.copyOf(habilidades);
    }

    /**
     * Obtiene el nombre del héroe.
     *
     * @return el nombre del héroe.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si el héroe posee una habilidad específica.
     *
     * @param habilidad la habilidad a consultar.
     * @return {@code true} si el héroe tiene dicha habilidad.
     */
    public boolean tieneHabilidad(TipoHabilidad habilidad) {
        return habilidades.contains(habilidad);
    }

    /**
     * Verifica si el héroe posee todas las habilidades de un conjunto dado.
     *
     * @param habilidadesRequeridas conjunto de habilidades a verificar.
     * @return {@code true} si el héroe posee todas y cada una.
     */
    public boolean tieneTodasLasHabilidades(Set<TipoHabilidad> habilidadesRequeridas) {
        return habilidades.containsAll(habilidadesRequeridas);
    }

    /**
     * Devuelve una vista inmodificable de las habilidades del héroe.
     *
     * @return conjunto inmutable con las habilidades.
     */
    public Set<TipoHabilidad> getHabilidades() {
        return Collections.unmodifiableSet(habilidades);
    }

    /**
     * Representación textual del héroe.
     *
     * @return el nombre del héroe.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
