package br.com.cast.componentes.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.cast.model.Carro;

public class RelatorioFactory {
	
	public static void gerarRelatorio (HttpServletResponse httpResponse, 
	List<Carro> listaCarros, String reportURL, Map<String, Object> parametros) {
		
		HttpServletResponse response =  httpResponse;
		OutputStream os = null;
		
		try{
			
			//Carrega o relatório localizado no caminho especificado.
			JasperReport jr = (JasperReport) JRLoader.loadObject(reportURL);
			
			//Popula o Bean com a lista informada pro parametro.
			JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(listaCarros);
			
			//Preenche o relatório com os parametros e o Bean.
			JasperPrint jsPrint = JasperFillManager.fillReport(jr, parametros, jrBean);
			
			//Guarda o PDF em um array de bytes.
			byte[] pdf = JasperExportManager.exportReportToPdf(jsPrint);
			
			//recupera o fluxo de saida do response.
			os = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdf.length);
			//Seta algumas configurações do PDF entre elas o nome do arquivo gerado para download.
			response.setHeader("Content-disposition", "attachment; filename=Ofertas CarroFacil");
			//Escreve o PDF na fluxo de saida do Response.
			os.write(pdf);
			
			System.out.println("Relatório gerado com sucesso.");
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao gerar o relatório.");
		} finally{
			try {
				if (os != null){
					os.flush();
					os.close();
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}	
