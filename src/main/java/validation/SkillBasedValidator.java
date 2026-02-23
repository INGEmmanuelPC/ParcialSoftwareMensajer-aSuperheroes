package validations;

import model.Hero;
import model.Mission;
import model.Skill;

/**
 * Implementación concreta de validación basada en un match 1:1 de habilidades.
 * <p>
 * Principio aplicado: Single Responsibility Principle (SRP). Esta clase tiene una única razón para cambiar:
 * si la lógica de cómo se emparejan las habilidades evoluciona.
 */
public class SkillBasedValidator implements CompatibilityValidator {

    @Override
    public boolean isCompatible(Hero hero, Mission mission) {
        for (Skill requiredSkill : mission.getRequiredSkills()) {
            if (!hero.hasSkill(requiredSkill)) {
                return false;
            }
        }
        return true;
    }
}
