package br.com.cast.view.carro.consulta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.cast.componentes.util.RelatorioFactory;
import br.com.cast.model.Carro;
import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;

public abstract class ConsultaCarroForm extends Form {

	private static final Integer QTD_POR_PAGINAS = 5;
	private static final long serialVersionUID = -3692821295970874252L;
	private DropDownChoice<Marca> dropMarca;
	private DropDownChoice<Modelo> dropModelo;
	private DropDownChoice<Integer> dropRegistrosPaginacao;
	private CheckBox checkArCondicionado;
	private CheckBox checkDirecaoHidraulica;
	private CheckBox checkVtEletrico;
	private ConsultaCarroGridPanel gridPanelCarro;
	private Carro carroConsulta = new Carro();
	private FeedbackPanel feedback;

	public ConsultaCarroForm(String id) {
		super(id);

		feedback = new FeedbackPanel("mensagens");
		feedback.setOutputMarkupId(true);
		add(feedback);

		dropModelo = new DropDownChoice<Modelo>("modeloConsulta");
		dropModelo.setChoiceRenderer(new ChoiceRenderer<Modelo>("descricao"));
		dropModelo.setModel(new PropertyModel<Modelo>(carroConsulta, "modelo"));
		dropModelo.setOutputMarkupId(true);
		add(dropModelo);

		dropRegistrosPaginacao = new DropDownChoice<Integer>("registrosPagina");
		List<Integer> qtdPaginas = new ArrayList<Integer>(Arrays.asList(3, 5, 8, 10));
		dropRegistrosPaginacao.setModel(new Model<Integer>());
		dropRegistrosPaginacao.setChoices(qtdPaginas);
		dropRegistrosPaginacao.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			private static final long serialVersionUID = -8523155811250146821L;

			protected void onUpdate(AjaxRequestTarget target) {
				if (dropRegistrosPaginacao.getModelObject() != null) {
					gridPanelCarro.getGridProdutos(listarCarros(), dropRegistrosPaginacao.getModelObject());
					target.add(gridPanelCarro);
				}
			}
		});
		add(dropRegistrosPaginacao);

		dropMarca = new DropDownChoice<Marca>("marcaConsulta");
		dropMarca.setChoices(buscarMarcas());
		dropMarca.setChoiceRenderer(new ChoiceRenderer<Marca>("descricao"));
		dropMarca.setModel(new Model<Marca>());
		dropMarca.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			private static final long serialVersionUID = -8523155811250146821L;

			protected void onUpdate(AjaxRequestTarget target) {
				if (dropMarca.getModelObject() != null) {
					dropModelo.setChoices(buscarModelos(dropMarca.getModelObject()));
					target.add(dropModelo);
				}
			}
		});
		add(dropMarca);

		checkArCondicionado = new CheckBox("arCondicionado");
		checkArCondicionado.setModel(new PropertyModel<Boolean>(carroConsulta, "arCondicionado"));
		add(checkArCondicionado);

		checkDirecaoHidraulica = new CheckBox("direcaoHidraulica");
		checkDirecaoHidraulica.setModel(new PropertyModel<Boolean>(carroConsulta, "direcaoHidraulica"));
		add(checkDirecaoHidraulica);

		checkVtEletrico = new CheckBox("vtEletrico");
		checkVtEletrico.setModel(new PropertyModel<Boolean>(carroConsulta, "vtEletrico"));
		add(checkVtEletrico);

		gridPanelCarro = new ConsultaCarroGridPanel("panelConsulta") {

			private static final long serialVersionUID = -8651320159597446128L;

			@Override
			protected void excluirCarro(Carro atual, AjaxRequestTarget target) {
				excluirCarroBanco(atual);
				gridPanelCarro.getGridProdutos(listarCarros(), QTD_POR_PAGINAS);
				target.add(gridPanelCarro);
			}
		};
		gridPanelCarro.getGridProdutos(listarCarros(), QTD_POR_PAGINAS);
		add(gridPanelCarro);

		add(new AjaxButton("buscar") {

			private static final long serialVersionUID = -1689331858745215108L;

			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				List<Carro> listaCarrosFiltrados = buscarCarroFiltro(carroConsulta, dropMarca.getModelObject());
				gridPanelCarro.getGridProdutos(listaCarrosFiltrados, QTD_POR_PAGINAS);
				target.add(gridPanelCarro);
			}
		});

		add(new AjaxButton("buscarTodos") {

			private static final long serialVersionUID = 6292852912816686898L;

			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				gridPanelCarro.getGridProdutos(listarCarros(), 10);
				target.add(gridPanelCarro);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {

			}
		});

		add(new Button("btRelatorio") {

			private static final long serialVersionUID = 5428718389845909496L;

			@Override
			public void onSubmit() {
				String relatorioURL = "C:\\Users\\Yuri\\Estudo\\WorkSpaces\\Git WorkSpace\\SistemaCarroFacil\\src\\main\\java\\br\\com\\cast\\componentes\\util\\relatorios\\RelatorioCarroFacil.jasper";
				String pdfFileName = "Relatorio";
				RelatorioFactory.gerarRelatorio((HttpServletResponse) getResponse().getContainerResponse(), listarCarros(), relatorioURL, null, pdfFileName);
			}
		});

	}

	protected abstract void excluirCarroBanco(Carro atual);

	protected abstract List<Carro> buscarCarroFiltro(Carro carroConsulta, Marca marca);

	protected abstract List<Modelo> buscarModelos(Marca modelObject);

	protected abstract List<Marca> buscarMarcas();

	protected abstract List<Carro> listarCarros();
}
