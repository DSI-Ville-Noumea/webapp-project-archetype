#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class UtilisateurTest {

	@Test
	@Transactional
	public void contructeur() {

		Utilisateur uti = new Utilisateur();
		uti.setIdentifiant("nicno85");
		uti.setNom("NICOLAS");
		uti.setPrenom("Noémie");

		assertEquals("nicno85", uti.getIdentifiant());
		assertEquals("NICOLAS", uti.getNom());
		assertEquals("Noémie", uti.getPrenom());

	}

}
