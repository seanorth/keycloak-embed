package cn.springseed.keycloak.spi;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的服务
 * 
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class DefaultMqttService implements MqttService {
    private MqttConfig config;

    public DefaultMqttService(MqttConfig config) {
        this.config = config;
    }

    @Override
    public void close() {
    }

    @Override
    public void publish(String topic, String message) {
        if (log.isDebugEnabled()) {
            log.debug("Send message to MQTT server, topic:{}, message:{}", topic, message);
        }

        if (Objects.isNull(topic) || Objects.isNull(message)) {
            throw new IllegalArgumentException("Parameters 'topci' and 'message' are required");
        }

        try {
            MqttClient client = new MqttClient(config.getServerUri(), config.getClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(config.isAutomaticReconnect());
            options.setCleanSession(config.isCleanSession());
            options.setConnectionTimeout(config.getConnectionTimeout());

            final String username = config.getUsername();
            final String password = config.getPassword();
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) ) {
                options.setUserName(username);
                options.setPassword(password.toCharArray());
            }
            client.connect(options);
            MqttMessage payload = toPayload(message);
            payload.setQos(0);
            payload.setRetained(true);
            client.publish(topic, payload);
            client.disconnect();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private MqttMessage toPayload(String s) {
        byte[] payload = s.getBytes();
        return new MqttMessage(payload);
    }
}
