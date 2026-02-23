package model;

import java.util.Objects;

/**
 * Modelo que representa una habilidad dentro del sistema.
 *
 * Es un objeto simple cuyo único propósito es identificar una capacidad
 * concreta (por ejemplo: "Volar", "Programar", "Estrategia").
 *
 * Se define como inmutable: una vez creada la habilidad, su nombre no cambia.
 * Esto evita efectos secundarios raros si la misma instancia se comparte
 * entre distintas partes del sistema.
 */
public class Skill {

    // Nombre que identifica la habilidad
    private final String name;

    /**
     * Crea una nueva habilidad.
     * El nombre funciona como identificador lógico dentro del dominio.
     */
    public Skill(String name) {
        this.name = name;
    }

    /**
     * Devuelve el nombre de la habilidad.
     */
    public String getName() {
        return name;
    }

    /*
     * Se sobrescriben equals y hashCode porque esta clase se usará en colecciones
     * como Set.
     *
     * Si no se redefinen, Java compararía objetos por referencia (memoria),
     * lo que haría que dos habilidades con el mismo nombre se consideren distintas.
     *
     * Con esta implementación, la igualdad se basa únicamente en el nombre,
     * que es lo que realmente define a la habilidad dentro del modelo.
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