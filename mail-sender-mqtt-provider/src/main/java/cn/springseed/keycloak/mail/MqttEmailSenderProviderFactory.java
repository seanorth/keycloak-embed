package cn.springseed.keycloak.mail;

import java.util.HashMap;
import java.util.Map;

import org.keycloak.Config.Scope;
import org.keycloak.email.EmailSenderProvider;
import org.keycloak.email.EmailSenderProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮件发送提供者工厂实现
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class MqttEmailSenderProviderFactory implements EmailSenderProviderFactory, ServerInfoAwareProviderFactory {
    private String topic;

    @Override
    public EmailSenderProvider create(KeycloakSession session) {
        final MqttEmailSenderProvider rslt = new MqttEmailSenderProvider(session, this.topic);
        if (log.isDebugEnabled()) {
            log.info("Created successfully: {}", rslt.toString());
        }
        return rslt;
    }

    @Override
    public void init(Scope config) {
        this.topic = resolveConfigVar(config, "topic", "mail.topic");
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
    
    private String resolveConfigVar(Scope config, String variableName, String defaultValue) {

        String value = defaultValue;
        if (config != null && config.get(variableName) != null) {
            value = config.get(variableName);
        }
        return value;
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        Map<String, String> rslt = new HashMap<>();
        rslt.put("topic", this.topic);
        return rslt;
    }
}
