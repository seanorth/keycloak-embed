package cn.springseed.keycloak.extension.protocolmapper;

import java.util.ArrayList;
import java.util.List;

import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.IDToken;

/**
 * 生成数字，并加入到令牌中
 * 
 * @author PinWei Wan
 * @since 17.0.1
 */
public class LuckyNumberTokenMapper extends AbstractOIDCProtocolMapper
        implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {
    public static final String PROVIDER_ID = "s8d-oidc-lucky-number-mapper";
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();

    static final String LOWER_BOUND = "lowerBound";
    static final String UPPER_BOUND = "upperBound";

    static {
        configProperties.add(new ProviderConfigProperty(LOWER_BOUND, "Lower Bound", "Lower bound of lucky number.",
                ProviderConfigProperty.STRING_TYPE, 1));
        configProperties.add(new ProviderConfigProperty(UPPER_BOUND, "Upper Bound", "Upper bound of lucky number.",
                ProviderConfigProperty.STRING_TYPE, 100));

        OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
        OIDCAttributeMapperHelper.addIncludeInTokensConfig(configProperties, LuckyNumberTokenMapper.class);
    }

    @Override
    public String getDisplayCategory() {
        return TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return "Lucky Number";
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "Map a random lucky number between bounds to a token claim.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    protected void setClaim(IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession,
            KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx) {
        int lower = Integer.parseInt(mappingModel.getConfig().get(LOWER_BOUND));
        int upper = Integer.parseInt(mappingModel.getConfig().get(UPPER_BOUND));

        int luckyNumber = (int) (Math.random() * (upper - lower)) + lower;

        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, luckyNumber);
    }
}
