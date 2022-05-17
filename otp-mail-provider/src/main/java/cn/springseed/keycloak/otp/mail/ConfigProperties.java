package cn.springseed.keycloak.otp.mail;

import java.util.HashMap;
import java.util.Map;

import org.keycloak.models.AuthenticatorConfigModel;

/**
 * 属性
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class ConfigProperties {
    public final static String PRO_LENGTH = "length";
    public final static String PRO_TTL = "ttl";
    public final static String PRO_MQTT_TOPIC = "topic";

    private Map<String, String> cache = new HashMap<>();

    private ConfigProperties(Map<String, String> config) {
        this.cache.putAll(config);
    }

    public static ConfigProperties of(AuthenticatorConfigModel model) {
        return new ConfigProperties(model.getConfig());
    }

    public Integer getLength() {
        return  Integer.parseInt(cache.get(PRO_LENGTH));
    }

    public Integer getTtl() {
        return Integer.parseInt(cache.get(PRO_TTL));
    }

    public String getTopic() {
        return cache.get(PRO_MQTT_TOPIC);
    }
}
