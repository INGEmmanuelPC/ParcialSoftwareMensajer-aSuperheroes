package parcial1.solid.avengers.mensajeria;

import parcial1.solid.avengers.heroes.SuperHeroe;

import java.util.Objects;

/**
 * Implementación de {@link IServicioMensajeria} que envía notificaciones
 * por correo electrónico usando el protocolo SMTP.
 *
 * <p>Las credenciales se reciben por constructor, aplicando
 * <strong>inyección de dependencias</strong>. Los valores reales se leen
 * desde el archivo {@code .env} a través de
 * {@link parcial1.solid.avengers.config.Config}.</p>
 *
 * <p>La lógica de JavaMail se omite en esta versión para mantener el
 * ejemplo enfocado en la arquitectura SOLID.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class EmailNotifier implements IServicioMensajeria {

    private final String smtpHost;
    private final String senderEmail;
    private final String senderPassword;

    /**
     * Crea el notificador de email con las credenciales SMTP.
     *
     * @param smtpHost       servidor SMTP (ej: {@code "smtp.gmail.com"}).
     * @param senderEmail    dirección de correo del remitente.
     * @param senderPassword contraseña o token de aplicación del remitente.
     * @throws NullPointerException     si algún parámetro es {@code null}.
     * @throws IllegalArgumentException si algún parámetro está vacío.
     */
    public EmailNotifier(String smtpHost, String senderEmail, String senderPassword) {
        Objects.requireNonNull(smtpHost, "El smtpHost no puede ser null");
        Objects.requireNonNull(senderEmail, "El senderEmail no puede ser null");
        Objects.requireNonNull(senderPassword, "El senderPassword no puede ser null");

        if (smtpHost.isBlank() || senderEmail.isBlank() || senderPassword.isBlank()) {
            throw new IllegalArgumentException("Las credenciales SMTP no pueden estar vacías");
        }

        this.smtpHost = smtpHost;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    /**
     * Envía una notificación por email asociada a un héroe.
     *
     * @param heroe   el héroe relacionado con la notificación.
     * @param mensaje el texto a enviar como cuerpo del correo.
     */
    @Override
    public void enviarNotificacion(SuperHeroe heroe, String mensaje) {
        System.out.println("[EMAIL] Enviando desde " + senderEmail + " vía " + smtpHost + ":");
        System.out.println("  Asunto: Actualización de misión para " + heroe.getNombre());
        System.out.println("  Cuerpo: " + mensaje);
        System.out.println();
    }
}
