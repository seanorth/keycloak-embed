package cn.springseed.keycloak.event.mqtt;

/**
 * 异常
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class EventListenerMqttException extends RuntimeException {
    public EventListenerMqttException(Throwable cause) {
        super(cause);
    } 
}
