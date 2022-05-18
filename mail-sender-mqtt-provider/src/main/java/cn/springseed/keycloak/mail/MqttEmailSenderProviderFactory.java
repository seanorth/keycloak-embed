package cn.springseed.keycloak.mail;

import org.keycloak.Config.Scope;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.email.EmailSenderProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件发送提供者工厂实现
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class MqttEmailSenderProviderFactory implements EmailSenderProviderFactory {
    @Override
    public EmailSenderProvider create(KeycloakSession session) {
        final MqttEmailSenderProvider rslt = new MqttEmailSenderProvider(session);
        if (log.isDebugEnabled()) {
            log.info("Created successfully: {}", rslt.toString());
        }
        return rslt;
    }

    @Override
    public void init(Scope config) {
        if (log.isDebugEnabled()) {
            log.info("Initialization succeeded");  
        }     
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
