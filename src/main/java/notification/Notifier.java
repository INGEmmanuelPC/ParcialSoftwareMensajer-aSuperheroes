package notification;

import model.Hero;

/**
 * Contrato que es como el "puerto de salida para la comunicación con sistemas externos".
 Porque cualquier Notifier debe poder enviar un mensaje a un Hero.

 * Se aplica Dependency Inversion Principle (DIP). El dominio de la aplicación no tiene por qué saber
 qué es un SMTP, porque solo debe notificar.
 */
public interface Notifier {
    void notify(Hero hero, String message);
}
