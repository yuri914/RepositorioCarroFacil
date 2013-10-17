package br.com.cast.view.carro.cadastro;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.cast.model.Ano;
import br.com.cast.model.Carro;
import br.com.cast.model.Cor;
import br.com.cast.model.Marca;
import br.com.cast.model.Modelo;
import br.com.cast.service.AnoService;
import br.com.cast.service.CarroService;
import br.com.cast.service.CorService;
import br.com.cast.service.MarcaService;
import br.com.cast.service.ModeloService;
import br.com.cast.view.BasePage;

public class CadastroCarroPage extends BasePage {

	private static final long serialVersionUID = -5474298184362672226L;

	@SpringBean
	private ModeloService serviceModelo;
	@SpringBean
	private MarcaService serviceMarca;
	@SpringBean
	private CorService serviceCor;
	@SpringBean
	private CarroService serviceCarro;
	@SpringBean
	private AnoService serviceAno;
	
	public CadastroCarroPage(Carro carro, boolean fluxo){
		super("Cadastro de Automoveis");
		add(new CadastroCarroForm("cadastroFormulario", carro, fluxo){

			private static final long serialVersionUID = -2469288309375780092L;

			protected boolean incluiCarro(Carro carro) {
				return serviceCarro.incluiCarro(carro);
			}

			protected List<Modelo> buscarModelos(Marca marca) {
				return serviceModelo.buscarModelos(marca);
			}

			protected List<Marca> buscarMarcas() {
				return serviceMarca.buscarMarcas();
			}

			protected List<Cor> buscarCores() {
				return serviceCor.buscarCores();
			}

			protected List<Ano> listarAno() {
				return serviceAno.consultarListaAno();
			}
			
		});
	}
}
