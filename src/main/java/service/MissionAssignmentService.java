package service;

import model.Hero;
import model.Mission;
import notification.Notifier;
import validation.CompatibilityValidator;

/**
 * Servicio de asignación (Orquestador).

 * cumple Principio de Responsabilidad Única (SRP): Solo delega valida y notifica,

 * no sabe CÓMO se valida ni CÓMO se notifica.

 * Revisa un Hero, si validator dice que Si entonces le dice al notifier que que mande
 un mensaje de éxito (de lo contrario, mensaje de rechazo).
 */
public class MissionAssignmentService {

    /*
    * Es como una inyección de dependencias, porque permite "inyectar" un Notifier "Falso"
    para evitar enviar correos reales cada vez que corren las pruebas de compilación.
    */
    private final CompatibilityValidator validator;
    private final Notifier notifier;

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
        }
        else {
            String failureMessage = String.format(
                    "Warning: %s lacks the required skills for mission '%s'. Assignment aborted to prevent casualties.",
                    hero.getName(), mission.getName()
            );
            notifier.notify(hero, failureMessage);
        }
    }
}
