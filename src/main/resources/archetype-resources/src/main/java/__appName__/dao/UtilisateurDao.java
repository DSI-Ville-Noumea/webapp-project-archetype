#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.dao;

import java.util.List;

import ${package}.${appName}.domain.Utilisateur;

public interface UtilisateurDao {

	List<Utilisateur> findAllOrderBy(String orderByProperty);

	void save(Utilisateur utilisateur);

	void delete(Utilisateur utilisateur);

	Utilisateur findByIdentifiant(String identifiant);

}
