#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.services;

import java.util.List;

import ${package}.${appName}.domain.Utilisateur;

public interface UtilisateurService {

	List<Utilisateur> findAllOrderBy(String orderByProperty);

	void update(List<Utilisateur> listeUtilisateur);

	void save(Utilisateur utilisateur);

	Utilisateur findByIdentifiant(String identifiant);

}
