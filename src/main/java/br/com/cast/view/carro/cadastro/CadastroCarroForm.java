package br.com.cast.view.carro.cadastro;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.cast.model.Ano;
import br.com.cast.model.Carro;
import br.com.cast.model.Cor;
import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;
import br.com.cast.persistencia.DAOCarro;
import br.com.cast.view.index.InicialPage;

public abstract class CadastroCarroForm extends Form<Carro> {

	private static final long serialVersionUID = 3327579498420327099L;
	
	private FeedbackPanel feedback;
	private DropDownChoice<Marca> dropMarca;
	private DropDownChoice<Modelo> dropModelo;
	private DropDownChoice<Cor> dropCor;
	private CheckBox checkArCondicionado;
	private CheckBox checkDirecaoHidraulica;
	private CheckBox checkVtEletrico;
	private TextField<Double> valor;
	private DropDownChoice<Ano> ano;
	private Label containerMarca;
	private Label containerModelo;
	private Label containerAno;
	private FileUploadField imagem;

	public CadastroCarroForm(String id, final Carro carro, boolean fluxo) {
		super(id);
		
		feedback = new FeedbackPanel("mensagens");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
		dropModelo = new DropDownChoice<Modelo>("modelo");
		dropModelo.setChoiceRenderer(new ChoiceRenderer<Modelo>("descricao"));
		dropModelo.setModel(new PropertyModel<Modelo>(carro, "modelo"));
		dropModelo.setOutputMarkupId(true);
		dropModelo.setRequired(true);
		add(dropModelo);
		
		valor = new TextField<Double>("valor");
		valor.setModel(new PropertyModel<Double>(carro, "valor"));
		valor.setRequired(true);
		add(valor);
		
		dropCor = new DropDownChoice<Cor>("cor");
		dropCor.setChoices(buscarCores());
		dropCor.setChoiceRenderer(new ChoiceRenderer<Cor>("descricao"));
		dropCor.setModel(new PropertyModel<Cor>(carro, "cor"));
		dropCor.setOutputMarkupId(true);
		dropCor.setRequired(true);
		add(dropCor);
		
		ano = new DropDownChoice<Ano>("ano");
		ano.setChoices(listarAno());
		ano.setModel(new PropertyModel<Ano>(carro, "ano"));
		ano.setChoiceRenderer(new ChoiceRenderer<Ano>("descricao"));
		ano.setRequired(true);
		add(ano);
		
		checkArCondicionado = new CheckBox("arCondicionado");
		checkArCondicionado.setOutputMarkupId(true);
		checkArCondicionado.setModel(new PropertyModel<Boolean>(carro, "arCondicionado"));
		add(checkArCondicionado);
		
		checkDirecaoHidraulica = new CheckBox("direcaoHidraulica");
		checkDirecaoHidraulica.setOutputMarkupId(true);
		checkDirecaoHidraulica.setModel(new PropertyModel<Boolean>(carro, "direcaoHidraulica"));
		add(checkDirecaoHidraulica);
		
		checkVtEletrico = new CheckBox("vtEletrico");
		checkVtEletrico.setOutputMarkupId(true);
		checkVtEletrico.setModel(new PropertyModel<Boolean>(carro, "vtEletrico"));
		add(checkVtEletrico);
		
		dropMarca = new DropDownChoice<Marca>("marca");
		dropMarca.setChoices(buscarMarcas());
		dropMarca.setChoiceRenderer(new ChoiceRenderer<Marca>("descricao"));
		dropMarca.setModel(new Model<Marca>());
		dropMarca.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			
			private static final long serialVersionUID = 5479262131988513287L;

			protected void onUpdate(AjaxRequestTarget target) {
				if(dropMarca.getModelObject() != null) {
					dropModelo.setChoices(buscarModelos(dropMarca.getModelObject()));
					target.add(dropModelo);
				}
			}
		});
		add(dropMarca);
		
		
		/*imagem = new FileUploadField("imagem");
		DAOCarro daoCarro = new DAOCarro();
		imagem.setDefaultModelObject(daoCarro.buscarCarro().getImagem());
		add(imagem);*/
		
		add(new AjaxButton("salvar"){
			private static final long serialVersionUID = 2450953576382428406L;
			
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				//carro.setImagem(imagem.getFileUpload().getBytes());
				if(incluiCarro(carro)){
					success("Cadastro/Alteração realizada com sucesso.");
					target.add(feedback, form);
				} else {
					error("Ocorreu um erro ao cadastrar o produto.");
					target.add(feedback);
				}
			}
			
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
			
		});
		
		add(new AjaxButton("voltar"){

			private static final long serialVersionUID = 1553879011099104337L;
			
			protected void onConfigure() {
				setDefaultFormProcessing(false);
			}
			
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(InicialPage.class);
			}
		});
		
		//Containers com Labels para serem escondidos ao iniciar o fluxo de alteração.
		add(containerMarca = new Label("containerMarca"));
		containerMarca.setDefaultModel(Model.of("Marca"));
		add(containerModelo = new Label("containerModelo"));
		containerModelo.setDefaultModel(Model.of("Modelo"));
		add(containerAno = new Label("containerAno"));
		containerAno.setDefaultModel(Model.of("Ano"));
		
		if (null != carro.getId()){
			esconderCampos();
		}
	}
	
	protected abstract List<Ano> listarAno();
	protected abstract boolean incluiCarro(Carro carro);
	protected abstract List<Modelo> buscarModelos(Marca marca);
	protected abstract List<Marca> buscarMarcas();
	protected abstract List<Cor> buscarCores();

	private void esconderCampos() {
		dropMarca.setVisible(false);
		dropModelo.setVisible(false);
		ano.setVisible(false);
		containerAno.setVisible(false);
		containerMarca.setVisible(false);
		containerModelo.setVisible(false);
	}
}
