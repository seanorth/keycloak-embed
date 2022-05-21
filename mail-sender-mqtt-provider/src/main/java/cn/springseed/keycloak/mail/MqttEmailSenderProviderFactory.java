package cn.springseed.keycloak.mail;

import org.keycloak.Config.Scope;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.email.EmailSenderProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * 邮件发送提供者工厂实现
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class MqttEmailSenderProviderFactory implements EmailSenderProviderFactory {
    @Override
    public EmailSenderProvider create(KeycloakSession session) {
        return new MqttEmailSenderProvider(session);
    }

    @Override
    public void init(Scope config) {  
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        
    }

    @Override
    public void close() {        
    }

    @Override
    public String getId() {
        return "s8d-mqtt";
    }
}
