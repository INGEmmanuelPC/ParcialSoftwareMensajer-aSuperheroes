package parcial1.solid.avengers.heroes;

import parcial1.solid.avengers.interfaces.IAcuatico;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import java.util.Set;

/**
 * Aquaman: héroe especializado en entornos acuáticos.
 *
 * <p>Implementa {@link IAcuatico} porque es el único comportamiento
 * específico que le corresponde (ISP).</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class Aquaman extends SuperHeroe implements IAcuatico {

    /**
     * Crea una instancia de Aquaman con habilidades predefinidas.
     */
    public Aquaman() {
        super("Aquaman", Set.of(TipoHabilidad.ACUATICO));
    }

    /**
     * {@inheritDoc}
     * <p>Aquaman se sumerge en las profundidades del océano.</p>
     */
    @Override
    public void nadar() {
        System.out.println(getNombre() + " se sumerge en las profundidades del océano.");
    }
}
