#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import ${package}.${appName}.domain.Utilisateur;
import ${package}.${appName}.services.UtilisateurService;

@VariableResolver(DelegatingVariableResolver.class)
public class AjoutUtilisateurViewModel {

	private Utilisateur			utilisateur;

	@WireVariable
	private UtilisateurService	utilisateurService;


	@Command
	public void cancelUtilisateur(@BindingParam("win") Window window) {
		window.detach();
	}

	@AfterCompose
	public void afterCompose(@ExecutionArgParam("utilisateurCreation") Utilisateur uti) {
		// pour la popup de creation
		if (uti != null) {
			setUtilisateur(uti);
		}

	}

	@Command
	public void saveUtilisateur(@BindingParam("win") Window window) {
		List<String> listeErreur = new ArrayList<>();

		if (getUtilisateur().getNom() == null) {
			listeErreur.add("Le nom est obligatoire");
		}
		if (getUtilisateur().getPrenom() == null) {
			listeErreur.add("Le prénom est obligatoire");
		}
		if (getUtilisateur().getIdentifiant() == null) {
			listeErreur.add("Le login est obligatoire");
		}
		if (utilisateurService.findByIdentifiant(getUtilisateur().getIdentifiant()) != null) {
			listeErreur.add(String.format("L'identifiant %s existe déjà.", getUtilisateur().getIdentifiant()));
		}

		if (!listeErreur.isEmpty()) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("errors", listeErreur);
			Executions.createComponents("/pages/returnMessage.zul", null, map);
			return;
		}

		utilisateurService.save(getUtilisateur());

		BindUtils.postGlobalCommand(null, null, "refreshListeUtilisateur", null);
		window.detach();
		Clients.showNotification("Utilisateurs enregistrés", "info", null, "middle_center", 20);
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
