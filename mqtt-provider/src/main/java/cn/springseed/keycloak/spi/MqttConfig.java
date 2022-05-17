package cn.springseed.keycloak.spi;

import java.util.Locale;

import org.keycloak.Config.Scope;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置属性
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
@Slf4j
public class MqttConfig {
    /** 模拟标志，如果true，则将消息打印到控制台 */
    private boolean simulation;
    /** 服务地址 */
    private String serverUri;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 客户端ID */
    private String clientId;

    /** 自动重新连接 */
    private boolean automaticReconnect;
    /** 清楚会话 */
    private boolean cleanSession;
    /** 连接超时，单位秒 */
    private int connectionTimeout;

    public static MqttConfig createFromScope(Scope config) {
        final boolean simulation = Boolean.valueOf(resolveConfigVar(config, "simulation", "true"));
        if (simulation) {
            return MqttConfig.builder().simulation(simulation).build();
        }


        return MqttConfig.builder()
                .simulation(simulation)
                .serverUri(resolveConfigVar(config, "serverUri", "tcp://localhost:1883"))
                .username(resolveConfigVar(config, "password", ""))
                .password(resolveConfigVar(config, "password", ""))
                .automaticReconnect(Boolean.valueOf(resolveConfigVar(config, "automaticReconnect", "true")))
                .cleanSession(Boolean.valueOf(resolveConfigVar(config, "cleanSession", "true")))
                .connectionTimeout((Integer.valueOf(resolveConfigVar(config, "connectionTimeout", "10"))))
                .clientId(resolveConfigVar(config, "clientId", "keycloak"))
                .build();
    }

    private static String resolveConfigVar(Scope config, String variableName, String defaultValue) {

        String value = defaultValue;
        if (config != null && config.get(variableName) != null) {
            value = config.get(variableName);
        } else {
            // 尝试从环境变量中读取配置信息，如: KK_TO_RMQ_SERVERURI:
            String envVariableName = "S8D_MQTT_" + variableName.toUpperCase(Locale.ENGLISH);
            String env = System.getenv(envVariableName);
            if (env != null) {
                value = env;
            }
        }
        if (!"password".equals(variableName)) {
            log.info("mqtt-provider configuration: {}={}", variableName, value);
        }
        return value;

    }
}
