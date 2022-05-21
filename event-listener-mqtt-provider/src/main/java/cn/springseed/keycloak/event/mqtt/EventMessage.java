package cn.springseed.keycloak.event.mqtt;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.keycloak.events.Event;
import org.keycloak.util.JsonSerialization;

/**
 * 事件消息
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(use = Id.CLASS)
public class EventMessage extends Event {
    public static EventMessage create(Event event) {
		EventMessage msg = new EventMessage();
		msg.setClientId(event.getClientId());
		msg.setDetails(event.getDetails());
		msg.setError(event.getError());
		msg.setIpAddress(event.getIpAddress());
		msg.setRealmId(event.getRealmId());
		msg.setSessionId(event.getSessionId());
		msg.setTime(event.getTime());
		msg.setType(event.getType());
		msg.setUserId(event.getUserId());

		return msg;
	}

    public String toJson() throws IOException {
        return JsonSerialization.writeValueAsPrettyString(this);
    }  
}
