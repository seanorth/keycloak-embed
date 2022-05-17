package cn.springseed.keycloak.mail;

import java.util.Locale;
import java.util.Map;

import org.keycloak.email.EmailException;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;

import cn.springseed.keycloak.mqtt.PublisherService;

/**
 * 邮件发送提供者实现
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
public class MqttEmailSenderProvider implements EmailSenderProvider {
    private static final String MAIL_TEMPLATE_CODE = "KK.";

    private final KeycloakSession session;
    private final String topic;

    public MqttEmailSenderProvider(KeycloakSession session, String topic) {
        this.session = session;
        this.topic = topic;
    }

    @Override
    public void close() {

    }

    @Override
    public void send(Map<String, String> config, UserModel user, String subject, String textBody, String htmlBody)
            throws EmailException {
        final Locale locale = session.getContext().resolveLocale(user);

        try {
            final Message message = Message.custom()
                    .mailTo(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .subject(subject)
                    .textBody(textBody)
                    .htmlBody(htmlBody)
                    .templateCode(MAIL_TEMPLATE_CODE)
                    .locale(locale);
            session.getProvider(PublisherService.class).publish(topic, message.toJson());
        } catch (Exception e) {
            throw new EmailException(e);
        }

    }

}
