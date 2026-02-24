package notification;

import model.Hero;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Implementación de Notifier utilizando el protocolo SMTP de JavaMail API (cumple su trabajo de conectarse a Internet).

* Se hizo settings.gradle.kts lo siguiente: implementation("org.eclipse.angus:angus-mail:2.0.2")
 */
public class EmailNotifier implements Notifier {

    /* deben inyectarse mediante variables de entorno (como el servidor del email SENDER_EMAIL y
    su Contraseña de aplicación SENDER_PASSWORD), para evitar vulnerabilidades.*/
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SENDER_EMAIL = "seguridadcontrasenia@gmail.com";
    private static final String SENDER_PASSWORD = "tcco uotw dxus eqev";

    // Destinatario fijo especificado por requerimiento
    private static final String TARGET_EMAIL = "tony.stark@avengers.com";

    private final Session mailSession;
    /**
     * Se inicializa la sesión en el constructor porque la configuración SSL y autenticación
     es costosa a nivel de procesamiento. Entonces así se configura una sola vez al instanciar el notificador,
     reutilizando la sesión para múltiples envíos de correos.
     */

    public EmailNotifier() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", "587");

        this.mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });
    }

    @Override
    public void notify(Hero hero, String messageContent) {
        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TARGET_EMAIL));

            // Asunto dinámico (con .getName concatenado al String) para mantener la trazabilidad de qué héroe está siendo asignado.
            message.setSubject("URGENT: Mission Update for " + hero.getName());
            message.setText(messageContent);

            Transport.send(message);

        } catch (MessagingException e) {
            /*
            * Se captura la excepción en vez de arrojarse al servidor principal, porque si el servidor de correos
            se cae, no debemos bloquear ni romper la ejecución principal.
           */
            System.err.println("Communication Failure. Could not send email to " + TARGET_EMAIL + ": " + e.getMessage());
        }
    }
}
