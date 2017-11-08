package com.eoh;
import java.io.IOException;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("create simple PDF file");
		String fileName = "pdfText.pdf";

		PDDocument doc = new PDDocument();// Creating instance of the PDFDocument
		PDPage page = new PDPage();
		doc.addPage(page);// adds a page to the pdf document
		PDPageContentStream content = new PDPageContentStream(doc, page);

		PDDocumentCatalog pdCatalog = doc.getDocumentCatalog();

		content.beginText();
		content.setFont(PDType1Font.HELVETICA, 27);
		content.newLineAtOffset(50.5F, 750.01F);
		content.showText("On-boarding Form");
		
		content.endText();
		content.beginText();
		content.newLine();
		content.setFont(PDType1Font.HELVETICA, 20);
		content.newLineAtOffset(40.0f, 650.0f);
		content.showText("Name:");
		
		PDAcroForm form = new PDAcroForm(doc);
		doc.getDocumentCatalog().setAcroForm(form);

		PDFont font = PDType1Font.HELVETICA;
		PDResources resources = new PDResources();
		resources.put(COSName.getPDFName("Helv"), font);
		form.setDefaultResources(resources);

		PDTextField textField = new PDTextField(form);
		textField.setPartialName("firstName");

		String defaultAppearance = "/Helv 12 Tf 0 0 1 rg";
		textField.setDefaultAppearance(defaultAppearance);
		form.getFields().add(textField);

		PDAnnotationWidget widget = textField.getWidgets().get(0);
		PDRectangle rect = new PDRectangle(140, 630, 200, 50);
		
		widget.setRectangle(rect);
		widget.setPage(page);

	
		widget.setPrinted(true);
		page.getAnnotations().add(widget);
		textField.setValue("Enter Name here");
	
		content.close();
		doc.save(fileName);
		doc.close();
		System.out.println("your file is created in :" + System.getProperty("/home/thakgatso/workspace/PDFBox"));

	}

}
