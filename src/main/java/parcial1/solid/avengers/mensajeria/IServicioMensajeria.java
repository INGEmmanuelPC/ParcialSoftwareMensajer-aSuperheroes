package parcial1.solid.avengers.mensajeria;

import parcial1.solid.avengers.heroes.SuperHeroe;

/**
 * Contrato que define el puerto de salida para la comunicación
 * con sistemas externos de notificación.
 *
 * <p>Aplica el <strong>Principio de Inversión de Dependencias (DIP)</strong>:
 * el dominio no necesita saber qué es SMTP o la API de Telegram.</p>
 *
 * <p>Aplica el <strong>Principio Abierto/Cerrado (OCP)</strong>: para agregar
 * un nuevo canal (WhatsApp, SMS), solo se crea una nueva clase que implemente
 * esta interfaz sin modificar el código existente.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public interface IServicioMensajeria {

    /**
     * Envía una notificación relacionada con un héroe.
     *
     * @param heroe   el héroe al que se refiere la notificación.
     * @param mensaje el contenido del mensaje a enviar.
     */
    void enviarNotificacion(SuperHeroe heroe, String mensaje);
}
