package cn.springseed.keycloak.otp.mail;

import java.util.List;
import java.util.Map;

import org.keycloak.provider.ProviderConfigProperty;

import lombok.Getter;

/**
 * 属性
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@Getter
public class ConfigProperties {
    private final int length;
    private final int ttl;
    private final String topic;

    public ConfigProperties(int length, int ttl, String topic) {
        this.length = length;
        this.ttl = ttl;
        this.topic = topic;
    }

    public static ConfigProperties readConfigVar(Map<String, String> config) {
        final int length = Integer.valueOf(Properties.LENGTH.resolveConfigVar(config));
        final int ttl = Integer.valueOf(Properties.TTL.resolveConfigVar(config));
        final String topic = Properties.TOPIC.resolveConfigVar(config);

        return new ConfigProperties(length, ttl, topic);
    }

    @Getter
    public enum Properties {
        LENGTH("length", "Code length", "The number of digits of the generated code.", 6),
        TTL("ttl", "Time-to-live", "The time to live in seconds for the code to be valid.", 300),
        TOPIC("topic", "MQTT Topic", "MQTT topics are a form of addressing that allows MQTT clients to share information.",
                "sms.topic");
    
        private final String code;
        private final String label;
        private final String helpText;
        private final Object defaultValue;
    
        private Properties(String code, String label, String helpText, Object defaultValue) {
            this.code = code;
            this.label = label;
            this.helpText = helpText;
            this.defaultValue = defaultValue;
        }
    
        public String resolveConfigVar(final Map<String, String> config) {
            String value = this.defaultValue.toString();
            if (config != null && config.get(this.code) != null) {
                value = config.get(this.code);
            }
            return value;
        }

        /**
         * 生成 {@link ProviderConfigProperty} 列表
         * @return
         */
        public static List<ProviderConfigProperty> providerConfigProperties() {
            return List.of(
                    new ProviderConfigProperty(LENGTH.getCode(), LENGTH.getLabel(), LENGTH.getHelpText(),
                            ProviderConfigProperty.STRING_TYPE, LENGTH.getDefaultValue()),
                    new ProviderConfigProperty(TTL.getCode(), TTL.getLabel(), TTL.getHelpText(),
                            ProviderConfigProperty.STRING_TYPE, TTL.getDefaultValue()),
                    new ProviderConfigProperty(TOPIC.getCode(), TOPIC.getLabel(), TOPIC.getHelpText(),
                            ProviderConfigProperty.STRING_TYPE, TOPIC.getDefaultValue()));
        }
    }
}
