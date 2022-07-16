package cn.dubhe.keycloak.otp.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.keycloak.util.JsonSerialization;

import lombok.Getter;


/**
 * 消息实体
 *  
 * @author PinWei Wan
 * @since 17.0.1
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(use = Id.CLASS)
public class Message {
    /** 手机号 */
    private List<String> phoneNumbers = new ArrayList<>();
    /** 模板代码 */
    private String templateCode;
    /** 区域 */
    private Locale locale;
    /** 参数 */
    private final Map<String, String> parameters = new HashMap<>();

    private Message() {
    }
    
    public static Message custom() {
        return new Message();
    }

    public Message phoneNumber(final String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
        return this;
    }

    public Message code(final String code) {
        this.parameters.put("code", code);
        return this;
    }

    public Message ttl(final int ttl) {
        this.parameters.put("ttl", String.valueOf(ttl));
        return this;
    }

    public Message templateCode(final String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public Message locale(final Locale locale) {
        this.locale = locale;
        return this;
    }

    public String toJson() throws IOException {
        return JsonSerialization.writeValueAsPrettyString(this);
    }
}
