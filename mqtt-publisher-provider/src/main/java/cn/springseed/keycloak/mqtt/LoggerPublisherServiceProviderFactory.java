package cn.springseed.keycloak.mqtt;

import java.util.Map;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志的服务提供者工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class LoggerPublisherServiceProviderFactory implements PublisherServiceProviderFactory {
    private static final PublisherService SINGLETON = new LoggerPublisherService();

    @Override
    public PublisherService create(KeycloakSession session) {
        if (log.isDebugEnabled()) {
            log.info("Created successfully: {}", SINGLETON.toString());
        }
        return SINGLETON;
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
        return "s8d-logger";
    }

    @Override
    public Map<String, String> getOperationalInfo() {
        return null;
    }
    
}
