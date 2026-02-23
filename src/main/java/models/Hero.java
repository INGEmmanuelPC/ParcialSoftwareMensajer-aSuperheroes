package model;

import java.util.Objects;

/**
 * Clase que representa una habilidad dentro del sistema.
 *
 * La idea es que una habilidad sea algo simple (por ejemplo: "Volar", "Programar", "Liderazgo")
 * y que, una vez creada, no cambie. Por eso la clase es inmutable:
 * - El atributo es final.
 * - No existen setters.
 *
 * Esto evita problemas si en algún punto del sistema se comparte la misma habilidad
 * entre varias entidades. Así nos aseguramos de que su valor no cambie inesperadamente.
 */
public class Skill {

    // Nombre único que identifica la habilidad
    private final String name;

    /**
     * Constructor de la habilidad.
     * Se asume que el nombre es el identificador principal.
     */
    public Skill(String name) {
        this.name = name;
    }

    /**
     * Retorna el nombre de la habilidad.
     */
    public String getName() {
        return name;
    }

    /*
     * Se sobrescriben equals y hashCode porque esta clase se usará en colecciones
     * como Set, donde no se permiten elementos duplicados.
     *
     * Si no redefinimos estos métodos, dos objetos:
     *     new Skill("Volar")
     *     new Skill("Volar")
     * se considerarían diferentes solo porque están en distintas posiciones de memoria.
     *
     * Con esta implementación, dos habilidades con el mismo nombre
     * se consideran iguales a nivel lógico, que es lo que realmente nos interesa.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}