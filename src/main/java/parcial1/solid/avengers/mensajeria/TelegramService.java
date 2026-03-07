package parcial1.solid.avengers.mensajeria;

import parcial1.solid.avengers.heroes.SuperHeroe;

import java.util.Objects;

/**
 * Implementación de {@link IServicioMensajeria} que envía notificaciones
 * a través de la API de Telegram Bot.
 *
 * <p>Las credenciales ({@code botToken} y {@code chatId}) se reciben por
 * constructor, aplicando <strong>inyección de dependencias</strong>.
 * Los valores reales se leen desde el archivo {@code .env} a través de
 * la clase {@link parcial1.solid.avengers.config.Config}.</p>
 *
 * <p>La lógica real de conexión HTTP se simula con salida por consola
 * para mantener el ejemplo enfocado en la arquitectura.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class TelegramService implements IServicioMensajeria {

    private final String botToken;
    private final String chatId;

    /**
     * Crea el servicio de Telegram con las credenciales necesarias.
     *
     * @param botToken token del bot de Telegram (no puede ser {@code null} ni vacío).
     * @param chatId   identificador del chat destino (no puede ser {@code null} ni vacío).
     * @throws NullPointerException     si algún parámetro es {@code null}.
     * @throws IllegalArgumentException si algún parámetro está vacío.
     */
    public TelegramService(String botToken, String chatId) {
        Objects.requireNonNull(botToken, "El botToken no puede ser null");
        Objects.requireNonNull(chatId, "El chatId no puede ser null");

        if (botToken.isBlank()) {
            throw new IllegalArgumentException("El botToken no puede estar vacío");
        }
        if (chatId.isBlank()) {
            throw new IllegalArgumentException("El chatId no puede estar vacío");
        }

        this.botToken = botToken;
        this.chatId = chatId;
    }

    /**
     * Envía una notificación por Telegram asociada a un héroe.
     *
     * @param heroe   el héroe relacionado con la notificación.
     * @param mensaje el texto a enviar.
     */
    @Override
    public void enviarNotificacion(SuperHeroe heroe, String mensaje) {
        System.out.println("[TELEGRAM] Enviando a chat " + chatId + ":");
        System.out.println("  Héroe: " + heroe.getNombre());
        System.out.println("  Mensaje: " + mensaje);
        System.out.println("  (Bot: " + botToken.substring(0, Math.min(5, botToken.length())) + "...)");
        System.out.println();
    }
}
