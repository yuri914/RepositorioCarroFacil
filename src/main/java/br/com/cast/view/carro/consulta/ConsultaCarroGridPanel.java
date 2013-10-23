package br.com.cast.view.carro.consulta;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import br.com.cast.model.Carro;
import br.com.cast.view.carro.cadastro.CadastroCarroPage;

public abstract class ConsultaCarroGridPanel extends Panel {

	private static final long serialVersionUID = -8320411276346582466L;

	private AjaxLink<Carro> linkExcluir;

	public ConsultaCarroGridPanel(String id) {
		super(id);

		setOutputMarkupId(true);

	}

	public void setGridProdutos(List<Carro> listaCarros, Integer qtdPaginas) {
		DataView<Carro> repetidor = new DataView<Carro>("carros", new ListDataProvider<Carro>(listaCarros), qtdPaginas) {

			private static final long serialVersionUID = 3602598736257950767L;

			@Override
			protected void populateItem(final Item<Carro> item) {
				final Carro atual = item.getModelObject();
				item.add(new Label("marca", atual.getModelo().getMarca().getDescricao()));
				item.add(new Label("modelo", atual.getModelo().getDescricao()));
				item.add(new Label("cor", atual.getCor().getDescricao()));
				item.add(new Label("ano", atual.getAno().getDescricao()));
				item.add(new Label("valor", atual.getValor()));
				item.add(new Label("arCondicionado", atual.getArFormatado()));
				item.add(new Label("direcaoHidraulica", atual.getDirecaoFormatada()));
				item.add(new Label("vidrosEletricos", atual.getVidrosFormatados()));
				item.add(new Label("travasEletricas", atual.getTravasFormatadas()));

				linkExcluir = new AjaxLink<Carro>("excluir") {

					private static final long serialVersionUID = 8722069905093286827L;

					public void onClick(AjaxRequestTarget target) {
						excluirCarro(atual, target);
					}
				};
				item.add(linkExcluir);

				item.add(new AjaxLink<Carro>("alterar") {

					private static final long serialVersionUID = 6008926582237155758L;

					public void onClick(AjaxRequestTarget target) {
						setResponsePage(new CadastroCarroPage(atual, true));
					}
				});

				item.add(new AjaxLink<Void>("visualizar") {

					private static final long serialVersionUID = 570032314068595481L;

					public void onClick(AjaxRequestTarget target) {
						exibirImagem(atual, target);
					}

				});
			}

		};
		addOrReplace(new PagingNavigator("paginacao", repetidor));
		addOrReplace(repetidor);
	}

	protected abstract void excluirCarro(Carro atual, AjaxRequestTarget target);

	protected abstract void exibirImagem(Carro atual, AjaxRequestTarget target);
}
