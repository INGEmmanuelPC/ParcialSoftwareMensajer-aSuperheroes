package validation;

import model.Hero;
import model.Mission;
import model.Skill;

/**
 * Implementación concreta que evalúa la compatibilidad cuando el héroe posea el 100% de las habilidades requeridas.

 * Si encuentra aunque sea una sola habilidad que el heroe no tenga, devuelve un Falso.
 */
public class SkillBasedValidator implements CompatibilityValidator {

    @Override
    public boolean isCompatible(Hero hero, Mission mission) {

        // Hace un retorno temprano para mejorar el rendimiento. A la primera Skill que falte, descartamos al Hero
        // Y así no iteramos sobre el resto de los requisitos.
        for (Skill requiredSkill : mission.getRequiredSkills()) {
            if (!hero.hasSkill(requiredSkill)) {
                return false;
            }
        }
        return true;
    }
}
