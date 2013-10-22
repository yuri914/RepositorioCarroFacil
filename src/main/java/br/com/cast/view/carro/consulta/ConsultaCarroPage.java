package br.com.cast.view.carro.consulta;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.model.Carro;
import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;
import br.com.cast.service.CarroService;
import br.com.cast.service.MarcaService;
import br.com.cast.service.ModeloService;
import br.com.cast.view.BasePage;

public class ConsultaCarroPage extends BasePage {

	private static final long serialVersionUID = 7145045138034313016L;

	@SpringBean
	private ModeloService serviceModelo;
	@SpringBean
	private MarcaService serviceMarca;
	@SpringBean
	private CarroService serviceCarro;
	
	public ConsultaCarroPage(String mensagem){
		super("Consulta de Veiculos");
		add(new ConsultaCarroForm("consultaFormulario", mensagem){

			private static final long serialVersionUID = 474947395775203059L;

			@Override
			protected List<Carro> buscarCarroFiltro(Carro carroConsulta, Marca marca) {
				return serviceCarro.buscarCarroFiltro(carroConsulta, marca);
			}

			@Override
			protected List<Modelo> buscarModelos(Marca marca) {
				return serviceModelo.buscarModelos(marca);
			}

			@Override
			protected List<Marca> buscarMarcas() {
				return serviceMarca.buscarMarcas();
			}

			@Override
			protected List<Carro> listarCarros() {
				return serviceCarro.listarCarros();
			}

			@Override
			protected void excluirCarroBanco(Carro carro) {
				serviceCarro.excluirCarro(carro);
			}
		});
	}
	
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("css/bootstrap.css"));
	}
}
