package cn.dubhe.keycloak.event.mqtt;

/**
 * 异常
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class EventListenerMqttException extends RuntimeException {
    public EventListenerMqttException(Throwable cause) {
        super(cause);
    } 
}
