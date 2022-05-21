package cn.springseed.keycloak.mqtt;

import java.util.Map;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * 默认的服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class DefaultPublisherServiceProviderFactory implements PublisherServiceProviderFactory {
    private ConfigProperties properties;
    private static PublisherService SINGLETON = null;

    @Override
    public PublisherService create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public void init(Scope config) {
        properties = ConfigProperties.create(config);
        SINGLETON = new DefaultPublisherService(properties);
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {        
    }

    @Override
    public void close() {
       
    }

    @Override
    public String getId() {
        return "s8d-server";
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        return properties.toMap();
    }
    
}
