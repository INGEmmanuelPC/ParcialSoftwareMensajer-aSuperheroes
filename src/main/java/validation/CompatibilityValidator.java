package validations;

import model.Hero;
import model.Mission;

/**
 * Contrato para las estrategias de evaluación de riesgo y compatibilidad.
 * <p>
 * Principio aplicado: Open/Closed Principle (OCP). Aislar la lógica de validación tras una interfaz
 * permite introducir nuevas reglas de negocio (ej. validación por nivel de fatiga, por nivel de poder, etc.)
 * sin modificar los servicios core que consumen esta validación.
 */
public interface CompatibilityValidator {
    boolean isCompatible(Hero hero, Mission mission);
}