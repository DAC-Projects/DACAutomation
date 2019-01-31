package com.selenium.testevidence;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import resources.CurrentState;

/**
 * Generate the test evidence in PDF file
 * 
 * @author Elias Nogueira <elias.nogueira@gmail.com>
 */
public class GenerateEvidenceReport {

	/**
	 * Generate evidence in PDF file. The evidence will be save on home dir on a
	 * folder called by the name of the project
	 * 
	 * @param list
	 *            list of evidence made of an text and image
	 * @param reportName
	 *            report name
	 * @param exception
	 *            exception text that are throwing by Java
	 * @throws IOException
	 *             if occurs any problem with the directory
	 */
	@Deprecated
	public static void generatePDFEvidence(List<SeleniumEvidence> list, String reportName, String tester,
			String project, String exception) throws IOException {
		List<SeleniumEvidence> data = list;

		Properties properties = SeleniumEvidenceUtils.loadProperties();
		String evidenceDir = System.getProperty("user.dir") + System.getProperty("file.separator")
				+ properties.getProperty("evidence.dir") + System.getProperty("file.separator");

		try {

			String companyImage = properties.getProperty("image.company.path");
			String customerImage = properties.getProperty("image.customer.path");

			BufferedImage imageCompany;
			BufferedImage imageClient;

			if (companyImage == null || companyImage.equals("null")) {
				imageCompany = null;
			} else {
				imageCompany = ImageIO.read(new File(companyImage));
			}

			if (customerImage == null || customerImage.equals("null")) {
				imageClient = null;
			} else {
				imageClient = ImageIO.read(new File(customerImage));
			}

			if (reportName == null) {
				reportName = "";
			}

			if (tester == null) {
				tester = "";
			}

			if (project == null) {
				project = "";
			}

			if (exception == null) {
				exception = "";
			}

			Map<String, Object> parameters = new HashMap<String, Object>();
			if (exception != null) {
				parameters.put("SEL_EXCEPTION", exception);
			}

			parameters.put("SEL_COMPANY_LOGO", imageCompany);
			parameters.put("SEL_CUSTOMER_LOGO", imageClient);
			parameters.put("SEL_PROJECT", project);
			parameters.put("SEL_TESTER", tester);
			
			parameters.put("SEL_LABEL_EVINDENCE_TITLE", properties.getProperty("label.evidenceReport"));
			parameters.put("SEL_LABEL_PROJECT", properties.getProperty("label.projetc"));
			parameters.put("SEL_LABEL_TESTER", properties.getProperty("label.tester"));
			parameters.put("SEL_LABEL_STATUS", properties.getProperty("label.status"));
			parameters.put("SEL_LABEL_PASS", properties.getProperty("label.status.pass"));
			parameters.put("SEL_LABEL_FAILED", properties.getProperty("label.status.failed"));
			parameters.put("SEL_LABEL_EVIDENCE_REPORT", properties.getProperty("label.evidenceReport"));
			parameters.put("SEL_LABEL_DATE", properties.getProperty("label.date"));
			parameters.put("SEL_LABEL_FOOTER", properties.getProperty("label.footer"));
			parameters.put("SEL_LABEL_ERROR_DETAIL", properties.getProperty("label.errorDetail"));
			parameters.put("SEL_LABEL_PAGE", properties.getProperty("label.page"));
			parameters.put("SEL_LABEL_COMPANY_NAME", properties.getProperty("label.company.name"));

			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);
			JasperReport jasperReport = JasperCompileManager.compileReport(properties.getProperty("evidence.file"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);

			JasperExportManager.exportReportToPdfFile(jasperPrint, evidenceDir + reportName + ".pdf");

		} catch (SecurityException ex) {
			ex.printStackTrace();
		} catch (JRException jre) {
			jre.printStackTrace();
		}
	}

