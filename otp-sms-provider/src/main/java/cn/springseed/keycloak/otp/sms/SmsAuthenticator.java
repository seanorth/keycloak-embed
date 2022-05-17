package cn.springseed.keycloak.otp.sms;

import java.util.Locale;

import javax.ws.rs.core.Response;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.common.util.SecretGenerator;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.sessions.AuthenticationSessionModel;

import cn.springseed.keycloak.mqtt.PublishService;

/**
 * 短信认证器
 *  
 * @author PinWei Wan
 * @since 1.0.0
 */
public class SmsAuthenticator implements Authenticator {
	private static final String SMS_TEMPLATE_CODE = "KK.OTP-SMS";
    private static final String TPL_CODE = "s8d-otp-sms.ftl";
    private static final String PHONE_NUMBER_ATTR = "phoneNumber";

    @Override
    public void close() {
        
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
		AuthenticatorConfigModel config = context.getAuthenticatorConfig();
		KeycloakSession session = context.getSession();
		UserModel user = context.getUser();
		ConfigProperties properties = ConfigProperties.of(config);

		String mobileNumber = user.getFirstAttribute(PHONE_NUMBER_ATTR);
		// mobileNumber of course has to be further validated on proper format, country code, ...

		int length = properties.getLength();
		int ttl = properties.getTtl();

		String code = SecretGenerator.getInstance().randomString(length, SecretGenerator.DIGITS);
		AuthenticationSessionModel authSession = context.getAuthenticationSession();
		authSession.setAuthNote("code", code);
		authSession.setAuthNote("ttl", Long.toString(System.currentTimeMillis() + (ttl * 1000L)));

		try {
			Locale locale = session.getContext().resolveLocale(user);
			// Theme theme = session.theme().getTheme(Theme.Type.LOGIN);
			// String smsAuthText = theme.getMessages(locale).getProperty("smsAuthText");
			// String smsText = String.format(smsAuthText, code, Math.floorDiv(ttl, 60));

			final Message message = Message.custom()
					.code(code)
					.ttl(Math.floorDiv(ttl, 60))
					.phoneNumber(mobileNumber)
					.templateCode(SMS_TEMPLATE_CODE).locale(locale);
			session.getProvider(PublishService.class).publish(properties.getTopic(), message.toJson());

			context.challenge(context.form().setAttribute("realm", context.getRealm()).createForm(TPL_CODE));
		} catch (Exception e) {
			context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR,
				context.form().setError("smsAuthSmsNotSent", e.getMessage())
					.createErrorPage(Response.Status.INTERNAL_SERVER_ERROR));
		}
        
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        String enteredCode = context.getHttpRequest().getDecodedFormParameters().getFirst("code");

		AuthenticationSessionModel authSession = context.getAuthenticationSession();
		String code = authSession.getAuthNote("code");
		String ttl = authSession.getAuthNote("ttl");

		if (code == null || ttl == null) {
			context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR,
				context.form().createErrorPage(Response.Status.INTERNAL_SERVER_ERROR));
			return;
		}

        // 验证码有效期判断
		boolean isValid = enteredCode.equals(code);
		if (isValid) {
			if (Long.parseLong(ttl) < System.currentTimeMillis()) {
				// 已过期(expired)
				context.failureChallenge(AuthenticationFlowError.EXPIRED_CODE,
					context.form().setError("smsAuthCodeExpired").createErrorPage(Response.Status.BAD_REQUEST));
			} else {
				// 有效的(valid)
				context.success();
			}
		} else {
			// 无效的(invalid)
			AuthenticationExecutionModel execution = context.getExecution();
			if (execution.isRequired()) {
				context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS,
					context.form().setAttribute("realm", context.getRealm())
						.setError("smsAuthCodeInvalid").createForm(TPL_CODE));
			} else if (execution.isConditional() || execution.isAlternative()) {
				context.attempted();
			}
		}
        
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return user.getFirstAttribute(PHONE_NUMBER_ATTR) != null;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        
    }
}
