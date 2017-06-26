package ${package}.${appName}.core;

import ${package}.${appName}.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de AuthenticationProvider pour l'authentification à
 * l'application
 * 
 */
public class NoumeaGroupAuthenticationProvider implements AuthenticationProvider {

	private static final Logger		LOGGER			= LoggerFactory.getLogger(NoumeaGroupAuthenticationProvider.class);

	/** Message par défaut */
	private static final String		DEFAULT_MESSAGE	= "Connection à l'application impossible";

	/** Authentication provider */
	private AuthenticationProvider	provider;

	/** Message d'erreur si échec d'authentification par le provider */
	private String					messageProvider	= DEFAULT_MESSAGE;

	/** Message d'erreur si échec d'authentification à l'application */
	private String					messageConf	= DEFAULT_MESSAGE;

	@Autowired
	private UtilisateurService utilisateurService;

	/**
	 * Override la méthode authenticate
	 * 
	 * @param authentication
	 *            Authentication
	 * @throws AuthenticationException
	 *             Exception d'authentification
	 */



	/**
	 * Override la méthode authenticate
	 *
	 * @param authentication Authentication
	 * @throws AuthenticationException Exception d'authentification
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Authentication authenticationResult = null;

		if (provider != null)
			try {
				authenticationResult = provider.authenticate(authentication);
			} catch (BadCredentialsException e) {
				LOGGER.error("Error lors de l'authentifation", e);
				throw new BadCredentialsException(messageProvider);
			}

		if (authenticationResult.getAuthorities().isEmpty()) {
			// MAUVAIS Group AD pour l'authentification
			throw new BadCredentialsException(messageConf);
		}

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		List<GrantedAuthority> roles = new ArrayList<>();

		return (provider == null) ? new UsernamePasswordAuthenticationToken(username, password, roles) : authenticationResult;
	}

	/**
	 * Override de la méthode supports
	 * 
	 * @param authentication
	 *            Authentication
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return (provider == null) ? true : provider.supports(authentication);
	}

	/**
	 * get Provider
	 * 
	 * @return provider
	 */
	public AuthenticationProvider getProvider() {
		return provider;
	}

	/**
	 * set Provider
	 * 
	 * @param provider
	 *            Provider to set
	 */
	public void setProvider(AuthenticationProvider provider) {
		this.provider = provider;
	}

	/**
	 * get MessageProvider
	 * 
	 * @return message
	 */
	public String getMessageProvider() {
		return messageProvider;
	}

	/**
	 * set MessageProvider
	 * 
	 * @param messageProvider
	 *            message to set
	 */
	public void setMessageProvider(String messageProvider) {
		this.messageProvider = messageProvider;
	}

	public String getMessageConf() {
		return messageConf;
	}

	public void setMessageConf(String messageConf) {
		this.messageConf = messageConf;
	}

}
