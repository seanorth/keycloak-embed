package cn.springseed.keycloak.mqtt;

import java.util.Map;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class DefaultPublisherServiceProviderFactory implements PublisherServiceProviderFactory {
    private PublisherProperties properties;

    @Override
    public PublisherService create(KeycloakSession session) {
        final PublisherService rslt = new DefaultPublisherService(properties);

        if (log.isDebugEnabled()) {
            log.info("Created successfully: {}", rslt.toString());
        }
        return rslt;
    }

    @Override
    public void init(Scope config) {
        properties = PublisherProperties.create(config);

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
        return "s8d-default";
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        return properties.toMap();
    }
    
}
