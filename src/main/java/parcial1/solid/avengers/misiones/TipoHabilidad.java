package parcial1.solid.avengers.misiones;

/**
 * Enumeración que representa las habilidades existentes dentro del sistema.
 *
 * <p>Se usa un {@code enum} en lugar de cadenas de texto para evitar el
 * <em>code smell</em> de <strong>Primitive Obsession</strong>: el compilador
 * verifica los valores en tiempo de compilación, eliminando errores por
 * tipografía.</p>
 *
 * <p>Cada constante incluye una etiqueta legible que se utiliza en la
 * interfaz gráfica y en los mensajes de consola.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public enum TipoHabilidad {

    /** Capacidad de operar bajo el agua y resistir presiones submarinas. */
    ACUATICO("Acuático"),

    /** Capacidad de volar o desplazarse por el aire. */
    VOLADOR("Volador"),

    /** Capacidad de escalar superficies verticales y techos. */
    TREPAMUROS("Trepamuros"),

    /** Capacidad de moverse sin ser detectado e infiltrarse. */
    SIGILOSO("Sigiloso"),

    /** Capacidad de generar y controlar fuego o energía térmica. */
    CONTROL_FUEGO("Control de Fuego");

    private final String etiqueta;

    /**
     * Constructor interno del enum.
     *
     * @param etiqueta nombre legible para mostrar en la interfaz gráfica.
     */
    TipoHabilidad(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Devuelve la etiqueta legible de la habilidad.
     *
     * @return cadena con el nombre legible (por ejemplo {@code "Acuático"}).
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Representación en cadena de la habilidad.
     *
     * @return la etiqueta legible.
     */
    @Override
    public String toString() {
        return etiqueta;
    }
}
