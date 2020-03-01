package br.com.home.message;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

/**
 * @author Eduardo Moritz
 * MessageBean injeta mensagens aos controllers
 *
 */
public class MessageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public void addMessage(String summary, String detail, int type) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		switch (type) {
		case 0:
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 1:
			PrimeFaces.current().dialog().showMessageDynamic(message);
			break;
		}
	}

}
