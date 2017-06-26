package ${package}.${appName}.services;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import ${package}.${appName}.dao.UtilisateurDao;
import ${package}.${appName}.domain.Utilisateur;
import ${package}.${appName}.services.impl.UtilisateurServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/applicationContext-test.xml" })
public class UtilisateurServiceTest {

	@Test
	public void findAllOrderBy_NoResult() {

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findAllOrderBy("code")).thenReturn(new ArrayList<>());

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		List<Utilisateur> res = service.findAllOrderBy("code");

		// Then
		assertNotNull(res);
		assertEquals(0, res.size());
	}

	@Test
	public void findAllOrderBy_Result() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");

		List<Utilisateur> list = new ArrayList<>();
		list.add(dir);

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findAllOrderBy("code")).thenReturn(list);

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		List<Utilisateur> res = service.findAllOrderBy("code");

		// Then
		assertNotNull(res);
		assertEquals(1, res.size());
		assertEquals(dir.getIdentifiant(), res.get(0).getIdentifiant());
	}

	@Test
	public void findByIdentifiant_NoResult() {

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findByIdentifiant("code")).thenReturn(null);
		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		Utilisateur res = service.findByIdentifiant("code");

		// Then
		assertEquals(null, res);
	}

	@Test
	public void findByIdentifiant_Result() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findByIdentifiant("code")).thenReturn(dir);

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		Utilisateur res = service.findByIdentifiant("code");

		// Then
		assertNotNull(res);
		assertEquals(dir.getIdentifiant(), res.getIdentifiant());
	}

	@Test
	public void save() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		service.save(dir);

		// Then
		Mockito.verify(utilisateurDao, Mockito.times(1)).save(Mockito.isA(Utilisateur.class));
	}

	@Test
	public void update_1Update() {
		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");
		dir.setId(1L);

		List<Utilisateur> list = new ArrayList<>();
		list.add(dir);

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findAllOrderBy("identifiant")).thenReturn(list);

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		service.update(list);

		// Then
		Mockito.verify(utilisateurDao, Mockito.times(1)).save(Mockito.isA(Utilisateur.class));
		Mockito.verify(utilisateurDao, Mockito.never()).delete(Mockito.isA(Utilisateur.class));
	}

	@Test
	public void update_1Delete() {
		Utilisateur dir2 = new Utilisateur();
		dir2.setIdentifiant("code2");
		dir2.setId(2L);

		Utilisateur dir = new Utilisateur();
		dir.setIdentifiant("code");
		dir.setId(1L);

		List<Utilisateur> list = new ArrayList<>();
		list.add(dir);
		list.add(dir2);

		UtilisateurDao utilisateurDao = Mockito.mock(UtilisateurDao.class);
		Mockito.when(utilisateurDao.findAllOrderBy("identifiant")).thenReturn(list);

		UtilisateurService service = new UtilisateurServiceImpl();
		ReflectionTestUtils.setField(service, "utilisateurDao", utilisateurDao);

		// When
		service.update(Arrays.asList(dir2));

		// Then
		Mockito.verify(utilisateurDao, Mockito.times(1)).save(Mockito.isA(Utilisateur.class));
		Mockito.verify(utilisateurDao, Mockito.times(1)).delete(Mockito.isA(Utilisateur.class));
	}


}
