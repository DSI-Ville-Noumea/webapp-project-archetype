#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ${package}.${appName}.dao.impl.UtilisateurDaoImpl;
import ${package}.${appName}.domain.Utilisateur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/applicationContext-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UtilisateurDaoImplTest {

	@Autowired
	UtilisateurDaoImpl		repository;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager	entityManager;

	@Test
	@Transactional
	public void findAllOrderBy_noResult() {

		// When
		List<Utilisateur> result = repository.findAllOrderBy("identifiant");

		// Then
		assertEquals(0, result.size());
		assertNotNull(result);
	}

	@Test
	@Transactional
	public void findAllOrderBy_Result() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");
		entityManager.persist(dir);
		Utilisateur dir2 = new Utilisateur();
		dir2.setIdentifiant("acode");
		entityManager.persist(dir2);

		// When
		List<Utilisateur> result = repository.findAllOrderBy("identifiant");

		// Then
		assertEquals(2, result.size());
		assertEquals(dir2.getIdentifiant(), result.get(0).getIdentifiant());
		assertEquals(dir.getIdentifiant(), result.get(1).getIdentifiant());
		assertNotNull(result);
	}

	@Test
	@Transactional
	public void findByIdentifiant_noResult() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");
		entityManager.persist(dir);
		Utilisateur dir2 = new Utilisateur();
		dir2.setIdentifiant("acode");
		entityManager.persist(dir2);

		// When
		Utilisateur result = repository.findByIdentifiant("codea");

		// Then
		assertEquals(null, result);
	}

	@Test
	@Transactional
	public void findByIdentifiant_Result() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");
		entityManager.persist(dir);
		Utilisateur dir2 = new Utilisateur();
		dir2.setIdentifiant("acode");
		entityManager.persist(dir2);

		// When
		Utilisateur result = repository.findByIdentifiant("code");

		// Then
		assertNotNull(result);
		assertEquals(dir.getIdentifiant(), result.getIdentifiant());
		assertNotNull(result);
	}

}
