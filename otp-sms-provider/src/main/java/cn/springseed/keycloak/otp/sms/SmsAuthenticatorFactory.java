package cn.springseed.keycloak.otp.sms;

import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信认证器工厂
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
@Slf4j
public class SmsAuthenticatorFactory implements AuthenticatorFactory {
    public static final String PROVIDER_ID = "s8d-otp-sms";
    private static final Authenticator SINGLETON = new SmsAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        if (log.isDebugEnabled()) {
            log.info("Created successfully: {}", SINGLETON.toString());
        }
        return SINGLETON;        
    }

    @Override
    public void init(Scope config) {
        if (log.isDebugEnabled()) {
            log.info("Initialization succeeded");    
        }    
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
		return ConfigProperties.Properties.providerConfigProperties();
    }
}
