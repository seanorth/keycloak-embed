package cn.springseed.keycloak.mqtt;

import org.keycloak.models.KeycloakSession;

import lombok.experimental.UtilityClass;

/**
 * 服务工厂
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@UtilityClass
public class PublishServiceFactory {
    public PublishService get(PublishProperties config, KeycloakSession session) {
        if (config.isSimulation()) {
            return new SimulationPublishService();
        } else {
            return new DefaultPublishService(config);
        }
    }
}
