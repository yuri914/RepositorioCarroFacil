package br.com.cast.geradorRelatorios;

import java.io.File;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import br.com.cast.service.CarroService;

public class RelatorioFactory {

	public final static String s = File.separator;
	
	private static CarroService service = new CarroService();
	
	public static void main(String[] args) {
		
		HashMap<String, String> parametros = null;
		
		try{
			
			String jasperFileName = "C:\\Users\\Yuri\\Estudo\\WorkSpaces\\WorkSpace - CAST\\SistemaCarroFacil\\src\\main\\java\\br\\com\\cast\\componentes\\util\\relatorios\\RelatorioCarroFacil.jasper";
			String pdfFileName = "C:\\Users\\Yuri\\Estudo\\WorkSpaces\\WorkSpace - CAST\\SistemaCarroFacil\\src\\main\\java\\br\\com\\cast\\componentes\\util\\relatorios\\RelatorioCarroFacil.pdf"; 
			
			//Carrega o relatório localizado no caminho especificado
			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFileName);
			
			//Popula o Bean com a lista de carros.
			JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(service.listarCarros());
			
			//Preenche o relatório com os parametros e o Bean
			JasperPrint jsPrint = JasperFillManager.fillReport(jr, parametros, jrBean);
			
			//Exporta o relatorio para o formato PDF.
			JasperExportManager.exportReportToPdfFile(jsPrint, pdfFileName);
			
			//Exibe o Relatório após exportação.
			JasperViewer.viewReport(jsPrint, false);
			
			System.out.println("Relatório criado com sucesso.");
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao criar o relatório.");
		}
		
	}	
}
