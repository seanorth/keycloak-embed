package cn.springseed.keycloak.otp.sms;

import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

/**
 * 短信认证器工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class SmsAuthenticatorFactory implements AuthenticatorFactory {
    public static final String PROVIDER_ID = "s8d-otp-sms";

    @Override
    public Authenticator create(KeycloakSession session) {
        return new SmsAuthenticator();
    }

    @Override
    public void init(Scope config) {       
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        
    }

    @Override
    public void close() {
        
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return PROVIDER_ID.toUpperCase();
    }

    @Override
    public String getReferenceCategory() {
        return "otp";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Validates an OTP sent via SMS to the users mobile phone.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
		return List.of(
			new ProviderConfigProperty("length", "Code length", "The number of digits of the generated code.", ProviderConfigProperty.STRING_TYPE, 6),
			new ProviderConfigProperty("ttl", "Time-to-live", "The time to live in seconds for the code to be valid.", ProviderConfigProperty.STRING_TYPE, "300"),
			new ProviderConfigProperty("topic", "MQTT Topic", "MQTT topics are a form of addressing that allows MQTT clients to share information.", ProviderConfigProperty.STRING_TYPE, "sms.topic")
		);
    }
}
