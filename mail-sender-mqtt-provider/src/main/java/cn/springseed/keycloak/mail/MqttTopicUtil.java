package cn.springseed.keycloak.mail;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * MQTT主题生成，格式：KEYCLAOK/MAIL/<REALM>
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@UtilityClass
public class MqttTopicUtil {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[^*#a-zA-Z0-9 _/-]");
	private static final Pattern SPACE = Pattern.compile(" ");
    private static final String TOPIC_KEY_PREFIX = "KEYCLAOK/MAIL/";


	public String getTopic(final String realmId) {
        String routingKey = TOPIC_KEY_PREFIX + realmId;
		return normalizeKey(routingKey).toUpperCase();
    }

	private String normalizeKey(CharSequence stringToNormalize) {
		return SPACE.matcher(SPECIAL_CHARACTERS.matcher(stringToNormalize).replaceAll(""))
				.replaceAll("_");
	}
}
