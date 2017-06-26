#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${appName}.viewModel;

import java.util.List;

import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;

public class ReturnMessage {
	private List<String> errorsMessages = null;

	@Init
	public void init(@ExecutionArgParam("errors") List<String> errors) {
		setErrorsMessages(errors);
	}

	public List<String> getErrorsMessages() {
		return errorsMessages;
	}

	public void setErrorsMessages(List<String> errorsMessages) {
		this.errorsMessages = errorsMessages;
	}
}
