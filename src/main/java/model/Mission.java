package model;

import java.util.Set;

/**
 * Representa una misión dentro del sistema.
 *
 * Una misión tiene un nombre y un conjunto de habilidades necesarias
 * para poder asignarla correctamente.
 *
 * La clase es inmutable: una vez creada la misión, sus datos no cambian.
 * Esto ayuda a mantener coherencia en el flujo del sistema y evita
 * modificaciones accidentales después de haber validado requisitos.
 */
public class Mission {

    // Nombre identificador de la misión
    private final String name;

    // Conjunto de habilidades necesarias para cumplir la misión
    // Se usa Set para evitar habilidades repetidas
    private final Set<Skill> requiredSkills;

    /**
     * Constructor de la misión.
     *
     * Se recibe el nombre y el conjunto de habilidades requeridas.
     * Se asume que el conjunto ya viene validado desde la capa superior
     * (por ejemplo, que no sea null y que tenga sentido para el dominio).
     */
    public Mission(String name, Set<Skill> requiredSkills) {
        this.name = name;
        this.requiredSkills = requiredSkills;
    }

    /**
     * Retorna el nombre de la misión.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna las habilidades necesarias para completar la misión.
     *
     * Se expone como Set porque semánticamente lo que importa
     * es la pertenencia (tener o no tener una habilidad),
     * no el orden ni la repetición.
     */
    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }
}