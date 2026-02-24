package validation;

import model.Hero;
import model.Mission;

/**
 * Contrato para la validación de misiones.
 Es decir que cualquier validador debe tener un método que reciba "Hero" o "Mission", y devuelva un Verdadero o Falso.

 * Aplica el "Principio Open/Closed (OCP)" porque depende de esta abstracción, el sistema está abierto
 a la extensión pero cerrado a la modificación. Si mañana decidimos que las "Missions" se miden por "Nivel de fuerza"
 y no por "Skills", solo creamos una nueva clase que implemente esta interfaz sin tocar el resto del programa.
 */
public interface CompatibilityValidator {
    boolean isCompatible(Hero hero, Mission mission);
}