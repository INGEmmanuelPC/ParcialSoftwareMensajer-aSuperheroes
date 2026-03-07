package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.interfaces.IVolador;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * IronMan: héroe tecnológico con capacidad de vuelo.
 *
 * <p>Solo implementa {@link IVolador} porque su habilidad principal
 * en este dominio es el vuelo (ISP).</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class IronMan extends SuperHeroe implements IVolador {

    /**
     * Crea una instancia de IronMan con habilidades predefinidas.
     */
    public IronMan() {
        super("IronMan", Set.of(TipoHabilidad.VOLADOR));
    }

    /**
     * {@inheritDoc}
     * <p>IronMan activa los propulsores de su armadura y despega.</p>
     */
    @Override
    public void volar() {
        System.out.println(getNombre() + " activa los propulsores de su armadura y despega.");
    }
}
