package parcial1.solid.avengers.main;

import parcial1.solid.avengers.config.Config;
import parcial1.solid.avengers.gui.MainForm;
import parcial1.solid.avengers.heroes.*;
import parcial1.solid.avengers.misiones.AsignadorMisiones;
import parcial1.solid.avengers.misiones.Mision;
import parcial1.solid.avengers.misiones.ResultadoAsignacion;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import javax.swing.*;
import java.util.List;
import java.util.Set;
/**
 * Punto de entrada de la aplicación.
 *
 * <p>Ofrece dos modos de ejecución:</p>
 * <ul>
 *   <li><strong>Sin argumentos</strong>: lanza la interfaz gráfica (Swing).</li>
 *   <li><strong>Con argumento {@code --consola}</strong>: ejecuta la demo
 *       por consola con asignación automática.</li>
 * </ul>
 *
 * <p>Gracias a la clase {@link Config}, el Main queda limpio y enfocado
 * únicamente en crear los datos de prueba y ejecutar los casos.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class ParcialMain {

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de línea de comandos. Usar {@code --consola}
     *             para ejecutar la demo en modo texto.
     */
    public static void main(String[] args) {
        Config config = new Config();
        AsignadorMisiones asignador = config.crearAsignador();

        if (args.length > 0 && "--consola".equals(args[0])) {
            ejecutarDemoConsola(asignador);
        } else {
            lanzarInterfazGrafica(asignador);
        }
    }

    /**
     * Lanza la interfaz gráfica del plugin "Swing UI Form" en el hilo de eventos de AWT.
     *
     * @param asignador servicio de asignación de misiones.
     */
    private static void lanzarInterfazGrafica(AsignadorMisiones asignador) {
        SwingUtilities.invokeLater(() -> {
            MainForm ventana = new MainForm(asignador);
            ventana.setVisible(true);
        });
    }

    /**
     * Ejecuta una demostración completa por consola con asignación automática.
     *
     * <p>Crea héroes y misiones de prueba, luego deja que el sistema
     * asigne automáticamente al mejor héroe para cada misión.</p>
     *
     * @param asignador servicio de asignación de misiones.
     */
    private static void ejecutarDemoConsola(AsignadorMisiones asignador) {

        // ── Creación de héroes ──
        List<SuperHeroe> heroes = List.of(
                new Aquaman(),
                new SpiderMan(),
                new Thor(),
                new IronMan(),
                new Hulk()
        );

        // ── Creación de misiones ──
        List<Mision> misiones = List.of(
                new Mision("Rescate Submarino", Set.of(TipoHabilidad.ACUATICO)),
                new Mision("Detener al Duende Verde", Set.of(TipoHabilidad.TREPAMUROS, TipoHabilidad.SIGILOSO)),
                new Mision("Patrulla Aérea sobre Nueva York", Set.of(TipoHabilidad.VOLADOR)),
                new Mision("Contener erupción volcánica", Set.of(TipoHabilidad.VOLADOR, TipoHabilidad.CONTROL_FUEGO)),
                new Mision("Misión Imposible Acuática-Aérea", Set.of(TipoHabilidad.ACUATICO, TipoHabilidad.VOLADOR))
        );

        // ── Asignación automática ──
        System.out.println("═══════════════════════════════════════════");
        System.out.println("  SISTEMA DE ASIGNACIÓN AUTOMÁTICA DE MISIONES");
        System.out.println("═══════════════════════════════════════════\n");

        for (Mision mision : misiones) {
            System.out.println("--- Misión: " + mision.getNombre() + " ---");
            System.out.println("    Habilidades requeridas: " + mision.getHabilidadesRequeridas());

            ResultadoAsignacion resultado = asignador.asignarMisionAutomatica(heroes, mision);

            if (resultado.isExitosa()) {
                System.out.println("    Resultado: ASIGNADO a " + resultado.getHeroeAsignado().getNombre());
            } else {
                System.out.println("    Resultado: SIN HÉROE COMPATIBLE");
            }
            System.out.println("    Detalle: " + resultado.getMensaje());
            System.out.println();
        }
    }
}
