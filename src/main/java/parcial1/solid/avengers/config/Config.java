package parcial1.solid.avengers.config;

import io.github.cdimascio.dotenv.Dotenv;
import parcial1.solid.avengers.mensajeria.EmailNotifier;
import parcial1.solid.avengers.mensajeria.IServicioMensajeria;
import parcial1.solid.avengers.mensajeria.TelegramService;
import parcial1.solid.avengers.misiones.AsignadorMisiones;
import parcial1.solid.avengers.validation.CompatibilityValidator;
import parcial1.solid.avengers.validation.SkillBasedValidator;

/**
 * Clase de configuración que actúa como fábrica centralizada de dependencias.
 *
 * Se encarga de crear y configurar todos los servicios del sistema
 * utilizando variables de entorno definidas en el archivo .env.
 */
public class Config {

    private final Dotenv dotenv;

    /**
     * Carga las variables de entorno desde el archivo .env
     */
    public Config() {
        this.dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }

    /**
     * Obtiene una variable obligatoria del .env
     * Si no existe, lanza una excepción para evitar errores de configuración.
     */
    private String obtenerVariable(String clave) {
        String valor = dotenv.get(clave);

        if (valor == null || valor.isBlank()) {
            throw new RuntimeException(
                    "La variable de entorno '" + clave + "' no está definida en el archivo .env"
            );
        }

        return valor;
    }

    /**
     * Crea el validador de compatibilidad de misiones.
     */
    public CompatibilityValidator crearValidador() {
        return new SkillBasedValidator();
    }

    /**
     * Crea el servicio de mensajería por Telegram.
     */
    public IServicioMensajeria crearMensajeriaTelegram() {

        String botToken = obtenerVariable("TELEGRAM_BOT_TOKEN");
        String chatId = obtenerVariable("TELEGRAM_CHAT_ID");

        return new TelegramService(botToken, chatId);
    }

    /**
     * Crea el servicio de mensajería por email.
     */
    public IServicioMensajeria crearMensajeriaEmail() {

    String smtpHost = obtenerVariable("SMTP_HOST");
    String senderEmail = obtenerVariable("SENDER_EMAIL");
    String senderPassword = obtenerVariable("SENDER_PASSWORD");
    String targetEmail = obtenerVariable("TARGET_EMAIL");

    return new EmailNotifier(
            smtpHost,
            senderEmail,
            senderPassword,
            targetEmail
    );
}
    /**
     * Crea el asignador de misiones con todas sus dependencias.
     */
    public AsignadorMisiones crearAsignador() {
        return new AsignadorMisiones(
                crearValidador(),
                crearMensajeriaEmail()
        );
    }
}