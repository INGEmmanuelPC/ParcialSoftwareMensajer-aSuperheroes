package parcial1.solid.avengers.misiones;

import parcial1.solid.avengers.heroes.SuperHeroe;
import parcial1.solid.avengers.mensajeria.IServicioMensajeria;
import parcial1.solid.avengers.validation.CompatibilityValidator;

import java.util.List;
import java.util.Objects;

/**
 * Servicio orquestador que coordina la asignación automática de misiones.
 *
 * <p>Cumple el <strong>Principio de Responsabilidad Única (SRP)</strong>:
 * su única responsabilidad es coordinar el flujo
 * «buscar héroe compatible → asignar → notificar».</p>
 *
 * <p>Cumple el <strong>Principio de Inversión de Dependencias (DIP)</strong>:
 * depende de abstracciones ({@link CompatibilityValidator} y
 * {@link IServicioMensajeria}) y no de implementaciones concretas.</p>
 *
 * <p><strong>MEJORA PRINCIPAL</strong>: la asignación es ahora
 * <em>automática</em>. El sistema recibe una lista de héroes disponibles
 * y selecciona automáticamente al primer héroe compatible con la misión,
 * sin intervención del usuario.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class AsignadorMisiones {

    private final CompatibilityValidator validador;
    private final IServicioMensajeria mensajeria;

    /**
     * Crea el servicio con sus dependencias inyectadas.
     *
     * @param validador  estrategia de validación de compatibilidad.
     * @param mensajeria servicio de notificación/mensajería.
     * @throws NullPointerException si alguna dependencia es {@code null}.
     */
    public AsignadorMisiones(CompatibilityValidator validador, IServicioMensajeria mensajeria) {
        Objects.requireNonNull(validador, "El validador no puede ser null");
        Objects.requireNonNull(mensajeria, "El servicio de mensajería no puede ser null");

        this.validador = validador;
        this.mensajeria = mensajeria;
    }

    /**
     * Asigna automáticamente una misión al primer héroe compatible
     * dentro de la lista de héroes disponibles.
     *
     * <p>Recorre la lista de héroes y selecciona al primero que cumpla
     * con todas las habilidades requeridas por la misión. Si ningún héroe
     * es compatible, genera un resultado fallido con un mensaje descriptivo.</p>
     *
     * @param heroesDisponibles lista de héroes candidatos (no puede ser {@code null} ni vacía).
     * @param mision            la misión a asignar (no puede ser {@code null}).
     * @return un {@link ResultadoAsignacion} con los detalles del proceso.
     * @throws NullPointerException     si algún parámetro es {@code null}.
     * @throws IllegalArgumentException si la lista de héroes está vacía.
     */
    public ResultadoAsignacion asignarMisionAutomatica(List<SuperHeroe> heroesDisponibles, Mision mision) {
        Objects.requireNonNull(heroesDisponibles, "La lista de héroes no puede ser null");
        Objects.requireNonNull(mision, "La misión no puede ser null");

        if (heroesDisponibles.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un héroe disponible");
        }

        for (SuperHeroe heroe : heroesDisponibles) {
            if (validador.esCompatible(heroe, mision)) {
                String mensajeExito = String.format(
                        "Misión '%s' asignada exitosamente a %s. Esperando despliegue.",
                        mision.getNombre(), heroe.getNombre()
                );
                mensajeria.enviarNotificacion(heroe, mensajeExito);

                return new ResultadoAsignacion(true, mision, heroe, mensajeExito, heroesDisponibles);
            }
        }

        // Ningún héroe fue compatible
        String mensajeFallo = String.format(
                "Ningún héroe disponible posee las habilidades requeridas para la misión '%s'. "
                        + "Habilidades necesarias: %s.",
                mision.getNombre(), mision.getHabilidadesRequeridas()
        );

        return new ResultadoAsignacion(false, mision, null, mensajeFallo, heroesDisponibles);
    }

    /**
     * Asigna manualmente una misión a un héroe específico.
     *
     * <p>Verifica la compatibilidad y notifica el resultado. Útil cuando
     * la interfaz gráfica permite selección manual.</p>
     *
     * @param heroe  el héroe candidato para la misión.
     * @param mision la misión a asignar.
     * @return {@code true} si la asignación fue exitosa, {@code false} si fue rechazada.
     */
    public boolean asignarMision(SuperHeroe heroe, Mision mision) {
        Objects.requireNonNull(heroe, "El héroe no puede ser null");
        Objects.requireNonNull(mision, "La misión no puede ser null");

        if (validador.esCompatible(heroe, mision)) {
            String mensajeExito = String.format(
                    "Misión '%s' asignada exitosamente a %s. Esperando despliegue.",
                    mision.getNombre(), heroe.getNombre()
            );
            mensajeria.enviarNotificacion(heroe, mensajeExito);
            return true;
        } else {
            String mensajeFallo = String.format(
                    "%s no posee las habilidades requeridas para la misión '%s'. Asignación abortada.",
                    heroe.getNombre(), mision.getNombre()
            );
            mensajeria.enviarNotificacion(heroe, mensajeFallo);
            return false;
        }
    }
}
