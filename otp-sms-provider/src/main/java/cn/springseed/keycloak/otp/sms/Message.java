package cn.springseed.keycloak.otp.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.keycloak.util.JsonSerialization;

import lombok.Getter;


/**
 * 消息实体
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Getter
public class Message {
    /** 手机号 */
    private List<String> phoneNumbers = new ArrayList<>();
    /** 模板代码 */
    private String templateCode;
    /** 区域 */
    private Locale locale;
    /** 参数 */
    private final Map<String, String> paramters = new HashMap<>();

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
        this.paramters.put("code", code);
        return this;
    }

    public Message ttl(final int ttl) {
        this.paramters.put("ttl", String.valueOf(ttl));
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
