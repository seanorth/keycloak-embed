package cn.springseed.keycloak.event.mqtt;

import java.util.regex.Pattern;

import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

import lombok.experimental.UtilityClass;

/**
 * MQTT主题生成
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@UtilityClass
public class MqttTopicUtil {
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[^*#a-zA-Z0-9 _/-]");
	private static final Pattern SPACE = Pattern.compile(" ");
    private static final String TOPIC_KEY_PREFIX = "KEYCLAOK/EVENT/";


	public String getTopic(final AdminEvent event) {
		String topic = TOPIC_KEY_PREFIX
				+ "ADMIN"
				+ "/" + event.getRealmId()
				+ "/" + (event.getError() != null ? "ERROR" : "SUCCESS")
				+ "/" + event.getResourceTypeAsString()
				+ "/" + event.getOperationType().toString();
		return normalizeKey(topic).toUpperCase();
    }

	public String getTopic(final Event event) {
		String topic = TOPIC_KEY_PREFIX
					+ "CLIENT"
					+ "/" + event.getRealmId()
					+ "/" + (event.getError() != null ? "ERROR" : "SUCCESS")
					+ "/" + event.getClientId()
					+ "/" + event.getType();
		return normalizeKey(topic).toUpperCase();
    }

	private String normalizeKey(CharSequence stringToNormalize) {
		return SPACE.matcher(SPECIAL_CHARACTERS.matcher(stringToNormalize).replaceAll(""))
				.replaceAll("_");
	}
}
