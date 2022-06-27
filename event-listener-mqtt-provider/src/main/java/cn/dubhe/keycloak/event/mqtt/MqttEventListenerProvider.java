package cn.dubhe.keycloak.event.mqtt;

import java.io.IOException;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerTransaction;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

import cn.dubhe.keycloak.mqtt.PublisherService;

/**
 * 服务
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
public class MqttEventListenerProvider implements EventListenerProvider {
    private final KeycloakSession session;

    private final EventListenerTransaction tx = new EventListenerTransaction(this::publishAdminEvent, this::publishEvent);
    
    public MqttEventListenerProvider(KeycloakSession session) {
        this.session = session;
        session.getTransactionManager().enlistAfterCompletion(tx);
    }

    @Override
    public void close() {
       
    }

    @Override
    public void onEvent(Event event) {
        tx.addEvent(event);
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        tx.addAdminEvent(event, includeRepresentation);
    }
    
    private void publishEvent(Event event) {
        final EventMessage message = EventMessage.create(event);
        final String topic = MqttTopicUtil.getTopic(event);
        
        try {
            session.getProvider(PublisherService.class).publish(topic, message.toJson());
        } catch (IOException e) {
            throw new EventListenerMqttException(e);
        }
    }

    private void publishAdminEvent(AdminEvent event, boolean includeRepresentation) {
        final AdminEventMessage message = AdminEventMessage.create(event);
        final String topic = MqttTopicUtil.getTopic(event);
        
        try {
            session.getProvider(PublisherService.class).publish(topic, message.toJson());
        } catch (IOException e) {
            throw new EventListenerMqttException(e);
        }
    }

}
