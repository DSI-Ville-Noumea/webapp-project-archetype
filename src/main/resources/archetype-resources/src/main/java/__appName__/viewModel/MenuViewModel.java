#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.viewModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Div;

@VariableResolver(DelegatingVariableResolver.class)
public class MenuViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean afficheRecette;

	@Init
	public void initMenu() {
		// TODO gerer l'etiquette "recette
		// if (environnementService.isRecette()) {
		// setAfficheRecette(true);
		// } else {
		// setAfficheRecette(false);
		// }
		setAfficheRecette(true);

	}

	@Command
	@GlobalCommand
	public void changeEcran(@BindingParam("page") String page, @BindingParam("ecran") Div div) {
		div.getChildren().clear();
		Map<String, Div> args = new HashMap<String, Div>();
		args.put("div", div);
		Executions.createComponents(page + ".zul", div, args);
	}

	public boolean isAfficheRecette() {
		return afficheRecette;
	}

	public void setAfficheRecette(boolean afficheRecette) {
		this.afficheRecette = afficheRecette;
	}
}
