package cn.springseed.keycloak.mqtt;

import java.util.Locale;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.keycloak.Config.Scope;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置属性
 * 
 * <br>
 * 参考：https://www.emqx.com/zh/blog/how-to-use-mqtt-packet-to-implement-publishing-and-subscribing-functions
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@Getter
@Slf4j
public class DefaultPublisherProperties {
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
    /** 保持连接, 单位秒 */
    private int keepAliveInterval;

    private DefaultPublisherProperties() {
    }

    public static DefaultPublisherProperties create() {
        return new DefaultPublisherProperties();
    }

    public DefaultPublisherProperties from(Scope config) {
        this.serverUri = resolveConfigVar(config, "serverUri", "tcp://localhost:1883");
        this.username = resolveConfigVar(config, "password", "");
        this.password = resolveConfigVar(config, "password", "");
        this.automaticReconnect = Boolean.valueOf(resolveConfigVar(config, "automaticReconnect", "true"));
        this.cleanSession = Boolean.valueOf(resolveConfigVar(config, "cleanSession", "true"));
        this.connectionTimeout = Integer.valueOf(resolveConfigVar(config, "connectionTimeout", "10"));
        this.keepAliveInterval = Integer.valueOf(resolveConfigVar(config, "keepAliveInterval", "60"));
        this.clientId = resolveConfigVar(config, "clientId", "keycloak");

        log.info(this.toString());
        return this;
    }

    private String resolveConfigVar(Scope config, String variableName, String defaultValue) {

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
        return value;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString( this);
    }

    
}
