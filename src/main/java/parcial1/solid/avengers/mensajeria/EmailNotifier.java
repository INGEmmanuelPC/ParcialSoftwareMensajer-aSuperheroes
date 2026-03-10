package parcial1.solid.avengers.mensajeria;

import parcial1.solid.avengers.heroes.SuperHeroe;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Objects;
import java.util.Properties;

/**
 * Implementación real de IServicioMensajeria que envía correos
 * usando SMTP (Gmail).
 */
public class EmailNotifier implements IServicioMensajeria {

    private final String smtpHost;
    private final String senderEmail;
    private final String senderPassword;
    private final String targetEmail;

    private final Session mailSession;

    public EmailNotifier(
            String smtpHost,
            String senderEmail,
            String senderPassword,
            String targetEmail
    ) {

        Objects.requireNonNull(smtpHost);
        Objects.requireNonNull(senderEmail);
        Objects.requireNonNull(senderPassword);
        Objects.requireNonNull(targetEmail);

        this.smtpHost = smtpHost;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
        this.targetEmail = targetEmail;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", "587");

        this.mailSession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    @Override
    public void enviarNotificacion(SuperHeroe heroe, String mensaje) {

        try {

            Message message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(senderEmail));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(targetEmail)
            );

            message.setSubject("Actualización de misión para " + heroe.getNombre());

            message.setText(mensaje);

            Transport.send(message);

            System.out.println("Correo enviado correctamente a " + targetEmail);

        } catch (MessagingException e) {

            System.err.println("Error enviando correo: " + e.getMessage());

        }
    }
}