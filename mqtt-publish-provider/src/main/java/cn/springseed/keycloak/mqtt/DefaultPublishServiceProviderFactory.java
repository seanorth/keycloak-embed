package cn.springseed.keycloak.mqtt;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * 默认的服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class DefaultPublishServiceProviderFactory implements PublishServiceProviderFactory {
    private PublishProperties properties;

    @Override
    public PublishService create(KeycloakSession session) {
        return PublishServiceFactory.get(properties, session);
    }

    @Override
    public void init(Scope config) {
        properties = PublishProperties.create().from(config);
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {        
    }

    @Override
    public void close() {
       
    }

    @Override
    public String getId() {
        return "s8d-default";
    }
    
}
