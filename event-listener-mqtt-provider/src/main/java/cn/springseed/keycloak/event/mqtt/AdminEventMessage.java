package cn.springseed.keycloak.event.mqtt;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.keycloak.events.admin.AdminEvent;
import org.keycloak.util.JsonSerialization;

/**
 * Admin事件消息
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(use = Id.CLASS)
public class AdminEventMessage extends AdminEvent {

    public static AdminEventMessage create(AdminEvent adminEvent) {
		AdminEventMessage msg = new AdminEventMessage();
		msg.setAuthDetails(adminEvent.getAuthDetails());
		msg.setError(adminEvent.getError());
		msg.setOperationType(adminEvent.getOperationType());
		msg.setRealmId(adminEvent.getRealmId());
		msg.setRepresentation(adminEvent.getRepresentation());
		msg.setResourcePath(adminEvent.getResourcePath());
		msg.setResourceType(adminEvent.getResourceType());
		msg.setResourceTypeAsString(adminEvent.getResourceTypeAsString());
		msg.setTime(adminEvent.getTime());
		return msg;
	}

	public String toJson() throws IOException {
        return JsonSerialization.writeValueAsPrettyString(this);
    }  
}
