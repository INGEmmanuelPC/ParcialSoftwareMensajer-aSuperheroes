package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.interfaces.ISigiloso;
import parcial1.solid.avengers.interfaces.ITrepamuros;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * SpiderMan: héroe con habilidades de trepar muros y actuar sigilosamente.
 *
 * <p>Implementa {@link ITrepamuros} e {@link ISigiloso} porque ambos
 * comportamientos le corresponden según el dominio (ISP).</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class SpiderMan extends SuperHeroe implements ITrepamuros, ISigiloso {

    /**
     * Crea una instancia de SpiderMan con habilidades predefinidas.
     */
    public SpiderMan() {
        super("SpiderMan", Set.of(TipoHabilidad.TREPAMUROS, TipoHabilidad.SIGILOSO));
    }

    /**
     * {@inheritDoc}
     * <p>SpiderMan trepa por las paredes del rascacielos.</p>
     */
    @Override
    public void trepar() {
        System.out.println(getNombre() + " trepa por las paredes del rascacielos.");
    }

    /**
     * {@inheritDoc}
     * <p>SpiderMan se infiltra sigilosamente en la base enemiga.</p>
     */
    @Override
    public void infiltrar() {
        System.out.println(getNombre() + " se infiltra sigilosamente en la base enemiga.");
    }
}
