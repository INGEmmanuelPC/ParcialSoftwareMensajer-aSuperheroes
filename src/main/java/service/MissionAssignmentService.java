package service;

import model.Hero;
import model.Mission;
import notification.Notifier;
import validation.CompatibilityValidator;

/**
 * Servicio principal (Orquestador) que maneja el flujo de negocio.
 * Sigue el Principio de Responsabilidad Única (SRP): Solo delega validación y notificación,
 * no sabe CÓMO se valida ni CÓMO se notifica.
 */
public class MissionAssignmentService {

    private final CompatibilityValidator validator;
    private final Notifier notifier;

    /**
     * Inyección de Dependencias.
     * ¿Por qué? Permite instanciar este servicio en entornos de pruebas unitarias (JUnit)
     * inyectando un Notificador "Falso" (Mock) para evitar enviar correos reales cada vez
     * que corren las pruebas de compilación.
     */
    public MissionAssignmentService(CompatibilityValidator validator, Notifier notifier) {
        this.validator = validator;
        this.notifier = notifier;
    }

    public void assignMission(Hero hero, Mission mission) {
        if (validator.isCompatible(hero, mission)) {
            String successMessage = String.format(
                    "Mission '%s' successfully assigned to %s. Awaiting deployment.",
                    mission.getName(), hero.getName()
            );
            notifier.notify(hero, successMessage);
        } else {
            String failureMessage = String.format(
                    "Warning: %s lacks the required skills for mission '%s'. Assignment aborted to prevent casualties.",
                    hero.getName(), mission.getName()
            );
            notifier.notify(hero, failureMessage);
        }
    }
}
