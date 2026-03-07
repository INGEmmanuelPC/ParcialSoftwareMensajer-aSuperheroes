package parcial1.solid.avengers.validation;

import parcial1.solid.avengers.heroes.SuperHeroe;
import parcial1.solid.avengers.misiones.Mision;

/**
 * Implementación que evalúa la compatibilidad verificando que el héroe
 * posea el 100% de las habilidades requeridas por la misión.
 *
 * <p>Si en el futuro se necesita un validador más flexible (por ejemplo,
 * que acepte héroes con al menos el 70% de las habilidades), se crea
 * otra clase que implemente {@link CompatibilityValidator} sin modificar
 * esta (OCP).</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class SkillBasedValidator implements CompatibilityValidator {

    /**
     * Verifica que el héroe tenga TODAS las habilidades de la misión.
     *
     * @param heroe  el héroe candidato.
     * @param mision la misión a evaluar.
     * @return {@code true} solo si el héroe posee cada habilidad requerida.
     */
    @Override
    public boolean esCompatible(SuperHeroe heroe, Mision mision) {
        return heroe.tieneTodasLasHabilidades(mision.getHabilidadesRequeridas());
    }
}
