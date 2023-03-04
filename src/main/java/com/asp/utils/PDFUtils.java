/**
 * 
 */
package com.asp.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

/**
 * @author Anjan S P
 *
 */
public class PDFUtils {
	/**
	 * Private constructor to avoid the external instantiation
	 */
	private PDFUtils() {
	}

	/**
	 * Used to compare the 2 pdf file by extracting the text from pdf files.
	 * 
	 * Difference will showed like this, old content will be enclosed within
	 * "<span></span>" with class='editOldLine' and new content will be enclosed
	 * within "<span></span>" with class='editNewLine'
	 * 
	 * @param sourcePdfFilePath
	 * @param targetPdfFilePath
	 * @return
	 * @throws IOException
	 */
	public static String compare(String sourcePdfFilePath, String targetPdfFilePath) throws IOException {
		StringBuilder builder = new StringBuilder();
		DiffRowGenerator gen = DiffRowGenerator.create().mergeOriginalRevised(true).showInlineDiffs(true)
				.inlineDiffByWord(true).build();
		String srcText = getPdfText(sourcePdfFilePath);
		String targetText = getPdfText(targetPdfFilePath);

		List<DiffRow> rows = gen.generateDiffRows(Arrays.asList(srcText), Arrays.asList(targetText));
		for (DiffRow row : rows) {
			builder = builder.append(row.getOldLine() + "\r\n");
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * Used to extract text from PDF file
	 * 
	 * @param pdfFilePath
	 * @return
	 * @throws IOException
	 */
	public static String getPdfText(String pdfFilePath) throws IOException {
		StringBuilder builder = new StringBuilder();
		PdfReader reader = new PdfReader(pdfFilePath);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy;
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			builder = builder.append(strategy.getResultantText() + "\r\n\r\n\r\n");
		}
		reader.close();
		return builder.toString();
	}
}
