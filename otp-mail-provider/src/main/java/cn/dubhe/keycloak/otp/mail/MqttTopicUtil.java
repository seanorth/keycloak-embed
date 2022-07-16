package cn.dubhe.keycloak.otp.mail;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * MQTT主题生成，格式：KEYCLOAK/MAIL/OTP/<REALM>
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@UtilityClass
public class MqttTopicUtil {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[^*#a-zA-Z0-9 _/-]");
	private static final Pattern SPACE = Pattern.compile(" ");
    public static final String TOPIC_KEY_PREFIX = "KEYCLOAK/MAIL/OTP/";

    public String getTopic(final String realmId) {
        String topic = TOPIC_KEY_PREFIX + realmId;
		return normalizeKey(topic).toUpperCase();
    }

	private String normalizeKey(CharSequence stringToNormalize) {
		return SPACE.matcher(SPECIAL_CHARACTERS.matcher(stringToNormalize).replaceAll(""))
				.replaceAll("_");
	}
}
