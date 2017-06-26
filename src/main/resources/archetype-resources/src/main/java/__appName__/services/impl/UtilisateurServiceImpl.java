#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${package}.${appName}.dao.UtilisateurDao;
import ${package}.${appName}.domain.Utilisateur;
import ${package}.${appName}.services.UtilisateurService;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Override
	public List<Utilisateur> findAllOrderBy(String orderByProperty) {
		return utilisateurDao.findAllOrderBy(orderByProperty);
	}

	@Override
	@Transactional
	public void update(List<Utilisateur> listeUtilisateur) {

		if (listeUtilisateur.isEmpty()) {
			return;
		}
		// on recupere la liste de toutes les utilisateurs
		List<Utilisateur> listeExistant = findAllOrderBy("identifiant");
		List<Utilisateur> listeASupprimer = new ArrayList<>();
		for (Utilisateur utiExist : listeExistant) {
			if (!listeUtilisateur.contains(utiExist)) {
				listeASupprimer.add(utiExist);
			}
		}
		// on fait la suppression
		for (Utilisateur uti : listeASupprimer) {
			utilisateurDao.delete(uti);
		}

		for (Utilisateur uti : listeUtilisateur) {
			utilisateurDao.save(uti);
		}
	}

	@Override
	@Transactional
	public void save(Utilisateur utilisateur) {
		utilisateurDao.save(utilisateur);
	}

	@Override
	public Utilisateur findByIdentifiant(String identifiant) {
		return utilisateurDao.findByIdentifiant(identifiant);
	}

}
