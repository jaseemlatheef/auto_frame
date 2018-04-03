package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetBase {
	protected XSSFSheet ExcelWSheet;
	protected XSSFWorkbook ExcelWBook;

	protected String Path;
	public String SheetName;

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public ExcelSheetBase(String path, String sheetName) throws Exception {
		Path = path;
		
		SheetName = sheetName;
		FileInputStream ExcelFile = new FileInputStream(path);
		//ZipSecureFile.setMinInflateRatio(0d);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
	}

	// This method is to read the test data from the Excel cell
	// In this we are passing parameters/arguments as Row Num and Col Num
	public String getCellData(int rowNum, int colNum) throws Exception {
		XSSFRow row = ExcelWSheet.getRow(rowNum);
		if (row == null) {
			return null;
		}
		XSSFCell cell = row.getCell(colNum);
		if (cell == null) {
			return null;
		}
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	// Update to excel file object
	public void setCellData(int rowNum, int columnNum, String value) {
		XSSFRow row = ExcelWSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(columnNum);
		if (cell == null) {
			cell = row.createCell(columnNum);
		}
		cell.setCellValue(value);
	}

	// Save excel
	public void saveData() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(Path);
		ExcelWBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		ExcelWBook = new XSSFWorkbook(new FileInputStream(Path));
	}
}
