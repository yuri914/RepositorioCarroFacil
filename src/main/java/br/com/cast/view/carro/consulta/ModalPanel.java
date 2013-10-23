package br.com.cast.view.carro.consulta;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.ByteArrayResource;

import br.com.cast.model.Carro;

public class ModalPanel extends Panel {

	private static final long serialVersionUID = -7700648460650557647L;

	public ModalPanel(String id, Carro carro) {
		super(id);

		ByteArrayResource resource = new ByteArrayResource("image/jpeg", carro.getImagem());
		Image img = new Image("img", resource);
		add(img);

	}

}
