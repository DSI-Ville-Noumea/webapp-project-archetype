package ${package}.${appName}.core;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Overriden Authorities Populator for Ldap authentication with spring security
 * This class loads roles from the database i/o using LDAP groups
 */
@Service
public class NoumeaUserAuthoritiesPopulator implements LdapAuthoritiesPopulator {


	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String login) {

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("USER"));

		return roles;
	}

}
