package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.interfaces.IControlFuego;
import parcial1.solid.avengers.interfaces.IVolador;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * Thor: dios del trueno con capacidad de volar y controlar energía.
 *
 * <p>Implementa {@link IVolador} e {@link IControlFuego} ya que puede
 * desplazarse por el aire y canalizar rayos (ISP).</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class Thor extends SuperHeroe implements IVolador, IControlFuego {

    /**
     * Crea una instancia de Thor con habilidades predefinidas.
     */
    public Thor() {
        super("Thor", Set.of(TipoHabilidad.VOLADOR, TipoHabilidad.CONTROL_FUEGO));
    }

    /**
     * {@inheritDoc}
     * <p>Thor surca los cielos impulsado por Mjolnir.</p>
     */
    @Override
    public void volar() {
        System.out.println(getNombre() + " surca los cielos impulsado por Mjolnir.");
    }

    /**
     * {@inheritDoc}
     * <p>Thor desata una tormenta de rayos y truenos.</p>
     */
    @Override
    public void lanzarFuego() {
        System.out.println(getNombre() + " desata una tormenta de rayos y truenos.");
    }
}
