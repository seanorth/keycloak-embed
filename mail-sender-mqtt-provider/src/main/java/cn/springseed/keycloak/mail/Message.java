package cn.springseed.keycloak.mail;

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
 * @since 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@JsonTypeInfo(use = Id.CLASS)
@Getter
public class Message {
    /** 收件人 */
    private List<String> mailToList = new ArrayList<>();
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

    public Message mailTo(final String mailTo) {
        mailToList.add(mailTo);
        return this;
    }

    public Message firstName(final String firstName) throws IOException {
        this.paramters.put("firstName", firstName);
        return this;
    }

    public Message lastName(final String lastName) throws IOException {
        this.paramters.put("lastName", lastName);
        return this;
    }

    public Message subject(final String subject) {
        this.paramters.put("subject", subject);
        return this;
    }

    public Message textBody(final String textBody) {
        this.paramters.put("textBody", textBody);
        return this;
    }


    public Message htmlBody(final String htmlBody) {
        this.paramters.put("htmlBody", htmlBody);
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