	/**
	 * Generate an evidence report based on EvidenceType (DOC, PDF, HTML)
	 * 
	 * @param evidenceReport
	 *            and EvidenceReport object with basic informations
	 * @param reportType
	 *            filetype
	 * @return
	 * @throws IOException
	 *             if any problem with the files (jasper, EvidenceType) or directory
	 *             occurs
	 */
	public static JasperPrint generareEvidenceReport(EvidenceReport evidenceReport, EvidenceType reportType)
			throws IOException {
		List<SeleniumEvidence> data = evidenceReport.getEvidenceList();
		JasperPrint jasperPrint = null;
		Properties properties = SeleniumEvidenceUtils.loadProperties();
		String evidenceDir = System.getProperty("user.dir") + System.getProperty("file.separator")
				+ properties.getProperty("evidence.dir") + System.getProperty("file.separator");

		createEvidenceDir(evidenceDir);

		try {

			String companyImage = properties.getProperty("image.company.path");
			String customerImage = properties.getProperty("image.customer.path");

			BufferedImage imageCompany;
			BufferedImage imageClient;

			if (companyImage == null || companyImage.equals("null")) {
				imageCompany = null;
			} else {
				imageCompany = ImageIO.read(new File(companyImage));
			}

			if (customerImage == null || customerImage.equals("null")) {
				imageClient = null;
			} else {
				imageClient = ImageIO.read(new File(customerImage));
			}

			String reportName = evidenceReport.getReportName();
			if (reportName == null) {
				reportName = "";
			}

			String tester = evidenceReport.getTester();
			if (tester == null) {
				tester = "";
			}

			String project = evidenceReport.getProject();
			if (project == null) {
				project = "";
			}

			String exception = evidenceReport.getExceptionString();
			if (exception == null) {
				exception = "";
			}

			Map<String, Object> parameters = new HashMap<String, Object>();
			if (exception != null) {
				parameters.put("SEL_EXCEPTION", exception);
			}

			parameters.put("SEL_COMPANY_LOGO", imageCompany);
			parameters.put("SEL_CUSTOMER_LOGO", imageClient);
			parameters.put("SEL_PROJECT", project);
			parameters.put("SEL_TESTER", tester);
			parameters.put("SEL_BROWSER", CurrentState.getBrowser());
			parameters.put("SEL_LABEL_EVINDENCE_TITLE", properties.getProperty("label.evidenceReport"));
			parameters.put("SEL_LABEL_PROJECT", properties.getProperty("label.projetc"));
			parameters.put("SEL_LABEL_TESTER", properties.getProperty("label.tester"));
			parameters.put("SEL_LABEL_STATUS", properties.getProperty("label.status"));
			parameters.put("SEL_LABEL_PASS", properties.getProperty("label.status.pass"));
			parameters.put("SEL_LABEL_FAILED", properties.getProperty("label.status.failed"));
			parameters.put("SEL_LABEL_EVIDENCE_REPORT", properties.getProperty("label.evidenceReport"));
			parameters.put("SEL_LABEL_DATE", properties.getProperty("label.date"));
			parameters.put("SEL_LABEL_FOOTER", properties.getProperty("label.footer"));
			parameters.put("SEL_LABEL_ERROR_DETAIL", properties.getProperty("label.errorDetail"));
			parameters.put("SEL_LABEL_PAGE", properties.getProperty("label.page"));
			parameters.put("SEL_LABEL_COMPANY_NAME", properties.getProperty("label.company.name"));

			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);
			JasperReport jasperReport = JasperCompileManager.compileReport(properties.getProperty("evidence.file"));

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);

		} catch (SecurityException ex) {
			ex.printStackTrace();
		} catch (JRException jre) {
			jre.printStackTrace();
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		return jasperPrint;
	}

	/**
	 * Create a directory with the project's name
	 * 
	 * @param project
	 *            project name
	 */
	private static boolean createEvidenceDir(String directory) {
		boolean dirExists = false;

		try {
			File dir = new File(directory);
			boolean exists = dir.exists();

			if (!exists) {
				dir.mkdir();
				dirExists = false;
			} else {
				dirExists = true;
			}
		} catch (SecurityException se) {
			se.printStackTrace();
		}
		return dirExists;
	}

	@SuppressWarnings("deprecation")
	public static void exportReport(List<JasperPrint> printList, String reportName) {

		Properties properties = null;
		try {
			properties = SeleniumEvidenceUtils.loadProperties();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String evidenceDir = System.getProperty("user.dir") + System.getProperty("file.separator")
				+ properties.getProperty("evidence.dir") + System.getProperty("file.separator");

		createEvidenceDir(evidenceDir);
		FileOutputStream os = null;
		File archivo = null;
		
		EvidenceType reportType =( properties.getProperty("report.format").trim().equalsIgnoreCase("doc"))?
		    EvidenceType.DOC:EvidenceType.PDF;
		
		switch (reportType) {
		case PDF:
			try {
			archivo = new File(evidenceDir + reportName + ".pdf");
			os = new FileOutputStream(archivo);
			JRPdfExporter PdfExporter = new JRPdfExporter();
			PdfExporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);
			PdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		
				PdfExporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case DOC:
			JRDocxExporter exporter = new JRDocxExporter();

			try {
				archivo = new File(evidenceDir + reportName + ".doc");
				os = new FileOutputStream(archivo);
				exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT_LIST, printList);
				exporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, os);

				try {
					exporter.exportReport();
					os.close();
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		/*
		 * case HTML: JasperExportManager.exportReportToHtmlFile(jasperPrint,
		 * evidenceDir + reportName + ".html"); break;
		 */
		default:
			break;
		}
	}
}
