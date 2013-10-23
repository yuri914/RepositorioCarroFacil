package br.com.cast.componentes.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioFactory {
	
	public static void gerarRelatorio (HttpServletResponse httpResponse, 
	List<?> lista, String reportURL, Map<String, Object> parametros, String PdfFileName) {
		
		HttpServletResponse response =  httpResponse;
		OutputStream os = null;
		
		try{
			os = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment; filename="+ PdfFileName);
			
			//Carrega o relatório localizado no caminho especificado.
			JasperReport jr = (JasperReport) JRLoader.loadObject(reportURL);
			
			//Popula o Bean com a lista informada pro parametro.
			JRBeanCollectionDataSource jrBean = new JRBeanCollectionDataSource(lista);
			
			//Preenche o relatório com os parametros e o Bean.
			JasperPrint jsPrint = JasperFillManager.fillReport(jr, parametros, jrBean);
			
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jsPrint);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.exportReport();
			
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
					e.printStackTrace();
				}
			}
		}
		
	}	
