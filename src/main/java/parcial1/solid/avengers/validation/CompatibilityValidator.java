package parcial1.solid.avengers.validation;

import parcial1.solid.avengers.heroes.SuperHeroe;
import parcial1.solid.avengers.misiones.Mision;

/**
 * Contrato para la validación de compatibilidad entre héroes y misiones.
 *
 * <p>Aplica el <strong>Principio Abierto/Cerrado (OCP)</strong>: el sistema
 * está abierto a extensión pero cerrado a modificación. Para agregar
 * una nueva estrategia de validación, basta crear una nueva clase
 * que implemente esta interfaz.</p>
 *
 * <p>Aplica el <strong>Principio de Inversión de Dependencias (DIP)</strong>:
 * {@link parcial1.solid.avengers.misiones.AsignadorMisiones} depende
 * de esta abstracción y no de una implementación concreta.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface CompatibilityValidator {

    /**
     * Evalúa si un héroe es compatible con una misión dada.
     *
     * @param heroe  el héroe candidato.
     * @param mision la misión a evaluar.
     * @return {@code true} si el héroe cumple los requisitos de la misión.
     */
    boolean esCompatible(SuperHeroe heroe, Mision mision);
}
