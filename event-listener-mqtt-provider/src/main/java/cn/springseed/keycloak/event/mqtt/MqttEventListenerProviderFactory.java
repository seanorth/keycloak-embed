package cn.springseed.keycloak.event.mqtt;

import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class MqttEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        final MqttEventListenerProvider rslt = new MqttEventListenerProvider(session);
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
        return "s8d-event-listener-mqtt";
    }
    
}
