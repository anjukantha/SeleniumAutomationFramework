package com.asp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.asp.constants.FrameworkConstants;
import com.asp.exceptions.FrameworkException;
import com.asp.exceptions.InvalidPathForExcelException;

/**
 * Utility class to read or write to excel.
 * 
 * @author Anjan S P
 * @see com.asp.listeners.MethodInterceptor
 */
public final class ExcelUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExcelUtils() {
	}

	/**
	 * Encapsulates all the value from the mentioned excel sheet and store it in
	 * List holding HashMaps. Key for the map in the column header in the excel
	 * sheet.
	 * 
	 * @author Anjan S P
	 * @param sheetname Excel sheetname to read the value from
	 * @return List where each index holds a map and Each map holds the details
	 *         about the test
	 */
	public static List<Map<String, String>> getTestDetails(String sheetname) {
		List<Map<String, String>> list = null;

		try (FileInputStream fs = new FileInputStream(FrameworkConstants.getExcelpath())) {
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);

			int lastrownum = sheet.getLastRowNum();
			int lastcolnum = sheet.getRow(0).getLastCellNum();

			Map<String, String> map = null;
			list = new ArrayList<>();

			for (int i = 1; i <= lastrownum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastcolnum; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you trying to read is not found");
		} catch (IOException e) {
			throw new FrameworkException("Some io exception happened  while reading excel file");
		}
		return list;
	}

}
