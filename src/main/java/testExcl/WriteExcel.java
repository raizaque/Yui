package testExcl;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class WriteExcel {
	
	private static String[] COLUMNs = {"Id", "Name", "Address", "Age"};
    private static List<Customer> customers = Arrays.asList(
			new Customer("1", "Jack Smith", "Massachusetts", 23),
			new Customer("2", "Adam Johnson", "New York", 27),
			new Customer("3", "Katherin Carter", "Washington DC", 26),
			new Customer("4", "Jack London", "Nevada", 33), 
			new Customer("5", "Jason Bourne", "California", 36));
    
	public static void main(String[] args) throws IOException {
		
		Workbook workbook = new XSSFWorkbook();
 
		CreationHelper createHelper = workbook.getCreationHelper();
 
		Sheet sheet = workbook.createSheet("Customers");
 
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BLUE.getIndex());
 
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
 
		// Row for Header
		Row headerRow = sheet.createRow(0);
 
		// Header
		for (int col = 0; col < COLUMNs.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(COLUMNs[col]);
			cell.setCellStyle(headerCellStyle);
		}
 
		// CellStyle for Age
		CellStyle ageCellStyle = workbook.createCellStyle();
		ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
 
		int rowIdx = 1;
		for (Customer customer : customers) {
			Row row = sheet.createRow(rowIdx++);
 
			row.createCell(0).setCellValue(customer.getId());
			row.createCell(1).setCellValue(customer.getName());
			row.createCell(2).setCellValue(customer.getAddress());
 
			Cell ageCell = row.createCell(3);
			ageCell.setCellValue(customer.getAge());
			ageCell.setCellStyle(ageCellStyle);
		}
 
		FileOutputStream fileOut = new FileOutputStream("customers.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}
}