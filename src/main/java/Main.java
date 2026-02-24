import model.Skill;
import model.Hero;
import model.Mission;
import notification.Notifier;
import notification.EmailNotifier;
import service.MissionAssignmentService;
import validation.CompatibilityValidator;
import validation.SkillBasedValidator;

import java.util.Set;

/**
 * Punto de entrada de la aplicación.
 * Actúa como "Composition Root": el lugar exclusivo donde se decide qué implementaciones
 * concretas usar y se ensambla la aplicación.
 */
public class Main {
    public static void main(String[] args) {

        // 1. Inicialización de datos base
        Skill flight = new Skill("Flight");
        Skill wallCrawling = new Skill("Wall Crawling");
        Skill aquatic = new Skill("Aquatic");

        Hero spiderMan = new Hero("SpiderMan", Set.of(wallCrawling));
        Hero aquaman = new Hero("Aquaman", Set.of(aquatic));

        Mission submarineRescue = new Mission("Submarine Rescue", Set.of(aquatic));
        Mission stopGreenGoblin = new Mission("Stop Green Goblin in Skyscraper", Set.of(wallCrawling, flight));

        // 2. Ensamblaje de dependencias (Cumpliendo SOLID)
        CompatibilityValidator validator = new SkillBasedValidator();
        // Aquí se inyecta el EmailNotifier. Si mañana queremos cambiar a SMS,
        // solo cambiamos esta línea por "new SmsNotifier()".
        Notifier notifier = new EmailNotifier();

        // 3. Creación del servicio con las dependencias inyectadas
        MissionAssignmentService commandCenter = new MissionAssignmentService(validator, notifier);

        // 4. Ejecución de casos de prueba
        // Caso Exitoso (Aquaman tiene habilidad Acuática)
        commandCenter.assignMission(aquaman, submarineRescue);

        // Caso Fallido (SpiderMan no tiene habilidad de Volar)
        commandCenter.assignMission(spiderMan, stopGreenGoblin);
    }
}