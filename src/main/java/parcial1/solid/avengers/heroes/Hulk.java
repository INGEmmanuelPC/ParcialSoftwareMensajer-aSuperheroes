package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.interfaces.IControlFuego;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * Hulk: héroe con fuerza descomunal y resistencia a energía térmica.
 *
 * <p>Implementa {@link IControlFuego} representando su capacidad de
 * resistir y manipular entornos de alta energía destructiva.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class Hulk extends SuperHeroe implements IControlFuego {

    /**
     * Crea una instancia de Hulk con habilidades predefinidas.
     */
    public Hulk() {
        super("Hulk", Set.of(TipoHabilidad.CONTROL_FUEGO));
    }

    /**
     * {@inheritDoc}
     * <p>Hulk genera una onda expansiva de energía pura con sus puños.</p>
     */
    @Override
    public void lanzarFuego() {
        System.out.println(getNombre() + " genera una onda expansiva de energía pura con sus puños.");
    }
}
