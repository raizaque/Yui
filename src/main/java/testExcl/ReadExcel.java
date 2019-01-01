package testExcl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcel {
 
	public static void main(String[] args) throws IOException {
 //////////////// excel
		FileInputStream excelFile = new FileInputStream(new File("Classeur1.xlsx"));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		DataFormatter dataFormatter = new DataFormatter();
		///////////////file
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		////////////loop
		int i=0;
		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cellsInRow = currentRow.cellIterator();
			while (cellsInRow.hasNext()) {			
				Cell currentCell = cellsInRow.next();
                String cellValue = dataFormatter.formatCellValue(currentCell);
                writer.println(cellValue);
				/*System.out.println("row number ="+currentCell.getRowIndex()+"\n");
				if (currentCell.getCellTypeEnum() == CellType.STRING) {
					System.out.print(currentCell.getStringCellValue() + " | ");
				} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
					System.out.print(currentCell.getNumericCellValue()+ " | ");
				} else if (currentCell.getCellTypeEnum() == CellType.BLANK) {
					System.out.print(0+ " | ");
				}*/
			}
		}
		writer.close();
		workbook.close();
		excelFile.close();
	}
}