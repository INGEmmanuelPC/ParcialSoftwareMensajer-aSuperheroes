package model;

import java.util.Set;

/**
 * Representa a un héroe dentro del sistema.
 *
 * Un héroe tiene un nombre y un conjunto de habilidades que definen
 * qué tipo de misiones puede asumir.
 *
 * La clase es inmutable: una vez creado el héroe, no se modifican
 * ni su nombre ni sus habilidades. Esto ayuda a mantener coherencia
 * en las asignaciones y evita cambios inesperados en tiempo de ejecución.
 */
public class Hero {

    // Nombre identificador del héroe
    private final String name;

    // Conjunto de habilidades del héroe.
    // Se usa Set para evitar duplicados de forma natural
    // (no tendría sentido que tenga dos veces la misma habilidad).
    private final Set<Skill> skills;

    /**
     * Crea un nuevo héroe con su nombre y habilidades.
     *
     * Se asume que las validaciones necesarias (null, conjunto vacío, etc.)
     * se manejan desde una capa superior.
     */
    public Hero(String name, Set<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    /**
     * Devuelve el nombre del héroe.
     */
    public String getName() {
        return name;
    }

    /**
     * Indica si el héroe posee una habilidad específica.
     *
     * En lugar de exponer directamente la colección de habilidades
     * para que otra clase la recorra, se encapsula esta lógica aquí.
     * Así el héroe responde por su propia capacidad y no delega
     * esa responsabilidad hacia afuera.
     */
    public boolean hasSkill(Skill skill) {
        return skills.contains(skill);
    }
}