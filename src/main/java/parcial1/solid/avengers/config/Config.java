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
 * <p>Separa la <strong>creación</strong> de objetos de su <strong>uso</strong>,
 * cumpliendo con el principio de responsabilidad única. Las variables sensibles
 * (tokens, contraseñas, hosts) se leen del archivo {@code .env} mediante la
 * librería <em>dotenv-java</em>, evitando que queden expuestas en el código fuente.</p>
 *
 * <p>Si mañana se quiere cambiar de {@link TelegramService} a {@link EmailNotifier},
 * solo se modifica esta clase. El resto del sistema no se entera del cambio.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class Config {

    private final Dotenv dotenv;

    /**
     * Crea la configuración cargando las variables de entorno desde {@code .env}.
     *
     * <p>Si el archivo {@code .env} no existe, la librería no lanza excepción
     * gracias a {@code ignoreIfMissing()}; en ese caso se usan valores de demostración.</p>
     */
    public Config() {
        this.dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }

    /**
     * Lee una variable de entorno desde el archivo {@code .env}.
     * Si la variable no existe, devuelve el valor por defecto proporcionado.
     *
     * @param clave        nombre de la variable (ej: {@code "TELEGRAM_BOT_TOKEN"}).
     * @param valorDefecto valor a usar si la variable no está definida.
     * @return el valor de la variable o el valor por defecto.
     */
    public String obtenerVariable(String clave, String valorDefecto) {
        String valor = dotenv.get(clave);
        return (valor != null && !valor.isBlank()) ? valor : valorDefecto;
    }

    /**
     * Crea la estrategia de validación a utilizar.
     *
     * @return instancia del validador configurado.
     */
    public CompatibilityValidator crearValidador() {
        return new SkillBasedValidator();
    }

    /**
     * Crea el servicio de mensajería a utilizar.
     * Lee las credenciales de Telegram desde el archivo {@code .env}.
     *
     * @return instancia del servicio de mensajería configurado.
     */
    public IServicioMensajeria crearMensajeria() {
        String botToken = obtenerVariable("TELEGRAM_BOT_TOKEN", "DEMO_TOKEN_12345");
        String chatId = obtenerVariable("TELEGRAM_CHAT_ID", "DEMO_CHAT_001");

        return new TelegramService(botToken, chatId);
    }

    /**
     * Crea el servicio de mensajería por email.
     * Lee las credenciales SMTP desde el archivo {@code .env}.
     *
     * @return instancia del servicio de email configurado.
     */
    public IServicioMensajeria crearMensajeriaEmail() {
        String smtpHost = obtenerVariable("SMTP_HOST", "smtp.gmail.com");
        String senderEmail = obtenerVariable("SENDER_EMAIL", "demo@ejemplo.com");
        String senderPassword = obtenerVariable("SENDER_PASSWORD", "demo_password");

        return new EmailNotifier(smtpHost, senderEmail, senderPassword);
    }

    /**
     * Crea el servicio asignador de misiones con todas sus dependencias ensambladas.
     *
     * @return instancia lista para usar de {@link AsignadorMisiones}.
     */
    public AsignadorMisiones crearAsignador() {
        return new AsignadorMisiones(crearValidador(), crearMensajeria());
    }
}
