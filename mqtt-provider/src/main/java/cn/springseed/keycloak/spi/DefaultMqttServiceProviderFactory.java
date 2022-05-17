package cn.springseed.keycloak.spi;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * 默认的服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class DefaultMqttServiceProviderFactory implements MqttServiceProviderFactory {
    private MqttConfig cfg;

    @Override
    public MqttService create(KeycloakSession session) {
        return MqttServiceFactory.get(cfg, session);
    }

    @Override
    public void init(Scope config) {
        cfg = MqttConfig.createFromScope(config);
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {        
    }

    @Override
    public void close() {
       
    }

    @Override
    public String getId() {
        return "s8d-mqtt-client";
    }
    
}
