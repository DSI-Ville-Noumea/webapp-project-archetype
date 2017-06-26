#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ${package}.${appName}.dao.UtilisateurDao;
import ${package}.${appName}.domain.Utilisateur;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utilisateur> findAllOrderBy(String orderByProperty) {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct(u) from Utilisateur u ");
		sb.append("order by u." + orderByProperty);

		TypedQuery<Utilisateur> query = entityManager.createQuery(sb.toString(), Utilisateur.class);

		return query.getResultList();
	}

	@Override
	public void save(Utilisateur uti) {
		if (uti.getId() == null) {
			entityManager.persist(uti);
		} else {
			entityManager.merge(uti);
		}
	}

	@Override
	public void delete(Utilisateur uti) {
		entityManager.remove(uti);

	}

	@Override
	public Utilisateur findByIdentifiant(String identifiant) {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct(u) from Utilisateur u ");
		sb.append("where u.identifiant=:identifiant ");

		TypedQuery<Utilisateur> query = entityManager.createQuery(sb.toString(), Utilisateur.class);
		query.setParameter("identifiant", identifiant);

		return query.getSingleResult();
	}

}
