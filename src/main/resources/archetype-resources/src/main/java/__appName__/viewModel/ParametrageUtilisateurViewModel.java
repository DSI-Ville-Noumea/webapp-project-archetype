#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

import ${package}.${appName}.domain.Utilisateur;
import ${package}.${appName}.services.UtilisateurService;

@VariableResolver(DelegatingVariableResolver.class)
public class ParametrageUtilisateurViewModel {

	private static final Logger	LOGGER	= LoggerFactory.getLogger(ParametrageUtilisateurViewModel.class);

	@WireVariable
	private UtilisateurService	utilisateurService;

	private List<Utilisateur>	listeUtilisateur;


	@Init
	public void init() {
		refreshListeUtilisateur();
	}

	@Command
	@GlobalCommand
	@NotifyChange("*")
	public void refreshListeUtilisateur() {
		setListeUtilisateur(utilisateurService.findAllOrderBy("identifiant"));
	}

	@Command
	@NotifyChange("listeUtilisateur")
	public void createUtilisateur() {
		LOGGER.debug("--> createUtilisateur() " + getListeUtilisateur().size());

		// create a window programmatically and use it as a modal dialog.
		Map<String, Utilisateur> args = new HashMap<String, Utilisateur>();
		args.put("utilisateurCreation", new Utilisateur());
		Window win = (Window) Executions.createComponents("/pages/popup/ajoutUtilisateur.zul", null, args);
		win.doModal();
	}

	@Command
	@NotifyChange({ "listeUtilisateur" })
	public void save() {

		// on supprime les lignes vides de la liste
		List<Utilisateur> listeTemp = new ArrayList<>();
		for (Utilisateur uti : getListeUtilisateur()) {
			if (uti.getIdentifiant() != null) {
				listeTemp.add(uti);
			}
		}
		setListeUtilisateur(listeTemp);
		List<String> listeErreur = new ArrayList<>();

		for (Utilisateur uti : getListeUtilisateur()) {
			if (uti.getNom() == null) {
				listeErreur.add("Le nom est obligatoire");
			}
			if (uti.getPrenom() == null) {
				listeErreur.add("Le prénom est obligatoire");
			}
			if (uti.getIdentifiant() == null) {
				listeErreur.add("Le login est obligatoire");
			}

			Utilisateur user = utilisateurService.findByIdentifiant(uti.getIdentifiant());
			if (user != null && !Objects.equals(user.getId(), uti.getId())) {
				listeErreur.add(String.format("L'identifiant %s existe déjà.", uti.getIdentifiant()));
			}

		}
		if (!listeErreur.isEmpty()) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("errors", listeErreur);
			Executions.createComponents("/pages/returnMessage.zul", null, map);
			return;
		}

		utilisateurService.update(getListeUtilisateur());
		refreshListeUtilisateur();
		Clients.showNotification("Utilisateurs enregistrés", "info", null, "middle_center", 20);
	}

	@Command
	public void supprimerUtilisateur(@BindingParam("utilisateur") Utilisateur uti) {
		String messageConfirmation = "Voulez-vous vraiment supprimer cette ligne ?";
		final Utilisateur utiASupprimer = uti;

		Messagebox.show(messageConfirmation, "Suppression", new Messagebox.Button[] { Messagebox.Button.YES, Messagebox.Button.NO },
				Messagebox.QUESTION, new EventListener<Messagebox.ClickEvent>() {

					@Override
					public void onEvent(ClickEvent evt) {
						if (evt.getName().equals("onYes")) {
							getListeUtilisateur().remove(utiASupprimer);
							BindUtils.postNotifyChange(null, null, ParametrageUtilisateurViewModel.this, "listeUtilisateur");
						}
					}
				});
	}

	public List<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur == null ? new ArrayList<Utilisateur>() : listeUtilisateur;
	}

	public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}

}
