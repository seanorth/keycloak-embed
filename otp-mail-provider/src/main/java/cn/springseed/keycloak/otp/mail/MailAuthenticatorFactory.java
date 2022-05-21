package cn.springseed.keycloak.otp.mail;

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
 * @since 17.0.1
 */
public class MailAuthenticatorFactory implements AuthenticatorFactory {
    public static final String PROVIDER_ID = "s8d-otp-mail";
    private static final Authenticator SINGLETON = new MailAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
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
        return "Validates an OTP sent to the users e-mail.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ConfigProperties.Properties.providerConfigProperties();
    }
    
}
