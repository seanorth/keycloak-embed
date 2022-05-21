package cn.springseed.keycloak.event.mqtt;

import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * 服务工厂
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class MqttEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new MqttEventListenerProvider(session);
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
        return "s8d-event-listener-mqtt";
    }
    
}
