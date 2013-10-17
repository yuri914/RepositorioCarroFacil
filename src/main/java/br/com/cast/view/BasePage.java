package br.com.cast.view;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import br.com.cast.model.Carro;
import br.com.cast.view.carro.cadastro.CadastroCarroPage;
import br.com.cast.view.carro.consulta.ConsultaCarroPage;
import br.com.cast.view.index.InicialPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 972155251892859774L;

	public BasePage() {

	}

	public BasePage(String titulo) {

		add(new Link<Void>("linkInicio") {

			private static final long serialVersionUID = -2523782860411875740L;

			public void onClick() {
				setResponsePage(InicialPage.class);
			}

		});
		
		add(new Link<Void>("linkCadastro") {

			private static final long serialVersionUID = -2523782860411875740L;

			public void onClick() {
				setResponsePage(new CadastroCarroPage(new Carro(), false));
			}

		});

		add(new Link<Void>("linkConsulta") {

			private static final long serialVersionUID = -2523782860411875740L;

			public void onClick() {
				setResponsePage(ConsultaCarroPage.class);
			}

		});

	}

	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}

}
