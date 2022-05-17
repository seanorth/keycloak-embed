package cn.springseed.keycloak.spi;

import org.keycloak.models.KeycloakSession;

import lombok.experimental.UtilityClass;

/**
 * 服务工厂
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@UtilityClass
public class MqttServiceFactory {
    public MqttService get(MqttConfig config, KeycloakSession session) {
        if (config.isSimulation()) {
            return new SimulationMqttService();
        } else {
            return new DefaultMqttService(config);
        }
    }
}
