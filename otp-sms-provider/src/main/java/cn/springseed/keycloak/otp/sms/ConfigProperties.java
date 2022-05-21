package cn.springseed.keycloak.otp.sms;

import java.util.List;
import java.util.Map;

import org.keycloak.provider.ProviderConfigProperty;

import lombok.Getter;

/**
 * 属性
 * 
 * @author PinWei Wan
 * @since 17.0.1
 */
@Getter
public class ConfigProperties {
    private final int length;
    private final int ttl;

    public ConfigProperties(int length, int ttl) {
        this.length = length;
        this.ttl = ttl;
    }

    public static ConfigProperties readConfigVar(Map<String, String> config) {
        final int length = Integer.valueOf(Properties.LENGTH.resolveConfigVar(config));
        final int ttl = Integer.valueOf(Properties.TTL.resolveConfigVar(config));

        return new ConfigProperties(length, ttl);
    }

    @Getter
    public enum Properties {
        LENGTH("length", "Code length", "The number of digits of the generated code.", 6),
        TTL("ttl", "Time-to-live", "The time to live in seconds for the code to be valid.", 300);
    
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
                            ProviderConfigProperty.STRING_TYPE, TTL.getDefaultValue()));
        }
    }
}
